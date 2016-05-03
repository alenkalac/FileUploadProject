import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import Controller.Controller;
import Model.Model;
import View.RMIClient;

public class _MainClient {

	public static void main(String[] args) {
		try {
			RMIClient client = new RMIClient();
			Model model = new Model();
			
			Controller controller = new Controller(client, model);
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}
