package Controller.Events;

import java.awt.Component;
import java.awt.event.*;
import java.io.*;
import javax.swing.tree.*;
import utils.StringUtils;
import Controller.Controller;
import View.RightClickMenu;
import View.InternalWindows.OpenNewWindow;

/**
 * Class that manages clicks on the tree
 * @author Alen Kalac
 *
 */
public class TreeAction extends MouseAdapter {
	
	private Controller c;
	
	/**
	 * Constructor for this class
	 * @param Controller c
	 */
	public TreeAction(Controller c) {
		this.c = c;
	}
	
	/**
	 * function that takes care of clicks on the nodes
	 * 
	 * if the left mouse button is clicked twice, try to open the file 
	 * by downloading it to temp storage
	 * 
	 * if the right mouse button is clicked, open a popup menu
	 */
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			if (e.getClickCount() == 2) {
				TreePath path = c.client.getTree().getPathForLocation(e.getX(), e.getY());
				
				if (path != null) {
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
					if(!node.getAllowsChildren()) {
						
					try {
							String filePath = c.client.getSelectedTreePath();
							String fileName = StringUtils.getFileNameFromPath(filePath);
							String fileExt = StringUtils.getFileExtension(filePath);
							File f = File.createTempFile(fileName, fileExt);
							f.deleteOnExit();
							FileOutputStream fos = new FileOutputStream(f);
							fos.write(c.model.downloadFile(filePath));
							Component comp = new OpenNewWindow().getWindow(fileName, filePath, fileExt, f);
							if(comp != null)
								c.client.addWindowToDesktop(comp);
							
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					}
				}
					
			}
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			TreePath path = c.client.getTree().getPathForLocation(e.getX(), e.getY());
			
			if (path != null) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
				String fullPath = StringUtils.formatToPath(path.toString());
				System.out.println(fullPath);
				RightClickMenu rcm = new RightClickMenu(c, fullPath);
				rcm.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}
}
