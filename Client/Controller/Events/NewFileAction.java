package Controller.Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Controller.Controller;

public class NewFileAction implements ActionListener{
	
	private Controller c;
	private String path = null; 
	
	public NewFileAction(Controller c) {
		this.c = c;
	}
	
	public NewFileAction(Controller c, String path) {
		this.c = c;
		this.path = path;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String filename = JOptionPane.showInputDialog("Enter File Name");
		
		if(filename != null && filename.length() != 0) {
			if(path == null)
				path = c.client.getFolderPath();
			
			if(c.model.createFile(filename, path))
				c.refreshTree();
		}
	}
}
