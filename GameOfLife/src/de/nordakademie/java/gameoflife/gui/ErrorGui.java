package de.nordakademie.java.gameoflife.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ErrorGui {
	
	public void initErrorGui(String errortext){
	JFrame frame = new JFrame("Fehlermeldung");
	frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
	frame.setSize(500, 200);
	frame.setLayout(new BorderLayout());
	frame.setResizable(false);
	
	setWindowIntoScreenCenter(frame);
	
	JPanel title = createTitlePanel();
	JPanel textPanel = createErrorTextPanel(errortext);
	
	frame.add(title, BorderLayout.NORTH);
	frame.add(textPanel, BorderLayout.CENTER);
	frame.add(new JButton("OK"), BorderLayout.SOUTH);

	frame.setVisible(true);
	}

	private JPanel createErrorTextPanel(String errortext) {
		JPanel textPanel = new JPanel();
		textPanel.add(new JTextArea(errortext));
		return textPanel;
	}

	private JPanel createTitlePanel() {
		JPanel title = new JPanel();
		title.add(new JLabel("Achtung, es ist ein Fehler aufgetreten!"));
		return title;
	}

	private void setWindowIntoScreenCenter(JFrame frame) {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation( (d.width- frame.getSize().width) /2,(d.height- frame.getSize().height) /2);
	}
}
