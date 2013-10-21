package de.nordakademie.java.gameoflife.gui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class StartMenuGui {
	
	
	public void initStartMenuGUI()
	{
		JFrame frame = new JFrame( "Startmenü Game Of Life");
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 500);
		frame.setVisible(true);
		frame.setLayout( new GridLayout(3,1));
				
		frame.add(createTitlePanel() );
		frame.add(createInitGamePanel());
		frame.add(createButtonPanel());
		
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation( (d.width- frame.getSize().width) /2,
						(d.height- frame.getSize().height) /2);
	}
	

	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel(new GridLayout(1,2));
		//buttonPanel.setSize(100, 100);
		buttonPanel.setBorder(new EtchedBorder(Color.black, Color.blue));
		buttonPanel.add(new JButton("Abbrechen"));
		buttonPanel.add(new JButton ("Start"));
		return buttonPanel;
	}
	
	private JPanel createTitlePanel(){
		JPanel titlePanel = new JPanel(new GridLayout(1,1));
		//titlePanel.setSize(100, 100);
		titlePanel.setBorder(new EtchedBorder(Color.black, Color.blue));
		titlePanel.add(new JLabel("Game of Life"));
		return titlePanel;
	}
	
	private JPanel createInitGamePanel(){
		JPanel initGamePanel = new JPanel(new GridLayout(3,2));
		//initGamePanel.setSize(100, 100);
		initGamePanel.setBorder(new EtchedBorder(Color.black, Color.blue));
		initGamePanel.add(new JLabel("Spielvariante"));
		
		JComboBox<String> GameChoice = new JComboBox<String>();
		initGamePanel.add(GameChoice);
		GameChoice.addItem("Game of Life");
		GameChoice.addItem("Game without Death");
		GameChoice.addItem("Three or four Life");
		GameChoice.addItem("HighLife");
		
		initGamePanel.add(new JLabel("Randvariante"));
		JComboBox<String> BorderChoice = new JComboBox<String>();
		initGamePanel.add(BorderChoice);
		BorderChoice.addItem("Pacman Style");
		BorderChoice.addItem("Wall of Death");
		
		
		return initGamePanel;
	}
	
	
	

}
