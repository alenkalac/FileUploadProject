package View;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.net.MalformedURLException;
import javax.swing.tree.*;
import java.rmi.*;
import java.util.*;
import javax.swing.*;
import fileio.FileObject;
import utils.StringUtils;

/**
 * The View for the client side
 * @author Alen Kalac
 */
@SuppressWarnings("serial")
public class RMIClient extends JFrame {

	private JSplitPane jsp = new JSplitPane();
	private JTree jt;
	private CustomDesktop desktop;
	private CustomToolBar toolbar;
	
	private HashMap<String, DefaultMutableTreeNode> fileStructure;

	/**
	 * Default Constructor
	 * 
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public RMIClient() throws MalformedURLException, RemoteException, NotBoundException {
		init(this);
	}

	/**
	 * Initialises the GUI and displays a window
	 * 
	 * @param frame
	 *            The JFrame instance to initialise
	 * @throws RemoteException
	 */
	private void init(JFrame frame) throws RemoteException {

		jsp.setResizeWeight(0.2);
		
		fileStructure = new HashMap<String, DefaultMutableTreeNode>();

		frame.setTitle("File Manager Client");
		frame.setSize(1000, 600);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().add(jsp, BorderLayout.CENTER);
		frame.setVisible(true);
		configureCenterArea(frame);
	}

	/**
	 * Configures the JTree component and adds all the nodes
	 * 
	 * @param files 
	 * 			The files to add to the tree. 
	 */
	public void configureTree(ArrayList<FileObject> files){
		
		fileStructure = new HashMap<String, DefaultMutableTreeNode>();
		
		DefaultMutableTreeNode troot = new DefaultMutableTreeNode("Files");
		DefaultTreeModel model = new DefaultTreeModel(troot, true);
		
		fileStructure.put("Files", troot);

		for (FileObject file : files) {
			String[] data = file.getPath().split("/");
			DefaultMutableTreeNode parent = null;
			for (String d : data) {
				if (fileStructure.containsKey(d))
					parent = fileStructure.get(d);
				else {
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(d, file.isDir());
					if (parent != null)
						parent.add(node);
					parent = node;
					fileStructure.put(d, node);
				}
			}
		}

		jt = new JTree(troot);
		jt.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		jt.setExpandsSelectedPaths(true);
		jt.setModel(model);
		jsp.setLeftComponent(jt);
	}

	/**
	 * Configure the main area of the program
	 * 
	 * @param frame
	 * 			The frame to add the file system to
	 */
	private void configureCenterArea(JFrame frame) {

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		
		toolbar = new CustomToolBar();
		buttonPanel.add(toolbar);
		
		rightPanel.add(buttonPanel, BorderLayout.NORTH);
		
		desktop = new CustomDesktop();
		
		rightPanel.add(desktop, BorderLayout.CENTER);
		jsp.setRightComponent(rightPanel);
		desktop.repaint();
	}

	/**
	 * Returns a JTree instance used in the GUI
	 * 
	 * @return JTree 
	 * 			A Tree that contains file structure
	 */
	public JTree getTree() {
		return this.jt;
	}

	/**
	 * Sets up a double click listener on the JTree
	 * 
	 * @param MouseAdapter event
	 * 			A listener that will handle the clicks
	 */
	public void addTreeDBLClick(MouseAdapter event) {
		this.jt.addMouseListener(event);
	}
	
	/**
	 * Returns a custom toolbar instance
	 * 
	 * @return CustomToolBar
	 * 			JToolBar that's on the side of the window
	 */
	public CustomToolBar getToolbar() {
		return this.toolbar;
	}
	
	/**
	 * Returns a path of the selected item
	 * @return String
	 */
	public String getSelectedTreePath() {
		if(this.jt.getSelectionPath() == null) return "Files";
		else
		return StringUtils.formatToPath(this.jt.getSelectionPath().toString());	
	}
	
	/**
	 * @return the closest folder parent
	 * @return String
	 * 			Path of the closest parent folder
	 */
	public String getFolderPath() {
		String path = this.getSelectedTreePath();
		if(path.equals("Files")) return path;
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.getTree().getLastSelectedPathComponent();
		if (!node.getAllowsChildren()) {
			DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
			path = StringUtils.formatToPath(parent);
		}
		return path;
	}
	
	/**
	 * Adds a JInternalFrame to the virtual desktop
	 * 
	 * @param Component window
	 */
	public void addWindowToDesktop(Component window) {
		this.desktop.add(window);
	}
	
}