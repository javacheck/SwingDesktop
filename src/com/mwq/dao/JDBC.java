package com.mwq.dao;

import javax.swing.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {

	private static final String DRIVERCLASS = "com.mysql.jdbc.Driver"; // ���ݿ�����

	private static final String URL = "jdbc:mysql://localhost:3306/db_ExpressLetter?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf8";// ���ݿ�URL

	private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>(); // ���������������ݿ����ӵ��߳�
	private static String userName = "root";
	private static String password = "mysql";
	private static Connection conn = null;// ���ݿ�����

	static { // ͨ����̬�����������ݿ����������������ݿⲻ���ڵ�����´������ݿ�
		try {
			Class.forName(DRIVERCLASS).newInstance(); // �������ݿ�����
			File dbExpressLetterFile = new File("db_ExpressLetter");// �������ݿ��ļ�����
			if (!dbExpressLetterFile.exists()) {// �ж����ݿ��ļ��Ƿ����
				String[] sqls = new String[6];// ���崴�����ݿ��SQL���
				sqls[0] = "create table tb_info (num int not null,type_id int not null,content varchar(500) not null,primary key (num))";
				sqls[1] = "create table tb_personnel (num int not null,type_id int not null,name varchar(8) not null,sex char(2) not null,birthday date not null,company varchar(50) not null,dept varchar(40) not null,duty varchar(30) not null,handset varchar(15) not null,email varchar(30) not null,primary key (num))";
				sqls[2] = "create table tb_type (id int not null,name varchar(20) not null,used char(4) not null,primary key (id))";
				sqls[3] = "create table tb_user (id int not null,name varchar(8) not null,sex char(2) not null,birthday date not null,id_card varchar(20) not null,password varchar(20) not null,freeze char(4) not null,primary key (id))";
				sqls[4] = "create view v_info_type  as SELECT tb_info.num, tb_type.name AS type_name, tb_info.content FROM tb_info INNER JOIN tb_type ON tb_info.type_id = tb_type.id ";
				sqls[5] = "create view v_personnel_type as SELECT tb_personnel.num, tb_type.name AS type_name, tb_personnel.name, tb_personnel.sex, tb_personnel.birthday, tb_personnel.company, tb_personnel.dept, tb_personnel.duty, tb_personnel.handset, tb_personnel.email FROM tb_personnel INNER JOIN tb_type ON tb_personnel.type_id = tb_type.id";
				conn = DriverManager.getConnection(URL,userName,password);// �������ݿ�����
				threadLocal.set(conn);// �������ݿ�����
				Statement stmt = conn.createStatement();// �������ݿ�����״̬����
				for (int i = 0; i < sqls.length; i++) {// ͨ��ִ��SQL��䴴�����ݿ�
					stmt.execute(sqls[i]);// ִ��SQL���
				}
				stmt.close();// �ر����ݿ�����״̬����
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() { // �������ݿ����ӵķ���
		conn = (Connection) threadLocal.get(); // ���߳��л�����ݿ�����
		if (conn == null) { // û�п��õ����ݿ�����
			try {
				conn = DriverManager.getConnection(URL,userName,password);// �����µ����ݿ�����
				threadLocal.set(conn); // �����ݿ����ӱ��浽�߳���
			} catch (Exception e) {
				String[] infos = { "δ�ܳɹ��������ݿ⣡", "��ȷ�ϱ�����Ƿ��Ѿ����У�" };
				JOptionPane.showMessageDialog(null, infos);// �����������ݿ�ʧ�ܵ���ʾ
				System.exit(0);// �ر�ϵͳ
				e.printStackTrace();
			}
		}
		return conn;
	}

	public static boolean closeConnection() { // �ر����ݿ����ӵķ���
		boolean isClosed = true; // Ĭ�Ϲرճɹ�
		conn = (Connection) threadLocal.get(); // ���߳��л�����ݿ�����
		threadLocal.set(null); // ����߳��е����ݿ�����
		if (conn != null) { // ���ݿ����ӿ���
			try {
				conn.close(); // �ر����ݿ�����
			} catch (SQLException e) {
				isClosed = false; // �ر�ʧ��
				e.printStackTrace();
			}
		}
		return isClosed;
	}

}
