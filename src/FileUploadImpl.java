import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("serial")
public class FileUploadImpl extends UnicastRemoteObject implements FileUpload {
	public FileUploadImpl() throws RemoteException {
		super();
	}

	public String sayHello(String who) throws RemoteException {
		return "Hello " + who + " from your friend RMI 433-652 :-)";
	}

	public static void main(String[] args) {
		String hostName = "localhost";
		String serviceName = "FileUploadService";
		
		try {
			FileUpload fileUpload = new FileUploadImpl();
			Naming.rebind("rmi://" + hostName + "/" + serviceName, fileUpload);
			System.out.println("FileUpload RMI Server is running...");
		} catch (Exception e) {}
	}

	@Override
	public String uploadFile(String file) throws RemoteException {
		return "uploading file " + file;
	}
}