package View.InternalWindows;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class PlainTextWindow extends JInternalFrame {
	
	private JTextArea jta;
	private JButton saveButton;
	private String path;
	
	public PlainTextWindow(String title, String path) {
		super(title, true, true, true, true);
		this.path = path;
		init();
	}
	
	private void init() {
		this.setLayout(new BorderLayout());
		jta = new JTextArea();
		JToolBar jtb = new JToolBar();
		saveButton = new JButton();
		saveButton.setIcon(new ImageIcon("res/save.png"));
		jtb.setFloatable(false);
		jtb.add(saveButton);
		
		this.add(jtb, BorderLayout.NORTH);
		this.add(jta, BorderLayout.CENTER);
		
		this.setBounds(10,10,250,250);
		this.setVisible(true);
	}
	
	
}
