package Controller;

import Controller.Events.*;
import Model.Model;
import View.RMIClient;

/**
 * Controller Class that handles all the interaction between 
 * View and Model
 * @author Alen Kalac
 */
public class Controller {
	public RMIClient client;
	public Model model;
	public Controller c;
	
	/**
	 * Constructor for the controller
	 * @param client
	 * @param model
	 */
	public Controller(RMIClient client, Model model) {
		this.client = client;
		this.model = model;
		this.c = this;
		
		this.refreshTree();
		this.client.getToolbar().addNewFileActionListener(new NewFileAction(this));
		this.client.getToolbar().addNewFolderActionListener(new NewDirAction(this));
		this.client.getToolbar().addUploadFileActionListener(new UploadFileAction(this));
	}
	
	/**
	 * Refreshes the tree in the RMI Client
	 */
	public void refreshTree() {
		this.client.configureTree(model.getFileList());
		this.client.addTreeDBLClick(new TreeAction(this));
	}
}
