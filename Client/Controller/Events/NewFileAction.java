package Controller.Events;

import java.awt.event.*;
import javax.swing.JOptionPane;
import Controller.Controller;

/**
 * 
 * @author Alen Kalac
 *
 */
public class NewFileAction implements ActionListener{
	
	private Controller c;
	private String path = null; 
	
	/**
	 * Constructor for this class
	 * @param Controller c
	 */
	public NewFileAction(Controller c) {
		this.c = c;
	}
	
	/**
	 * Overloaded Constructor for this class
	 * @param Controller c
	 * @param String path
	 */
	public NewFileAction(Controller c, String path) {
		this.c = c;
		this.path = path;
	}
	
	/**
	 * When a button is clicked, start the new file sequence 
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		String filename = JOptionPane.showInputDialog("Enter File Name");
		
		if(filename != null && filename.length() != 0) {
			if(path == null)
				path = c.client.getFolderPath();
			if(!filename.contains("."))
				filename += ".txt";
			
			if(c.model.createFile(filename, path))
				c.refreshTree();
		}
	}
}
