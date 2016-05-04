package Controller.Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.Controller;

public class RefreshTreeAction implements ActionListener {
	
	private Controller c;
	
	public RefreshTreeAction(Controller c) {
		this.c = c;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		c.refreshTree();
	}

}
