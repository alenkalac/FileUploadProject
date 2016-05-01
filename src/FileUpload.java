import java.io.FileInputStream;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface FileUpload extends Remote {
	
	public void sendFile(String filename, byte[] fileBytes) throws RemoteException;
	public ArrayList<String> getFileList() throws RemoteException;
	
}