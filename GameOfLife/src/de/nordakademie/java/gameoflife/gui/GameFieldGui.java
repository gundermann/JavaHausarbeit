package de.nordakademie.java.gameoflife.gui;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSlider;

import de.nordakademie.java.gameoflife.logic.game.Cell;

public class GameFieldGui {
	
	private int cellGeneration;
	GameFieldCanvas gameFieldCanvas;
	
	public void initGameFieldGui(Integer [][] cellsAliveArray){
		JFrame frame = new JFrame("Game Of Life");
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setJMenuBar(createJMenuBar());
		setWindowIntoScreenCenter(frame);
		
		gameFieldCanvas = new GameFieldCanvas(cellsAliveArray);		
		frame.add(gameFieldCanvas);
		frame.setVisible(true);
	}
	
	public void updateGameFieldGui(Integer [][] cellsAliveArray){
		gameFieldCanvas.updateGameFieldCanvas(cellsAliveArray);
		gameFieldCanvas.repaint();
	}

	private JMenuBar createJMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu options = new JMenu("Spieloptionen");
		
		options.add(new JMenuItem("Beenden"));
		menuBar.add(options);
		menuBar.add(new JLabel("     Dauer Generationswechsel: schnellstmöglich "));
		menuBar.add(createSpeedChooser());
		menuBar.add(new JLabel(" 1sek   "));
		menuBar.add(new JLabel("Zellengeneration: "+ cellGeneration +" "));
		
		return menuBar;
	}
	
	private JSlider createSpeedChooser() {
		JSlider speedChooser = new JSlider();
		speedChooser.setMinimum(0);
		speedChooser.setMaximum(100);
		speedChooser.setMajorTickSpacing(5);
		return speedChooser;
	}

	public void setCellGeneration(Integer currentCellGeneration){
		cellGeneration = currentCellGeneration;
	}
	
	private void setWindowIntoScreenCenter(JFrame frame) {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation( (d.width- frame.getSize().width) /2,(d.height- frame.getSize().height) /2);
	}

}
