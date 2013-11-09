package de.nordakademie.java.gameoflife;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

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
	private GameController gameController;
	public static final Map<String, Class> DEFINED_GAME_RULES = new HashMap<String, Class>();
	public static final Map<String, Class> DEFINED_BORDER_RULES = new HashMap<String, Class>();

	public static void main(String[] args) {
		DEFINED_GAME_RULES.put("Game of Life", GameOfLife.class);
		DEFINED_GAME_RULES.put("Game without death", GameWithoutDeath.class);
		DEFINED_GAME_RULES.put("High life", HighLife.class);
		DEFINED_GAME_RULES.put("Three or four life", ThreeOrFourLife.class);

		DEFINED_BORDER_RULES.put("Pacman style", PacmanStyle.class);
		DEFINED_BORDER_RULES.put("Wall of death", WallOfDeath.class);
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
			gameRule = (GameRule) DEFINED_GAME_RULES.get(ruleName)
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
			borderRule = (BorderRule) DEFINED_BORDER_RULES.get(ruleName)
					.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return borderRule;
	}

}
