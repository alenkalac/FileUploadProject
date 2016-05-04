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
	private String path;
	
	public RightClickMenu(Controller c, String fullPath) {
		super();
		this.c = c;
		this.path = fullPath;
		
		newFile = new JMenuItem("New File");
		newFolder = new JMenuItem("New Folder");
		deleteFile = new JMenuItem("Delete");
		shareFile = new JMenuItem("Share");
		
		
		newFile.addActionListener(new NewFileAction(c, path));
		newFolder.addActionListener(new NewDirAction(c, path));
		deleteFile.addActionListener(new DeleteFileAction(c, path));
		
		this.add(newFile);
		this.add(newFolder);
		this.add(deleteFile);
		this.addSeparator();
		this.add(shareFile);
	}
	
	
	
	
}
