package Controller.Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.Controller;

public class DeleteFileAction implements ActionListener{

	private Controller c;
	private String path;
	
	public DeleteFileAction(Controller c, String path) {
		this.c = c;
		this.path = path;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(c.model.deleteFile(this.path))
			c.refreshTree();
	}

}
