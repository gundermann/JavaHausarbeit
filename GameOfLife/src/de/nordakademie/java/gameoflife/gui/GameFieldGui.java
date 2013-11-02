package de.nordakademie.java.gameoflife.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSlider;

import de.nordakademie.java.gameoflife.business.Cell;
import de.nordakademie.java.gameoflife.business.GameFieldGuiHandler;

public class GameFieldGui extends JFrame implements GameFieldGuiHandler {

	private int cellGeneration;
	GameFieldPanel gameFieldPanel;

	public GameFieldGui() {
		setTitle("Game Of Life");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		cellGeneration = 1;

		setWindowIntoScreenCenter();

		
		//gameFieldPanel.updateCellArray(cellsArray);
		//gameFieldPanel.setPreferredSize(getDimension(cellsArray));

		// JScrollPane gameFieldScrollPane = new JScrollPane();
		// gameFieldScrollPane.setViewportView(gameFieldPanel);
		// gameFieldScrollPane.setVisible(true);
		//
		// add(gameFieldScrollPane, BorderLayout.CENTER);
		
		add(createJMenuBar(), BorderLayout.NORTH);
		//pack();
		//setVisible(true);

	}

	private Dimension getDimension(Cell[][] cellsArray) {
		int columns = cellsArray.length;
		int rows = 0;
		if (columns > 0) {
			rows = cellsArray[0].length;
		}
		int cellDrawingSize = gameFieldPanel.getCellsDrawingSize();
		return (new Dimension(columns * cellDrawingSize, rows * cellDrawingSize));
	}

	public void updateGameFieldGui(Cell[][] currentCellArray) {
		if(gameFieldPanel == null){
			gameFieldPanel = new GameFieldPanel(currentCellArray);
			add(gameFieldPanel, BorderLayout.CENTER);
			gameFieldPanel.setPreferredSize(getDimension(currentCellArray));
			pack();
			setVisible(true);
		}
		else{
		gameFieldPanel.updateCellArray(currentCellArray);
		repaint();
		}
	}

	private JMenuBar createJMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu options = new JMenu("Spieloptionen");

		options.add(new JMenuItem("Beenden"));
		menuBar.add(options);
		menuBar.add(new JLabel(
				"     Dauer Generationswechsel: schnellstmöglich "));
		menuBar.add(createSpeedChooser());
		menuBar.add(new JLabel(" 1sek   "));
		menuBar.add(new JLabel("Zellengeneration: " + cellGeneration + " "));

		return menuBar;
	}

	private JSlider createSpeedChooser() {
		JSlider speedChooser = new JSlider();
		speedChooser.setMinimum(0);
		speedChooser.setMaximum(100);
		speedChooser.setMajorTickSpacing(5);
		return speedChooser;
	}

	public void increaseGeneration() {
		cellGeneration++;
		gameFieldPanel.repaint();
	}

	private void setWindowIntoScreenCenter() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((d.width - getSize().width) / 2,
				(d.height - getSize().height) / 2);
	}

	@Override
	public long getSliderPosition() {
		// TODO Auto-generated method stub
		return 0;
	}


}
