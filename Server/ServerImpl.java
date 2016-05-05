import java.io.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import config.Config;
import fileio.FileObject;
import fileio.FileUpload;

/**
 * Server implementation
 * 
 * @author Alen Kalac
 *
 */
@SuppressWarnings("serial")
public class ServerImpl extends UnicastRemoteObject implements FileUpload {

	/**
	 * Default Constructor
	 * @throws RemoteException
	 */
	public ServerImpl() throws RemoteException {
		super();

		startRMIRegistry();

		init();

		bind();

	}

	/**
	 * Binds the server to listen on host and port
	 */
	private void bind() {
		try {
			Naming.rebind("rmi://" + Config.host + "/" + Config.service, this);
			System.out.println("FileUpload RMI Server is running...");
		} catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialises few things like setting up for first use
	 */
	private void init() {
		// set up the folder where to save things
		File f = new File("Files");

		if (!f.exists()) {
			f.mkdir();
			System.out.println("dir made");
		}
	}

	/**
	 * Register RMI registry
	 */
	private void startRMIRegistry() {
		try {
			LocateRegistry.createRegistry(7500);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Start the server
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new ServerImpl();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Implementation of sendFile. 
	 * @param fileData
	 * 			Byte array that represents a file
	 * @param filename
	 * 			name of the file to save
	 * @param path
	 * 			path of where to save the file
	 * @return boolean
	 * 			true if save was successful
	 */
	@Override
	public boolean sendFile(byte[] fileData, String filename, String path)
			throws RemoteException {
		try {
			FileOutputStream fos = new FileOutputStream(path + "/" + filename);
			fos.write(fileData);
			fos.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Gets the list of all files
	 * @return ArrayList<String>
	 */
	@Override
	public ArrayList<FileObject> getFileList() {
		return getFiles("Files");
	}

	/**
	 * Recursive search for files and folders
	 * @param folder
	 * 			Starting location
	 * @return ArrayList list of files
	 * 			
	 */
	private ArrayList<FileObject> getFiles(String folder) {
		ArrayList<FileObject> allFiles = new ArrayList<FileObject>();
		File f = new File(folder);
		File[] files = f.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				FileObject fo = new FileObject(file.getName(), true, folder);
				allFiles.add(fo);
				allFiles.addAll(getFiles(folder + "/"
						+ file.getName().toString()));
			} else {
				FileObject fo = new FileObject(file.getName(), false, folder);
				allFiles.add(fo);
			}
		}
		return allFiles;
	}

	/**
	 * Creates a directory
	 * @param name
	 * 			name of the folder to create
	 * @param path
	 * 			path of where to create a folder
	 * @return boolean
	 * 			true if creation is successful
	 */
	@Override
	public boolean createDirectory(String name, String path)
			throws RemoteException {
		File f = new File(path + "/" + name);
		return f.mkdirs();
	}

	/**
	 * create an empty file
	 * @param name
	 * 			Name of the file to create
	 * @param path
	 * 			Path of where to create an empty file 
	 * @return boolean 
	 * 			true if file is created successfully
	 */
	@Override
	public boolean createFile(String name, String path) throws RemoteException {
		File f = new File(path + "/" + name);
		try {
			return f.createNewFile();
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * delete a file from the server
	 * @param path
	 * 			path of the file to delete
	 * @return boolean
	 * 			true if file is deleted successfully
	 */
	@Override
	public boolean deleteFile(String path) throws RemoteException {
		File f = new File(path);
		return f.delete();
	}

	/**
	 * Download a file
	 * 
	 * @param path
	 * 			path of where the file is located
	 * @return byte[]
	 * 			an array of bytes that represents a file
	 */
	@Override
	public byte[] downloadFile(String path) throws RemoteException {
		File f = new File(path);
		FileInputStream fio;
		try {
			fio = new FileInputStream(f);
			byte[] buffer = new byte[fio.available()];
			fio.read(buffer);
			fio.close();
			return buffer;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean renameFile(String path, String newPath) throws RemoteException {
		File f = new File(path);
		File newf = new File(newPath);
		
		return f.renameTo(newf);
	}
}