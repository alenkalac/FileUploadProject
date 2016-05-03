package Controller.Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import utils.StringUtils;
import Controller.Controller;

public class NewDirAction implements ActionListener {

	Controller c;

	public NewDirAction(Controller c) {
		this.c = c;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// get a file name
		String dirname = JOptionPane.showInputDialog("Enter Directory Name");
		String path = c.client.getFolderPath();

		if (c.model.createDirectory(dirname, path))
			c.refreshTree();
	}
}