import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FileUpload extends Remote {
	public String sayHello(String who) throws RemoteException;
	
	public String uploadFile(String file) throws RemoteException;
}