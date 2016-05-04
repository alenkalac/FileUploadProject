package utils;

import java.util.Arrays;
import javax.swing.tree.DefaultMutableTreeNode;

public class StringUtils {
	public static String join(Object[] arr) {
		
		String data = "";
		
		for(int i = 0; i < arr.length; i++) {
			data += arr.toString();
			
			if(i < arr.length)
				data += "/";
		}
		System.out.println(data);
		return data;
	}
	
	public static String formatToPath(String str) {
		String data = str.replace("[", "");
		data = data.replace("]", "");
		data = data.replace(", ", "/");
		
		return data;
	}
	
	public static String formatToPath(DefaultMutableTreeNode node) {
		String data = Arrays.toString(node.getPath());
		return formatToPath(data);
	}
	
	public static String getFileNameFromPath(String path) {
		return path.substring(path.lastIndexOf("/")+1);
	}
	
	public static String getFileExtension(String filename) {
		return filename.substring(filename.lastIndexOf("."));
	}
}
