package Controller.Events;

import java.awt.event.*;

import javax.swing.JOptionPane;

import Controller.Controller;

/**
 * Rename File Class that renames a file on the server
 * @author Alen Kalac
 */
public class RenameFileAction implements ActionListener{

	private Controller c;
	private String path;
	
	/**
	 * Constructor for the class. 
	 * Sets up some variables
	 * @param Controller c
	 * 			Controller to use
	 * @param String path
	 * 			Path of the file to rename
	 */
	public RenameFileAction(Controller c, String path) {
		this.c = c;
		this.path = path;
	}
	
	/**
	 * When a button is clicked. request delete of file and refresh a tree on success
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String newFilename = JOptionPane.showInputDialog("Enter new File name");
		if(newFilename != null) {
			String dirPath = path.substring(0, path.lastIndexOf("/"));
			String newFilePath = dirPath + "/" + newFilename;
			//System.out.println(newFilePath);
			if(c.model.renameFile(path, newFilePath))
				c.refreshTree();
		}
	}

}
