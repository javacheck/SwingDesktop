package internalFrame.keHuGuanLi;
import com.lyz.dao.Dao;
import keyListener.InputKeyListener;
import model.TbKhinfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
public class KeHuTianJiaPanel extends JPanel {
	private JTextField keHuQuanCheng;
	private JTextField yinHangZhangHao;
	private JTextField kaiHuYinHang;
	private JTextField EMail;
	private JTextField lianXiDianHua;
	private JTextField lianXiRen;
	private JTextField chuanZhen;
	private JTextField dianHua;
	private JTextField youZhengBianMa;
	private JTextField diZhi;
	private JTextField keHuJianCheng;
	private JButton resetButton;
	public KeHuTianJiaPanel() {
		super();
		setBounds(10, 10, 460, 300);
		setLayout(new GridBagLayout());
		setVisible(true);
		final JLabel khName = new JLabel();
		khName.setText("�ͻ�ȫ�ƣ�");
		setupComponet(khName, 0, 0, 1, 0, false);
		keHuQuanCheng = new JTextField();
		// ��λȫ���ı���
		setupComponet(keHuQuanCheng, 1, 0, 3, 350, true);
		final JLabel addressLabel = new JLabel("�ͻ���ַ��");
		setupComponet(addressLabel, 0, 1, 1, 0, false);
		diZhi = new JTextField();
		// ��λ��ַ�ı���
		setupComponet(diZhi, 1, 1, 3, 0, true);
		final JLabel jc = new JLabel();
		jc.setText("�ͻ���ƣ�");
		setupComponet(jc, 0, 2, 1, 0, false);
		keHuJianCheng = new JTextField();
		// ��λ�ͻ�����ı���
		setupComponet(keHuJianCheng, 1, 2, 1, 100, true);
		setupComponet(new JLabel("�������룺"), 2, 2, 1, 0, false);
		youZhengBianMa = new JTextField();
		// ��λ���������ı���
		setupComponet(youZhengBianMa, 3, 2, 1, 100, true);
		youZhengBianMa.addKeyListener(new InputKeyListener());
		setupComponet(new JLabel("�绰��"), 0, 3, 1, 0, false);
		dianHua = new JTextField();
		// ��λ�绰�ı���
		setupComponet(dianHua, 1, 3, 1, 100, true);
		dianHua.addKeyListener(new InputKeyListener());
		setupComponet(new JLabel("���棺"), 2, 3, 1, 0, false);
		chuanZhen = new JTextField();
		// ��λ�����ı���
		chuanZhen.addKeyListener(new InputKeyListener());
		setupComponet(chuanZhen, 3, 3, 1, 100, true);
		setupComponet(new JLabel("��ϵ�ˣ�"), 0, 4, 1, 0, false);
		lianXiRen = new JTextField();
		// ��λ��ϵ���ı���
		setupComponet(lianXiRen, 1, 4, 1, 100, true);
		setupComponet(new JLabel("��ϵ�绰��"), 2, 4, 1, 0, false);
		lianXiDianHua = new JTextField();
		// ��λ��ϵ�绰�ı���
		setupComponet(lianXiDianHua, 3, 4, 1, 100, true);
		lianXiDianHua.addKeyListener(new InputKeyListener());
		setupComponet(new JLabel("E-Mail��"), 0, 5, 1, 0, false);
		EMail = new JTextField();
		// ��λE-Mail�ı���
		setupComponet(EMail, 1, 5, 3, 350, true);
		setupComponet(new JLabel("�������У�"), 0, 6, 1, 0, false);
		kaiHuYinHang = new JTextField();
		// ��λ���������ı���
		setupComponet(kaiHuYinHang, 1, 6, 1, 100, true);
		setupComponet(new JLabel("�����˺ţ�"), 2, 6, 1, 0, false);
		yinHangZhangHao = new JTextField();
		// ��λ�����˺��ı���
		setupComponet(yinHangZhangHao, 3, 6, 1, 100, true);
		final JButton saveButton = new JButton("����");
		// ��λ���水ť
		setupComponet(saveButton, 1, 7, 1, 0, false);
		saveButton.addActionListener(new SaveButtonActionListener());
		resetButton = new JButton("����");
		// ��λ���ð�ť
		setupComponet(resetButton, 3, 7, 1, 0, false);
		resetButton.addActionListener(new ChongZheButtonActionListener());
	}
	// �������λ�ò���ӵ�������
	private void setupComponet(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		add(component, gridBagConstrains);
	}
	// ���水ť���¼�������
	private final class SaveButtonActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			if (diZhi.getText().equals("")
					|| youZhengBianMa.getText().equals("")
					|| chuanZhen.getText().equals("")
					|| yinHangZhangHao.getText().equals("")
					|| keHuJianCheng.getText().equals("")
					|| keHuQuanCheng.getText().equals("")
					|| lianXiRen.getText().equals("")
					|| lianXiDianHua.getText().equals("")
					|| EMail.getText().equals("")
					|| dianHua.getText().equals("")
					|| kaiHuYinHang.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "����дȫ����Ϣ");
				return;
			}
			ResultSet haveUser = Dao
					.query("select * from tb_khinfo where khname='"
							+ keHuQuanCheng.getText().trim() + "'");
			try {
				if (haveUser.next()){
					System.out.println("error");
					JOptionPane.showMessageDialog(KeHuTianJiaPanel.this,
							"�ͻ���Ϣ���ʧ�ܣ�����ͬ���ͻ�", "�ͻ������Ϣ",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			} catch (Exception er) {
				er.printStackTrace();
			}
			ResultSet set = Dao.query("select max(id) from tb_khinfo");
			String id = null;
			try {
				if (set != null && set.next()) {
					String sid = set.getString(1);
					if (sid == null)
						id = "kh1001";
					else {
						String str = sid.substring(2);
						id = "kh" + (Integer.parseInt(str) + 1);
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			TbKhinfo khinfo = new TbKhinfo();
			khinfo.setId(id);
			khinfo.setAddress(diZhi.getText().trim());
			khinfo.setBianma(youZhengBianMa.getText().trim());
			khinfo.setFax(chuanZhen.getText().trim());
			khinfo.setHao(yinHangZhangHao.getText().trim());
			khinfo.setJian(keHuJianCheng.getText().trim());
			khinfo.setKhname(keHuQuanCheng.getText().trim());
			khinfo.setLian(lianXiRen.getText().trim());
			khinfo.setLtel(lianXiDianHua.getText().trim());
			khinfo.setMail(EMail.getText().trim());
			khinfo.setTel(dianHua.getText().trim());
			khinfo.setXinhang(kaiHuYinHang.getText());
			Dao.addKeHu(khinfo);
			JOptionPane.showMessageDialog(KeHuTianJiaPanel.this, "�ѳɹ���ӿͻ�",
					"�ͻ������Ϣ", JOptionPane.INFORMATION_MESSAGE);
			resetButton.doClick();
		}
	}
	// ���ð�ť���¼�������
	private class ChongZheButtonActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			keHuQuanCheng.setText("");
			yinHangZhangHao.setText("");
			kaiHuYinHang.setText("");
			EMail.setText("");
			lianXiDianHua.setText("");
			lianXiRen.setText("");
			chuanZhen.setText("");
			dianHua.setText("");
			youZhengBianMa.setText("");
			diZhi.setText("");
			keHuJianCheng.setText("");
		}
	}
}