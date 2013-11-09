package de.nordakademie.java.gameoflife;

import de.nordakademie.java.gameoflife.business.CellGrid;
import de.nordakademie.java.gameoflife.business.GameThread;
import de.nordakademie.java.gameoflife.business.rules.BorderRule;
import de.nordakademie.java.gameoflife.business.rules.GameRule;
import de.nordakademie.java.gameoflife.gui.ErrorGui;
import de.nordakademie.java.gameoflife.gui.GameFieldGui;
import de.nordakademie.java.gameoflife.utils.NeighbourFinder;

public class GameFieldController{
	private GameFieldGui gameField;
	private GameThread gameThread;
	

	public GameFieldController(int[][] cellArray, GameRule gameRules,
			BorderRule borderRules) {
		gameField = new GameFieldGui();
		CellGrid cellGrid = new CellGrid(cellArray);
		NeighbourFinder neighbourFinder = new NeighbourFinder(borderRules.isGridBorderDead());
		gameThread = new GameThread(cellGrid, gameRules, neighbourFinder);
		gameThread.setGameController(this);
		new Thread(gameThread).start();
	}

	public void showFinishing(){
		new GameFieldGui();
	}
	
	public void updateGui(CellGrid cellGrid, int generation) {
		gameField.updateGameFieldGui(
				cellGrid.getCellGridAsBooleanArray(), generation);
	}

	public long getSettedTime() {
		return (1000 * gameField.getSliderPosition()) / 100;
	}

	public void showError(String errortext) {
		new ErrorGui(errortext);
	}

}