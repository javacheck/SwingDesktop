package internalFrame;

import com.lyz.dao.Dao;
import internalFrame.guanli.Item;
import model.TbKhinfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
public class KeHuChaXun extends JInternalFrame {
	private JTable table;
	private JTextField conditionContent;
	private JComboBox conditionBox2;
	private JComboBox conditionBox1;
	private JButton showAllButton;
	public KeHuChaXun() {
		super();
		setIconifiable(true);
		setClosable(true);
		setTitle("�ͻ���Ϣ��ѯ");
		getContentPane().setLayout(new GridBagLayout());
		setBounds(100, 100, 640, 375);

		table = new JTable();
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		String[] tableHeads = new String[]{"�ͻ�ID", "�ͻ�ȫ��", "�ͻ���ַ", "�ͻ����",
				"��������", "�硡����", "��������", "��  ϵ  ��", "��ϵ�绰", "E-Mail", "��������",
				"�����˺�"};
		final DefaultTableModel dftm = (DefaultTableModel) table.getModel();
		dftm.setColumnIdentifiers(tableHeads);
		
		final JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setAutoscrolls(true);
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.weighty = 1.0;
		gridBagConstraints_6.insets = new Insets(0, 10, 5, 10);
		gridBagConstraints_6.fill = GridBagConstraints.BOTH;
		gridBagConstraints_6.gridwidth = 6;
		gridBagConstraints_6.gridy = 1;
		gridBagConstraints_6.gridx = 0;
		getContentPane().add(scrollPane, gridBagConstraints_6);

		setupComponet(new JLabel(" ѡ���ѯ������"), 0, 0, 1, 1, false);
		conditionBox1 = new JComboBox();
		conditionBox1.setModel(new DefaultComboBoxModel(new String[]{"�ͻ�ȫ��",
				"�ͻ����"}));
		setupComponet(conditionBox1, 1, 0, 1, 30, true);

		conditionBox2 = new JComboBox();
		conditionBox2.setModel(new DefaultComboBoxModel(
				new String[]{"����", "����"}));
		setupComponet(conditionBox2, 2, 0, 1, 30, true);

		conditionContent = new JTextField();
		setupComponet(conditionContent, 3, 0, 1, 140, true);

		final JButton queryButton = new JButton();
		queryButton.addActionListener(new queryAction(dftm));
		queryButton.setText("��ѯ");
		setupComponet(queryButton, 4, 0, 1, 1, false);

		showAllButton = new JButton();
		showAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				conditionContent.setText("");
				List list = Dao.getKhInfos();
				updateTable(list, dftm);
			}
		});
		setupComponet(showAllButton, 5, 0, 1, 1, false);
		showAllButton.setText("��ʾȫ������");
	}

	private void updateTable(List list, final DefaultTableModel dftm) {
		int num = dftm.getRowCount();
		for (int i = 0; i < num; i++)
			dftm.removeRow(0);
		Iterator iterator = list.iterator();
		TbKhinfo khInfo;
		while (iterator.hasNext()) {
			List info = (List) iterator.next();
			Item item = new Item();
			item.setId((String) info.get(0));
			item.setName((String) info.get(1));
			khInfo = Dao.getKhInfo(item);
			Vector rowData = new Vector();
			rowData.add(khInfo.getId().trim());
			rowData.add(khInfo.getKhname().trim());
			rowData.add(khInfo.getAddress().trim());
			rowData.add(khInfo.getJian().trim());
			rowData.add(khInfo.getBianma().trim());
			rowData.add(khInfo.getTel().trim());
			rowData.add(khInfo.getFax().trim());
			rowData.add(khInfo.getLian().trim());
			rowData.add(khInfo.getLtel().trim());
			rowData.add(khInfo.getMail().trim());
			rowData.add(khInfo.getXinhang().trim());
			rowData.add(khInfo.getHao().trim());
			dftm.addRow(rowData);
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

	private final class queryAction implements ActionListener {
		private final DefaultTableModel dftm;
		private queryAction(DefaultTableModel dftm) {
			this.dftm = dftm;
		}
		public void actionPerformed(final ActionEvent e) {
			String condition, conditionOperation, conditionString;
			List list = null;
			condition = conditionBox1.getSelectedItem().toString().trim();
			conditionOperation = conditionBox2.getSelectedItem().toString()
					.trim();
			conditionString = conditionContent.getText().trim();
			if (!conditionString.isEmpty()) {
				String sql = "select * from tb_khinfo where ";
				if (condition.equals("�ͻ�ȫ��")) {
					if (conditionOperation.equals("����"))
						list = Dao.findForList(sql + "khname='"
								+ conditionString + "'");
					else
						list = Dao.findForList(sql + "khname like '%"
								+ conditionString + "%'");
				} else {
					if (conditionOperation.equals("����"))
						list = Dao.findForList(sql + "jian='" + conditionString
								+ "'");
					else
						list = Dao.findForList(sql + "jian like '%"
								+ conditionString + "%'");
				}
				updateTable(list, dftm);
			} else
				showAllButton.doClick();
		}
	}
}
