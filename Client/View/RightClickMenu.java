package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import Controller.Controller;
import Controller.Events.*;

@SuppressWarnings("serial")
public class RightClickMenu extends JPopupMenu{
	private JMenuItem deleteFile;
	private JMenuItem shareFile;
	private JMenuItem newFile;
	private JMenuItem newFolder;
	
	private Controller c;
	
	public RightClickMenu(Controller c) {
		super();
		this.c = c;
		
		newFile = new JMenuItem("New File");
		newFolder = new JMenuItem("New Folder");
		deleteFile = new JMenuItem("Delete");
		shareFile = new JMenuItem("Share");
		
		
		newFile.addActionListener(new NewFileAction(c));
		newFolder.addActionListener(new NewDirAction(c));
		deleteFile.addActionListener(new DeleteFileAction(c));
		
		this.add(newFile);
		this.add(newFolder);
		this.add(deleteFile);
		this.addSeparator();
		this.add(shareFile);
	}
	
	
	
	
}
