import java.rmi.Naming;
import java.util.Scanner;

public class RMIClient {
	public static void main(String[] args) {
		String hostName = "localhost";
		String serviceName = "FileUploadService";
		String who = "Alen";
		Scanner s = new Scanner(System.in);
	
		try {
			
			FileUpload hello = (FileUpload) Naming.lookup("rmi://" + hostName + "/" + serviceName);
			while(true) {
				
				String data = s.nextLine();
				if(data.equalsIgnoreCase("hello")) {
					System.out.println(hello.sayHello(who));
				}
				else if(data.equalsIgnoreCase("upload")) {
					System.out.println(hello.uploadFile("test"));
				}
				
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}