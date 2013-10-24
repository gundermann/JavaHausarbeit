package logic.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import de.nordakademie.java.gameoflife.logic.game.Cell;
import de.nordakademie.java.gameoflife.logic.game.CellGrid;
import de.nordakademie.java.gameoflife.logic.game.GamePad;
import de.nordakademie.java.gameoflife.rules.border.PacmanStyle;
import de.nordakademie.java.gameoflife.rules.border.WallOfDeath;

public class CellGridTest {

	private CellGrid cellGrid;

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
	public void shouldGetRightCell() {
		int row = 2;
		int column = 3;
		
		Cell[][] cellArray = cellGrid.getCellGrid();
		
		assertTrue(cellArray[2][3] == cellGrid.getCellAtPosition(row, column));
		
		row = 4;
		column = 4;
		
		assertNull(cellGrid.getCellAtPosition(row, column));
	}

	@Test
	public void cellsInitinalized() {
		for (int row = 0; row < cellGrid.getRowCount(); row++) {
			for (int column = 0; column < cellGrid.getColumnCount(); column++) {
				assertFalse(cellGrid.isCellAtPositionAlive(row, column));
			}
		}
	}

	@Test
	public void shouldGetAllCells() {
		assertTrue(cellGrid.getCellsAsList().size() == 12);
	}
	
	@Test
	public void shouldConvertToIntegerArray(){
		fail("Not yet implemented");
	}
	
	@Test
	public void shouldKillAndBearCellAtPosition(){
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
