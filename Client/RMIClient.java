import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.rmi.Naming;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JSplitPane;
import javax.swing.JTree;

@SuppressWarnings("serial")
public class RMIClient extends JFrame {
	
	private JSplitPane jsp = new JSplitPane();
	
	public RMIClient() {
		init(this);
	}
	
	private void init(JFrame frame) {
		frame.setTitle("File Upload Client");
		frame.setSize(800, 600);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().add(jsp, BorderLayout.CENTER);
		frame.setVisible(true);
		
		configureTree(frame);
		configureFileSystem(frame);
	}
	
	private void configureTree(JFrame frame) {
		//TODO: populate with the files
		jsp.setLeftComponent(new JTree());
	}
	
	private void configureFileSystem(JFrame frame) {
		//TODO: populate with the files
		jsp.setRightComponent(new JList());
	}
	
	
	
	public static void main(String[] args) {
		
		new RMIClient();
		
		
		try {
			
			FileUpload server = (FileUpload) Naming.lookup("rmi://" + Config.host + "/" + Config.service);
			
			//FileInputStream fi = new FileInputStream(new File("bin/test.txt"));
			//byte[] b = new byte[50];
			//fi.read(b);
			//fi.close();
			
			//server.sendFileBytes("test2.txt", b);
			//System.out.println("File Sent");
			//System.out.println(server.getFileList());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}