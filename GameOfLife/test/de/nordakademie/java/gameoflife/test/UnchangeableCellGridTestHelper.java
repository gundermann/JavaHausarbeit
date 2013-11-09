package de.nordakademie.java.gameoflife.test;

import org.mockito.Mockito;

import de.nordakademie.java.gameoflife.business.Cell;
import de.nordakademie.java.gameoflife.business.CellGrid;

public class UnchangeableCellGridTestHelper {

	private static CellGrid cellGrid;
	private static Cell cell1;
	private static Cell cell2;
	private static Cell cell3;
	private static Cell cell4;
	private static Cell cell5;
	private static Cell cell6;
	private static Cell cell7;
	private static Cell cell8;
	private static Cell cell9;

	public static CellGrid getMockedCellGrid() {
		cellGrid = Mockito.mock(CellGrid.class);
		cell1 = Mockito.mock(Cell.class);
		cell2 = Mockito.mock(Cell.class);
		cell3 = Mockito.mock(Cell.class);
		cell4 = Mockito.mock(Cell.class);
		cell5 = Mockito.mock(Cell.class);
		cell6 = Mockito.mock(Cell.class);
		cell7 = Mockito.mock(Cell.class);
		cell8 = Mockito.mock(Cell.class);
		cell9 = Mockito.mock(Cell.class);
		initMockedMethod();
		return cellGrid;
	}

	protected static void initMockedMethod() {
		initGetCellAtPosition();
		initGetCellArray();
		initIsAlive();
		int count = 3;
		Mockito.when(cellGrid.getColumnCount()).thenReturn(count);
		Mockito.when(cellGrid.getRowCount()).thenReturn(count);
	}

	protected static void initGetCellArray() {
		Cell[][] cellArray = new Cell[3][3];

		cellArray[0][0] = cell1;
		cellArray[0][1] = cell2;
		cellArray[0][2] = cell3;
		cellArray[1][0] = cell4;
		cellArray[1][1] = cell5;
		cellArray[1][2] = cell6;
		cellArray[2][0] = cell7;
		cellArray[2][1] = cell8;
		cellArray[2][2] = cell9;

		Mockito.when(cellGrid.getCellArray()).thenReturn(cellArray);
	}

	protected static void initGetCellAtPosition() {
		Mockito.when(cellGrid.getCellAtPosition(0, 0)).thenReturn(cell1);
		Mockito.when(cellGrid.getCellAtPosition(0, 1)).thenReturn(cell2);
		Mockito.when(cellGrid.getCellAtPosition(0, 2)).thenReturn(cell3);
		Mockito.when(cellGrid.getCellAtPosition(1, 0)).thenReturn(cell4);
		Mockito.when(cellGrid.getCellAtPosition(1, 1)).thenReturn(cell5);
		Mockito.when(cellGrid.getCellAtPosition(1, 2)).thenReturn(cell6);
		Mockito.when(cellGrid.getCellAtPosition(2, 0)).thenReturn(cell7);
		Mockito.when(cellGrid.getCellAtPosition(2, 1)).thenReturn(cell8);
		Mockito.when(cellGrid.getCellAtPosition(2, 2)).thenReturn(cell9);
	}

	protected static void initIsAlive() {
		Mockito.when(cellGrid.isCellAlive(cell1)).thenReturn(false);
		Mockito.when(cellGrid.isCellAlive(cell2)).thenReturn(true);
		Mockito.when(cellGrid.isCellAlive(cell3)).thenReturn(false);
		Mockito.when(cellGrid.isCellAlive(cell4)).thenReturn(true);
		Mockito.when(cellGrid.isCellAlive(cell5)).thenReturn(false);
		Mockito.when(cellGrid.isCellAlive(cell6)).thenReturn(true);
		Mockito.when(cellGrid.isCellAlive(cell7)).thenReturn(false);
		Mockito.when(cellGrid.isCellAlive(cell8)).thenReturn(true);
		Mockito.when(cellGrid.isCellAlive(cell9)).thenReturn(false);
	}
}
