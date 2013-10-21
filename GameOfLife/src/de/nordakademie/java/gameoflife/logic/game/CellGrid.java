package de.nordakademie.java.gameoflife.logic.game;

import java.util.ArrayList;
import java.util.List;

public class CellGrid {
	
	private int rows;
	private int columns;
	private Cell[][] cellArray;
	
	public CellGrid(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		initCellGrid();
	}

	private void initCellGrid() {
		cellArray = new Cell[rows][columns];
		
		for(int row = 0; row < rows; row++){
			for(int column = 0; column < columns; column++){
				cellArray[row][column] = new Cell();
			}
		}
	}

	public void bearCells(List<Cell> cellsToBear) {
		for(int row = 0; row < cellArray.length; row++){
			for(int column = 0; column < cellArray[row].length; column++){
				if(cellsToBear.contains(cellArray[row][column])){
					cellArray[row][column].bear();
					cellsToBear.remove(cellArray[row][column]);
				}
			}
		}
	}
	
	public void killCells(List<Cell> cellsToKill) {
		for(int row = 0; row < cellArray.length; row++){
			for(int column = 0; column < cellArray[row].length; column++){
				if(cellsToKill.contains(cellArray[row][column])){
					cellArray[row][column].killYourself();
					cellsToKill.remove(cellArray[row][column]);
				}
			}
		}
	}

	public int getColumnCount() {
		return columns;
	}

	public int getRowCount() {
		return rows;
	}

	public Cell[][] getCellGrid() {
		return cellArray;
	}

	public List<Cell> getCells() {
		List<Cell> cells = new ArrayList<Cell>();
		
		for(int row = 0; row < cellArray.length; row++){
			for(int column = 0; column < cellArray[row].length; column++){
					cells.add(cellArray[row][column]);
				}
			}
		
		return cells;
	}

	public Cell getCellAtPosition(int row, int column) throws ArrayIndexOutOfBoundsException{
		try {
			return cellArray[row][column];
		} catch (ArrayIndexOutOfBoundsException borderOverflow) {
			return null;
		}
	}

	public List<Cell> getNeighbours(Cell cell, boolean lookOverBorder) {
		List<Cell> neighbours = new ArrayList<Cell>();
		
		neighbours.add(getNeighbourAtNorth(cell, lookOverBorder));
		neighbours.add(getNeighbourAtNorthEast(cell, lookOverBorder));
		neighbours.add(getNeighbourAtEast(cell, lookOverBorder));
		neighbours.add(getNeighbourAtSouthEast(cell, lookOverBorder));
		neighbours.add(getNeighbourAtSouth(cell, lookOverBorder));
		neighbours.add(getNeighbourAtSouthWest(cell, lookOverBorder));
		neighbours.add(getNeighbourAtWest(cell, lookOverBorder));
		neighbours.add(getNeighbourAtNorthWest(cell, lookOverBorder));
		
		return neighbours;
	}

	private Cell getNeighbourAtNorth(Cell cell, boolean lookOverBorder) {
		int row = getRowOfCell(cell);
		int column = getColumnOfCell(cell);
		Cell neighbour = getNeighbour(lookOverBorder, row-1, column, rows-1, column);
		return neighbour == cell ? null : neighbour;
	}
	
	private Cell getNeighbourAtNorthEast(Cell cell, boolean lookOverBorder) {
		Cell neighbourAtNorth = getNeighbourAtNorth(cell, lookOverBorder);
		Cell neighbour = getNeighbourAtEast(neighbourAtNorth, lookOverBorder);
		return neighbour == cell ? null : neighbour;
	}
	
	private Cell getNeighbourAtEast(Cell cell, boolean lookOverBorder) {
		int row = getRowOfCell(cell);
		int column = getColumnOfCell(cell);
		Cell neighbour = getNeighbour(lookOverBorder, row, column+1, row, 0);
		return neighbour == cell ? null : neighbour;
	}
	
	private Cell getNeighbourAtSouthEast(Cell cell, boolean lookOverBorder) {
		Cell neighbourAtSouth = getNeighbourAtSouth(cell, lookOverBorder);
		Cell neighbour = getNeighbourAtEast(neighbourAtSouth, lookOverBorder);
		return neighbour == cell ? null : neighbour;
	}
	
	private Cell getNeighbourAtSouth(Cell cell, boolean lookOverBorder) {
		int row = getRowOfCell(cell);
		int column = getColumnOfCell(cell);
		Cell neighbour = getNeighbour(lookOverBorder, row+1, column, 0, column);
		return neighbour == cell ? null : neighbour;
	}
	
	private Cell getNeighbourAtSouthWest(Cell cell, boolean lookOverBorder) {
		Cell neighbourAtSouth = getNeighbourAtSouth(cell, lookOverBorder);
		Cell neighbour = getNeighbourAtWest(neighbourAtSouth, lookOverBorder);
		return neighbour == cell ? null : neighbour;
	}
	
	private Cell getNeighbourAtWest(Cell cell, boolean lookOverBorder) {
		int row = getRowOfCell(cell);
		int column = getColumnOfCell(cell);
		Cell neighbour = getNeighbour(lookOverBorder, row, column-1, row, columns-1);
		return neighbour == cell ? null : neighbour;
	}
	
	private Cell getNeighbourAtNorthWest(Cell cell, boolean lookOverBorder) {
		Cell neighbourAtNorth = getNeighbourAtNorth(cell, lookOverBorder);
		Cell neighbour = getNeighbourAtWest(neighbourAtNorth, lookOverBorder);
		return neighbour == cell ? null : neighbour;
	}
	
	private Cell getNeighbour(boolean lookOverBorder, int row, int column, int rowForBorderoverflow, int columnForBorderoverflow){
		Cell neighbour = getCellAtPosition(row, column);
		if(neighbour == null && lookOverBorder){
			neighbour =  getCellAtPosition(rowForBorderoverflow, columnForBorderoverflow);
		}
		return neighbour;
	}

	private int getColumnOfCell(Cell cell) {
		int columnOfCell = -1;
		for(int row = 0; row < cellArray.length; row++){
			for(int column = 0; column < cellArray[row].length; column++){
					if(cell == cellArray[row][column]){
						columnOfCell = column;
						break;
					}
				}
			}
		return columnOfCell;
	}

	private int getRowOfCell(Cell cell) {
		int rowOfCell = -1;
		for(int row = 0; row < cellArray.length; row++){
			for(int column = 0; column < cellArray[row].length; column++){
					if(cell == cellArray[row][column]){
						rowOfCell = row;
						break;
					}
				}
			}
		return rowOfCell;
	}


}
