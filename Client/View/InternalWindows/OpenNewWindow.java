package View.InternalWindows;

import java.awt.Component;
import java.io.File;
import java.net.MalformedURLException;
import java.rmi.*;
import java.util.ArrayList;

import Model.Model;

/**
 * The factory class that will return one window based on the file ext
 * @author Alen Kalac
 */
public class OpenNewWindow{
	ArrayList<String> plainFileTypes;
	ArrayList<String> imageFileTypes;
	
	/**
	 * Constructor for the class
	 * Add files that you want to use with different windows
	 */
	public OpenNewWindow() {
		plainFileTypes = new ArrayList<>();
		plainFileTypes.add(".txt");
		plainFileTypes.add(".xml");
		plainFileTypes.add(".html");
		plainFileTypes.add(".php");
		plainFileTypes.add(".java");
		
		imageFileTypes = new ArrayList<>();
		imageFileTypes.add(".jpg");
		imageFileTypes.add(".png");
		imageFileTypes.add(".bmp");
		
	}
	
	/**
	 * 
	 * @param String title
	 * 			Window Title
	 * @param String path
	 * 			Path of where the file is
	 * @param String ext
	 * 			Extension of the file. 
	 * @param File f
	 * 			The file object
	 * 
	 * @return Component window
	 * 			Window that will be returned, of class JInternalFrame 
	 * or null if there is some error happening.
	 */
	public Component getWindow(String title, String path, String ext, File f) {
		ext = ext.toLowerCase();
		if(plainFileTypes.contains(ext))
			return new PlainTextWindow(title,  path, f);
		if(imageFileTypes.contains(ext))
			return new ImageWindow(title,  path, f);
		else {
			try {
				Model m = new Model();
				m.downloadFileWithPicker(path);
			} catch (MalformedURLException | RemoteException | NotBoundException e) {
				
				e.printStackTrace();
			}
		}
		return null;
	}
}
