package View;

import javax.swing.*;
import Controller.Controller;
import Controller.Events.*;

/**
 * @author Alen Kalac
 */
@SuppressWarnings("serial")
public class RightClickMenu extends JPopupMenu{
	private JMenuItem deleteFile;
	private JMenuItem shareFile;
	private JMenuItem newFile;
	private JMenuItem uploadFile;
	private JMenuItem downloadFile;
	private JMenuItem refreshTree;
	private JMenuItem newFolder;
	
	private Controller c;
	private String path;
	
	/**
	 * Default Constructor
	 * @param Controller c
	 * @param String fullPath
	 */
	public RightClickMenu(Controller c, String fullPath) {
		super();
		this.c = c;
		this.path = fullPath;
		
		newFile = new JMenuItem("New File");
		newFolder = new JMenuItem("New Folder");
		uploadFile = new JMenuItem("Upload File");
		downloadFile = new JMenuItem("Download File");
		refreshTree = new JMenuItem("Refresh");
		deleteFile = new JMenuItem("Delete");
		shareFile = new JMenuItem("Share");
		
		
		newFile.addActionListener(new NewFileAction(c, path));
		newFolder.addActionListener(new NewDirAction(c, path));
		uploadFile.addActionListener(new UploadFileAction(c));
		downloadFile.addActionListener(new DownloadFileAction(c, path));
		refreshTree.addActionListener(new RefreshTreeAction(c));
		deleteFile.addActionListener(new DeleteFileAction(c, path));
		
		this.add(newFile);
		this.add(newFolder);
		this.addSeparator();
		this.add(uploadFile);
		this.add(downloadFile);
		this.add(refreshTree);
		this.addSeparator();
		this.add(deleteFile);
		this.addSeparator();
		this.add(shareFile);
	}
	
	
	
	
}
