package Controller.Events;

import java.awt.event.*;
import java.io.*;
import javax.swing.JFileChooser;
import Controller.Controller;

/**
 * Class that handles the uploading of files
 * @author Alen Kalac
 *
 */
public class UploadFileAction implements ActionListener {

	private Controller c;
	private String path = null;
	
	/**
	 * Constructor for the class
	 * @param Controller c
	 * 			Controller to use
	 */
	public UploadFileAction(Controller c) {
		this.c = c;
	}
	
	/**
	 * Overloaded Constructor for the class
	 * @param Controller c
	 * 			Controller to use
	 * @param String path
	 * 			Path of where to upload a file
	 */
	public UploadFileAction(Controller c, String path) {
		this.c = c;
		this.path = path;
	}
	
	/**
	 * Action that happens when the button is clicked.
	 * 
	 * it will read the file from the local machine and convert it 
	 * into bytes and then send it off to the server
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc = new JFileChooser();
		int option = jfc.showOpenDialog(null);
		
		if(option == JFileChooser.APPROVE_OPTION) {
			File f = jfc.getSelectedFile();
			
			
			try {
				FileInputStream fio = new FileInputStream(f);
				
				byte[] byteArray = new byte[fio.available()];
				fio.read(byteArray);
				
				if(byteArray == null) return;
				if(path == null)
					path =  c.client.getFolderPath();
				if(c.model.uploadFile(byteArray, f.getName(), path))
					c.refreshTree();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
