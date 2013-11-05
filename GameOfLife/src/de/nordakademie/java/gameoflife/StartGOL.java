package de.nordakademie.java.gameoflife;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import de.nordakademie.java.gameoflife.business.CellGrid;
import de.nordakademie.java.gameoflife.business.GameController;
import de.nordakademie.java.gameoflife.business.rules.BorderRule;
import de.nordakademie.java.gameoflife.business.rules.GameRule;
import de.nordakademie.java.gameoflife.business.rules.border.PacmanStyle;
import de.nordakademie.java.gameoflife.business.rules.border.WallOfDeath;
import de.nordakademie.java.gameoflife.business.rules.game.GameOfLife;
import de.nordakademie.java.gameoflife.business.rules.game.GameWithoutDeath;
import de.nordakademie.java.gameoflife.business.rules.game.HighLife;
import de.nordakademie.java.gameoflife.business.rules.game.ThreeOrFourLife;
import de.nordakademie.java.gameoflife.exceptions.FileReadingErrorException;
import de.nordakademie.java.gameoflife.gui.ErrorGui;
import de.nordakademie.java.gameoflife.gui.GameFieldGui;
import de.nordakademie.java.gameoflife.gui.StartMenuGui;
import de.nordakademie.java.gameoflife.utils.fileloader.FileLoader;

public class StartGOL implements StartGOLHandler {
	private final StartMenuGui startGui;
	private int[][] cellArray;
	private GameController gamePad;

	public static void main(String[] args) {
		new StartGOL();
	}

	public StartGOL() {
		startGui = new StartMenuGui();
		startGui.setHandler(this);
	}

	@Override
	public String handleFileUplod() {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(new JFrame());
		File file = chooser.getSelectedFile();
		try {
			FileLoader fileLoader = new FileLoader();
			fileLoader.readFile(file);
			cellArray = fileLoader.getCells();
			return file.getPath();
		} catch (FileReadingErrorException exception) {
			System.out.println(exception);
			if(exception.getErrorMessage().equals("Abbruch")){
			}
			else{
				new ErrorGui(exception.getErrorMessage());
			}
		}
		return "";
	}

	@Override
	public void handleStartButtonPressedEvent() {
		if (cellArray != null) {
			GameFieldGui gameField = new GameFieldGui();
			gamePad = new GameController(new CellGrid(cellArray),
					getSelectedGameRule(), getSelectedBorderRule());
			gamePad.setGameControlHandler(gameField);
			new Thread(gamePad).start();
			startGui.dispose();
		} else {
			new ErrorGui("Es wurde keine Datei gefunden");
		}
	}

	// TODO:3 Positionen zu �ndern f�r Regel hinzuf�gen
	private GameRule getSelectedGameRule() {
		String ruleName = startGui.getSelectedGameRule();
		if (ruleName.equals("Game of Life")) {
			return new GameOfLife();
		}
		if (ruleName.equals("Game without Death")) {
			return new GameWithoutDeath();
		}
		if (ruleName.equals("Three or four to life")) {
			return new ThreeOrFourLife();
		} else {
			return new HighLife();
		}
	}

	private BorderRule getSelectedBorderRule() {
		String ruleName = startGui.getSelectedBorderRule();
		if (ruleName.equals("Wall of Death")) {
			return new WallOfDeath();
		} else {
			return new PacmanStyle();
		}
	}

}
