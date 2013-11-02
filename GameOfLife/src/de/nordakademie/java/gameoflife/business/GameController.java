package de.nordakademie.java.gameoflife.business;

import java.util.ArrayList;
import java.util.List;

import de.nordakademie.java.gameoflife.business.rules.BorderRule;
import de.nordakademie.java.gameoflife.business.rules.GameRule;
import de.nordakademie.java.gameoflife.utils.NeighbourFinder;

public class GameController implements Runnable {

	private final GameRule gameRules;
	private final BorderRule borderRules;
	private CellGrid cellGrid;
	private int generation = 1;
	private GameFieldGuiHandler gameControlHandler;
	private NeighbourFinder neighbourFinder;

	// Zu Testzwecken
	// public static int FRAME = 0;
	// public static long STARTTIME;

	public GameController(final CellGrid cellGrid, GameRule gameRules,
			BorderRule borderRules) {
		this.cellGrid = cellGrid;
		this.gameRules = gameRules;
		this.borderRules = borderRules;
		neighbourFinder = new NeighbourFinder(borderRules.isGridBorderDead());
	}

	private boolean isCellGridChanging() {
		return true;
	}

	public List<Cell> findCellsToBear() {
		List<Cell> cellsToBear = new ArrayList<Cell>();
		for (Cell cell : cellGrid.getCellsAsList()) {
			if (!cell.isAlive()) {
				checkRulesForBearingAndMarkCell(cell, cellsToBear);
			}
		}
		return cellsToBear;
	}

	private void checkRulesForBearingAndMarkCell(Cell cell,
			List<Cell> cellsToBear) {
		if (!cell.isAlive()) {
			int neighbours = countLivingNeighbours(cell);
			if (gameRules.isCellBorn(neighbours)) {
				cellsToBear.add(cell);
			}
		}
	}

	public List<Cell> findCellsToKill() {
		List<Cell> cellsToKill = new ArrayList<Cell>();
		for (Cell cell : cellGrid.getCellsAsList()) {
			if (cell.isAlive()) {
				checkRulesForKillingAndMarkCell(cell, cellsToKill);
			}
		}
		return cellsToKill;
	}

	private void checkRulesForKillingAndMarkCell(Cell cell,
			List<Cell> cellsToKill) {
		int neighbours = countLivingNeighbours(cell);
		if (!gameRules.isCellStayingAlive(neighbours)) {
			cellsToKill.add(cell);
		}
	}

	private int countLivingNeighbours(Cell cell) {
		int livingNeighbours = 0;
		List<Cell> neighbours = getNeighbours(cell);
		for (Cell neighbourCell : neighbours) {
			if (neighbourCell.isAlive()) {
				livingNeighbours++;
			}
		}
		return livingNeighbours;
	}

	private List<Cell> getNeighbours(Cell cell) {
		return neighbourFinder.getNeighbours(cell, cellGrid);
	}

	public void calculateNextGeneration() {
		List<Cell> cellsToBear = findCellsToBear();
		List<Cell> cellToKill = findCellsToKill();

		for (Cell cell : cellsToBear) {
			cellGrid.bearCell(cell);
		}

		for (Cell cell : cellToKill) {
			cellGrid.killCell(cell);
		}

		generation = generation + 1;
	}

	public int getGeneration() {
		return generation;
	}

	public GameRule getGameRules() {
		return gameRules;
	}

	public BorderRule getBorderRules() {
		return borderRules;
	}

	public void setGameControlHandler(GameFieldGuiHandler gameFieldGui) {
		gameControlHandler = gameFieldGui;
	}

	@Override
	public void run() {
		// STARTTIME = System.currentTimeMillis();
		while (isCellGridChanging()) {
			try {
				Thread.sleep(getSettedTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			gameControlHandler.updateGameFieldGui(cellGrid.getCellArray());
			calculateNextGeneration();
			// FRAME++;
			//
			// if (System.currentTimeMillis() - STARTTIME > 1000) {
			// System.out.println(FRAME);
			// System.exit(0);
			// }
		}
	}

	private long getSettedTime() {
		return (1000 * gameControlHandler.getSliderPosition()) / 100;
	}

}
