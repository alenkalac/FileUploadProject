package Model;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import config.Config;
import fileio.FileObject;
import fileio.FileUpload;


public class Model {
	private FileUpload server;
	
	public Model() throws MalformedURLException, RemoteException, NotBoundException {
		this.server = (FileUpload) Naming.lookup("rmi://" + Config.host + "/" + Config.service);
	}
	
	public boolean uploadFile(byte[] byteArray, String filename, String path) {
		try {
			return server.sendFile(byteArray, filename, path);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean createFile(String filename, String path) {
		try {
			return server.createFile(filename, path);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean createDirectory(String dirname, String path) {
		try {
			return server.createDirectory(dirname, path);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<FileObject> getFileList() {
		try {
			return this.server.getFileList();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean deleteFile(String path) {
		try {
			return server.deleteFile(path);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public byte[] downloadFile(String path) {
		try {
			return server.downloadFile(path);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
}
