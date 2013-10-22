package de.nordakademie.java.gameoflife.logic.game;

import java.util.ArrayList;
import java.util.List;

import de.nordakademie.java.gameoflife.rules.game.GameRule;

public class GamePad {
	
	private CellGrid grid;
	private GameRule rules;
	private int generation = 1;

	public GamePad(Integer[][] initinalArray, GameRule rules) {
		int rows = initinalArray.length;
		int columns = initinalArray[0].length;
		grid = new CellGrid(rows, columns);
		this.rules = rules;
	}
	
	public List<Cell> findCellsToBear(){
		List<Cell> cellsToBear = new ArrayList<Cell>();
		
		for(Cell cell : grid.getCells()){
			if(!cell.isAlive()){
				int neighbours = countLivingNeighbours(cell, grid.getCells());
//				if(rules.getNeighboursToBear(neighbours)){
//					cellsToBear.add(cell);
//				}
			}
		}
		
		return cellsToBear;
	}

	private int countLivingNeighbours(Cell cell, List<Cell> cells) {
		int numberOfLivingNeighbours = 0;
		
		List<Cell> neighbours = findNeighbours(cell, cells);
		
		for(Cell neighbourCell : neighbours){
			if(neighbourCell.isAlive())
				numberOfLivingNeighbours++;
		}
		
		return numberOfLivingNeighbours;
	}

	public List<Cell> findNeighbours(Cell cell, List<Cell> cells) {
		return null;
	}

	public void calculateNextGeneration() {
		List<Cell> cellsToBear = new ArrayList<Cell>();
		List<Cell> cellsToKill = new ArrayList<Cell>();
		
		grid.bearCells(cellsToBear);
		grid.killCells(cellsToKill);
		
		nextGeneration();
	}

	private void nextGeneration() {
		generation = generation + 1 ;
	}

	public CellGrid getCellGrid() {
		return grid;
	}

	public GameRule getRules() {
		return rules;
	}

	public int getGeneration() {
		return generation;
	}



}
