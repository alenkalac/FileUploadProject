package Controller.Events;

import java.awt.event.*;
import Controller.Controller;

/**
 * Download file class that request a file to be downloaded
 * @author Alen Kalac
 */
public class DownloadFileAction implements ActionListener {

	private Controller c;
	private String path;
	
	/**
	 * Constructor for the class
	 * @param Controller c
	 * 			Controller to use
	 * @param String path
	 * 			Path of the file to download
	 */
	public DownloadFileAction(Controller c, String path) {
		this.c = c;
		this.path = path;
	}
	
	/**
	 * When a button is pressed. start the download
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		c.model.downloadFileWithPicker(path);
	}

}
