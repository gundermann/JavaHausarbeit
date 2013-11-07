package de.nordakademie.java.gameoflife.utils;

import java.util.ArrayList;
import java.util.List;

import de.nordakademie.java.gameoflife.business.Cell;
import de.nordakademie.java.gameoflife.business.CellGrid;

/**
 * Sucht nach den Nachbarn einer Zelle
 * 
 * @author Niels Gundermann
 */
public class NeighbourFinder {

	private CellGrid cellGrid;
	private boolean isGridBorderDead;
	private int rowOfCurrentCell;
	private int columnOfCurrentCell;

	public NeighbourFinder(boolean isGridBorderDead) {
		this.isGridBorderDead = isGridBorderDead;
	}

	public List<Cell> getNeighbours(int row, int column, CellGrid cellGrid) {
		this.cellGrid = cellGrid;
		rowOfCurrentCell = row;
		columnOfCurrentCell = column;
		List<Cell> neighbours = new ArrayList<Cell>();

		neighbours.add(getNeighbourAtNorth());
		neighbours.add(getNeighbourAtEast());
		neighbours.add(getNeighbourAtSouth());
		neighbours.add(getNeighbourAtWest());
		neighbours.add(getNeighbourAtNorthEast());
		neighbours.add(getNeighbourAtNorthWest());
		neighbours.add(getNeighbourAtSouthEast());
		neighbours.add(getNeighbourAtSouthWest());

		for (Cell neighbour : neighbours) {
			if (neighbour == null) {
				neighbours.set(neighbours.indexOf(neighbour), new Cell());
			}
		}
		return neighbours;
	}

	private Cell getNeighbourAtNorth() {

		Cell neighbour = getNeighbour(rowOfCurrentCell - 1, columnOfCurrentCell);

		if (neighbour == null && !isGridBorderDead) {
			neighbour = getNeighbour(cellGrid.getRowCount() - 1,
					columnOfCurrentCell);
		}
		return neighbour;
	}

	private Cell getNeighbourAtNorthEast() {
		Cell neighbour = getNeighbour(rowOfCurrentCell - 1,
				columnOfCurrentCell + 1);

		if (neighbour == null && !isGridBorderDead) {
			neighbour = getNeighbour(rowOfCurrentCell - 1, 0);
			if (neighbour == null) {
				neighbour = getNeighbour(cellGrid.getRowCount() - 1,
						columnOfCurrentCell + 1);
			}
			if (neighbour == null) {
				neighbour = getNeighbour(cellGrid.getRowCount() - 1, 0);
			}
		}
		return neighbour;
	}

	private Cell getNeighbourAtEast() {
		Cell neighbour = getNeighbour(rowOfCurrentCell, columnOfCurrentCell + 1);

		if (neighbour == null && !isGridBorderDead) {
			neighbour = getNeighbour(rowOfCurrentCell, 0);
		}
		return neighbour;
	}

	private Cell getNeighbourAtSouthEast() {
		Cell neighbour = getNeighbour(rowOfCurrentCell + 1,
				columnOfCurrentCell + 1);

		if (neighbour == null && !isGridBorderDead) {
			neighbour = getNeighbour(rowOfCurrentCell + 1, 0);
			if (neighbour == null) {
				neighbour = getNeighbour(0, columnOfCurrentCell + 1);
			}
			if (neighbour == null) {
				neighbour = getNeighbour(0, 0);
			}
		}
		return neighbour;
	}

	private Cell getNeighbourAtSouth() {
		Cell neighbour = getNeighbour(rowOfCurrentCell + 1, columnOfCurrentCell);

		if (neighbour == null && !isGridBorderDead) {
			neighbour = getNeighbour(0, columnOfCurrentCell);
		}
		return neighbour;
	}

	private Cell getNeighbourAtSouthWest() {
		Cell neighbour = getNeighbour(rowOfCurrentCell + 1,
				columnOfCurrentCell - 1);

		if (neighbour == null && !isGridBorderDead) {
			neighbour = getNeighbour(rowOfCurrentCell + 1,
					cellGrid.getColumnCount() - 1);
			if (neighbour == null) {
				neighbour = getNeighbour(0, columnOfCurrentCell - 1);
			}
			if (neighbour == null) {
				neighbour = getNeighbour(0, cellGrid.getColumnCount() - 1);
			}
		}
		return neighbour;
	}

	private Cell getNeighbourAtWest() {
		Cell neighbour = getNeighbour(rowOfCurrentCell, columnOfCurrentCell - 1);

		if (neighbour == null && !isGridBorderDead) {
			neighbour = getNeighbour(rowOfCurrentCell,
					cellGrid.getColumnCount() - 1);
		}
		return neighbour;
	}

	private Cell getNeighbourAtNorthWest() {
		Cell neighbour = getNeighbour(rowOfCurrentCell - 1,
				columnOfCurrentCell - 1);

		if (neighbour == null && !isGridBorderDead) {
			neighbour = getNeighbour(rowOfCurrentCell - 1,
					cellGrid.getColumnCount() - 1);
			if (neighbour == null) {
				neighbour = getNeighbour(cellGrid.getRowCount() - 1,
						columnOfCurrentCell - 1);
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
