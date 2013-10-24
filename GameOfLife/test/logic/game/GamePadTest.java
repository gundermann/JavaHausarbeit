package logic.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.nordakademie.java.gameoflife.logic.game.Cell;
import de.nordakademie.java.gameoflife.logic.game.CellGrid;
import de.nordakademie.java.gameoflife.logic.game.CellGridHandler;
import de.nordakademie.java.gameoflife.logic.game.GamePad;
import de.nordakademie.java.gameoflife.rules.border.BorderRule;
import de.nordakademie.java.gameoflife.rules.border.PacmanStyle;
import de.nordakademie.java.gameoflife.rules.game.GameOfLife;
import de.nordakademie.java.gameoflife.rules.game.GameRule;

public class GamePadTest {

	GamePad gameControl;
	Integer[][] initinalArray = { { 0, 1, 1, 1 }, { 0, 0, 0, 0 },
			{ 0, 0, 0, 0 }, { 0, 0, 0, 0 } };
	GameRule gameRules;
	BorderRule borderRules;
	CellGridHandler cellGridHandler;

	@Before
	public void setUp() {
		gameRules = new GameOfLife();
		borderRules = new PacmanStyle();
		gameControl = new GamePad(initinalArray, gameRules, borderRules);
	}

	@Test
	public void shouldBeInitinalized() {
		assertNotNull(GamePad.gameRules);
		assertNotNull(GamePad.borderRules);
		assertTrue(gameControl.getGeneration() == 1);
	}

	@Test
	public void shouldFindCellsToBear() {
		Cell[][] cellGrid = CellGrid.getCellGrid();// TODO Mockito
		assertTrue(gameControl.findCellsToBear().contains(cellGrid[1][2]));
		assertTrue(gameControl.findCellsToBear().contains(cellGrid[3][2]));
		
	}

	@Test
	public void shouldFindCellsToKill() {
		Cell[][] cellGrid = CellGrid.getCellGrid();// TODO Mockito
		assertTrue(gameControl.findCellsToKill().contains(cellGrid[0][1]));
		assertTrue(gameControl.findCellsToKill().contains(cellGrid[0][3]));
	}

	@Test
	public void shouldCalculateNextGeneration() {
		gameControl.calculateNextGeneration();
		Cell[][] cellGrid = CellGrid.getCellGrid(); // TODO
																		// Mockito

		assertTrue(gameControl.getGeneration() == 2);

		assertFalse(cellGrid[0][0].isAlive());
		assertFalse(cellGrid[0][1].isAlive());
		assertTrue(cellGrid[0][2].isAlive());
		assertFalse(cellGrid[0][3].isAlive());

		assertFalse(cellGrid[1][0].isAlive());
		assertFalse(cellGrid[1][1].isAlive());
		assertTrue(cellGrid[1][2].isAlive());
		assertFalse(cellGrid[1][3].isAlive());

		assertFalse(cellGrid[2][0].isAlive());
		assertFalse(cellGrid[2][1].isAlive());
		assertFalse(cellGrid[2][2].isAlive());
		assertFalse(cellGrid[2][3].isAlive());

		assertFalse(cellGrid[3][0].isAlive());
		assertFalse(cellGrid[3][1].isAlive());
		assertTrue(cellGrid[3][2].isAlive());
		assertFalse(cellGrid[3][3].isAlive());
	}
}
