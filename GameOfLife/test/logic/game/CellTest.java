package logic.game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.nordakademie.java.gameoflife.logic.game.Cell;

public class CellTest {
	
	Cell cell;
	
	@Before
	public void setUp(){
		cell = new Cell();
	}

	@Test
	public void newCell() {
		assertNotNull(cell);
	}
	
	@Test 
	public void newCellIsDead(){
		assertFalse(cell.isAlive());
	}
	
	@Test
	public void bearCell(){
		cell.bear();
		assertTrue(cell.isAlive());
	}
	
	@Test
	public void killCell(){
		cell.killYourself();
		assertFalse(cell.isAlive());
	}

}
