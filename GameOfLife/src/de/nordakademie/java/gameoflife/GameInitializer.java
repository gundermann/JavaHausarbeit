package de.nordakademie.java.gameoflife;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import de.nordakademie.java.gameoflife.business.CellGrid;
import de.nordakademie.java.gameoflife.business.GameController;
import de.nordakademie.java.gameoflife.business.rules.BorderRule;
import de.nordakademie.java.gameoflife.business.rules.GameRule;
import de.nordakademie.java.gameoflife.exceptions.FileReadingErrorException;
import de.nordakademie.java.gameoflife.gui.ErrorGui;
import de.nordakademie.java.gameoflife.gui.GameFieldGui;
import de.nordakademie.java.gameoflife.gui.StartMenuGui;
import de.nordakademie.java.gameoflife.utils.fileloader.FileLoader;

public class GameInitializer implements GameHandler {

	private final StartMenuGui startGui;
	private int[][] cellArray;
	private GameController gameController;

	public GameInitializer() {
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
			if (exception.getErrorMessage().equals("Abbruch")) {
			} else {
				new ErrorGui(exception.getErrorMessage());
			}
		}
		return "";
	}

	@Override
	public void handleStartButtonPressedEvent() {
		if (cellArray != null) {
			GameFieldGui gameField = new GameFieldGui();
			gameController = new GameController(new CellGrid(cellArray),
					getSelectedGameRule(), getSelectedBorderRule());
			gameController.setGameControlHandler(gameField);
			new Thread(gameController).start();
			startGui.dispose();
		} else {
			new ErrorGui("Es wurde keine Datei gefunden");
		}
	}

	private GameRule getSelectedGameRule() {
		GameRule gameRule = null;
		String ruleName = startGui.getSelectedGameRule();
		try {
			gameRule = (GameRule) StartGOL.DEFINED_GAME_RULES.get(ruleName)
					.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return gameRule;
	}

	private BorderRule getSelectedBorderRule() {
		BorderRule borderRule = null;
		String ruleName = startGui.getSelectedBorderRule();
		try {
			borderRule = (BorderRule) StartGOL.DEFINED_BORDER_RULES.get(
					ruleName).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return borderRule;
	}

}
