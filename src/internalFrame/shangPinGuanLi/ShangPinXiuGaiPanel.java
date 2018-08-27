package internalFrame.shangPinGuanLi;
import com.lyz.dao.Dao;
import internalFrame.guanli.Item;
import model.TbGysinfo;
import model.TbSpinfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class ShangPinXiuGaiPanel extends JPanel {
	private JComboBox gysQuanCheng;
	private JTextField beiZhu;
	private JTextField wenHao;
	private JTextField piHao;
	private JTextField baoZhuang;
	private JTextField guiGe;
	private JTextField danWei;
	private JTextField chanDi;
	private JTextField jianCheng;
	private JTextField quanCheng;
	private JButton modifyButton;
	private JButton delButton;
	private JComboBox sp;
	public ShangPinXiuGaiPanel() {
		setLayout(new GridBagLayout());
		setBounds(10, 10, 550, 400);

		setupComponet(new JLabel("��Ʒ���ƣ�"), 0, 0, 1, 1, false);
		quanCheng = new JTextField();
		quanCheng.setEditable(false);
		setupComponet(quanCheng, 1, 0, 3, 1, true);

		setupComponet(new JLabel("��ƣ�"), 0, 1, 1, 1, false);
		jianCheng = new JTextField();
		setupComponet(jianCheng, 1, 1, 3, 10, true);

		setupComponet(new JLabel("���أ�"), 0, 2, 1, 1, false);
		chanDi = new JTextField();
		setupComponet(chanDi, 1, 2, 3, 300, true);

		setupComponet(new JLabel("��λ��"), 0, 3, 1, 1, false);
		danWei = new JTextField();
		setupComponet(danWei, 1, 3, 1, 130, true);

		setupComponet(new JLabel("���"), 2, 3, 1, 1, false);
		guiGe = new JTextField();
		setupComponet(guiGe, 3, 3, 1, 1, true);

		setupComponet(new JLabel("��װ��"), 0, 4, 1, 1, false);
		baoZhuang = new JTextField();
		setupComponet(baoZhuang, 1, 4, 1, 1, true);

		setupComponet(new JLabel("���ţ�"), 2, 4, 1, 1, false);
		piHao = new JTextField();
		setupComponet(piHao, 3, 4, 1, 1, true);

		setupComponet(new JLabel("��׼�ĺţ�"), 0, 5, 1, 1, false);
		wenHao = new JTextField();
		setupComponet(wenHao, 1, 5, 3, 1, true);

		setupComponet(new JLabel("��Ӧ��ȫ�ƣ�"), 0, 6, 1, 1, false);
		gysQuanCheng = new JComboBox();
		gysQuanCheng.setMaximumRowCount(5);
		setupComponet(gysQuanCheng, 1, 6, 3, 1, true);

		setupComponet(new JLabel("��ע��"), 0, 7, 1, 1, false);
		beiZhu = new JTextField();
		setupComponet(beiZhu, 1, 7, 3, 1, true);

		setupComponet(new JLabel("ѡ����Ʒ"), 0, 8, 1, 0, false);
		sp = new JComboBox();
		sp.setPreferredSize(new Dimension(230, 21));
		// ����ͻ���Ϣ������ѡ����ѡ���¼�
		sp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSpSelectAction();
			}
		});
		// ��λ��Ʒ��Ϣ������ѡ���
		setupComponet(sp, 1, 8, 2, 0, true);
		modifyButton = new JButton("�޸�");
		delButton = new JButton("ɾ��");
		JPanel panel = new JPanel();
		panel.add(modifyButton);
		panel.add(delButton);
		// ��λ��ť
		setupComponet(panel, 3, 8, 1, 0, false);
		// ����ɾ����ť�ĵ����¼�
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = (Item) sp.getSelectedItem();
				if (item == null || !(item instanceof Item))
					return;
				int confirm = JOptionPane.showConfirmDialog(
						ShangPinXiuGaiPanel.this, "ȷ��ɾ����Ʒ��Ϣ��");
				if (confirm == JOptionPane.YES_OPTION) {
					int rs = Dao.delete("delete tb_spinfo where id='"
							+ item.getId() + "'");
					if (rs > 0) {
						JOptionPane.showMessageDialog(ShangPinXiuGaiPanel.this,
								"��Ʒ��" + item.getName() + "��ɾ���ɹ�");
						sp.removeItem(item);
					}
				}
			}
		});
		// �����޸İ�ť�ĵ����¼�
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = (Item) sp.getSelectedItem();
				TbSpinfo spInfo = new TbSpinfo();
				spInfo.setId(item.getId());
				spInfo.setBz(baoZhuang.getText().trim());
				spInfo.setCd(chanDi.getText().trim());
				spInfo.setDw(danWei.getText().trim());
				spInfo.setGg(guiGe.getText().trim());
				spInfo.setGysname(gysQuanCheng.getSelectedItem().toString()
						.trim());
				spInfo.setJc(jianCheng.getText().trim());
				spInfo.setMemo(beiZhu.getText().trim());
				spInfo.setPh(piHao.getText().trim());
				spInfo.setPzwh(wenHao.getText().trim());
				spInfo.setSpname(quanCheng.getText().trim());
				if (Dao.updateSp(spInfo) == 1)
					JOptionPane.showMessageDialog(ShangPinXiuGaiPanel.this,
							"�޸����");
				else
					JOptionPane.showMessageDialog(ShangPinXiuGaiPanel.this,
							"�޸�ʧ��");
			}
		});
	}
	// ��ʼ����Ʒ����ѡ���
	public void initComboBox() {
		List khInfo = Dao.getSpInfos();
		List<Item> items = new ArrayList<Item>();
		sp.removeAllItems();
		for (Iterator iter = khInfo.iterator(); iter.hasNext();) {
			List element = (List) iter.next();
			Item item = new Item();
			item.setId(element.get(0).toString().trim());
			item.setName(element.get(1).toString().trim());
			if (items.contains(item))
				continue;
			items.add(item);
			sp.addItem(item);
		}
		doSpSelectAction();
	}
	// ��ʼ����Ӧ������ѡ���
	public void initGysBox() {
		List gysInfo = Dao.getGysInfos();
		List<Item> items = new ArrayList<Item>();
		gysQuanCheng.removeAllItems();
		for (Iterator iter = gysInfo.iterator(); iter.hasNext();) {
			List element = (List) iter.next();
			Item item = new Item();
			item.setId(element.get(0).toString().trim());
			item.setName(element.get(1).toString().trim());
			if (items.contains(item))
				continue;
			items.add(item);
			gysQuanCheng.addItem(item);
		}
		doSpSelectAction();
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
	// ������Ʒѡ���¼�
	private void doSpSelectAction() {
		Item selectedItem;
		if (!(sp.getSelectedItem() instanceof Item)) {
			return;
		}
		selectedItem = (Item) sp.getSelectedItem();
		TbSpinfo spInfo = Dao.getSpInfo(selectedItem);
		if (!spInfo.getId().isEmpty()) {
			quanCheng.setText(spInfo.getSpname());
			baoZhuang.setText(spInfo.getBz());
			chanDi.setText(spInfo.getCd());
			danWei.setText(spInfo.getDw());
			guiGe.setText(spInfo.getGg());
			jianCheng.setText(spInfo.getJc());
			beiZhu.setText(spInfo.getMemo());
			piHao.setText(spInfo.getPh());
			wenHao.setText(spInfo.getPzwh());
			beiZhu.setText(spInfo.getMemo());
			// ���ù�Ӧ��������ĵ�ǰѡ����
			Item item = new Item();
			item.setId(null);
			item.setName(spInfo.getGysname());
			TbGysinfo gysInfo = Dao.getGysInfo(item);
			item.setId(gysInfo.getId());
			item.setName(gysInfo.getName());
			for (int i = 0; i < gysQuanCheng.getItemCount(); i++) {
				Item gys = (Item) gysQuanCheng.getItemAt(i);
				if (gys.getName().equals(item.getName())) {
					item = gys;
				}
			}
			gysQuanCheng.setSelectedItem(item);
		}
	}
}
