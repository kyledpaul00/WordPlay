package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import code.Model;
import code.Observer;
import gui.event_handlers.ClearButtonHandler;
import gui.event_handlers.GuessButtonHandler;
import gui.event_handlers.InventoryButtonHandler;
import gui.event_handlers.ShuffleButtonHandler;
import gui.event_handlers.SubmitButtonHandler;
import main.Driver;

public class GUI implements Observer {

	private JPanel _inventoryPanel;
	private JPanel _guessPanel;
	private Model _model;
	private Driver _windowHolder;
	private JPanel _Control;
	private JPanel _feedBack;

	public GUI(Model m, JPanel mp, Driver driver) {
		_windowHolder = driver;
		_model = m;
		
		JPanel _mainPanel = mp;
		_mainPanel.setLayout(new BoxLayout(_mainPanel, BoxLayout.Y_AXIS));
		
		_Control = new JPanel();
		_Control.setLayout(new GridLayout(12,0));
		_mainPanel.add(_Control);
		
		_feedBack = new JPanel();
		_feedBack.setLayout(new BoxLayout(_feedBack, BoxLayout.X_AXIS));
		_mainPanel.add(_feedBack);
		
		
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
		_mainPanel.add(middlePanel);
		
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
		_mainPanel.add(controlPanel);
		

		_inventoryPanel = new JPanel();
		_inventoryPanel.setLayout(new BoxLayout(_inventoryPanel, BoxLayout.X_AXIS));

		_guessPanel = new JPanel();
		_guessPanel.setLayout(new BoxLayout(_guessPanel, BoxLayout.X_AXIS));
		
		
		middlePanel.add(new JLabel("INVENTORY: "));
		middlePanel.add(_inventoryPanel);
		middlePanel.add(new JLabel("GUESS: "));
		middlePanel.add(_guessPanel);
		
		JButton b3 = new JButton("Submit");
		JButton b2 = new JButton("Clear");
		JButton b1 = new JButton("Shuffle");
		
		b1.addActionListener(new ShuffleButtonHandler(_model));
		b2.addActionListener(new ClearButtonHandler(_model));
		b3.addActionListener(new SubmitButtonHandler(_model));
		
		controlPanel.add(b1);
		controlPanel.add(b2);
		controlPanel.add(b3);
		_model.startNewGame();
		_model.addObserver(this);
		
		
	}
	
	@Override
	public void update() {
		_inventoryPanel.removeAll();
		ArrayList<Character> inventoryLetters = _model.getInventoryLetters();
		for (int i=0; i<inventoryLetters.size(); i=i+1) {
			JButton b = new JButton(""+inventoryLetters.get(i));
			setButtonProperties(b);
			_inventoryPanel.add(b);
			b.addActionListener(new InventoryButtonHandler(_model, i));
		}
		_guessPanel.removeAll();
		ArrayList<Character> guessLetters = _model.getGuessLetters();
		for (int i=0; i<guessLetters.size(); i=i+1) {
			JButton b = new JButton(""+guessLetters.get(i));
			setButtonProperties(b);
			_guessPanel.add(b);
			b.addActionListener(new GuessButtonHandler(_model, i));
		}
		_feedBack.removeAll();
			if(_model.lastSubmittedWordWasValid()==-1) {
				_feedBack.add(new JLabel("Sorry, Your Guess was wrong"));
			}
			else if(_model.lastSubmittedWordWasValid()==1) {
				_feedBack.add(new JLabel("Congrats! You got a word!"));
			}	
			else if(_model.lastSubmittedWordWasValid()==0) {
				_feedBack.add(new JLabel("Make a Guess"));
			}
			_Control.removeAll();
		for(String e:_model.words()) {
			JLabel t = new JLabel(e);
			setLabelProperties(t);
			_Control.add(t);
		}
		
		// This should be last statement of this method:
		updateJFrameIfNotHeadless();
	}

	public void updateJFrameIfNotHeadless() {
		if (_windowHolder != null) {
			_windowHolder.updateJFrame();
		}
	}

	public void setButtonProperties(JButton button) {
		button.setFont(new Font("Courier", Font.BOLD, 44));
		button.setBackground(Color.WHITE);
		button.setForeground(Color.BLACK);
		button.setOpaque(true);
		button.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
	}

	public void setLabelProperties(JLabel label) {
		label.setFont(new Font("Courier", Font.BOLD, 44));
		label.setBackground(Color.WHITE);
		label.setForeground(Color.BLACK);
		label.setOpaque(true);
		label.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
	}
}
