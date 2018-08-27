package internalFrame;

import com.lyz.dao.Dao;
import internalFrame.guanli.Item;
import model.TbUserlist;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class QuanManager extends JInternalFrame {
	private JTextField userName;
	private JComboBox quanXian;
	private JTextField name;
	private JTextField pass;
	private JButton modifyButton;
	private JButton closeButton;
	private JComboBox userCombo;
	public QuanManager() {
		setClosable(true);
		setIconifiable(true);
		setBounds(10, 10, 420, 170);
		setTitle("Ȩ�޹���");
		setLayout(new GridBagLayout());
		setVisible(true);

		final JLabel khName = new JLabel();
		khName.setText("�û�������");
		setupComponet(khName, 0, 0, 1, 0, false);
		userName = new JTextField();
		userName.setEditable(false);
		setupComponet(userName, 1, 0, 1, 100, true);

		final JLabel addressLabel = new JLabel("��¼����");
		setupComponet(addressLabel, 2, 0, 1, 0, false);
		name = new JTextField();
		name.setEditable(false);
		setupComponet(name, 3, 0, 1, 100, true);

		setupComponet(new JLabel("���룺"), 0, 1, 1, 0, false);
		pass = new JTextField();
		pass.setEditable(false);
		setupComponet(pass, 1, 1, 1, 100, true);

		setupComponet(new JLabel("Ȩ�ޣ�"), 2, 1, 1, 0, false);
		quanXian = new JComboBox(new String[]{"����Ա", "����Ա"});
		setupComponet(quanXian, 3, 1, 1, 100, true);

		setupComponet(new JLabel("ѡ���û�"), 0, 2, 1, 0, false);
		userCombo = new JComboBox();
		userCombo.setPreferredSize(new Dimension(80, 21));
		// �����û���Ϣ������ѡ����ѡ���¼�
		userCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doUserSelectAction();
			}
		});
		// ��λ�û���Ϣ������ѡ���
		setupComponet(userCombo, 1, 2, 2, 0, true);
		modifyButton = new JButton("�޸�");
		closeButton = new JButton("�ر�");
		JPanel panel = new JPanel();
		panel.add(modifyButton);
		panel.add(closeButton);
		setupComponet(panel, 3, 2, 1, 0, false);
		// ����ɾ����ť�ĵ����¼�
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuanManager.this.doDefaultCloseAction();
			}
		});
		// �����޸İ�ť�ĵ����¼�
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = (Item) userCombo.getSelectedItem();
				TbUserlist user = Dao.getUser(item);
				int index = quanXian.getSelectedIndex();
				if (index == 0)
					user.setQuan("a");
				else
					user.setQuan("c");
				if (Dao.updateUser(user) >= 1)
					JOptionPane.showMessageDialog(QuanManager.this, "�޸����");
				else
					JOptionPane.showMessageDialog(QuanManager.this, "�޸�ʧ��");
			}
		});
		// ���弤���¼�
		addInternalFrameListener(new InternalFrameAdapter() {
			public void internalFrameActivated(InternalFrameEvent e) {
				initComboBox();// ��ʼ���û�����ѡ���
			}
		});
	}
	// ��ʼ���û�����ѡ���
	public void initComboBox() {
		List khInfo = Dao.getUsers();
		List<Item> items = new ArrayList<Item>();
		userCombo.removeAllItems();
		for (Iterator iter = khInfo.iterator(); iter.hasNext();) {
			List element = (List) iter.next();
			Item item = new Item();
			item.setId(element.get(0).toString().trim());
			item.setName(element.get(1).toString().trim());
			if (items.contains(item))
				continue;
			items.add(item);
			userCombo.addItem(item);
		}
		doUserSelectAction();
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
		add(component, gridBagConstrains);
	}
	private void doUserSelectAction() {
		Item selectedItem;
		if (!(userCombo.getSelectedItem() instanceof Item)) {
			return;
		}
		selectedItem = (Item) userCombo.getSelectedItem();
		TbUserlist user = Dao.getUser(selectedItem);
		userName.setText(user.getUsername());
		name.setText(user.getName());
		pass.setText(user.getPass());
		if (user.getQuan().equals("a"))
			quanXian.setSelectedIndex(0);
		else
			quanXian.setSelectedIndex(1);
	}
}
