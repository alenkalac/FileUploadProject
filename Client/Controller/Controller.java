package Controller;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import Controller.Events.*;
import Model.Model;
import View.RMIClient;
import View.RightClickMenu;
import utils.StringUtils;

public class Controller {
	public RMIClient client;
	public Model model;
	public Controller c;
	
	public Controller(RMIClient client, Model model) {
		this.client = client;
		this.model = model;
		this.c = this;
		
		this.refreshTree();
		this.client.addNewFileActionListener(new NewFileAction(this));
		this.client.addNewFolderActionListener(new NewDirAction(this));
		this.client.addUploadFileActionListener(new UploadFileAction(this));
	}
	
	public void refreshTree() {
		this.client.configureTree(model.getFileList());
		this.client.addTreeDBLClick(new TreeAction());
	}
	
	public class TreeAction extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				if(e.getClickCount() == 2) {
					TreePath path = client.getTree().getPathForLocation(e.getX(), e.getY());
					if(path != null)
						System.out.println(StringUtils.formatToPath(path.toString())) ;
				}
			}
			else if(e.getButton() == MouseEvent.BUTTON3) {
				TreePath path = client.getTree().getPathForLocation(e.getX(), e.getY());
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
				if(path != null) {
					System.out.println(path.toString());
					RightClickMenu rcm = new RightClickMenu(c);
					rcm.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		}
	}
}
