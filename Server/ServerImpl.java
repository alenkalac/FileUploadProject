import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Vector;

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

	@Override
	public ArrayList<String> getFileList() {
		return getFiles("Files");
	}
	
	private ArrayList<String> getFiles(String folder) {
		ArrayList<String> allFiles = new ArrayList<String>();
		File f = new File(folder);
		File[] files = f.listFiles(); 
		for(File file : files) {
			if(file.isDirectory())
				allFiles.addAll(getFiles(folder + "/" + file.getName().toString()));
			else
				allFiles.add(folder + "/" + file.getName());
		}
		return allFiles;
	}
}