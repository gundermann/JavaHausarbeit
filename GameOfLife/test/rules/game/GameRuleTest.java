package rules.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.nordakademie.java.gameoflife.business.rules.GameRule;
import de.nordakademie.java.gameoflife.business.rules.game.GameOfLife;
import de.nordakademie.java.gameoflife.business.rules.game.GameWithoutDeath;
import de.nordakademie.java.gameoflife.business.rules.game.HighLife;
import de.nordakademie.java.gameoflife.business.rules.game.ThreeOrFourLife;

public class GameRuleTest {

	GameOfLife gol;
	GameWithoutDeath wid;
	HighLife hl;
	ThreeOrFourLife tofl;

	@Before
	public void setUp() {
		gol = new GameOfLife();
		wid = new GameWithoutDeath();
		hl = new HighLife();
		tofl = new ThreeOrFourLife();
	}

	@Test
	public void testGameOfLife() {
		assertTrue(gol instanceof GameRule);
	}

	@Test
	public void testGameWithoutDeath() {
		assertTrue(wid instanceof GameRule);
	}

	@Test
	public void testHighLife() {
		assertTrue(hl instanceof GameRule);
	}

	@Test
	public void testThreeOrFourLife() {
		assertTrue(tofl instanceof GameRule);
	}

	@Test
	public void shouldBeMarkedToBear() {
		assertTrue(gol.isCellBorn(3));
		assertFalse(gol.isCellBorn(0));

		assertTrue(wid.isCellBorn(3));
		assertFalse(wid.isCellBorn(0));

		assertTrue(hl.isCellBorn(3));
		assertTrue(hl.isCellBorn(6));
		assertFalse(hl.isCellBorn(0));

		assertTrue(tofl.isCellBorn(3));
		assertTrue(tofl.isCellBorn(4));
		assertFalse(tofl.isCellBorn(0));
	}

	@Test
	public void shouldBeMarkedtoStayAlive() {
		assertFalse(gol.isCellStayingAlive(0));
		assertFalse(gol.isCellStayingAlive(1));
		assertTrue(gol.isCellStayingAlive(2));
		assertTrue(gol.isCellStayingAlive(3));
		assertFalse(gol.isCellStayingAlive(4));
		assertFalse(gol.isCellStayingAlive(5));
		assertFalse(gol.isCellStayingAlive(6));
		assertFalse(gol.isCellStayingAlive(7));
		assertFalse(gol.isCellStayingAlive(8));

		assertTrue(wid.isCellStayingAlive(0));
		assertTrue(wid.isCellStayingAlive(1));
		assertTrue(wid.isCellStayingAlive(2));
		assertTrue(wid.isCellStayingAlive(3));
		assertTrue(wid.isCellStayingAlive(4));
		assertTrue(wid.isCellStayingAlive(5));
		assertTrue(wid.isCellStayingAlive(6));
		assertTrue(wid.isCellStayingAlive(7));
		assertTrue(wid.isCellStayingAlive(8));

		assertFalse(hl.isCellStayingAlive(0));
		assertFalse(hl.isCellStayingAlive(1));
		assertTrue(hl.isCellStayingAlive(2));
		assertTrue(hl.isCellStayingAlive(3));
		assertFalse(hl.isCellStayingAlive(4));
		assertFalse(hl.isCellStayingAlive(5));
		assertFalse(hl.isCellStayingAlive(6));
		assertFalse(hl.isCellStayingAlive(7));
		assertFalse(hl.isCellStayingAlive(8));

		assertFalse(tofl.isCellStayingAlive(0));
		assertFalse(tofl.isCellStayingAlive(1));
		assertFalse(tofl.isCellStayingAlive(2));
		assertTrue(tofl.isCellStayingAlive(3));
		assertTrue(tofl.isCellStayingAlive(4));
		assertFalse(tofl.isCellStayingAlive(5));
		assertFalse(tofl.isCellStayingAlive(6));
		assertFalse(tofl.isCellStayingAlive(7));
		assertFalse(tofl.isCellStayingAlive(8));
	}
}
