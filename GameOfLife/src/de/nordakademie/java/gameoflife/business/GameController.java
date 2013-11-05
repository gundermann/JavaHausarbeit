package de.nordakademie.java.gameoflife.business;

import java.util.ArrayList;
import java.util.List;

import de.nordakademie.java.gameoflife.business.rules.BorderRule;
import de.nordakademie.java.gameoflife.business.rules.GameRule;
import de.nordakademie.java.gameoflife.gui.GameFieldGuiHandler;
import de.nordakademie.java.gameoflife.gui.GameFinishedGui;
import de.nordakademie.java.gameoflife.utils.NeighbourFinder;

public class GameController implements Runnable {

	private final GameRule gameRules;
	private CellGrid cellGrid;
	private boolean gameIsOngoing = true;
	private int generation = 1;
	private GameFieldGuiHandler gameControlHandler;
	private NeighbourFinder neighbourFinder;
	

	// Zu Testzwecken
	public static int FRAME = 0;
	public static long STARTTIME;

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
				checkRules(cell, neighbours, cellsToBearOrKill);
			}
		}

		if (cellsToBearOrKill.isEmpty()) {
			gameIsOngoing = false;
		}

		return cellsToBearOrKill;
	}

	private void checkRules(Cell cell, int neighbours,
			List<Cell> cellsToBearOrKill) {
		if (!cell.isAlive() && gameRules.isCellBorn(neighbours)) {
			cellsToBearOrKill.add(cell);
		} else if (cell.isAlive() && !gameRules.isCellStayingAlive(neighbours)) {
			cellsToBearOrKill.add(cell);
		}

	}

	private int countLivingNeighbours(int row, int column) {
		int livingNeighbours = 0;
		List<Cell> neighbours = getNeighbours(row, column);
		for (Cell neighbourCell : neighbours) {
			if (neighbourCell.isAlive()) {
				livingNeighbours++;
			}
		}
		return livingNeighbours;
	}

	private List<Cell> getNeighbours(int row, int column) {
		return neighbourFinder.getNeighbours(row, column, cellGrid);
	}

	public void calculateNextGeneration() {
		List<Cell> cellsToChange = findCellsToBearOrKill();

		for (Cell cell : cellsToChange) {
			if (cell.isAlive()) {
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
		int cellGeneration = 1;
		STARTTIME = System.currentTimeMillis();
		while (gameIsOngoing) {
			try {
				Thread.sleep(getSettedTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			gameControlHandler.updateGameFieldGui(cellGrid.getCellArray(), cellGeneration);
			cellGeneration++;
			calculateNextGeneration();

		}
		System.out.println((System.currentTimeMillis() - STARTTIME) / 1000);
		new GameFinishedGui();
		while(true){
			gameControlHandler.updateGameFieldGui(cellGrid.getCellArray(), cellGeneration);
		}
	}

	private long getSettedTime() {
		return (1000 * gameControlHandler.getSliderPosition()) / 100;
	}

}
