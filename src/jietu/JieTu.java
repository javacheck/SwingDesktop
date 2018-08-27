package jietu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Java做出类似于QQ截图软件的源码
 */
public class JieTu extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;

	public JieTu() {

		try {

			// 拿到屏幕的分辨率
			Toolkit t = Toolkit.getDefaultToolkit();

			int w = t.getScreenSize().width;
			int h = t.getScreenSize().height;
			Robot r = new Robot();
			// 开始截图
			BufferedImage image = r.createScreenCapture(
					new Rectangle(0, 0, w,h));

			JLabel jl1 = new JLabel(new ImageIcon(image));

			this.addMouseListener(this);

			this.add(jl1);
			this.setBounds(0, 0, w, h);
			// 窗口据顶
			this.setAlwaysOnTop(true);
			// 去掉窗口的边框
			this.setUndecorated(true);
			this.setVisible(true);

		} catch (Exception e) {
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getClickCount() == 2) { // 双击

			try {
				Robot r = new Robot();
				// 开始截图
				BufferedImage image = r.createScreenCapture(
						new Rectangle(x + 1, y + 1, ww - 1, hh - 1));
				ImageIO.write(image, "jpeg", new File("c:\\a.jpg"));
				this.setVisible(false);

				Runtime.getRuntime()
						.exec("c:\\windows\\System32\\rundll32.exe \"C:\\Program Files\\Windows Photo Viewer\\PhotoViewer.dll\", ImageView_Fullscreen c:\\a.jpg");

				System.exit(0);

			} catch (Exception e2) {
			}

		}

	}

	int x, y;
	int ww, hh;
	boolean b = false;

	@Override
	public void mousePressed(MouseEvent e) { // 按下

		if (b == false) {

			x = e.getX();
			y = e.getY();
			Graphics g = this.getGraphics();
			g.drawRect(x, y, -100, -100);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {// 弹起
		if (b == false) {
			// JLabel jl = (JLabel) e.getSource();
			b = true;
			Graphics g = this.getGraphics();
			g.setColor(Color.red);
			g.drawRect(x, y, e.getX() - x, e.getY() - y);

			g.drawRect(x, y, e.getX() - x, e.getY() - y);
			ww = e.getX() - x;
			hh = e.getY() - y;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}