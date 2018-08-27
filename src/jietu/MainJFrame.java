package jietu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MainJFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	// 构造器
	public MainJFrame() {

		JButton jb1 = new JButton("开始截图");
		// 注册按钮事件
		jb1.addActionListener(this);
		this.add(jb1);
		this.setBounds(100, 100, 100, 100);
		this.setVisible(true);
		
		this.setResizable(false); // 不可以改变窗口的大小

	}

	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);

		try {
			Thread.sleep(500);
		} catch (Exception e1) {
		}
		new JieTu();

	}
	
	public static void main(String[] args) {
		new MainJFrame();
	}
}