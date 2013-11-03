package de.nordakademie.java.gameoflife.business;

import java.util.ArrayList;
import java.util.List;

import de.nordakademie.java.gameoflife.business.rules.BorderRule;
import de.nordakademie.java.gameoflife.business.rules.GameRule;
import de.nordakademie.java.gameoflife.gui.GameFinishedGui;
import de.nordakademie.java.gameoflife.utils.NeighbourFinder;

public class GameController implements Runnable {

	private final GameRule gameRules;
	private final BorderRule borderRules;
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
		this.borderRules = borderRules;
		neighbourFinder = new NeighbourFinder(borderRules.isGridBorderDead());
	}

	public List<Cell> findCellsToBearOrKill() {
		List<Cell> cellsToBearOrKill = new ArrayList<Cell>();
		for (Cell cell : cellGrid.getCellsAsList()) {
			if (!cell.isAlive()) {
				checkRulesForBearingAndMarkCell(cell, cellsToBearOrKill);
			} else {
				checkRulesForKillingAndMarkCell(cell, cellsToBearOrKill);
			}
		}

		if (cellsToBearOrKill.isEmpty()) {
			gameIsOngoing = false;
		}

		return cellsToBearOrKill;
	}

	private void checkRulesForBearingAndMarkCell(Cell cell,
			List<Cell> cellsToBear) {
		int neighbours = countLivingNeighbours(cell);
		if (gameRules.isCellBorn(neighbours)) {
			cellsToBear.add(cell);
		}
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
		STARTTIME = System.currentTimeMillis();
		while (gameIsOngoing) {
			try {
				Thread.sleep(getSettedTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			gameControlHandler.updateGameFieldGui(cellGrid.getCellArray());
			calculateNextGeneration();

			FRAME++;

			// if (System.currentTimeMillis() - STARTTIME > 1000) {
			// System.out.println(FRAME);
			// System.exit(0);
			// }
		}
		new GameFinishedGui();
	}

	private long getSettedTime() {
		return (1000 * gameControlHandler.getSliderPosition()) / 100;
	}

}
