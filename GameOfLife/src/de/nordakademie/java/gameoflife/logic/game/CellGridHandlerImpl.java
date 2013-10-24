package de.nordakademie.java.gameoflife.logic.game;

import java.util.ArrayList;
import java.util.List;

public class CellGridHandlerImpl implements CellGridHandler, NeighbourFinder{

	private CellGrid cellGrid;
	private boolean isGridBorderDead;
	
	public CellGridHandlerImpl(Integer[][] initinalCellArray, boolean isGridBorderDead){
		initCellGrid(initinalCellArray);
		this.isGridBorderDead = isGridBorderDead;
	}
	
	private void initCellGrid(Integer[][] initinalCellArray) {
		int rows = initinalCellArray.length;
		int columns = initinalCellArray[0].length;
		this.cellGrid = new CellGrid(rows, columns);
		List<Cell> livingCells = new ArrayList<Cell>();
		for(int row = 0; row < rows; row++){
			for(int column = 0; column < columns; column++){
				Cell currentCell = getCellAtPosition(row, column);
				int currentSetup = initinalCellArray[row][column];
				checkInitinalSetupAndMarkCellToBear(currentSetup, currentCell, livingCells);
				}
			}
		bearCells(livingCells);
	}
	
	private void checkInitinalSetupAndMarkCellToBear(Integer setup, Cell currentCell, List<Cell> livingCells) {
		if(setup == 1){
			livingCells.add(currentCell);
		}
	}
	
	@Override
	public List<Cell> getNeighbours(Cell cell, CellGrid cellGrid) {
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
	
	private Cell getNeighbourAtNorth(Cell cell) {
		int row = getRowOfCell(cell);
		int column = getColumnOfCell(cell);
		Cell neighbour = getNeighbour(row-1, column, cellGrid.getRowCount()-1, column);
		return neighbour == cell ? null : neighbour;
	}
	
	
	private Cell getNeighbourAtNorthEast(Cell cell) {
		Cell neighbourAtNorth = getNeighbourAtNorth(cell);
		Cell neighbour = getNeighbourAtEast(neighbourAtNorth);
		return neighbour;
	}
	
	private Cell getNeighbourAtEast(Cell cell) {
		int row = getRowOfCell(cell);
		int column = getColumnOfCell(cell);
		Cell neighbour = getNeighbour(row, column+1, row, 0);
		return neighbour == cell ? null : neighbour;
	}
	
	private Cell getNeighbourAtSouthEast(Cell cell) {
		Cell neighbourAtSouth = getNeighbourAtSouth(cell);
		Cell neighbour = getNeighbourAtEast(neighbourAtSouth);
		return neighbour;
	}
	
	private Cell getNeighbourAtSouth(Cell cell) {
		int row = getRowOfCell(cell);
		int column = getColumnOfCell(cell);
		Cell neighbour = getNeighbour(row+1, column, 0, column);
		return neighbour == cell ? null : neighbour;
	}
	
	private Cell getNeighbourAtSouthWest(Cell cell) {
		Cell neighbourAtSouth = getNeighbourAtSouth(cell);
		Cell neighbour = getNeighbourAtWest(neighbourAtSouth);
		return neighbour;
	}
	
	private Cell getNeighbourAtWest(Cell cell) {
		int row = getRowOfCell(cell);
		int column = getColumnOfCell(cell);
		Cell neighbour = getNeighbour(row, column-1, row, cellGrid.getColumnCount()-1);
		return neighbour == cell ? null : neighbour;
	}
	
	private Cell getNeighbourAtNorthWest(Cell cell) {
		Cell neighbourAtNorth = getNeighbourAtNorth(cell);
		Cell neighbour = getNeighbourAtWest(neighbourAtNorth);
		return neighbour;
	}
	
	private Cell getNeighbour(int row, int column, int rowForBorderoverflow, int columnForBorderoverflow){
		Cell neighbour = getCellAtPosition(row, column);
		if(neighbour == null && !isGridBorderDead){
			neighbour =  getCellAtPosition(rowForBorderoverflow, columnForBorderoverflow);
		}
		return neighbour;
	}
	
	@Override
	public Cell getCellAtPosition(int row, int column){
		return cellGrid.getCellAtPosition(row, column);
	}

	public int getColumnOfCell(Cell cell) {
		return cellGrid.getColumnOfCell(cell);
	}

	public int getRowOfCell(Cell cell) {
		return cellGrid.getRowOfCell(cell);
	}

	@Override
	public void bearCells(List<Cell> cellsToBear) {
		for(Cell cell : getCellsAsList()){
			if(cellsToBear.contains(cell)){
				cellGrid.bearCell(cell);
			}
		}
	}

	@Override
	public void killCells(List<Cell> cellsToKill) {
		for(Cell cell : getCellsAsList()){
			if(cellsToKill.contains(cell)){
				cellGrid.killCell(cell);
			}
		}
	}
	
	@Override
	public List<Cell> getCellsAsList(){
		return cellGrid.getCellsAsList();
	}
}
