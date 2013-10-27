package de.nordakademie.java.gameoflife.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSlider;

import de.nordakademie.java.gameoflife.business.Cell;

public class GameFieldGui {

	private int cellGeneration;
	GameFieldCanvas gameFieldCanvas;

	public GameFieldGui(Cell[][] cellsArray) {
		JFrame frame = new JFrame("Game Of Life");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		cellGeneration = 1;

		setWindowIntoScreenCenter(frame);

		gameFieldCanvas = new GameFieldCanvas(cellsArray);
		gameFieldCanvas.setIgnoreRepaint(true);
		gameFieldCanvas.setPreferredSize(getDimension(cellsArray));

		JScrollPane gameFieldPanel = new JScrollPane();
		gameFieldPanel.setViewportView(gameFieldCanvas);
		gameFieldPanel.setVisible(true);

		frame.add(gameFieldPanel, BorderLayout.CENTER);
		frame.add(createJMenuBar(), BorderLayout.NORTH);
		frame.pack();
		frame.setVisible(true);

	}

	private Dimension getDimension(Cell[][] cellsArray) {
		int rows = cellsArray.length;
		int columns = 0;
		// TODO: wird im Uploader abgefangen, muss es falls unterwegs ein Fehler
		// passiert trotzdem getestet werden?
		if (rows > 0) {
			columns = cellsArray[0].length;
		}
		int cellDrawingSize = gameFieldCanvas.getCellsDrawingSize();
		return (new Dimension(columns * cellDrawingSize, rows * cellDrawingSize));
	}

	public void updateGameFieldGui(Cell[][] cellsAliveArray) {
		gameFieldCanvas.updateGameFieldCanvas(cellsAliveArray);
		gameFieldCanvas.paint(gameFieldCanvas.getGraphics());
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
		gameFieldCanvas.repaint();
	}

	private void setWindowIntoScreenCenter(JFrame frame) {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width) / 2,
				(d.height - frame.getSize().height) / 2);
	}

}
