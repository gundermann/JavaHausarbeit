package business;

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
		initGetPositionOfCell();
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

	private void initGetPositionOfCell() {
		Mockito.when(cellGrid.getPositionOfCell(cell1)).thenReturn(
				new int[] { 0, 0 });
		Mockito.when(cellGrid.getPositionOfCell(cell2)).thenReturn(
				new int[] { 0, 1 });
		Mockito.when(cellGrid.getPositionOfCell(cell3)).thenReturn(
				new int[] { 0, 2 });
		Mockito.when(cellGrid.getPositionOfCell(cell4)).thenReturn(
				new int[] { 1, 0 });
		Mockito.when(cellGrid.getPositionOfCell(cell5)).thenReturn(
				new int[] { 1, 1 });
		Mockito.when(cellGrid.getPositionOfCell(cell6)).thenReturn(
				new int[] { 1, 2 });
		Mockito.when(cellGrid.getPositionOfCell(cell7)).thenReturn(
				new int[] { 2, 0 });
		Mockito.when(cellGrid.getPositionOfCell(cell8)).thenReturn(
				new int[] { 2, 1 });
		Mockito.when(cellGrid.getPositionOfCell(cell9)).thenReturn(
				new int[] { 2, 2 });
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
