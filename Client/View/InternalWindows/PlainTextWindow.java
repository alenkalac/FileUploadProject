package View.InternalWindows;

import java.rmi.NotBoundException;
import java.awt.BorderLayout;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.*;
import Model.Model;
import java.io.*;

/**
 * Shows a text field in the frame with the file loaded
 * 
 * @author Alen Kalac
 */
@SuppressWarnings("serial")
public class PlainTextWindow extends JInternalFrame {

	private JTextArea jta;
	private JButton saveButton;
	private String path;
	private File f;
	private String title;

	/**
	 * Constructor for the class
	 * 
	 * @param String
	 *            title
	 * @param String
	 *            path
	 * @param File
	 *            f
	 */
	public PlainTextWindow(String title, String path, File f) {
		super(title, true, true, true, true);
		this.path = path;
		this.title = title;
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.f = f;
		init();

		this.addInternalFrameListener(new InternalFrameAdapter() {

			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				JInternalFrame frame = (JInternalFrame) e.getSource();
				int result = JOptionPane.showConfirmDialog(null,
						"Do You want to SAVE before exit?");
				if (result == 0) {
					saveFile();
					frame.dispose();
				} else if (result == 1)
					frame.dispose();
			}
		});
	}

	/**
	 * Initialises components and variables
	 */
	private void init() {
		this.setLayout(new BorderLayout());
		jta = new JTextArea();
		readFile();
		JToolBar jtb = new JToolBar();
		saveButton = new JButton();
		saveButton.setIcon(new ImageIcon(getClass().getResource("/res/save.png")));
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		});

		JScrollPane jsp = new JScrollPane(jta);

		jtb.setFloatable(false);
		jtb.add(saveButton);

		this.add(jtb, BorderLayout.NORTH);
		this.add(jsp, BorderLayout.CENTER);

		this.setBounds(10, 10, 450, 250);
		this.setVisible(true);
	}

	/**
	 * reads the file from the temp storage
	 */
	private void readFile() {
		try {
			FileReader reader = new FileReader(this.f);
			this.jta.read(reader, "jta");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Saves the file back into the cloud
	 */
	public void saveFile() {
		BufferedWriter br;
		try {
			br = new BufferedWriter(new FileWriter(f));
			jta.write(br);
			br.close();

			String parentPath = path.substring(0, path.lastIndexOf("/"));

			FileInputStream fis = new FileInputStream(f);
			byte[] bytes = new byte[fis.available()];
			fis.read(bytes);

			Model m = new Model();
			if (m.uploadFile(bytes, title, parentPath))
				JOptionPane.showMessageDialog(null,
						"File has been saved successfully!");

		} catch (IOException | NotBoundException e) {
			e.printStackTrace();
		}
	}

}
