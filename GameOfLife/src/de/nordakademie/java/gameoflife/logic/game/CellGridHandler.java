package de.nordakademie.java.gameoflife.logic.game;

import java.util.List;

public interface CellGridHandler {
//	List<Cell> getNeighbours(Cell cell);
	Cell getCellAtPosition(int row, int column);
	void bearCells(List<Cell> cellsToBear);
	void killCells(List<Cell> cellsToKill);
	List<Cell> getCellsAsList();
}
