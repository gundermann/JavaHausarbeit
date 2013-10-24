package de.nordakademie.java.gameoflife.logic.game;

import java.util.ArrayList;
import java.util.List;

import de.nordakademie.java.gameoflife.rules.border.BorderRule;
import de.nordakademie.java.gameoflife.rules.game.GameRule;

public class GamePad {
	
	//konstanten
	public static GameRule gameRules;
	public static BorderRule borderRules;
	public CellGridHandler cellGridHandler;
	private int generation = 1;
	private NeighbourFinder neighbourFinder;

	public GamePad(Integer[][] initinalArray, GameRule gameRules, BorderRule borderRules) {
		CellGridHandlerImpl handler = new CellGridHandlerImpl(initinalArray, borderRules.isGridBoarderDead()); //TODO kommt weg
		this.cellGridHandler = handler;
		this.neighbourFinder = handler;
		GamePad.gameRules = gameRules;
		GamePad.borderRules = borderRules;
	}

	public List<Cell> findCellsToBear(){
		List<Cell> cellsToBear = new ArrayList<Cell>();
		for(Cell cell : cellGridHandler.getCellsAsList()){
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
		for(Cell cell : cellGridHandler.getCellsAsList()){
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
		List<Cell> neighbours = neighbourFinder.getNeighbours(cell, new CellGrid(1, 1)); //TODO Nachbarsuche statisch machen
		for(Cell neighbourCell : neighbours){
			if(neighbourCell.isAlive())
				livingNeighbours++;
		}
		return livingNeighbours;
	}

	public void calculateNextGeneration() {
		List<Cell> cellsToBear = findCellsToBear();
		List<Cell> cellsToKill = findCellsToKill();
		
		cellGridHandler.bearCells(cellsToBear);
		cellGridHandler.killCells(cellsToKill);
		
		generation = generation + 1 ;
	}

	public int getGeneration() {
		return generation;
	}
}
