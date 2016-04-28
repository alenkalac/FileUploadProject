import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("serial")
public class ServerImpl extends UnicastRemoteObject implements FileUpload {
	
	public ServerImpl() throws RemoteException {
		super();
		
		startRMIRegistry();
		
		init();
		
		bind();
	}
	
	private void bind() {
		try {
			Naming.rebind("rmi://" + Config.host + "/" + Config.service, this);
			System.out.println("FileUpload RMI Server is running...");
		} catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
		}
	}

	private void init() {
		//set up the folder where to save things
		File f = new File("Files");
		
		if(!f.exists()) {
			f.mkdir();
			System.out.println("dir made");
		}
	}
	
	private void startRMIRegistry() {
		try {
			LocateRegistry.createRegistry(7500);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			new ServerImpl();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendFileBytes(String filename, byte[] bytes) throws RemoteException {
		try {
			FileOutputStream stream = new FileOutputStream("Files/" + filename);
			stream.write(bytes);
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}