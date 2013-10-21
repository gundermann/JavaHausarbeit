package rules.border;

import static org.junit.Assert.*;

import org.junit.Test;

import de.nordakademie.java.gameoflife.rules.border.BorderRule;
import de.nordakademie.java.gameoflife.rules.border.PacmanStyle;
import de.nordakademie.java.gameoflife.rules.border.WallOfDeath;



public class BorderRuleExtentionsTest {

	@Test
	public void testPacmanStyle() {
		PacmanStyle border = new PacmanStyle();
		assertTrue(border instanceof BorderRule);		
	}
	
	@Test
	public void testWallOfDeath() {
		WallOfDeath border = new WallOfDeath();
		assertTrue(border instanceof BorderRule);		
	}
	
	
		

}
