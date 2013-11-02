package de.nordakademie.java.gameoflife.business;

import java.util.ArrayList;
import java.util.List;

import de.nordakademie.java.gameoflife.business.rules.BorderRule;
import de.nordakademie.java.gameoflife.business.rules.GameRule;
import de.nordakademie.java.gameoflife.gui.GameFieldGui;
import de.nordakademie.java.gameoflife.utils.NeighbourFinder;

public class GamePad {

	private final GameRule gameRules;
	private final BorderRule borderRules;
	private final CellGrid cellGrid;
	private int generation = 1;
	private final Runnable gameThread;
	private final NeighbourFinder neighbourFinder;

	public GamePad(CellGrid cellGrid, GameRule gameRules, BorderRule borderRules) {
		this.cellGrid = cellGrid;
		this.gameRules = gameRules;
		this.borderRules = borderRules;
		neighbourFinder = new NeighbourFinder(borderRules.isGridBorderDead());
		final GameFieldGui gameField = new GameFieldGui(cellGrid.getCellArray());
		gameField.repaint();
		// machen wir anders
		gameThread = new Runnable() {

			@Override
			public void run() {
				while (gameField.isVisible()) {
					try {
						Thread.sleep(400);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					calculateNextGeneration();
					gameField.update(GamePad.this.cellGrid.getCellArray());
				}
			}
		};
	}

	public void startGame() {
		new Thread(gameThread).start();
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
		int neighbours = countLivingNeighbours(cell);
		if (gameRules.isCellBorn(neighbours)) {
			cellsToBear.add(cell);
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

}
