package Model;

import java.io.*;
import java.net.MalformedURLException;
import java.rmi.*;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import config.Config;
import fileio.*;

/**
 * Model Class
 * 
 * @author Alen Kalac
 */
public class Model {
	private FileUpload server;

	/**
	 * Constructor for the class
	 * 
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public Model() throws MalformedURLException, RemoteException,
			NotBoundException {
		this.server = (FileUpload) Naming.lookup("rmi://" + Config.host + "/"
				+ Config.service);
	}

	/**
	 * Upload file function
	 * 
	 * @param byte[] byteArray Array of bytes that makes up a file
	 * @param String
	 *            filename File name
	 * @param String
	 *            path Path of where to upload
	 * @return boolean True if upload is successful
	 */
	public boolean uploadFile(byte[] byteArray, String filename, String path) {
		try {
			return server.sendFile(byteArray, filename, path);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Create a new file function
	 * 
	 * @param String
	 *            filename File Name
	 * @param path
	 *            Path of where to create a file
	 * @return boolean True if file is created successfully
	 */
	public boolean createFile(String filename, String path) {
		try {
			return server.createFile(filename, path);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Create a new directory function
	 * 
	 * @param String
	 *            dirname Directory name
	 * @param String
	 *            path Path of where to create a directory
	 * @return boolean True if directory is created successfully
	 */
	public boolean createDirectory(String dirname, String path) {
		try {
			return server.createDirectory(dirname, path);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Request a list of files from the RMI server
	 * 
	 * @return ArrayList<String> List of all files on the server including the
	 *         root directory
	 */
	public ArrayList<FileObject> getFileList() {
		try {
			return this.server.getFileList();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Deletes a file from the server
	 * 
	 * @param String
	 *            path Path to File to delete
	 * @return boolean True if deletion is successful
	 */
	public boolean deleteFile(String path) {
		try {
			return server.deleteFile(path);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Download a file from the server
	 * 
	 * @param String
	 *            path Path of the file to download
	 * @return byte[] or null Array of bytes that represents a file.
	 */
	public byte[] downloadFile(String path) {
		try {
			return server.downloadFile(path);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Download a file but with the option to let the user choose where to
	 * download
	 * 
	 * @param String
	 *            path Path of the file to download
	 * 
	 */
	public void downloadFileWithPicker(String path) {
		JFileChooser jfc = new JFileChooser();
		jfc.setSelectedFile(new File(path));
		jfc.setMultiSelectionEnabled(false);
		int option = jfc.showSaveDialog(null);

		if (option == JFileChooser.APPROVE_OPTION) {
			File f = jfc.getSelectedFile();

			try {
				FileOutputStream fos = new FileOutputStream(f);
				byte[] fileBytes = this.downloadFile(path);
				fos.write(fileBytes);
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
