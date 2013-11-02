package logic.game;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

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

public class GamePadTest {

	GameController gameControl;
	Integer[][] initinalArray = { { 0, 1, 1, 1 }, { 0, 0, 0, 0 },
			{ 0, 0, 0, 0 }, { 0, 0, 0, 0 } };
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
		initGetColumnOfCell();
		initGetRowOfCell();
		initGetCellsAsList();
		initIsAlive();
		int count = 3;
		Mockito.when(cellGrid.getColumnCount()).thenReturn(count);
		Mockito.when(cellGrid.getRowCount()).thenReturn(count);
		Mockito.when(borderRules.isGridBorderDead()).thenReturn(false);
		Mockito.when(gameRules.isCellBorn(3)).thenReturn(true);

	}

	private void initIsAlive() {
		Mockito.when(cell1.isAlive()).thenReturn(true);
		Mockito.when(cell2.isAlive()).thenReturn(true);
		Mockito.when(cell3.isAlive()).thenReturn(true);
		Mockito.when(cell4.isAlive()).thenReturn(false);
		Mockito.when(cell5.isAlive()).thenReturn(false);
		Mockito.when(cell6.isAlive()).thenReturn(false);
		Mockito.when(cell7.isAlive()).thenReturn(false);
		Mockito.when(cell8.isAlive()).thenReturn(false);
		Mockito.when(cell9.isAlive()).thenReturn(false);
	}

	private void initGetCellsAsList() {
		ArrayList<Cell> cellList = new ArrayList<Cell>();
		cellList.add(cell1);
		cellList.add(cell2);
		cellList.add(cell3);
		cellList.add(cell4);
		cellList.add(cell5);
		cellList.add(cell6);
		cellList.add(cell7);
		cellList.add(cell8);
		cellList.add(cell9);
		Mockito.when(cellGrid.getCellsAsList()).thenReturn(cellList);
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

	private void initGetColumnOfCell() {
		Mockito.when(cellGrid.getColumnOfCell(cell1)).thenReturn(0);
		Mockito.when(cellGrid.getColumnOfCell(cell2)).thenReturn(1);
		Mockito.when(cellGrid.getColumnOfCell(cell3)).thenReturn(2);
		Mockito.when(cellGrid.getColumnOfCell(cell4)).thenReturn(0);
		Mockito.when(cellGrid.getColumnOfCell(cell5)).thenReturn(1);
		Mockito.when(cellGrid.getColumnOfCell(cell6)).thenReturn(2);
		Mockito.when(cellGrid.getColumnOfCell(cell7)).thenReturn(0);
		Mockito.when(cellGrid.getColumnOfCell(cell8)).thenReturn(1);
		Mockito.when(cellGrid.getColumnOfCell(cell9)).thenReturn(2);
	}

	private void initGetRowOfCell() {
		Mockito.when(cellGrid.getRowOfCell(cell1)).thenReturn(0);
		Mockito.when(cellGrid.getRowOfCell(cell2)).thenReturn(0);
		Mockito.when(cellGrid.getRowOfCell(cell3)).thenReturn(0);
		Mockito.when(cellGrid.getRowOfCell(cell4)).thenReturn(1);
		Mockito.when(cellGrid.getRowOfCell(cell5)).thenReturn(1);
		Mockito.when(cellGrid.getRowOfCell(cell6)).thenReturn(1);
		Mockito.when(cellGrid.getRowOfCell(cell7)).thenReturn(2);
		Mockito.when(cellGrid.getRowOfCell(cell8)).thenReturn(2);
		Mockito.when(cellGrid.getRowOfCell(cell9)).thenReturn(2);
	}

	@Test
	public void shouldBeInitinalized() {
		assertNotNull(gameControl.getGameRules());
		assertNotNull(gameControl.getBorderRules());
		assertTrue(gameControl.getGeneration() == 1);
		assertTrue(cell1.isAlive());
	}

	@Test
	public void shouldFindCellsToBear() {
		assertTrue(gameControl.findCellsToBear().contains(cell5));
		assertTrue(gameControl.findCellsToBear().contains(cell8));
	}

	@Test
	public void shouldFindCellsToKill() {
		assertTrue(gameControl.findCellsToKill().contains(cell1));
		assertTrue(gameControl.findCellsToKill().contains(cell2));
		assertTrue(gameControl.findCellsToKill().contains(cell3));
	}

	@Test
	public void shouldCalculateNextGeneration() {
		gameControl.calculateNextGeneration();
		assertTrue(gameControl.getGeneration() == 2);
	}
}
