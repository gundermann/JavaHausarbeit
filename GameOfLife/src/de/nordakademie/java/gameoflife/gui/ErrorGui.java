package de.nordakademie.java.gameoflife.gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ErrorGui {

	public void initErrorGui(String errortext) {
		JFrame frame = new JFrame("Fehler");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200, 200);
		frame.setVisible(true);
		frame.setLayout(new GridLayout(3, 1));

		JPanel title = new JPanel();
		title.add(new JLabel("Fehler!"));

		JPanel textPanel = new JPanel();
		textPanel.add(new JLabel(errortext));

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(new JButton("OK"));

		frame.add(title);
		frame.add(textPanel);
		frame.add(buttonPanel);
	}
}
