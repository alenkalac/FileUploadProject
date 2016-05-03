package Controller.Events;

import java.awt.event.*;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import Controller.Controller;
import utils.StringUtils;

public class NewFileAction implements ActionListener{
	
	private Controller c;
	
	public NewFileAction(Controller c) {
		this.c = c;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		//get a file name
		
		String filename = JOptionPane.showInputDialog("Enter File Name");
		String path = c.client.getFolderPath();
		
		if(c.model.createFile(filename, path))
			c.refreshTree();
	}
}
