package de.nordakademie.java.gameoflife.test.business;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.nordakademie.java.gameoflife.business.Cell;
import de.nordakademie.java.gameoflife.business.CellGrid;
import de.nordakademie.java.gameoflife.business.GameController;
import de.nordakademie.java.gameoflife.business.rules.BorderRule;
import de.nordakademie.java.gameoflife.business.rules.GameRule;
import de.nordakademie.java.gameoflife.gui.GameFieldGuiHandler;
import de.nordakademie.java.gameoflife.test.CellGridTestHelper;
import de.nordakademie.java.gameoflife.test.UnchangeableCellGridTestHelper;

public class GameControllerTest {

	GameController gameControl;
	GameRule gameRules;
	BorderRule borderRules;
	CellGrid cellGrid;
	GameFieldGuiHandler fieldHandler;

	@Before
	public void setUp() {
		gameRules = Mockito.mock(GameRule.class);
		borderRules = Mockito.mock(BorderRule.class);
		cellGrid = CellGridTestHelper.getMockedCellGrid();
		fieldHandler = Mockito.mock(GameFieldGuiHandler.class);
		initMockedMethod();
		gameControl = new GameController(cellGrid, gameRules, borderRules);
		gameControl.setGameControlHandler(fieldHandler);
	}

	private void initMockedMethod() {
		Mockito.when(borderRules.isGridBorderDead()).thenReturn(true);
		Mockito.when(gameRules.isCellBorn(3)).thenReturn(true);
		Mockito.when(gameRules.isCellStayingAlive(3)).thenReturn(true);
		Mockito.when(gameRules.isCellStayingAlive(2)).thenReturn(true);
		Mockito.when(fieldHandler.getSliderPosition()).thenReturn(0L);
	}

	@Test
	public void shouldFindCellsToBear() {
		Cell cell5 = cellGrid.getCellAtPosition(1, 1);
		assertTrue(gameControl.findCellsToBearOrKill().contains(cell5));
	}

	@Test
	public void shouldFindCellsToKill() {
		Cell cell1 = cellGrid.getCellAtPosition(0, 0);
		Cell cell3 = cellGrid.getCellAtPosition(0, 2);
		assertTrue(gameControl.findCellsToBearOrKill().contains(cell1));
		assertTrue(gameControl.findCellsToBearOrKill().contains(cell3));
	}

	@Test
	public void shouldCalculateNextGeneration() {
		gameControl.calculateNextGeneration();
		assertTrue(gameControl.getGeneration() == 2);
	}

	@Test
	public void shouldTerminateGameThread() {
		cellGrid = UnchangeableCellGridTestHelper.getMockedCellGrid();
		gameControl = new GameController(cellGrid, gameRules, borderRules);
		gameControl.setGameControlHandler(fieldHandler);
		Thread gameThread = new Thread(gameControl);
		gameThread.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Cell cell1 = cellGrid.getCellAtPosition(0, 0);
		Cell cell2 = cellGrid.getCellAtPosition(0, 1);
		Cell cell3 = cellGrid.getCellAtPosition(0, 2);
		Cell cell4 = cellGrid.getCellAtPosition(1, 0);
		Cell cell5 = cellGrid.getCellAtPosition(1, 1);
		Cell cell6 = cellGrid.getCellAtPosition(1, 2);
		Cell cell7 = cellGrid.getCellAtPosition(2, 0);
		Cell cell8 = cellGrid.getCellAtPosition(2, 1);
		Cell cell9 = cellGrid.getCellAtPosition(2, 2);

		assertTrue(gameControl.getGeneration() == 1);
		assertTrue(cellGrid.isCellAlive(cell2));
		assertTrue(cellGrid.isCellAlive(cell4));
		assertTrue(cellGrid.isCellAlive(cell6));
		assertTrue(cellGrid.isCellAlive(cell8));
		assertFalse(cellGrid.isCellAlive(cell1));
		assertFalse(cellGrid.isCellAlive(cell3));
		assertFalse(cellGrid.isCellAlive(cell5));
		assertFalse(cellGrid.isCellAlive(cell7));
		assertFalse(cellGrid.isCellAlive(cell9));
	}
}
