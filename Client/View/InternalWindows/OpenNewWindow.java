package View.InternalWindows;

import java.awt.Component;
import java.util.ArrayList;

public class OpenNewWindow{
	
	ArrayList<String> plainFileTypes;
	ArrayList<String> imageFileTypes;
	
	public OpenNewWindow() {
		plainFileTypes = new ArrayList<>();
		plainFileTypes.add(".txt");
		plainFileTypes.add(".xml");
		plainFileTypes.add(".html");
		plainFileTypes.add(".php");
		
		imageFileTypes = new ArrayList<>();
		imageFileTypes.add(".jpg");
		imageFileTypes.add(".png");
		imageFileTypes.add(".bmp");
		
	}
	
	public Component getWindow(String title, String path, String ext) {
		if(plainFileTypes.contains(ext))
			return new PlainTextWindow(title,  path);
		return null;
	}
	
	
}
