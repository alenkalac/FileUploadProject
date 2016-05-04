package fileio;
import java.io.Serializable;


@SuppressWarnings("serial")
public class FileObject implements Serializable{
	
	private boolean isDir = false;
	private String name = "";
	private String path;
	
	public FileObject(String name, boolean isDir, String path) {
		this.name = name;
		this.isDir = isDir;
		this.path = path;
	}
	
	public boolean isDir() {
		return this.isDir;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPath() {
		return this.path + "/" + this.getName();
	}
}
