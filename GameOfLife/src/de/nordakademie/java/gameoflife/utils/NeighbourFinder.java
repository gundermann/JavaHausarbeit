package de.nordakademie.java.gameoflife.utils;

import java.util.ArrayList;
import java.util.List;

import de.nordakademie.java.gameoflife.business.Cell;
import de.nordakademie.java.gameoflife.business.CellGrid;

public class NeighbourFinder {

	private CellGrid cellGrid;
	private boolean isGridBorderDead;
	private Cell celltoCheck;
	int[] positionOfCurrentCell;
	int row;
	int column;
	private List<Cell> neighbours = new ArrayList<Cell>();

	public NeighbourFinder(boolean isGridBorderDead) {
		this.isGridBorderDead = isGridBorderDead;

	}

	public List<Cell> getNeighbours(Cell cell, CellGrid cellGrid) {
		this.cellGrid = cellGrid;
		celltoCheck = cell;
		positionOfCurrentCell = cellGrid.getPositionOfCell(cell);
		row = positionOfCurrentCell[0];
		column = positionOfCurrentCell[1];
		neighbours.clear();

		neighbours.add(getNeighbourAtNorth(celltoCheck));
		neighbours.add(getNeighbourAtEast(celltoCheck));
		neighbours.add(getNeighbourAtSouth(celltoCheck));
		neighbours.add(getNeighbourAtWest(celltoCheck));
		neighbours.add(getNeighbourAtNorthEast(celltoCheck));
		neighbours.add(getNeighbourAtNorthWest(celltoCheck));
		neighbours.add(getNeighbourAtSouthEast(celltoCheck));
		neighbours.add(getNeighbourAtSouthWest(celltoCheck));

		for (Cell neighbour : neighbours) {
			if (neighbour == null) {
				neighbours.set(neighbours.indexOf(neighbour), new Cell(-1, -1));
			}
		}
		return neighbours;
	}

	private Cell getNeighbourAtNorth(Cell cell) {

		Cell neighbour = getNeighbour(row - 1, column);

		if (neighbour == null && !isGridBorderDead) {
			neighbour = getNeighbour(cellGrid.getRowCount() - 1, column);
		}
		return neighbour;
	}

	private Cell getNeighbourAtNorthEast(Cell cell) {
		Cell neighbour = getNeighbour(row - 1, column + 1);

		if (neighbour == null && !isGridBorderDead) {
			neighbour = getNeighbour(row - 1, 0);
			if (neighbour == null) {
				neighbour = getNeighbour(cellGrid.getRowCount() - 1, column + 1);
			}
			if (neighbour == null) {
				neighbour = getNeighbour(cellGrid.getRowCount() - 1, 0);
			}
		}
		return neighbour;
	}

	private Cell getNeighbourAtEast(Cell cell) {
		Cell neighbour = getNeighbour(row, column + 1);

		if (neighbour == null && !isGridBorderDead) {
			neighbour = getNeighbour(row, 0);
		}
		return neighbour;
	}

	private Cell getNeighbourAtSouthEast(Cell cell) {
		Cell neighbour = getNeighbour(row + 1, column + 1);

		if (neighbour == null && !isGridBorderDead) {
			neighbour = getNeighbour(row + 1, 0);
			if (neighbour == null) {
				neighbour = getNeighbour(0, column + 1);
			}
			if (neighbour == null) {
				neighbour = getNeighbour(0, 0);
			}
		}
		return neighbour;
	}

	private Cell getNeighbourAtSouth(Cell cell) {
		Cell neighbour = getNeighbour(row + 1, column);

		if (neighbour == null && !isGridBorderDead) {
			neighbour = getNeighbour(0, column);
		}
		return neighbour;
	}

	private Cell getNeighbourAtSouthWest(Cell cell) {
		Cell neighbour = getNeighbour(row + 1, column - 1);

		if (neighbour == null && !isGridBorderDead) {
			neighbour = getNeighbour(row + 1, cellGrid.getColumnCount() - 1);
			if (neighbour == null) {
				neighbour = getNeighbour(0, column - 1);
			}
			if (neighbour == null) {
				neighbour = getNeighbour(0, cellGrid.getColumnCount() - 1);
			}
		}
		return neighbour;
	}

	private Cell getNeighbourAtWest(Cell cell) {
		Cell neighbour = getNeighbour(row, column - 1);

		if (neighbour == null && !isGridBorderDead) {
			neighbour = getNeighbour(row, cellGrid.getColumnCount() - 1);
		}
		return neighbour;
	}

	private Cell getNeighbourAtNorthWest(Cell cell) {
		Cell neighbour = getNeighbour(row - 1, column - 1);

		if (neighbour == null && !isGridBorderDead) {
			neighbour = getNeighbour(row - 1, cellGrid.getColumnCount() - 1);
			if (neighbour == null) {
				neighbour = getNeighbour(cellGrid.getRowCount() - 1, column - 1);
			}
			if (neighbour == null) {
				neighbour = getNeighbour(cellGrid.getRowCount() - 1,
						cellGrid.getColumnCount() - 1);
			}
		}
		return neighbour;
	}

	private Cell getNeighbour(int row, int column) {
		Cell neighbour = cellGrid.getCellAtPosition(row, column);
		return neighbour;
	}
}
