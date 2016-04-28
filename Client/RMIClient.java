import java.io.File;
import java.io.FileInputStream;
import java.rmi.Naming;

public class RMIClient {
	
	public static void main(String[] args) {
		
		try {
			
			FileUpload server = (FileUpload) Naming.lookup("rmi://" + Config.host + "/" + Config.service);
			
			FileInputStream fi = new FileInputStream(new File("bin/test.txt"));
			byte[] b = new byte[50];
			fi.read(b);
			fi.close();
			
			server.sendFileBytes("test2.txt", b);
			System.out.println("File Sent");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}