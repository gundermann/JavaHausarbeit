package business;

import static org.junit.Assert.assertFalse;
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

		assertTrue(cellArray[1][2] == cellGrid.getCellAtPosition(y, x));

		x = 4;
		y = 4;

		assertNull(cellGrid.getCellAtPosition(y, x));
	}

	@Test
	public void shouldKillAndBearCellAtPosition() {
		Cell cell1 = cellGrid.getCellAtPosition(0, 1);
		Cell cell2 = cellGrid.getCellAtPosition(1, 1);

		cellGrid.bearCell(cell1);
		cellGrid.bearCell(cell2);

		assertTrue(cell1.isAlive());
		assertTrue(cell2.isAlive());

		cellGrid.killCell(cell2);
		assertTrue(cell1.isAlive());
		assertFalse(cell2.isAlive());
	}

}
