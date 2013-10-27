package logic.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.nordakademie.java.gameoflife.business.Cell;
import de.nordakademie.java.gameoflife.business.CellGrid;

public class CellGridTest {

	private CellGrid cellGrid;

	@Before
	public void setUp() {
		int[][] initinalArray = { { 1, 0, 1 }, { 0, 1, 0 }, { 1, 0, 1 } };
		cellGrid = new CellGrid(initinalArray);
	}

	@Test
	public void cellGridDimension() {
		assertTrue(cellGrid.getColumnCount() == 3);
		assertTrue(cellGrid.getRowCount() == 3);
	}

	@Test
	public void shouldGetRightCell() {
		int y = 1;
		int x = 2;

		Cell[][] cellArray = cellGrid.getCellArray();

		assertTrue(cellArray[1][2] == cellGrid.getCellAtCoordinates(y, x));

		x = 4;
		y = 4;

		assertNull(cellGrid.getCellAtCoordinates(y, x));
	}

	@Test
	public void cellsInitinalized() {
		for (int row = 0; row < cellGrid.getRowCount(); row++) {
			for (int column = 0; column < cellGrid.getColumnCount(); column++) {
				assertNotNull(cellGrid.getCellAtCoordinates(row, column));
			}
		}

		// TODO alle Zellen testen
		assertFalse(cellGrid.isCellAtPositionAlive(0, 1));
	}

	@Test
	public void shouldGetAllCells() {
		assertTrue(cellGrid.getCellsAsList().size() == 9);
	}

	@Test
	public void shouldGetRightRowOfCell() {
		Cell cell1 = cellGrid.getCellAtCoordinates(0, 0);
		Cell cell2 = cellGrid.getCellAtCoordinates(0, 2);
		Cell cell3 = cellGrid.getCellAtCoordinates(1, 0);
		Cell cell4 = cellGrid.getCellAtCoordinates(1, 2);
		Cell cell5 = cellGrid.getCellAtCoordinates(2, 0);
		Cell cell6 = cellGrid.getCellAtCoordinates(2, 2);

		assertTrue(cellGrid.getRowOfCell(cell1) == 0);
		assertTrue(cellGrid.getRowOfCell(cell2) == 0);
		assertTrue(cellGrid.getRowOfCell(cell3) == 1);
		assertTrue(cellGrid.getRowOfCell(cell4) == 1);
		assertTrue(cellGrid.getRowOfCell(cell5) == 2);
		assertTrue(cellGrid.getRowOfCell(cell6) == 2);
		assertTrue(cellGrid.getRowOfCell(new Cell()) == -1);
	}

	@Test
	public void shouldGetRightColumnOfCell() {
		Cell cell1 = cellGrid.getCellAtCoordinates(0, 0);
		Cell cell2 = cellGrid.getCellAtCoordinates(0, 0);
		Cell cell3 = cellGrid.getCellAtCoordinates(1, 1);
		Cell cell4 = cellGrid.getCellAtCoordinates(1, 1);
		Cell cell5 = cellGrid.getCellAtCoordinates(2, 2);
		Cell cell6 = cellGrid.getCellAtCoordinates(2, 2);

		assertTrue(cellGrid.getColumnOfCell(cell1) == 0);
		assertTrue(cellGrid.getColumnOfCell(cell2) == 0);
		assertTrue(cellGrid.getColumnOfCell(cell3) == 1);
		assertTrue(cellGrid.getColumnOfCell(cell4) == 1);
		assertTrue(cellGrid.getColumnOfCell(cell5) == 2);
		assertTrue(cellGrid.getColumnOfCell(cell6) == 2);
		assertTrue(cellGrid.getColumnOfCell(new Cell()) == -1);
	}

	@Test
	public void shouldConvertToBooleanArray() {
		boolean[][] boolArray = cellGrid.convertCellGridToBooleanArray();
		assertTrue(boolArray[0][0]);
		assertFalse(boolArray[0][1]);
		assertTrue(boolArray[0][2]);
		assertFalse(boolArray[1][0]);
		assertTrue(boolArray[1][1]);
		assertFalse(boolArray[1][2]);
		assertTrue(boolArray[2][0]);
		assertFalse(boolArray[2][1]);
		assertTrue(boolArray[2][2]);
	}

	@Test
	public void shouldKillAndBearCellAtPosition() {
		Cell cell1 = cellGrid.getCellAtCoordinates(0, 1);
		Cell cell2 = cellGrid.getCellAtCoordinates(1, 1);

		cellGrid.bearCell(cell1);
		cellGrid.bearCell(cell2);

		assertTrue(cell1.isAlive());
		assertTrue(cell2.isAlive());

		cellGrid.killCell(cell2);
		assertTrue(cell1.isAlive());
		assertFalse(cell2.isAlive());
	}

}
