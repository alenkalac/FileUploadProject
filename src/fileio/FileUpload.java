package fileio;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface FileUpload extends Remote {
	
	public void sendFile(String filename, byte[] fileBytes) throws RemoteException;
	public ArrayList<FileObject> getFileList() throws RemoteException;
	public boolean createDirectory(String name, String path) throws RemoteException;
	public boolean createFile(String name, String path) throws RemoteException;
	
}