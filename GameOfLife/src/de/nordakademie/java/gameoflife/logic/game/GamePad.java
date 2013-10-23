package de.nordakademie.java.gameoflife.logic.game;

import java.util.ArrayList;
import java.util.List;

import de.nordakademie.java.gameoflife.rules.border.BorderRule;
import de.nordakademie.java.gameoflife.rules.game.GameRule;

public class GamePad {
	
	//konstanten
	public static GameRule gameRules;
	public static BorderRule borderRules;
	private int generation = 1;

	public GamePad(Integer[][] initinalArray, GameRule gameRules, BorderRule borderRules) {
		initCellGrid(initinalArray);
		GamePad.gameRules = gameRules;
		GamePad.borderRules = borderRules;
	}
	
	private void initCellGrid(Integer[][] initinalArray) {
		int rows = initinalArray.length;
		int columns = initinalArray[0].length;
		new CellGrid(rows, columns);
		List<Cell> livingCells = new ArrayList<Cell>();
		for(int row = 0; row < rows; row++){
			for(int column = 0; column < columns; column++){
				Cell currentCell = CellGrid.getCellAtPosition(row, column);
				int currentSetup = initinalArray[row][column];
				checkInitinalSetupAndBearCell(currentSetup, currentCell, livingCells);
				}
			}
		CellGrid.bearCells(livingCells);
	}

	private void checkInitinalSetupAndBearCell(Integer setup, Cell currentCell, List<Cell> livingCells) {
		if(setup == 1){
			livingCells.add(currentCell);
		}
	}

	public List<Cell> findCellsToBear(){
		List<Cell> cellsToBear = new ArrayList<Cell>();
		for(Cell cell : CellGrid.getCells()){
			if(!cell.isAlive()){
				checkRulesForBearingAndMarkCell(cell, cellsToBear);
			}
		}
		
		return cellsToBear;
	}

	private void checkRulesForBearingAndMarkCell(Cell cell, List<Cell> cellsToBear) {
		int neighbours = countLivingNeighbours(cell);
		if(gameRules.isCellBorn(neighbours)){
			cellsToBear.add(cell);
		}
	}

	public List<Cell> findCellsToKill() {
		List<Cell> cellsToKill = new ArrayList<Cell>();
		for(Cell cell : CellGrid.getCells()){
			if(cell.isAlive()){
				checkRulesForKillingAndMarkCell(cell, cellsToKill);
			}
		}
		return cellsToKill;
	}

	private void checkRulesForKillingAndMarkCell(Cell cell,	List<Cell> cellsToKill) {
		int neighbours = countLivingNeighbours(cell);
		if(!gameRules.isCellStayingAlive(neighbours)){
			cellsToKill.add(cell);
		}
	}

	private int countLivingNeighbours(Cell cell) {
		int livingNeighbours = 0;
		List<Cell> neighbours = CellGrid.getNeighbours(cell);
		for(Cell neighbourCell : neighbours){
			if(neighbourCell.isAlive())
				livingNeighbours++;
		}
		return livingNeighbours;
	}

	public void calculateNextGeneration() {
		List<Cell> cellsToBear = findCellsToBear();
		List<Cell> cellsToKill = findCellsToKill();
		
		CellGrid.bearCells(cellsToBear);
		CellGrid.killCells(cellsToKill);
		
		generation = generation + 1 ;
	}

	public int getGeneration() {
		return generation;
	}
}
