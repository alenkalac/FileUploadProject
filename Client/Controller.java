import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.tree.TreePath;

import utils.StringUtils;

public class Controller {
	private RMIClient client;
	
	public Controller(RMIClient client) {
		this.client = client;
		
		this.client.addTreeDBLClick(new DBLClick());
	}
	
	
	public class DBLClick extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 2) {
				TreePath path = client.getTree().getPathForLocation(e.getX(), e.getY());
				if(path != null)
					System.out.println(StringUtils.formatToPath(path.toString())) ;
			}
		}
	}
}