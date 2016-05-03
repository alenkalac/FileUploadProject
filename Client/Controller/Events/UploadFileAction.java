package Controller.Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JFileChooser;

import Controller.Controller;

public class UploadFileAction implements ActionListener {

	private Controller c;
	
	public UploadFileAction(Controller c) {
		this.c = c;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc = new JFileChooser();
		int option = jfc.showOpenDialog(null);
		
		if(option == JFileChooser.APPROVE_OPTION) {
			File f = jfc.getSelectedFile();
			
			
			try {
				FileInputStream fio = new FileInputStream(f);
				
				byte[] byteArray = new byte[fio.available()];
				fio.read(byteArray);
				
				if(byteArray == null) return;
				
				if(c.model.uploadFile(byteArray, f.getName(), c.client.getFolderPath()))
					c.refreshTree();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
