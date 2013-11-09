package de.nordakademie.java.gameoflife.test.business;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.nordakademie.java.gameoflife.GameFieldController;
import de.nordakademie.java.gameoflife.business.Cell;
import de.nordakademie.java.gameoflife.business.CellGrid;
import de.nordakademie.java.gameoflife.business.GameThread;
import de.nordakademie.java.gameoflife.business.rules.GameRule;
import de.nordakademie.java.gameoflife.test.CellGridTestHelper;
import de.nordakademie.java.gameoflife.test.UnchangeableCellGridTestHelper;
import de.nordakademie.java.gameoflife.utils.NeighbourFinder;

public class GameThreadTest {

	GameThread gameThread;
	GameRule gameRules;
	NeighbourFinder neighbourFinder;
	CellGrid cellGrid;
	GameFieldController gameController;

	@Before
	public void setUp() {
		gameRules = Mockito.mock(GameRule.class);
		cellGrid = CellGridTestHelper.getMockedCellGrid();
		gameController = Mockito.mock(GameFieldController.class);
		neighbourFinder = new NeighbourFinder(true);
		initMockedMethod();
		gameThread = new GameThread(cellGrid, gameRules, neighbourFinder);
		gameThread.setGameController(gameController);
	}

	private void initMockedMethod() {
		Mockito.when(gameRules.isCellBorn(3)).thenReturn(true);
		Mockito.when(gameRules.isCellStayingAlive(3)).thenReturn(true);
		Mockito.when(gameRules.isCellStayingAlive(2)).thenReturn(true);
		Mockito.when(gameController.getSettedTime()).thenReturn(0L);
	}

	@Test
	public void shouldFindCellsToBear() {
		Cell cell5 = cellGrid.getCellAtPosition(1, 1);
		assertTrue(gameThread.findCellsToBearOrKill().contains(cell5));
	}

	@Test
	public void shouldIncrementGeneration(){
		gameThread.calculateNextGeneration();
		assertTrue(gameThread.getGeneration() == 2);
	}
	
	@Test
	public void shouldFindCellsToKill() {
		Cell cell1 = cellGrid.getCellAtPosition(0, 0);
		Cell cell3 = cellGrid.getCellAtPosition(0, 2);
		assertTrue(gameThread.findCellsToBearOrKill().contains(cell1));
		assertTrue(gameThread.findCellsToBearOrKill().contains(cell3));
	}

	@Test
	public void shouldTerminateGameThread() {
		cellGrid = UnchangeableCellGridTestHelper.getMockedCellGrid();
		gameThread = new GameThread(cellGrid, gameRules, neighbourFinder);
		gameThread.setGameController(gameController);
		new Thread(gameThread).start();

		Cell cell1 = cellGrid.getCellAtPosition(0, 0);
		Cell cell2 = cellGrid.getCellAtPosition(0, 1);
		Cell cell3 = cellGrid.getCellAtPosition(0, 2);
		Cell cell4 = cellGrid.getCellAtPosition(1, 0);
		Cell cell5 = cellGrid.getCellAtPosition(1, 1);
		Cell cell6 = cellGrid.getCellAtPosition(1, 2);
		Cell cell7 = cellGrid.getCellAtPosition(2, 0);
		Cell cell8 = cellGrid.getCellAtPosition(2, 1);
		Cell cell9 = cellGrid.getCellAtPosition(2, 2);

		assertTrue(gameThread.getGeneration() == 1);
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
