package fileio;

import java.rmi.*;
import java.util.ArrayList;

/**
 * File Upload Class
 * @author Alen Kalac
 */
public interface FileUpload extends Remote {
	public boolean sendFile(byte[] fileBytes, String filename, String path) throws RemoteException;
	public ArrayList<FileObject> getFileList() throws RemoteException;
	public boolean createDirectory(String name, String path) throws RemoteException;
	public boolean createFile(String name, String path) throws RemoteException;
	public boolean deleteFile(String path) throws RemoteException;
	public byte[] downloadFile(String path) throws RemoteException;
	
}