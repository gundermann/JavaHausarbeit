package de.nordakademie.java.gameoflife.utils;

import java.util.ArrayList;
import java.util.List;

import de.nordakademie.java.gameoflife.business.Cell;
import de.nordakademie.java.gameoflife.business.CellGrid;

public class NeighbourFinder {

	private static CellGrid cellGrid;
	private static boolean isGridBorderDead;

	public static List<Cell> getNeighbours(Cell cell, CellGrid cellGrid,
			boolean isGridBorderDead) {
		NeighbourFinder.cellGrid = cellGrid;
		NeighbourFinder.isGridBorderDead = isGridBorderDead;
		List<Cell> neighbours = new ArrayList<Cell>();

		neighbours.add(getNeighbourAtNorth(cell));
		neighbours.add(getNeighbourAtNorthEast(cell));
		neighbours.add(getNeighbourAtEast(cell));
		neighbours.add(getNeighbourAtSouthEast(cell));
		neighbours.add(getNeighbourAtSouth(cell));
		neighbours.add(getNeighbourAtSouthWest(cell));
		neighbours.add(getNeighbourAtWest(cell));
		neighbours.add(getNeighbourAtNorthWest(cell));

		// for (Cell neighbourCell : neighbours) {
		// if (neighbourCell == null) {
		// int currentIndex = neighbours.indexOf(neighbourCell);
		// neighbours.set(currentIndex, new Cell());
		// }
		// }

		return neighbours;
	}

	private static Cell getNeighbourAtNorth(Cell cell) {
		int row = cellGrid.getRowOfCell(cell);
		int column = cellGrid.getColumnOfCell(cell);
		Cell neighbour = getNeighbour(row - 1, column);

		if (neighbour == null && !isGridBorderDead) {
			neighbour = getNeighbour(cellGrid.getRowCount() - 1, column);
		}

		// if (neighbour == cell) {
		// neighbour = new Cell();
		// }
		return neighbour;
	}

	private static Cell getNeighbourAtNorthEast(Cell cell) {
		Cell neighbourAtNorth = getNeighbourAtNorth(cell);
		Cell neighbour = getNeighbourAtEast(neighbourAtNorth);
		return neighbour;
	}

	private static Cell getNeighbourAtEast(Cell cell) {
		int row = cellGrid.getRowOfCell(cell);
		int column = cellGrid.getColumnOfCell(cell);
		Cell neighbour = getNeighbour(row, column + 1);

		if (neighbour == null && !isGridBorderDead) {
			neighbour = getNeighbour(row, 0);
		}

		// if (neighbour == cell) {
		// neighbour = new Cell();
		// }
		return neighbour;
	}

	private static Cell getNeighbourAtSouthEast(Cell cell) {
		Cell neighbourAtSouth = getNeighbourAtSouth(cell);
		Cell neighbour = getNeighbourAtEast(neighbourAtSouth);
		return neighbour;
	}

	private static Cell getNeighbourAtSouth(Cell cell) {
		int row = cellGrid.getRowOfCell(cell);
		int column = cellGrid.getColumnOfCell(cell);
		Cell neighbour = getNeighbour(row + 1, column);

		if (neighbour == null && !isGridBorderDead) {
			neighbour = getNeighbour(0, column);
		}

		// if (neighbour == cell) {
		// neighbour = new Cell();
		// }
		return neighbour;
	}

	private static Cell getNeighbourAtSouthWest(Cell cell) {
		Cell neighbourAtSouth = getNeighbourAtSouth(cell);
		Cell neighbour = getNeighbourAtWest(neighbourAtSouth);
		return neighbour;
	}

	private static Cell getNeighbourAtWest(Cell cell) {
		int row = cellGrid.getRowOfCell(cell);
		int column = cellGrid.getColumnOfCell(cell);
		Cell neighbour = getNeighbour(row, column - 1);

		if (neighbour == null && !isGridBorderDead) {
			neighbour = getNeighbour(row, cellGrid.getColumnCount() - 1);
		}

		// if (neighbour == cell) {
		// neighbour = new Cell();
		// }
		return neighbour;
	}

	private static Cell getNeighbourAtNorthWest(Cell cell) {
		Cell neighbourAtNorth = getNeighbourAtNorth(cell);
		Cell neighbour = getNeighbourAtWest(neighbourAtNorth);
		return neighbour;
	}

	private static Cell getNeighbour(int row, int column) {
		Cell neighbour = cellGrid.getCellAtCoordinates(row, column);
		return neighbour;
	}
}
