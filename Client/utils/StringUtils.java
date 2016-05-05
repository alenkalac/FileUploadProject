package utils;

import java.util.Arrays;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Utility Class that Formats a String
 * 
 * @author Alen Kalac
 */
public class StringUtils {

	/**
	 * Joins an array of strings into a single string
	 * 
	 * @param Object[]
	 *            An array of objects
	 * @return String a string that looks like element1/elenent2/elemenet3
	 */
	public static String join(Object[] arr) {

		String data = "";

		for (int i = 0; i < arr.length; i++) {
			data += arr.toString();

			if (i < arr.length)
				data += "/";
		}
		System.out.println(data);
		return data;
	}

	/**
	 * Formats a string that is represented as an array [obj1, obj2, obj3] into
	 * obj1/obj2/obj3
	 * 
	 * @param String
	 *            String to format
	 * @return String Formated string that looks like obj1/obj2/obj3
	 */
	public static String formatToPath(String str) {
		String data = str.replace("[", "");
		data = data.replace("]", "");
		data = data.replace(", ", "/");

		return data;
	}

	/**
	 * Format a node into a path
	 * 
	 * @param DefaultMutableTreeNode
	 *            node Node to format into a string
	 * @return String Path of the node as a string Files/x/y
	 */
	public static String formatToPath(DefaultMutableTreeNode node) {
		String data = Arrays.toString(node.getPath());
		return formatToPath(data);
	}

	/**
	 * gets the file name from the path
	 * 
	 * @param String
	 *            Full path
	 * @return String File name by grabbing the last substring
	 */
	public static String getFileNameFromPath(String path) {
		return path.substring(path.lastIndexOf("/") + 1);
	}

	/**
	 * gets the file extension of the file
	 * 
	 * @param String
	 *            File name with .ext
	 * @return String returns the .ext
	 */
	public static String getFileExtension(String filename) {
		return filename.substring(filename.lastIndexOf("."));
	}
}
