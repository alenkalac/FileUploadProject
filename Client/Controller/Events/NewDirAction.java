package Controller.Events;

import java.awt.event.*;
import javax.swing.JOptionPane;
import Controller.Controller;

/**
 * A class that creates a new directory on the server
 * @author Alen Kalac
 */
public class NewDirAction implements ActionListener {

	Controller c;
	String path = null;

	/**
	 * Constructor for the class
	 * @param Controller c
				Controller to use
	 */
	public NewDirAction(Controller c) {
		this.c = c;
	}
	
	/**
	 * Overloaded Constructor
	 * @param Controller c
	 * 			Controller to use
	 * @param String path
	 * 			Path of the directory where to create it
	 */
	public NewDirAction(Controller c, String path) {
		this.c = c;
		this.path = path;
	}

	/**
	 * when a button is pressed, begin folder creation process
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		String dirname = JOptionPane.showInputDialog("Enter Directory Name");
		if(dirname != null && dirname.length() != 0) {
			if(this.path == null)
				path = c.client.getFolderPath();
	
			if (c.model.createDirectory(dirname, path))
				c.refreshTree();
		}
	}
}