package de.nordakademie.java.gameoflife.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSlider;

public class GameFieldGui {
	
	private int cellGeneration;
	
	public void initGameFieldGui(){
		JFrame frame = new JFrame("Game Of Life");
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
				
		frame.setJMenuBar(createJMenuBar());
		JLabel testLabel = new JLabel("Hier kommt das Spielfeld hin!");
		frame.add(testLabel);
		
		setWindowIntoScreenCenter(frame);	
		frame.setVisible(true);
	}

	private JMenuBar createJMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu options = new JMenu("Spieloptionen");
		
		options.add(new JMenuItem("Beenden"));
		menuBar.add(options);
		menuBar.add(new JLabel("     Spielgeschwindigkeit: "));
		menuBar.add(new JSlider());
		menuBar.add(new JLabel("Zellengeneration: "+ cellGeneration +" "));
		
		return menuBar;
	}
	
	public void setCellGeneration(Integer currentCellGeneration){
		cellGeneration = currentCellGeneration;
	}
	
	private void setWindowIntoScreenCenter(JFrame frame) {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation( (d.width- frame.getSize().width) /2,(d.height- frame.getSize().height) /2);
	}

}
