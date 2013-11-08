package de.nordakademie.java.gameoflife.test.business;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.nordakademie.java.gameoflife.business.Cell;
import de.nordakademie.java.gameoflife.business.CellGrid;
import de.nordakademie.java.gameoflife.business.GameController;
import de.nordakademie.java.gameoflife.business.rules.BorderRule;
import de.nordakademie.java.gameoflife.business.rules.GameRule;
import de.nordakademie.java.gameoflife.business.rules.border.PacmanStyle;
import de.nordakademie.java.gameoflife.business.rules.game.GameOfLife;

public class GameControllerTest {

	GameController gameControl;
	GameRule gameRules;
	BorderRule borderRules;
	CellGrid cellGrid;
	Cell cell1;
	Cell cell2;
	Cell cell3;
	Cell cell4;
	Cell cell5;
	Cell cell6;
	Cell cell7;
	Cell cell8;
	Cell cell9;

	@Before
	public void setUp() {
		gameRules = Mockito.mock(GameOfLife.class);
		borderRules = Mockito.mock(PacmanStyle.class);
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
		gameControl = new GameController(cellGrid, gameRules, borderRules);
	}

	private void initMockedMethod() {
		initGetCellAtPosition();
		initGetCellArray();
		initIsAlive();
		int count = 3;
		Mockito.when(cellGrid.getColumnCount()).thenReturn(count);
		Mockito.when(cellGrid.getRowCount()).thenReturn(count);
		Mockito.when(borderRules.isGridBorderDead()).thenReturn(false);
		Mockito.when(gameRules.isCellBorn(3)).thenReturn(true);

	}

	private void initGetCellArray() {
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

	private void initIsAlive() {
		Mockito.when(cellGrid.isCellAlive(cell1)).thenReturn(true);
		Mockito.when(cellGrid.isCellAlive(cell2)).thenReturn(true);
		Mockito.when(cellGrid.isCellAlive(cell3)).thenReturn(true);
		Mockito.when(cellGrid.isCellAlive(cell4)).thenReturn(false);
		Mockito.when(cellGrid.isCellAlive(cell5)).thenReturn(false);
		Mockito.when(cellGrid.isCellAlive(cell6)).thenReturn(false);
		Mockito.when(cellGrid.isCellAlive(cell7)).thenReturn(false);
		Mockito.when(cellGrid.isCellAlive(cell8)).thenReturn(false);
		Mockito.when(cellGrid.isCellAlive(cell9)).thenReturn(false);
	}

	private void initGetCellAtPosition() {
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

	@Test
	public void shouldFindCellsToBear() {
		assertTrue(gameControl.findCellsToBearOrKill().contains(cell5));
		assertTrue(gameControl.findCellsToBearOrKill().contains(cell8));
	}

	@Test
	public void shouldFindCellsToKill() {
		assertTrue(gameControl.findCellsToBearOrKill().contains(cell1));
		assertTrue(gameControl.findCellsToBearOrKill().contains(cell2));
		assertTrue(gameControl.findCellsToBearOrKill().contains(cell3));
	}

	@Test
	public void shouldCalculateNextGeneration() {
		gameControl.calculateNextGeneration();
		assertTrue(gameControl.getGeneration() == 2);
	}
}
