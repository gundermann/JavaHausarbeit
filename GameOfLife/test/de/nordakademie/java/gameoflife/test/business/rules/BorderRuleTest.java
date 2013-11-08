package de.nordakademie.java.gameoflife.test.business.rules;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.nordakademie.java.gameoflife.business.rules.BorderRule;
import de.nordakademie.java.gameoflife.business.rules.border.PacmanStyle;
import de.nordakademie.java.gameoflife.business.rules.border.WallOfDeath;

public class BorderRuleTest {

	private PacmanStyle pacman;
	private WallOfDeath wod;

	@Before
	public void setUp() {
		pacman = new PacmanStyle();
		wod = new WallOfDeath();
	}

	@Test
	public void testPacmanStyle() {
		assertTrue(pacman instanceof BorderRule);
	}

	@Test
	public void testWallOfDeath() {
		assertTrue(wod instanceof BorderRule);
	}

	@Test
	public void borderShouldNotBeDead() {
		assertFalse(pacman.isGridBorderDead());
	}

	@Test
	public void borderShouldBeDead() {
		assertTrue(wod.isGridBorderDead());
	}

}
