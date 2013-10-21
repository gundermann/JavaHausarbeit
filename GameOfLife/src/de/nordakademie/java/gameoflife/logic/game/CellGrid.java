package de.nordakademie.java.gameoflife.logic.game;

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

	public Cell[][] getCellArray() {
		return cellArray;
	}


}
