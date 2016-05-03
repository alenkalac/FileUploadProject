
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTree;
import javax.swing.tree.TreePath;


public class MouseClickEvent extends MouseAdapter{
	
	private JTree tree;
	
	public MouseClickEvent(JTree tree) {
		this.tree = tree;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2) {
			TreePath path = tree.getPathForLocation(e.getX(), e.getY());
			System.out.println(path.toString());
		}
	}
	
}
