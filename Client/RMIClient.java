import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("serial")
public class RMIClient extends JFrame {

	private JSplitPane jsp = new JSplitPane();
	private FileUpload server;
	private JTree jt;

	private HashMap<String, DefaultMutableTreeNode> fileStructure;

	/**
	 * Default Constructor
	 * 
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public RMIClient() throws MalformedURLException, RemoteException, NotBoundException {
		server = (FileUpload) Naming.lookup("rmi://" + Config.host + "/"
				+ Config.service);
		init(this);
	}

	/**
	 * Initializes the GUI and displays a window
	 * 
	 * @param frame
	 *            The JFrame instance to initialize
	 * @throws RemoteException
	 */
	private void init(JFrame frame) throws RemoteException {

		fileStructure = new HashMap<String, DefaultMutableTreeNode>();

		frame.setTitle("File Manager Client");
		frame.setSize(800, 600);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().add(jsp, BorderLayout.CENTER);
		frame.setVisible(true);
		configureTree(frame);
		configureFileSystem(frame);
	}

	/**
	 * Configures the JTree component and adds all the nodes
	 * 
	 * @param frame 
	 * 			The frame to add the JTree to
	 * @throws RemoteException
	 */
	private void configureTree(JFrame frame) throws RemoteException {

		ArrayList<String> files = server.getFileList();
		DefaultMutableTreeNode troot = new DefaultMutableTreeNode("Files");
		fileStructure.put("Files", troot);

		for (String file : files) {
			String[] data = file.split("/");
			DefaultMutableTreeNode parent = null;
			for (String d : data) {

				if (fileStructure.containsKey(d)) {
					parent = fileStructure.get(d);
				} else {
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(d);
					if (parent != null)
						parent.add(node);
					parent = node;
					fileStructure.put(d, node);
				}
			}
		}

		jt = new JTree(troot);
		jsp.setLeftComponent(jt);
	}

	/**
	 * Configure the main area of the program
	 * 
	 * @param frame
	 * 			The frame to add the file system to
	 */
	private void configureFileSystem(JFrame frame) {

		JPanel rightPanel = new JPanel();
		JButton b = new JButton("Upload");
		rightPanel.add(b);

		jsp.setRightComponent(rightPanel);
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
}