package de.nordakademie.java.gameoflife.business;

import java.util.ArrayList;
import java.util.List;

import de.nordakademie.java.gameoflife.business.rules.BorderRule;
import de.nordakademie.java.gameoflife.business.rules.GameRule;
import de.nordakademie.java.gameoflife.gui.GameFieldGuiHandler;
import de.nordakademie.java.gameoflife.gui.GameFinishedGui;
import de.nordakademie.java.gameoflife.utils.NeighbourFinder;

/**
 * Kontrolliert den Spielablauf. Läuft in einem eigenen Thread.
 */
public class GameController implements Runnable {

	private final GameRule gameRules;
	private final CellGrid cellGrid;
	private boolean gameIsOngoing = true;
	private int generation = 1;
	private GameFieldGuiHandler gameControlHandler;
	private final NeighbourFinder neighbourFinder;

	public GameController(final CellGrid cellGrid, GameRule gameRules,
			BorderRule borderRules) {
		this.cellGrid = cellGrid;
		this.gameRules = gameRules;
		neighbourFinder = new NeighbourFinder(borderRules.isGridBorderDead());
	}

	public List<Cell> findCellsToBearOrKill() {
		List<Cell> cellsToBearOrKill = new ArrayList<Cell>();
		Cell[][] cellArray = cellGrid.getCellArray();
		for (int row = 0; row < cellArray.length; row++) {
			for (int column = 0; column < cellArray[0].length; column++) {
				Cell cell = cellGrid.getCellAtPosition(row, column);
				int neighbours = countLivingNeighbours(row, column);
				if(cellWillBeKilledOrBeared(cell, neighbours)){
					cellsToBearOrKill.add(cell);
				}
			}
		}

		if (cellsToBearOrKill.isEmpty()) {
			gameIsOngoing = false;
		}

		return cellsToBearOrKill;
	}

	private boolean cellWillBeKilledOrBeared(Cell cell, int neighbours) {
		boolean isAnyRuleApplicable = false;
		if (!cellGrid.isCellAlive(cell) && gameRules.isCellBorn(neighbours)) {
			isAnyRuleApplicable = true;
		} else if (cellGrid.isCellAlive(cell)
				&& !gameRules.isCellStayingAlive(neighbours)) {
			isAnyRuleApplicable = true;
		}
		return isAnyRuleApplicable;
	}

	private int countLivingNeighbours(int row, int column) {
		int livingNeighbours = 0;
		List<Cell> neighbours = neighbourFinder.getNeighbours(row, column, cellGrid);
		for (Cell neighbourCell : neighbours) {
			if (cellGrid.isCellAlive(neighbourCell)) {
				livingNeighbours++;
			}
		}
		return livingNeighbours;
	}

	public void calculateNextGeneration() {
		List<Cell> cellsToChange = findCellsToBearOrKill();
		for (Cell cell : cellsToChange) {
			if (cellGrid.isCellAlive(cell)) {
				cellGrid.killCell(cell);
			} else {
				cellGrid.bearCell(cell);
			}
		}
		generation = generation + 1;
	}

	public int getGeneration() {
		return generation;
	}

	public void setGameControlHandler(GameFieldGuiHandler gameFieldGui) {
		gameControlHandler = gameFieldGui;
	}

	@Override
	public void run() {
		while (gameIsOngoing) {
			try {
				Thread.sleep(getSettedTime());
				updateGui();
				calculateNextGeneration();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		new GameFinishedGui();
		while (!gameIsOngoing) {
			updateGui();
		}
	}

	private void updateGui() {
		gameControlHandler.updateGameFieldGui(
				cellGrid.getCellGridAsBooleanArray(), generation);
	}

	private long getSettedTime() {
		return (1000 * gameControlHandler.getSliderPosition()) / 100;
	}

}