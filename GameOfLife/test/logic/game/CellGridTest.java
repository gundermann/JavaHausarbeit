package logic.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import de.nordakademie.java.gameoflife.logic.game.Cell;
import de.nordakademie.java.gameoflife.logic.game.CellGrid;
import de.nordakademie.java.gameoflife.logic.game.GamePad;
import de.nordakademie.java.gameoflife.rules.border.PacmanStyle;
import de.nordakademie.java.gameoflife.rules.border.WallOfDeath;

public class CellGridTest {


	@Before
	public void setUp() {
		new CellGrid(3, 4);
	}

	@Test
	public void cellGridDimension() {
		assertTrue(CellGrid.getColumnCount() == 4);
		assertTrue(CellGrid.getRowCount() == 3);
	}

	@Test
	public void cellsInitinalized() {
		Cell[][] cellArray = CellGrid.getCellGrid();

		for (int row = 0; row < CellGrid.getRowCount(); row++) {
			for (int column = 0; column < CellGrid.getColumnCount(); column++) {
				assertTrue(cellArray[row][column] instanceof Cell);
			}
		}
	}

	@Test
	public void shouldGetAllCells() {
		assertTrue(CellGrid.getCells().size() == 12);
	}

	@Test
	public void shouldGetRightCell() {
		int row = 2;
		int column = 3;

		Cell[][] cellArray = CellGrid.getCellGrid();

		assertTrue(cellArray[2][3] == CellGrid.getCellAtPosition(row, column));
	}

	@Test
	public void shouldBearCells() {
		Cell[][] cellArray = CellGrid.getCellGrid();
		Cell cell1 = cellArray[0][1];
		Cell cell2 = cellArray[1][2];
		Cell cell3 = cellArray[2][0];

		List<Cell> cellsToBear = new ArrayList<Cell>();
		cellsToBear.add(cell1);
		cellsToBear.add(cell2);
		cellsToBear.add(cell3);

		CellGrid.bearCells(cellsToBear);

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
		Cell[][] cellArray = CellGrid.getCellGrid();
		Cell cell1 = cellArray[0][1];
		Cell cell2 = cellArray[1][2];
		Cell cell3 = cellArray[2][0];

		List<Cell> cellsToKill = new ArrayList<Cell>();
		cellsToKill.add(cell1);
		cellsToKill.add(cell2);
		cellsToKill.add(cell3);

		CellGrid.killCells(cellsToKill);

		for (int row = 0; row < cellArray.length; row++) {
			for (int column = 0; column < cellArray[0].length; column++) {
				assertFalse(cellArray[row][column].isAlive());
			}
		}
	}

	@Test
	public void shouldFindNeighboursInTheMiddle() {
		GamePad.borderRules = new WallOfDeath();
		Cell[][] cellArray = CellGrid.getCellGrid();
		Cell cell = cellArray[1][1];
		List<Cell> neighbours = CellGrid.getNeighbours(cell);

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
	public void shouldFindNoNeighbours() {
		new CellGrid(1, 1);
		GamePad.borderRules = new PacmanStyle();
		Cell[][] cellArray = CellGrid.getCellGrid(); //TODO Mockito
		Cell cell = cellArray[0][0];
		List<Cell> neighbours = CellGrid.getNeighbours(cell);

		assertTrue(neighbours.size() == 8);
		for(Cell neighbour : neighbours){
			assertFalse(neighbour.isAlive());
		}
	}

	@Test
	public void shouldFindNeighboursOnTheBorderWithWallOfDeath() {
		GamePad.borderRules = new WallOfDeath();
		Cell[][] cellArray = CellGrid.getCellGrid();
		Cell cell = cellArray[0][0];
		List<Cell> neighbours = CellGrid.getNeighbours(cell);

		assertThat(neighbours.size(), Matchers.is(8));
		assertTrue(neighbours.contains(cellArray[0][1]));
		assertTrue(neighbours.contains(cellArray[1][0]));
		assertTrue(neighbours.contains(cellArray[1][1]));
		assertFalse(neighbours.contains(cellArray[0][2]));
		assertFalse(neighbours.contains(cellArray[1][2]));
	}

	@Test
	public void shouldFindNeighboursOnTheBorderWithPacman() {
		GamePad.borderRules = new PacmanStyle();
		Cell[][] cellArray = CellGrid.getCellGrid();
		Cell cell = cellArray[0][0];
		List<Cell> neighbours = CellGrid.getNeighbours(cell);

		assertTrue(neighbours.contains(cellArray[0][1]));
		assertTrue(neighbours.contains(cellArray[1][0]));
		assertTrue(neighbours.contains(cellArray[1][1]));
		assertTrue(neighbours.contains(cellArray[0][3]));
		assertTrue(neighbours.contains(cellArray[1][3]));
		assertTrue(neighbours.contains(cellArray[2][0]));
		assertTrue(neighbours.contains(cellArray[2][1]));
		assertTrue(neighbours.contains(cellArray[2][3]));
	}

	@Test
	public void shouldConvertToIntegerArray(){
	}
}
