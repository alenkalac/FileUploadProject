package Controller.Events;

import java.awt.event.*;
import Controller.Controller;

/**
 * Refreshes a tree that's on the RMI client
 * @author Alen Kalac
 *
 */
public class RefreshTreeAction implements ActionListener {
	
	private Controller c;
	
	/**
	 * Constructor for this class
	 * @param Controller c
	 * 			Controller to use
	 */
	public RefreshTreeAction(Controller c) {
		this.c = c;
	}

	/**
	 * Action that happens when a button is clicked.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		c.refreshTree();
	}

}
