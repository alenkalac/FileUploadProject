package View.InternalWindows;

import java.awt.Component;
import java.io.File;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Model.Model;

public class OpenNewWindow{
	
	ArrayList<String> plainFileTypes;
	ArrayList<String> imageFileTypes;
	
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
