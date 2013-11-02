package de.nordakademie.java.gameoflife.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSlider;

import de.nordakademie.java.gameoflife.business.Cell;
import de.nordakademie.java.gameoflife.business.GameFieldGuiHandler;

public class GameFieldGui extends JFrame implements GameFieldGuiHandler {

	private int cellGeneration = 1;
	GameFieldPanel gameFieldPanel;
	JMenuBar menuBar;
	JMenu options;
	JMenuItem closeItem;
	JLabel generationChangeLabel;
	JSlider speedChooser;
	private JLabel highspeedLabel;
	private JLabel oneSecondLabel;
	private JLabel cellGenerationTitleLabel;
	private JLabel currentCellGenerationLabel;

	public GameFieldGui() {
		setTitle("Game Of Life");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// setWindowIntoScreenCenter();

		add(createJMenuBar(), BorderLayout.NORTH);
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

	@Override
	public void updateGameFieldGui(Cell[][] currentCellArray) {
		if (gameFieldPanel == null) {
			gameFieldPanel = new GameFieldPanel(currentCellArray);
			add(gameFieldPanel, BorderLayout.CENTER);
			gameFieldPanel.setPreferredSize(getDimension(currentCellArray));

			JScrollPane gameFieldScrollPane = new JScrollPane();
			gameFieldScrollPane.setViewportView(gameFieldPanel);
			gameFieldScrollPane.setVisible(true);
			add(gameFieldScrollPane, BorderLayout.CENTER);

			pack();
			setVisible(true);
		} else {
			gameFieldPanel.updateCellArray(currentCellArray);
			cellGeneration++;
			currentCellGenerationLabel.setText(cellGeneration + "");
			repaint();
		}
	}

	private JMenuBar createJMenuBar() {
		menuBar = new JMenuBar();
		options = new JMenu("Spieloptionen");
		closeItem = new JMenuItem("Beenden");
		generationChangeLabel = new JLabel("Geschwindigkeit:");
		highspeedLabel = new JLabel("schnellstmöglich");
		oneSecondLabel = new JLabel("1sek");
		cellGenerationTitleLabel = new JLabel("Zellengeneration: ");
		currentCellGenerationLabel = new JLabel();

		options.add(closeItem);
		menuBar.add(options);
		menuBar.add(generationChangeLabel);
		menuBar.add(highspeedLabel);
		menuBar.add(createSpeedChooser());
		menuBar.add(oneSecondLabel);
		menuBar.add(oneSecondLabel);
		menuBar.add(cellGenerationTitleLabel);
		menuBar.add(currentCellGenerationLabel);
		return menuBar;
	}

	private JSlider createSpeedChooser() {
		speedChooser = new JSlider();
		speedChooser.setMinimum(0);
		speedChooser.setMaximum(100);
		speedChooser.setValue(50);
		return speedChooser;
	}

	@Override
	public long getSliderPosition() {
		return speedChooser.getValue();
	}

}
