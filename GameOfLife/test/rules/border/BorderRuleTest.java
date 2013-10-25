package rules.border;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.nordakademie.java.gameoflife.rules.BorderRule;
import de.nordakademie.java.gameoflife.rules.border.PacmanStyle;
import de.nordakademie.java.gameoflife.rules.border.WallOfDeath;



public class BorderRuleTest {
	
	private PacmanStyle pacman;
	private WallOfDeath wod;
	
	@Before
	public void setUp(){
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
	public void borderShouldNotBeDead(){
		assertFalse(pacman.isGridBorderDead());
	}
	
	@Test
	public void borderShouldBeDead(){
		assertTrue(wod.isGridBorderDead());
	}
	
		

}
