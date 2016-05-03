
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import config.Config;
import fileio.FileObject;
import fileio.FileUpload;

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
	public void sendFile(String filename, byte[] fileData) throws RemoteException {
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			fos.write(fileData);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<FileObject> getFileList() {
		return getFiles("Files");
	}
	
	private ArrayList<FileObject> getFiles(String folder) {
		ArrayList<FileObject> allFiles = new ArrayList<FileObject>();
		File f = new File(folder);
		File[] files = f.listFiles(); 
		for(File file : files) {
			if(file.isDirectory()) {
				FileObject fo = new FileObject(file.getName(), true, folder);
				allFiles.add(fo);
				allFiles.addAll(getFiles(folder + "/" + file.getName().toString()));
			}
			else {
				FileObject fo = new FileObject(file.getName(), false, folder);
				allFiles.add(fo);
			}
		}
		return allFiles;
	}

	@Override
	public boolean createDirectory(String name, String path) throws RemoteException {
		File f = new File(path + "/" + name);
		return f.mkdirs();
	}

	@Override
	public boolean createFile(String name, String path) throws RemoteException {
		File f = new File(path + "/" + name);
		try {
			return f.createNewFile();
		} catch (IOException e) {
			return false;
		}
	}
}