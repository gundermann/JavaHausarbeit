package logic.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.nordakademie.java.gameoflife.logic.game.Cell;
import de.nordakademie.java.gameoflife.logic.game.CellGrid;

public class CellGridTest {

	CellGrid cellGrid;

	@Before
	public void setUp() {
		cellGrid = new CellGrid(3, 4);
	}

	@Test
	public void cellGridDimension() {
		assertTrue(cellGrid.getColumnCount() == 4);
		assertTrue(cellGrid.getRowCount() == 3);
	}

	@Test
	public void cellsInitinalized() {
		Cell[][] cellArray = cellGrid.getCellGrid();

		for (int row = 0; row < cellGrid.getRowCount(); row++) {
			for (int column = 0; column < cellGrid.getColumnCount(); column++) {
				assertTrue(cellArray[row][column] instanceof Cell);
			}
		}
	}

	@Test
	public void shouldGetAllCells() {
		assertTrue(cellGrid.getCells().size() == 12);
	}

	@Test
	public void shouldGetRightCell() {
		int row = 2;
		int column = 3;

		Cell[][] cellArray = cellGrid.getCellGrid();

		assertTrue(cellArray[2][3] == cellGrid.getCellAtPosition(row, column));
	}

	@Test
	public void shouldBearCells() {
		Cell[][] cellArray = cellGrid.getCellGrid();
		Cell cell1 = cellArray[0][1];
		Cell cell2 = cellArray[1][2];
		Cell cell3 = cellArray[2][0];

		List<Cell> cellsToBear = new ArrayList<Cell>();
		cellsToBear.add(cell1);
		cellsToBear.add(cell2);
		cellsToBear.add(cell3);

		cellGrid.bearCells(cellsToBear);

		// Muss nocheinaml hinzugefuegt werden, da die Liste nach bearCells leer
		// ist
		cellsToBear.add(cell1);
		cellsToBear.add(cell2);
		cellsToBear.add(cell3);

		for (int row = 0; row < cellArray.length; row++) {
			for (int column = 0; column < cellArray[0].length; column++) {
				if (cellsToBear.contains(cellArray[row][column])) {
					assertTrue(cellArray[row][column].isAlive());
				} else {
					assertFalse(cellArray[row][column].isAlive());
				}
			}
		}
	}

	@Test
	public void shouldKillCells() {
		Cell[][] cellArray = cellGrid.getCellGrid();
		Cell cell1 = cellArray[0][1];
		Cell cell2 = cellArray[1][2];
		Cell cell3 = cellArray[2][0];

		List<Cell> cellsToKill = new ArrayList<Cell>();
		cellsToKill.add(cell1);
		cellsToKill.add(cell2);
		cellsToKill.add(cell3);

		cellGrid.killCells(cellsToKill);

		for (int row = 0; row < cellArray.length; row++) {
			for (int column = 0; column < cellArray[0].length; column++) {
				assertFalse(cellArray[row][column].isAlive());
			}
		}
	}

	@Test
	public void shouldFindNeighboursInTheMiddle() {
		Cell[][] cellArray = cellGrid.getCellGrid();
		Cell cell = cellArray[1][1];
		List<Cell> neighbours = cellGrid.getNeighbours(cell, false);

		assertTrue(neighbours.size() == 8);
		assertTrue(neighbours.contains(cellArray[0][0]));
		assertTrue(neighbours.contains(cellArray[1][0]));
		assertTrue(neighbours.contains(cellArray[2][0]));
		assertTrue(neighbours.contains(cellArray[0][1]));
		assertTrue(neighbours.contains(cellArray[2][1]));
		assertTrue(neighbours.contains(cellArray[0][2]));
		assertTrue(neighbours.contains(cellArray[1][2]));
		assertTrue(neighbours.contains(cellArray[2][2]));
	}

	@Test
	public void shouldFindNeighboursInLessThanThreeRows() {
		cellGrid = new CellGrid(1, 4);
		Cell[][] cellArray = cellGrid.getCellGrid();
		Cell cell = cellArray[0][0];
		List<Cell> neighbours = cellGrid.getNeighbours(cell, true);

		assertTrue(neighbours.size() == 8);
		assertTrue(neighbours.contains(cellArray[0][1]));
		assertTrue(neighbours.contains(cellArray[0][3]));
		assertTrue(neighbours.contains(null));
	}

	@Test
	public void shouldFindNeighboursOnTheBorderWithWallOfDeath() {
		Cell[][] cellArray = cellGrid.getCellGrid();
		Cell cell = cellArray[0][0];
		List<Cell> neighbours = cellGrid.getNeighbours(cell, false);

		assertTrue(neighbours.size() == 8);
		assertTrue(neighbours.contains(cellArray[0][1]));
		assertTrue(neighbours.contains(cellArray[1][0]));
		assertTrue(neighbours.contains(cellArray[1][1]));
		assertTrue(neighbours.contains(null));
	}

	@Test
	public void shouldFindNeighboursOnTheBorderWithPacman() {
		Cell[][] cellArray = cellGrid.getCellGrid();
		Cell cell = cellArray[0][0];
		List<Cell> neighbours = cellGrid.getNeighbours(cell, true);

		assertTrue(neighbours.size() == 8);
		assertTrue(neighbours.contains(cellArray[0][1]));
		assertTrue(neighbours.contains(cellArray[1][0]));
		assertTrue(neighbours.contains(cellArray[1][1]));
		assertTrue(neighbours.contains(cellArray[0][3]));
		assertTrue(neighbours.contains(cellArray[1][3]));
		assertTrue(neighbours.contains(cellArray[2][0]));
		assertTrue(neighbours.contains(cellArray[2][1]));
		assertTrue(neighbours.contains(cellArray[2][3]));
	}

}
