package logic.game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.nordakademie.java.gameoflife.logic.game.GamePad;

public class GameControlTest {

	GamePad gameControl;
	Integer[][] initinalArray = {{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}};
//	GameRules rules;
	
	@Before
	public void setUp(){
		gameControl = new GamePad(initinalArray);
	}
	
	@Test
	public void shouldInitinalizedCellGrid() {
		assertNotNull(gameControl.getCellGrid());
	}
	
	@Test
	public void shouldInitinalizedRules(){
		
	}
	
	@Test 
	public void shouldCalculateNextGeneration(){
		
	}
}
