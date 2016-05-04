package Controller.Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Controller.Controller;

public class NewDirAction implements ActionListener {

	Controller c;
	String path = null;

	public NewDirAction(Controller c) {
		this.c = c;
	}
	
	public NewDirAction(Controller c, String path) {
		this.c = c;
		this.path = path;
	}

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