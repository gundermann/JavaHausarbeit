package de.nordakademie.java.gameoflife;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import de.nordakademie.java.gameoflife.business.CellGrid;
import de.nordakademie.java.gameoflife.business.GamePad;
import de.nordakademie.java.gameoflife.business.rules.BorderRule;
import de.nordakademie.java.gameoflife.business.rules.GameRule;
import de.nordakademie.java.gameoflife.business.rules.border.PacmanStyle;
import de.nordakademie.java.gameoflife.business.rules.border.WallOfDeath;
import de.nordakademie.java.gameoflife.business.rules.game.GameOfLife;
import de.nordakademie.java.gameoflife.business.rules.game.GameWithoutDeath;
import de.nordakademie.java.gameoflife.business.rules.game.HighLife;
import de.nordakademie.java.gameoflife.business.rules.game.ThreeOrFourLife;
import de.nordakademie.java.gameoflife.constants.ErrorCodes;
import de.nordakademie.java.gameoflife.constants.ErrorTexts;
import de.nordakademie.java.gameoflife.gui.ErrorGui;
import de.nordakademie.java.gameoflife.gui.StartMenuGui;
import de.nordakademie.java.gameoflife.utils.fileLoader.FileLoader;

public class StartGOL implements StartGOLHandler {
	private StartMenuGui startGui;
	private int[][] cellArray;
	private GamePad gamePad;

	public static void main(String[] args) {
		new StartGOL();
	}

	public StartGOL() {
		startGui = new StartMenuGui();
		startGui.setHandler(this);
	}

	// TODO nach Umstellung auf Exceptionhandling noch einmal überarbeiten
	@Override
	public String handleFileUplod() {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(new JFrame());
		File file = chooser.getSelectedFile();
		int errorCode = FileLoader.readFileAndReturnErrorCode(file);
		if (errorCode == ErrorCodes.No_Error) {
			cellArray = FileLoader.getCells();
			return file.getPath();
		} else {
			new ErrorGui(ErrorTexts.getTextToErrorCode(errorCode));
			return "";
		}
	}

	@Override
	public void handleStartButtonPressedEvent() {
		if (cellArray != null) {

			gamePad = new GamePad(new CellGrid(cellArray),
					getSelectedGameRule(), getSelectedBorderRule());
			gamePad.startGame();
		}
	}

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
