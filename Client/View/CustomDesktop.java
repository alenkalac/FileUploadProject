package View;

import java.awt.Graphics;
import javax.swing.*;

/**
 * Custom desktop that paints a nice desktop background
 * @author Alen Kalac
 *
 */
@SuppressWarnings("serial")
public class CustomDesktop extends JDesktopPane{
	
	private ImageIcon img;
	
	/**
	 * Constructor for the class
	 */
	public CustomDesktop() {
		super();
		img = new ImageIcon(getClass().getResource("/res/bg.jpg"));
		this.repaint();
	}

	/**
	 * Overrides the default paintComponent and paints the background
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.printComponents(g);
		g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
	}
}
