package rules.game;

import static org.junit.Assert.*;

import org.junit.Test;

import de.nordakademie.java.gameoflife.rules.game.GameOfLife;
import de.nordakademie.java.gameoflife.rules.game.GameRule;
import de.nordakademie.java.gameoflife.rules.game.GameWithoutDeath;
import de.nordakademie.java.gameoflife.rules.game.HighLife;
import de.nordakademie.java.gameoflife.rules.game.ThreeOrFourLife;


public class GameRuleExtentionsTest {

	@Test
	public void testGameOfLife() {
		GameOfLife gol = new GameOfLife();
		assertTrue(gol instanceof GameRule);		
	}
	
	@Test
	public void testGameWithoutDeath() {
		GameWithoutDeath gol = new GameWithoutDeath();
		assertTrue(gol instanceof GameRule);		
	}

	@Test
	public void testHighLife() {
		HighLife gol = new HighLife();
		assertTrue(gol instanceof GameRule);		
	}
	
	@Test
	public void testThreeOrFourLife() {
		ThreeOrFourLife gol = new ThreeOrFourLife();
		assertTrue(gol instanceof GameRule);		
	}
}
