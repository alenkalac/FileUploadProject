package Controller.Events;

import java.awt.event.*;
import Controller.Controller;

/**
 * Delete File Class that deletes a file from the server
 * @author Alen Kalac
 */
public class DeleteFileAction implements ActionListener{

	private Controller c;
	private String path;
	
	/**
	 * Constructor for the class. 
	 * Sets up some variables
	 * @param Controller c
	 * 			Controller to use
	 * @param String path
	 * 			Path of the file to delete
	 */
	public DeleteFileAction(Controller c, String path) {
		this.c = c;
		this.path = path;
	}
	
	/**
	 * When a button is clicked. request delete of file and refresh a tree on success
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(c.model.deleteFile(this.path))
			c.refreshTree();
	}

}
