import java.net.MalformedURLException;
import java.rmi.*;
import Controller.Controller;
import Model.Model;
import View.RMIClient;

/**
 * Main Entry Class for a client
 * @author Alen Kalac
 */
public class _MainClient {

	/**
	 * Main entry point
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			RMIClient client = new RMIClient();
			Model model = new Model();
			
			Controller controller = new Controller(client, model);
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
}
