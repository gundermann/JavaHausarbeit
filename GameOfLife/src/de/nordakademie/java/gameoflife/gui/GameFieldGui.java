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

	private int cellGeneration =1;
	GameFieldPanel gameFieldPanel;
	private JLabel cellGenerationLabel = new JLabel();

	public GameFieldGui() {
		setTitle("Game Of Life");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		setWindowIntoScreenCenter();
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
		cellGeneration++;
		cellGenerationLabel.setText(cellGeneration +"");
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
		menuBar.add(new JLabel("Zellengeneration: "));
		
		cellGenerationLabel.setText(cellGeneration +"");
		
		menuBar.add(cellGenerationLabel);

		return menuBar;
	}

	private JSlider createSpeedChooser() {
		JSlider speedChooser = new JSlider();
		speedChooser.setMinimum(0);
		speedChooser.setMaximum(100);
		speedChooser.setMajorTickSpacing(5);
		return speedChooser;
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
