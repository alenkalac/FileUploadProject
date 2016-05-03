package Controller.Events;

import java.awt.event.*;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import Controller.Controller;
import utils.StringUtils;

public class NewFileAction implements ActionListener{
	
	private Controller controller;
	
	public NewFileAction(Controller c) {
		this.controller = c;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		//get a file name
		String path = controller.client.getSelectedTreePath();
		String filename = JOptionPane.showInputDialog("Enter File Name");
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) controller.client.getTree().getLastSelectedPathComponent();
		if(!node.getAllowsChildren()) {
			DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
			path = StringUtils.formatToPath(parent);
		}
		
		if(controller.model.createFile(filename, path))
			controller.refreshTree();
	}
}
