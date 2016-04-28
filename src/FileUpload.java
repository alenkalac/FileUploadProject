import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface FileUpload extends Remote {
	
	public void sendFileBytes(String filename, byte[] bytes) throws RemoteException;
	public ArrayList<String> getFileList() throws RemoteException;
	
}