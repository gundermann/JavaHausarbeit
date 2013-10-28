package de.nordakademie.java.gameoflife.utils;

import java.util.ArrayList;
import java.util.List;

import de.nordakademie.java.gameoflife.business.Cell;
import de.nordakademie.java.gameoflife.business.CellGrid;

public class NeighbourFinder {

	private CellGrid cellGrid;
	private boolean isGridBorderDead;

	public NeighbourFinder(CellGrid cellGrid, boolean isGridBorderDead) {
		this.cellGrid = cellGrid;
		this.isGridBorderDead = isGridBorderDead;

	}

	public List<Cell> getNeighbours(Cell cell) {
		List<Cell> neighbours = new ArrayList<Cell>();

		neighbours.add(getNeighbourAtNorth(cell));
		neighbours.add(getNeighbourAtNorthEast(cell));
		neighbours.add(getNeighbourAtEast(cell));
		neighbours.add(getNeighbourAtSouthEast(cell));
		neighbours.add(getNeighbourAtSouth(cell));
		neighbours.add(getNeighbourAtSouthWest(cell));
		neighbours.add(getNeighbourAtWest(cell));
		neighbours.add(getNeighbourAtNorthWest(cell));

		for (Cell neighbour : neighbours) {
			if (neighbour == null) {
				neighbours.set(neighbours.indexOf(neighbour), new Cell());
			}
		}
		return neighbours;
	}

	private Cell getNeighbourAtNorth(Cell cell) {
		int row = cellGrid.getRowOfCell(cell);
		int column = cellGrid.getColumnOfCell(cell);
		Cell neighbour = getNeighbour(row - 1, column);

		if (neighbour == null && !isGridBorderDead) {
			neighbour = getNeighbour(cellGrid.getRowCount() - 1, column);
		}
		return neighbour;
	}

	private Cell getNeighbourAtNorthEast(Cell cell) {
		Cell neighbour;
		Cell neighbourAtNorth = getNeighbourAtNorth(cell);
		if (neighbourAtNorth != null) {
			neighbour = getNeighbourAtEast(neighbourAtNorth);
		} else {
			neighbour = null;
		}
		return neighbour;
	}

	private Cell getNeighbourAtEast(Cell cell) {
		int row = cellGrid.getRowOfCell(cell);
		int column = cellGrid.getColumnOfCell(cell);
		Cell neighbour = getNeighbour(row, column + 1);

		if (neighbour == null && !isGridBorderDead) {
			neighbour = getNeighbour(row, 0);
		}
		return neighbour;
	}

	private Cell getNeighbourAtSouthEast(Cell cell) {
		Cell neighbour;
		Cell neighbourAtSouth = getNeighbourAtSouth(cell);
		if (neighbourAtSouth != null) {
			neighbour = getNeighbourAtEast(neighbourAtSouth);
		} else {
			neighbour = null;
		}
		return neighbour;
	}

	private Cell getNeighbourAtSouth(Cell cell) {
		int row = cellGrid.getRowOfCell(cell);
		int column = cellGrid.getColumnOfCell(cell);
		Cell neighbour = getNeighbour(row + 1, column);

		if (neighbour == null && !isGridBorderDead) {
			neighbour = getNeighbour(0, column);
		}
		return neighbour;
	}

	private Cell getNeighbourAtSouthWest(Cell cell) {
		Cell neighbour;
		Cell neighbourAtSouth = getNeighbourAtSouth(cell);
		if (neighbourAtSouth != null) {
			neighbour = getNeighbourAtWest(neighbourAtSouth);
		} else {
			neighbour = null;
		}
		return neighbour;
	}

	private Cell getNeighbourAtWest(Cell cell) {
		int row = cellGrid.getRowOfCell(cell);
		int column = cellGrid.getColumnOfCell(cell);
		Cell neighbour = getNeighbour(row, column - 1);

		if (neighbour == null && !isGridBorderDead) {
			neighbour = getNeighbour(row, cellGrid.getColumnCount() - 1);
		}
		return neighbour;
	}

	private Cell getNeighbourAtNorthWest(Cell cell) {
		Cell neighbour;
		Cell neighbourAtNorth = getNeighbourAtNorth(cell);
		if (neighbourAtNorth != null) {
			neighbour = getNeighbourAtWest(neighbourAtNorth);
		} else {
			neighbour = null;
		}
		return neighbour;
	}

	private Cell getNeighbour(int row, int column) {
		Cell neighbour = cellGrid.getCellAtCoordinates(row, column);
		return neighbour;
	}
}
