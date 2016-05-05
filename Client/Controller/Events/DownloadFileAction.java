package Controller.Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.Controller;

public class DownloadFileAction implements ActionListener {

	private Controller c;
	private String path;
	
	public DownloadFileAction(Controller c, String path) {
		this.c = c;
		this.path = path;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		c.model.downloadFileWithPicker(path);
	}

}
