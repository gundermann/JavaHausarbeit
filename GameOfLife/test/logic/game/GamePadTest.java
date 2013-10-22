package logic.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.nordakademie.java.gameoflife.logic.game.Cell;
import de.nordakademie.java.gameoflife.logic.game.GamePad;
import de.nordakademie.java.gameoflife.rules.game.GameOfLife;
import de.nordakademie.java.gameoflife.rules.game.GameRule;

public class GamePadTest {

	GamePad gameControl;
	Integer[][] initinalArray = { { 0, 0, 0, 0 }, { 0, 1, 1, 0 },
			{ 0, 1, 1, 0 }, { 0, 0, 0, 0 } };
	GameRule rules;

	@Before
	public void setUp() {
		rules = new GameOfLife();
		gameControl = new GamePad(initinalArray, rules);
	}

	@Test
	public void shouldBeInitinalized() {
		assertNotNull(gameControl.getCellGrid());
		assertNotNull(gameControl.getRules());
		assertTrue(gameControl.getGeneration() == 1);
	}

	@Test
	public void shouldFindCellsToBear() {
		List<Cell> cellsToKill = new ArrayList<Cell>();
		Cell[][] cellGrid = gameControl.getCellGrid().getCellGrid();// TODO
																	// Mockito
		cellsToKill.add(cellGrid[1][1]);
		cellsToKill.add(cellGrid[1][2]);
		cellsToKill.add(cellGrid[2][1]);
		cellsToKill.add(cellGrid[2][2]);

		assertTrue(cellsToKill.equals(gameControl.findCellsToBear()));
	}

	@Test
	public void shouldFindCellsToKill() {

	}

	@Test
	public void shouldCalculateNextGeneration() {
		gameControl.calculateNextGeneration();
		Cell[][] cellGrid = gameControl.getCellGrid().getCellGrid(); // TODO
																		// Mockito

		assertTrue(gameControl.getGeneration() == 2);

		assertTrue(cellGrid[0][0].isAlive());
		assertFalse(cellGrid[0][1].isAlive());
		assertFalse(cellGrid[0][2].isAlive());
		assertTrue(cellGrid[0][3].isAlive());

		assertFalse(cellGrid[1][0].isAlive());
		assertFalse(cellGrid[1][1].isAlive());
		assertFalse(cellGrid[1][2].isAlive());
		assertFalse(cellGrid[1][3].isAlive());

		assertFalse(cellGrid[2][0].isAlive());
		assertFalse(cellGrid[2][1].isAlive());
		assertFalse(cellGrid[2][2].isAlive());
		assertFalse(cellGrid[2][3].isAlive());

		assertTrue(cellGrid[3][0].isAlive());
		assertFalse(cellGrid[3][1].isAlive());
		assertFalse(cellGrid[3][2].isAlive());
		assertTrue(cellGrid[3][3].isAlive());
	}

}
