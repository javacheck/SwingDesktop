package internalFrame;
import com.lyz.dao.Dao;
import com.lyz.login.Login;
import internalFrame.guanli.Item;
import model.*;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
public class JinHuoDan extends JInternalFrame {
	private final JTable table;
	private TbUserlist user = Login.getUser(); 			// ��¼�û���Ϣ
	private final JTextField jhsj = new JTextField(); 	// ����ʱ��
	private final JTextField jsr = new JTextField(); 	// ������
	private final JComboBox jsfs = new JComboBox(); 	// ���㷽ʽ
	private final JTextField lian = new JTextField(); 	// ��ϵ��
	private final JComboBox gys = new JComboBox(); 		// ��Ӧ��
	private final JTextField piaoHao = new JTextField();// Ʊ��
	private final JTextField pzs = new JTextField("0"); // Ʒ������
	private final JTextField hpzs = new JTextField("0");// ��Ʒ����
	private final JTextField hjje = new JTextField("0");// �ϼƽ��
	private final JTextField ysjl = new JTextField(); 	// ���ս���
	private final JTextField czy = new JTextField(user.getName());// ����Ա
	private Date jhsjDate;
	private JComboBox sp;
	public JinHuoDan() {
		super();
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		getContentPane().setLayout(new GridBagLayout());
		setTitle("������");
		setBounds(50, 50, 700, 400);

		setupComponet(new JLabel("����Ʊ�ţ�"), 0, 0, 1, 0, false);
		piaoHao.setFocusable(false);
		setupComponet(piaoHao, 1, 0, 1, 140, true);

		setupComponet(new JLabel("��Ӧ�̣�"), 2, 0, 1, 0, false);
		gys.setPreferredSize(new Dimension(160, 21));
		// ��Ӧ������ѡ����ѡ���¼�
		gys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doGysSelectAction();
			}
		});
		setupComponet(gys, 3, 0, 1, 1, true);

		setupComponet(new JLabel("��ϵ�ˣ�"), 4, 0, 1, 0, false);
		lian.setFocusable(false);
		setupComponet(lian, 5, 0, 1, 80, true);

		setupComponet(new JLabel("���㷽ʽ��"), 0, 1, 1, 0, false);
		jsfs.addItem("�ֽ�");
		jsfs.addItem("֧Ʊ");
		jsfs.setEditable(true);
		setupComponet(jsfs, 1, 1, 1, 1, true);

		setupComponet(new JLabel("����ʱ�䣺"), 2, 1, 1, 0, false);
		jhsj.setFocusable(false);
		setupComponet(jhsj, 3, 1, 1, 1, true);

		setupComponet(new JLabel("�����ˣ�"), 4, 1, 1, 0, false);
		setupComponet(jsr, 5, 1, 1, 1, true);

		sp = new JComboBox();
		sp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TbSpinfo info = (TbSpinfo) sp.getSelectedItem();
				// ���ѡ����Ч�͸��±��
				if (info != null && info.getId() != null) {
					updateTable();
				}
			}
		});

		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		initTable();
		// ����¼����Ʒ����������Ʒ�������ϼƽ��ļ���
		table.addContainerListener(new computeInfo());
		JScrollPane scrollPanel = new JScrollPane(table);
		scrollPanel.setPreferredSize(new Dimension(380, 200));
		setupComponet(scrollPanel, 0, 2, 6, 1, true);

		setupComponet(new JLabel("Ʒ��������"), 0, 3, 1, 0, false);
		pzs.setFocusable(false);
		setupComponet(pzs, 1, 3, 1, 1, true);

		setupComponet(new JLabel("��Ʒ������"), 2, 3, 1, 0, false);
		hpzs.setFocusable(false);
		setupComponet(hpzs, 3, 3, 1, 1, true);

		setupComponet(new JLabel("�ϼƽ�"), 4, 3, 1, 0, false);
		hjje.setFocusable(false);
		setupComponet(hjje, 5, 3, 1, 1, true);

		setupComponet(new JLabel("���ս��ۣ�"), 0, 4, 1, 0, false);
		setupComponet(ysjl, 1, 4, 1, 1, true);

		setupComponet(new JLabel("������Ա��"), 2, 4, 1, 0, false);
		czy.setFocusable(false);
		setupComponet(czy, 3, 4, 1, 1, true);

		// ������Ӱ�ť�ڱ��������µ�һ��
		JButton tjButton = new JButton("���");
		tjButton.addActionListener(new TjActionListener());
		setupComponet(tjButton, 4, 4, 1, 1, false);

		// ������ⰴť���������Ϣ
		JButton rkButton = new JButton("���");
		rkButton.addActionListener(new RkActionListener());
		setupComponet(rkButton, 5, 4, 1, 1, false);
		// ��Ӵ������������ɳ�ʼ��
		addInternalFrameListener(new initTasks());
	}
	// ��ʼ�����
	private void initTable() {
		String[] columnNames = {"��Ʒ����", "��Ʒ���", "����", "��λ", "���", "��װ", "����",
				"����", "����", "��׼�ĺ�"};
		((DefaultTableModel) table.getModel())
				.setColumnIdentifiers(columnNames);
		TableColumn column = table.getColumnModel().getColumn(0);
		final DefaultCellEditor editor = new DefaultCellEditor(sp);
		editor.setClickCountToStart(2);
		column.setCellEditor(editor);
	}
	// ��ʼ����Ʒ����ѡ���
	private void initSpBox() {
		List list = new ArrayList();
		ResultSet set = Dao.query("select * from tb_spinfo where gysName='"
				+ gys.getSelectedItem() + "'");
		sp.removeAllItems();
		sp.addItem(new TbSpinfo());
		for (int i = 0; table != null && i < table.getRowCount(); i++) {
			TbSpinfo tmpInfo = (TbSpinfo) table.getValueAt(i, 0);
			if (tmpInfo != null && tmpInfo.getId() != null)
				list.add(tmpInfo.getId());
		}
		try {
			while (set.next()) {
				TbSpinfo spinfo = new TbSpinfo();
				spinfo.setId(set.getString("id").trim());
				// ���������Դ���ͬ����Ʒ����Ʒ�������оͲ��ٰ�������Ʒ
				if (list.contains(spinfo.getId()))
					continue;
				spinfo.setSpname(set.getString("spname").trim());
				spinfo.setCd(set.getString("cd").trim());
				spinfo.setJc(set.getString("jc").trim());
				spinfo.setDw(set.getString("dw").trim());
				spinfo.setGg(set.getString("gg").trim());
				spinfo.setBz(set.getString("bz").trim());
				spinfo.setPh(set.getString("ph").trim());
				spinfo.setPzwh(set.getString("pzwh").trim());
				spinfo.setMemo(set.getString("memo").trim());
				spinfo.setGysname(set.getString("gysname").trim());
				sp.addItem(spinfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// �������λ�ò���ӵ�������
	private void setupComponet(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(component, gridBagConstrains);
	}
	// ��Ӧ��ѡ��ʱ������ϵ���ֶ�
	private void doGysSelectAction() {
		Item item = (Item) gys.getSelectedItem();
		TbGysinfo gysInfo = Dao.getGysInfo(item);
		lian.setText(gysInfo.getLian());
		initSpBox();
	}
	class RkActionListener implements ActionListener {	// ��ⰴť���¼�������
		public void actionPerformed(ActionEvent e) {
			// ���������û�б�д�ĵ�Ԫ
			stopTableCellEditing();
			// �������
			clearEmptyRow();
			String hpzsStr = hpzs.getText(); // ��Ʒ����
			String pzsStr = pzs.getText(); // Ʒ����
			String jeStr = hjje.getText(); // �ϼƽ��
			String jsfsStr = jsfs.getSelectedItem().toString(); // ���㷽ʽ
			String jsrStr = jsr.getText().trim(); // ������
			String czyStr = czy.getText(); // ����Ա
			String rkDate = jhsjDate.toLocaleString(); // ���ʱ��
			String ysjlStr = ysjl.getText().trim(); // ���ս���
			String id = piaoHao.getText(); // Ʊ��
			String gysName = gys.getSelectedItem().toString();// ��Ӧ������
			if (jsrStr == null || jsrStr.isEmpty()) {
				JOptionPane.showMessageDialog(JinHuoDan.this, "����д������");
				return;
			}
			if (ysjlStr == null || ysjlStr.isEmpty()) {
				JOptionPane.showMessageDialog(JinHuoDan.this, "��д���ս���");
				return;
			}
			if (table.getRowCount() <= 0) {
				JOptionPane.showMessageDialog(JinHuoDan.this, "��������Ʒ");
				return;
			}
			TbRukuMain ruMain = new TbRukuMain(id, pzsStr, jeStr, ysjlStr,
					gysName, rkDate, czyStr, jsrStr, jsfsStr);
			Set<TbRukuDetail> set = ruMain.getTabRukuDetails();
			int rows = table.getRowCount();
			for (int i = 0; i < rows; i++) {
				TbSpinfo spinfo = (TbSpinfo) table.getValueAt(i, 0);
				String djStr = (String) table.getValueAt(i, 6);
				String slStr = (String) table.getValueAt(i, 7);
				Double dj = Double.valueOf(djStr);
				Integer sl = Integer.valueOf(slStr);
				TbRukuDetail detail = new TbRukuDetail();
				detail.setTabSpinfo(spinfo.getId());
				detail.setTabRukuMain(ruMain.getRkId());
				detail.setDj(dj);
				detail.setSl(sl);
				set.add(detail);
			}
			boolean rs = Dao.insertRukuInfo(ruMain);
			if (rs) {
				JOptionPane.showMessageDialog(JinHuoDan.this, "������");
				DefaultTableModel dftm = new DefaultTableModel();
				table.setModel(dftm);
				initTable();
				pzs.setText("0");
				hpzs.setText("0");
				hjje.setText("0");
			}
		}
	}
	class TjActionListener implements ActionListener {	// ��Ӱ�ť���¼�������
		public void actionPerformed(ActionEvent e) {
			// ��ʼ��Ʊ��
			initPiaoHao();
			// ���������û�б�д�ĵ�Ԫ
			stopTableCellEditing();
			// �������л��������У������������
			for (int i = 0; i < table.getRowCount(); i++) {
				TbSpinfo info = (TbSpinfo) table.getValueAt(i, 0);
				if (table.getValueAt(i, 0) == null)
					return;
			}
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.addRow(new Vector());
			initSpBox();
		}
	}
	// ���¼��м���Ʒ����������Ʒ�������ϼƽ��
	private final class computeInfo implements ContainerListener {
		public void componentRemoved(ContainerEvent e) {
			// �������
			clearEmptyRow();
			// �������
			int rows = table.getRowCount();
			int count = 0;
			double money = 0.0;
			// ����Ʒ������
			TbSpinfo column = null;
			if (rows > 0)
				column = (TbSpinfo) table.getValueAt(rows - 1, 0);
			if (rows > 0 && (column == null || column.getId().isEmpty()))
				rows--;
			// �����Ʒ�����ͽ��
			for (int i = 0; i < rows; i++) {
				String column7 = (String) table.getValueAt(i, 7);
				String column6 = (String) table.getValueAt(i, 6);
				int c7 = (column7 == null || column7.isEmpty()) ? 0 : Integer
						.parseInt(column7);
				float c6 = (column6 == null || column6.isEmpty()) ? 0 : Float
						.parseFloat(column6);
				count += c7;
				money += c6 * c7;
			}

			pzs.setText(rows + "");
			hpzs.setText(count + "");
			hjje.setText(money + "");
			// /////////////////////////////////////////////////////////////////
		}
		public void componentAdded(ContainerEvent e) {
		}
	}
	// ����ĳ�ʼ������
	private final class initTasks extends InternalFrameAdapter {
		public void internalFrameActivated(InternalFrameEvent e) {
			super.internalFrameActivated(e);
			initTimeField();
			initGysField();
			initPiaoHao();
			initSpBox();
		}
		private void initGysField() {// ��ʼ����Ӧ���ֶ�
			List gysInfos = Dao.getGysInfos();
			for (Iterator iter = gysInfos.iterator(); iter.hasNext();) {
				List list = (List) iter.next();
				Item item = new Item();
				item.setId(list.get(0).toString().trim());
				item.setName(list.get(1).toString().trim());
				gys.addItem(item);
			}
			doGysSelectAction();
		}
		private void initTimeField() {// ��������ʱ���߳�
			new Thread(new Runnable() {
				public void run() {
					try {
						while (true) {
							jhsjDate = new Date();
							jhsj.setText(jhsjDate.toLocaleString());
							Thread.sleep(100);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
	// ��ʼ��Ʊ���ı���ķ���
	private void initPiaoHao() {
		java.sql.Date date = new java.sql.Date(jhsjDate.getTime());
		String maxId = Dao.getRuKuMainMaxId(date);
		piaoHao.setText(maxId);
	}
	// ������Ʒ�������ѡ�񣬸��±��ǰ�е�����
	private synchronized void updateTable() {
		TbSpinfo spinfo = (TbSpinfo) sp.getSelectedItem();
		int row = table.getSelectedRow();
		if (row >= 0 && spinfo != null) {
			table.setValueAt(spinfo.getId(), row, 1);
			table.setValueAt(spinfo.getCd(), row, 2);
			table.setValueAt(spinfo.getDw(), row, 3);
			table.setValueAt(spinfo.getGg(), row, 4);
			table.setValueAt(spinfo.getBz(), row, 5);
			table.setValueAt("0", row, 6);
			table.setValueAt("0", row, 7);
			table.setValueAt(spinfo.getPh(), row, 8);
			table.setValueAt(spinfo.getPzwh(), row, 9);
			table.editCellAt(row, 6);
		}
	}
	// �������
	private synchronized void clearEmptyRow() {
		DefaultTableModel dftm = (DefaultTableModel) table.getModel();
		for (int i = 0; i < table.getRowCount(); i++) {
			TbSpinfo info2 = (TbSpinfo) table.getValueAt(i, 0);
			if (info2 == null || info2.getId() == null
					|| info2.getId().isEmpty()) {
				dftm.removeRow(i);
			}
		}
	}
	// ֹͣ���Ԫ�ı༭
	private void stopTableCellEditing() {
		TableCellEditor cellEditor = table.getCellEditor();
		if (cellEditor != null)
			cellEditor.stopCellEditing();
	}
}
