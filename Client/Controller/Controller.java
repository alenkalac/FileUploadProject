package Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import Controller.Events.NewDirAction;
import Controller.Events.NewFileAction;
import Controller.Events.UploadFileAction;
import Model.Model;
import View.RMIClient;
import utils.StringUtils;

public class Controller {
	public RMIClient client;
	public Model model;
	
	public Controller(RMIClient client, Model model) {
		this.client = client;
		this.model = model;
		
		this.refreshTree();
		this.client.addNewFileActionListener(new NewFileAction(this));
		this.client.addNewFolderActionListener(new NewDirAction(this));
		this.client.addUploadFileActionListener(new UploadFileAction(this));
	}
	
	public void refreshTree() {
		this.client.configureTree(model.getFileList());
		this.client.addTreeDBLClick(new DBLClick());
	}
	
	
	public class DBLClick extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 2) {
				TreePath path = client.getTree().getPathForLocation(e.getX(), e.getY());
				if(path != null)
					System.out.println(StringUtils.formatToPath(path.toString())) ;
			}
		}
	}
}
