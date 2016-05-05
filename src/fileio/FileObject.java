package fileio;

import java.io.Serializable;

/**
 * 
 * @author Alen Kalac
 */
@SuppressWarnings("serial")
public class FileObject implements Serializable{
	
	private boolean isDir = false;
	private String name = "";
	private String path;
	
	/**
	 * Constructor for the class
	 * 
	 * @param String name
	 * @param boolean isDir
	 * @param String path
	 */
	public FileObject(String name, boolean isDir, String path) {
		this.name = name;
		this.isDir = isDir;
		this.path = path;
	}
	
	/**
	 * if the file is a directory return true
	 * @return boolean
	 */
	public boolean isDir() {
		return this.isDir;
	}
	
	/**
	 * Gets the file's name
	 * @return String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Return the path location of the file
	 * @return String
	 */
	public String getPath() {
		return this.path + "/" + this.getName();
	}
}
