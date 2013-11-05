package de.nordakademie.java.gameoflife.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSlider;

import de.nordakademie.java.gameoflife.business.Cell;

/*
 * GUI für das Spielfeld mit dem Universum
 * 
 * @author Kathrin Kurtz
 */

public class GameFieldGui extends GolGui implements GameFieldGuiHandler {

	private GameFieldPanel gameFieldPanel;
	private JMenuBar menuBar;
	private JMenu options;
	private JMenuItem closeItem;
	private JLabel generationChangeLabel;
	private JSlider speedChooser;
	private JLabel highspeedLabel;
	private JLabel oneSecondLabel;
	private JLabel cellGenerationTitleLabel;
	private JLabel currentCellGenerationLabel;

	public GameFieldGui() {
		setTitle("Game Of Life");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		add(createJMenuBar(), BorderLayout.NORTH);
		initActionListener();
	}

	private void initActionListener() {
		closeItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				closeGui();
			}
		});
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
	public void updateGameFieldGui(Cell[][] currentCellArray,
			Integer cellGeneration) {
		if (gameFieldPanel == null) {
			initGameFieldPanel(currentCellArray);

		} else {
			gameFieldPanel.updateCellArray(currentCellArray);
			currentCellGenerationLabel.setText(cellGeneration + "");
			repaint();
		}
	}

	private void initGameFieldPanel(Cell[][] currentCellArray) {
		gameFieldPanel = new GameFieldPanel(currentCellArray);
		add(gameFieldPanel, BorderLayout.CENTER);
		gameFieldPanel.setPreferredSize(getDimension(currentCellArray));

		JScrollPane gameFieldScrollPane = new JScrollPane();
		gameFieldScrollPane.setViewportView(gameFieldPanel);
		gameFieldScrollPane.setVisible(true);
		add(gameFieldScrollPane, BorderLayout.CENTER);

		this.showGui();
	}

	private JMenuBar createJMenuBar() {
		menuBar = new JMenuBar();
		options = new JMenu("Spieloptionen");
		closeItem = new JMenuItem("Beenden");
		generationChangeLabel = new JLabel("   Geschwindigkeit: ");
		highspeedLabel = new JLabel("schnellstm\u00f6glich");
		oneSecondLabel = new JLabel("1sek  ");
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
