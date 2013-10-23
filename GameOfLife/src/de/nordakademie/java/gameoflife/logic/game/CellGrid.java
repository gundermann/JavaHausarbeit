package de.nordakademie.java.gameoflife.logic.game;

import java.util.ArrayList;
import java.util.List;

/*
 * Diese Klasse bildet das Spielfeld innerhalb des Programms ab.
 * Die Zellen werden hier geboren bzw. getötet. 
 * Weiterhin wird über diese Klasse nach Nachbarn gesucht.
 * 
 * @author niels.gundermann
 */
public class CellGrid {
	
	private static int rows;
	private static int columns;
	private static Cell[][] cellArray;
	
	public CellGrid(int rows, int columns) {
		CellGrid.rows = rows;
		CellGrid.columns = columns;
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

	public static void bearCells(List<Cell> cellsToBear) {
		for(int row = 0; row < rows; row++){
			for(int column = 0; column < columns; column++){
				if(cellsToBear.contains(cellArray[row][column])){
					cellArray[row][column].bear();
					cellsToBear.remove(cellArray[row][column]);
				}
			}
		}
	}
	
	public static void killCells(List<Cell> cellsToKill) {
		for(int row = 0; row < rows; row++){
			for(int column = 0; column < columns; column++){
				if(cellsToKill.contains(cellArray[row][column])){
					cellArray[row][column].killYourself();
					cellsToKill.remove(cellArray[row][column]);
				}
			}
		}
	}

	public static int getColumnCount() {
		return columns;
	}

	public static int getRowCount() {
		return rows;
	}

	public static Cell[][] getCellGrid() {
		return cellArray;
	}

	public static List<Cell> getCells() {
		List<Cell> cells = new ArrayList<Cell>();
		for(int row = 0; row < rows; row++){
			for(int column = 0; column < columns; column++){
					cells.add(getCellAtPosition(row, column));
				}
			}
		return cells;
	}

	public static Cell getCellAtPosition(int row, int column) throws ArrayIndexOutOfBoundsException{
		Cell cellAtPosition;
		try {
			cellAtPosition = cellArray[row][column];
		} catch (ArrayIndexOutOfBoundsException borderOverflow) {
			cellAtPosition = null;
		}
		return cellAtPosition;
	}

	public static List<Cell> getNeighbours(Cell cell) {
		List<Cell> neighbours = new ArrayList<Cell>();
		
		neighbours.add(getNeighbourAtNorth(cell));
		neighbours.add(getNeighbourAtNorthEast(cell));
		neighbours.add(getNeighbourAtEast(cell));
		neighbours.add(getNeighbourAtSouthEast(cell));
		neighbours.add(getNeighbourAtSouth(cell));
		neighbours.add(getNeighbourAtSouthWest(cell));
		neighbours.add(getNeighbourAtWest(cell));
		neighbours.add(getNeighbourAtNorthWest(cell));

		for(Cell neighbourCell : neighbours){
			if(neighbourCell == null){
				int currentIndex = neighbours.indexOf(neighbourCell);
				neighbours.set(currentIndex, new Cell());
			}
		}
		
		return neighbours;
	}

	
	private static Cell getNeighbourAtNorth(Cell cell) {
		int row = getRowOfCell(cell);
		int column = getColumnOfCell(cell);
		Cell neighbour = getNeighbour(row-1, column, rows-1, column);
		return neighbour == cell ? null : neighbour;
	}
	
	private static Cell getNeighbourAtNorthEast(Cell cell) {
		Cell neighbourAtNorth = getNeighbourAtNorth(cell);
		Cell neighbour = getNeighbourAtEast(neighbourAtNorth);
		return neighbour;
	}
	
	private static Cell getNeighbourAtEast(Cell cell) {
		int row = getRowOfCell(cell);
		int column = getColumnOfCell(cell);
		Cell neighbour = getNeighbour(row, column+1, row, 0);
		return neighbour == cell ? null : neighbour;
	}
	
	private static Cell getNeighbourAtSouthEast(Cell cell) {
		Cell neighbourAtSouth = getNeighbourAtSouth(cell);
		Cell neighbour = getNeighbourAtEast(neighbourAtSouth);
		return neighbour;
	}
	
	private static Cell getNeighbourAtSouth(Cell cell) {
		int row = getRowOfCell(cell);
		int column = getColumnOfCell(cell);
		Cell neighbour = getNeighbour(row+1, column, 0, column);
		return neighbour == cell ? null : neighbour;
	}
	
	private static Cell getNeighbourAtSouthWest(Cell cell) {
		Cell neighbourAtSouth = getNeighbourAtSouth(cell);
		Cell neighbour = getNeighbourAtWest(neighbourAtSouth);
		return neighbour;
	}
	
	private static Cell getNeighbourAtWest(Cell cell) {
		int row = getRowOfCell(cell);
		int column = getColumnOfCell(cell);
		Cell neighbour = getNeighbour(row, column-1, row, columns-1);
		return neighbour == cell ? null : neighbour;
	}
	
	private static Cell getNeighbourAtNorthWest(Cell cell) {
		Cell neighbourAtNorth = getNeighbourAtNorth(cell);
		Cell neighbour = getNeighbourAtWest(neighbourAtNorth);
		return neighbour;
	}
	
	private static Cell getNeighbour(int row, int column, int rowForBorderoverflow, int columnForBorderoverflow){
		Cell neighbour = getCellAtPosition(row, column);
		if(neighbour == null && !GamePad.borderRules.isGridBoarderDead()){
			neighbour =  getCellAtPosition(rowForBorderoverflow, columnForBorderoverflow);
		}
		return neighbour;
	}

	private static int getColumnOfCell(Cell cell) {
		int columnOfCell = -1;
		for(int row = 0; row < rows; row++){
			for(int column = 0; column < columns; column++){
				if(cell == cellArray[row][column]){
						columnOfCell = column;
						break;
					}
				}
			}
		return columnOfCell;
	}

	private static int getRowOfCell(Cell cell) {
		int rowOfCell = -1;
		for(int row = 0; row < rows; row++){
			for(int column = 0; column < columns; column++){
					if(cell == cellArray[row][column]){
						rowOfCell = row;
						break;
					}
				}
			}
		return rowOfCell;
	}

	public static Integer[][] convertCellGridToIntegerArray() {
		Integer[][] integerArray = new Integer[rows][columns];
		for(int row = 0; row < rows; row++){
			for(int column = 0; column < columns; column++){
				integerArray[row][column] = getNumericFormBoolean(getCellAtPosition(row, column).isAlive());
			}
		}
		return integerArray;
	}

	private static Integer getNumericFormBoolean(boolean isAlive) {
		Integer value = 0;
		if(isAlive){
			value = 1;
		}
		return value;
	}

	private static void checkCellAliveAndAddSetArrayValue(Cell cell, Integer arrayValue) {
		if(cell.isAlive()){
			arrayValue = 1;
		}
		else{
			arrayValue = 0;
		}
	}


}
