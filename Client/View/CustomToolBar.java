package View;

import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

/**
 * @author Alen Kalac
 */
@SuppressWarnings("serial")
public class CustomToolBar extends JToolBar implements Runnable{
	
	private JButton newFile;
	private JButton newFolder;
	private JButton uploadFile;
	private JLabel time;
	
	/**
	 * Default Constructor
	 */
	public CustomToolBar() {
		
		this.newFile = new JButton("New File");
		this.newFolder = new JButton("New Folder");
		this.uploadFile = new JButton("Upload a file");
		
		newFile.setIcon(new ImageIcon("res/add_file.png"));
		newFolder.setIcon(new ImageIcon("res/newfolder.png"));
		uploadFile.setIcon(new ImageIcon("res/upload.png"));
		
		this.time = new JLabel();
		
		this.add(newFile);
		this.add(newFolder);
		this.add(uploadFile);
		
		this.setFloatable(false);
		this.add(Box.createHorizontalGlue());
		this.add(time);
		
		Thread t = new Thread(this);
		t.start();
	}
	
	/**
	 * Add a new Listener to the button
	 * @param ActionListener event
	 */
	public void addNewFileActionListener(ActionListener event) {
		this.newFile.addActionListener(event);
	}
	
	/**
	 * Add a new listener to the button
	 * @param ActionListener event
	 */
	public void addNewFolderActionListener(ActionListener event) {
		this.newFolder.addActionListener(event);
	}
	
	/**
	 * Add a new listener to the button
	 * @param ActionListener event
	 */
	public void addUploadFileActionListener(ActionListener event) {
		this.uploadFile.addActionListener(event);
	}

	/**
	 * A thread that runs to keep the clock updated
	 */
	@Override
	public void run() {
		while(true) {
			try {
				LocalDateTime time = LocalDateTime.now();
				DateTimeFormatter format = DateTimeFormatter.ofPattern("H:mm:ss");
				this.time.setText(time.format(format));
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}
