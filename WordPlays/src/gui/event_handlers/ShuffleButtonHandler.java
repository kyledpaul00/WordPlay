package gui.event_handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Model;

public class ShuffleButtonHandler implements ActionListener {
private Model M;
	public ShuffleButtonHandler(Model m) {
		// TODO Auto-generated constructor stub
		M=m;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		M.shuffle();
	}

}
