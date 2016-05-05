package View.InternalWindows;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;

/**
 * Displays an image inside a frame
 * @author Alen Kalac
 */
@SuppressWarnings("unused")
public class ImageWindow extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	private ImageIcon img;
	private JButton saveButton;
	private String path;
	private File f;
	private String title;

	/**
	 * Constructor that sets up the object
	 * @param String title
	 * @param String path
	 * @param File f
	 */
	public ImageWindow(String title, String path, File f) {
		super(title, true, true, true, true);
		this.path = path;
		this.title = title;
		this.f = f;
		init();
	}

	/**
	 * Initialises some components
	 */
	private void init() {
		img = new ImageIcon();
		this.setBounds(10, 10, 450, 250);
		readFile();
		
		JScrollPane jsp = new JScrollPane(new JLabel(img));
		
		this.add(jsp);
		this.setVisible(true);
		
	}

	/**
	 * Reads the file from temp storage
	 */
	private void readFile() {
		try {
			BufferedImage bimg = ImageIO.read(f);
			this.img.setImage(bimg);
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
