package de.nordakademie.java.gameoflife.business;

import java.util.ArrayList;
import java.util.List;

import de.nordakademie.java.gameoflife.GameFieldController;
import de.nordakademie.java.gameoflife.business.rules.GameRule;
import de.nordakademie.java.gameoflife.utils.NeighbourFinder;

public class GameThread implements Runnable {

	private boolean gameIsOngoing = true;
	private final NeighbourFinder neighbourFinder;
	private final CellGrid cellGrid;
	private final GameRule gameRules;
	private int generation = 1;
	private GameFieldController gameController;
	
	public GameThread(final CellGrid cellGrid, GameRule gameRules,
			NeighbourFinder neighbourFinder){
		this.cellGrid = cellGrid;
		this.gameRules = gameRules;
		this.neighbourFinder = neighbourFinder;
	}
	
	public void setGameController(GameFieldController gameController) {
		this.gameController = gameController;
	}

	public List<Cell> findCellsToBearOrKill() {
		List<Cell> cellsToBearOrKill = new ArrayList<Cell>();
		Cell[][] cellArray = cellGrid.getCellArray();
		for (int row = 0; row < cellArray.length; row++) {
			for (int column = 0; column < cellArray[0].length; column++) {
				Cell cell = cellGrid.getCellAtPosition(row, column);
				int neighbours = countLivingNeighbours(row, column);
				if (cellWillBeKilledOrBeared(cell, neighbours)) {
					cellsToBearOrKill.add(cell);
				}
			}
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
		List<Cell> neighbours = neighbourFinder.getNeighbours(row, column,
				cellGrid);
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
		
		if (cellsToChange.isEmpty()) {
			gameIsOngoing = false;
		}else{
			generation = generation + 1;
		}
	}
	
	@Override
	public void run() {
		updateGui();
		while (gameIsOngoing) {
			try {
				Thread.sleep(getSettedTime());
				updateGui();
				calculateNextGeneration();
			} catch (InterruptedException e) {
				gameController.showError("Beim Ablauf des Spiels ist ein Fehler aufgetreten.");
			}
		}
		gameController.showFinishing();
		while (!gameIsOngoing) {
			updateGui();
		}
	}

	private void updateGui() {
		gameController.updateGui(cellGrid, generation);
	}

	private long getSettedTime() {
		return gameController.getSettedTime();
	}

	public int getGeneration() {
		return generation;
	}

}
