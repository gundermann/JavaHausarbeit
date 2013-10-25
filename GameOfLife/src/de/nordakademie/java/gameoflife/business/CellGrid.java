package de.nordakademie.java.gameoflife.business;

import java.util.List;

public interface CellGrid {

	void killCell(Cell cell);

	void bearCell(Cell cell);

	List<Cell> getCellsAsList();

	int getRowOfCell(Cell cell);

	int getColumnOfCell(Cell cell);

	int getRowCount();

	int getColumnCount();

	Cell getCellAtCoordinates(int y, int x);

	boolean[][] convertCellGridToBooleanArray();
}
