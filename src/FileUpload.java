import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FileUpload extends Remote {
	
	public void sendFileBytes(String filename, byte[] bytes) throws RemoteException;
	
}