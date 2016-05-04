package View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

@SuppressWarnings("serial")
public class CustomDesktop extends JDesktopPane{
	
	private ImageIcon img;
	
	public CustomDesktop() {
		super();
		img = new ImageIcon("res/bg.jpg");
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.printComponents(g);
		g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
	
	
}
