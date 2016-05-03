

import java.awt.BorderLayout;
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
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("serial")
public class RMIClient extends JFrame {
	
	private JSplitPane jsp = new JSplitPane();
	private FileUpload server;
	HashMap<String, DefaultMutableTreeNode> fileStructure;
	
	public RMIClient() throws MalformedURLException, RemoteException, NotBoundException {
		server = (FileUpload) Naming.lookup("rmi://" + Config.host + "/" + Config.service);
		init(this);
	}
	
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
	
	private void configureTree(JFrame frame) throws RemoteException {
		
		ArrayList<String> files = server.getFileList();
		DefaultMutableTreeNode troot = new DefaultMutableTreeNode("Files");
		fileStructure.put("Files", troot);
		
		for(String file : files) {
			String[] data = file.split("/");
			DefaultMutableTreeNode parent = null;
			for(String d : data) {
				
				if(fileStructure.containsKey(d)) {
					parent = fileStructure.get(d);
				}
				else {
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(d);
					if(parent != null) 
						parent.add(node);
					parent = node;
					fileStructure.put(d, node);
				}
			}
		}
		
		
		JTree jt = new JTree(troot);
		jt.addMouseListener(new MouseClickEvent(jt));
		jsp.setLeftComponent(jt);
	}
	
	private void configureFileSystem(JFrame frame) {
		
		JPanel rightPanel = new JPanel();
		JButton b = new JButton("Upload");
		rightPanel.add(b);
		
		jsp.setRightComponent(rightPanel);
	}
	
	public static void main(String[] args) {
		
		try {
			new RMIClient();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}