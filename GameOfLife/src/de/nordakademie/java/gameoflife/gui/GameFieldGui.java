package de.nordakademie.java.gameoflife.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class GameFieldGui {
	
	public void initGameFieldGui(){
		JFrame frame = new JFrame("Game Of Life");
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());
		
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new GridLayout(1,4));
		menuPanel.setBorder(new EtchedBorder(Color.black, Color.blue));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(new JButton("Beenden"));
		menuPanel.add(buttonPanel);
		
		JPanel title = new JPanel();
		title.add(new JLabel("Game Of Life"));
		menuPanel.add(title);
		
		JPanel gameSpeed = new JPanel();
		gameSpeed.add(new JLabel("Spielgeschwindigkeit"));
		menuPanel.add(gameSpeed);
		
		JPanel generation = new JPanel();
		generation.add(new JLabel("Zellengeneration"));
		menuPanel.add(generation);
		
		JPanel gameFieldPanel = new JPanel();
		JLabel testLabel = new JLabel("Hier kommt das Spielfeld hin!");
		
		frame.add(menuPanel, BorderLayout.NORTH);
		frame.add(testLabel, BorderLayout.CENTER);
	}

}
