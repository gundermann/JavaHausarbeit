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
import de.nordakademie.java.gameoflife.test.CellGridTestHelper;

public class GameControllerTest {

	GameController gameControl;
	GameRule gameRules;
	BorderRule borderRules;
	CellGrid cellGrid;

	@Before
	public void setUp() {
		gameRules = Mockito.mock(GameOfLife.class);
		borderRules = Mockito.mock(PacmanStyle.class);
		cellGrid = CellGridTestHelper.getMockedCellGrid();
		initMockedMethod();
		gameControl = new GameController(cellGrid, gameRules, borderRules);
	}

	private void initMockedMethod() {
		Mockito.when(borderRules.isGridBorderDead()).thenReturn(false);
		Mockito.when(gameRules.isCellBorn(3)).thenReturn(true);

	}

	@Test
	public void shouldFindCellsToBear() {
		Cell cell5 = cellGrid.getCellAtPosition(1, 1);
		Cell cell8 = cellGrid.getCellAtPosition(2, 1);

		assertTrue(gameControl.findCellsToBearOrKill().contains(cell5));
		assertTrue(gameControl.findCellsToBearOrKill().contains(cell8));
	}

	@Test
	public void shouldFindCellsToKill() {
		Cell cell1 = cellGrid.getCellAtPosition(0, 0);
		Cell cell2 = cellGrid.getCellAtPosition(0, 1);
		Cell cell3 = cellGrid.getCellAtPosition(0, 2);

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
