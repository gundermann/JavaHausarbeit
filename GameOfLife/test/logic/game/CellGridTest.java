package logic.game;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.nordakademie.java.gameoflife.logic.game.Cell;
import de.nordakademie.java.gameoflife.logic.game.CellGrid;

public class CellGridTest {
	
	CellGrid cellGrid;
	
	@Before
	public void setUp(){
		cellGrid = new CellGrid(3, 4);
	}

	@Test
	public void cellGridDimension() {
		assertTrue(cellGrid.getColumnCount() == 4);
		assertTrue(cellGrid.getRowCount() == 3);
	}
	
	@Test
	public void cellsInitinalized(){
		Cell[][] cellArray = cellGrid.getCellArray();
		
		for(int row = 0; row < cellGrid.getRowCount(); row++){
			for(int column = 0; column < cellGrid.getColumnCount(); column++){
				assertTrue(cellArray[row][column] instanceof Cell);
			}
		}
	}
	
	
	@Test
	public void shouldBearCells(){
		Cell[][] cellArray = cellGrid.getCellArray();
		Cell cell1 = cellArray[0][1];
		Cell cell2 = cellArray[1][2];
		Cell cell3 = cellArray[2][0];
		
		List<Cell> cellsToBear = new ArrayList<Cell>();
		cellsToBear.add(cell1);
		cellsToBear.add(cell2);
		cellsToBear.add(cell3);
		
		cellGrid.bearCells(cellsToBear);
		
		//Muss nocheinaml hinzugefuegt werden, da die Liste nach bearCells leer ist
		cellsToBear.add(cell1);
		cellsToBear.add(cell2);
		cellsToBear.add(cell3);
		
		for(int row = 0; row < cellArray.length; row++){
			for(int column = 0; column < cellArray[0].length; column++){
				if(cellsToBear.contains(cellArray[row][column])){
					assertTrue(cellArray[row][column].isAlive());
				}
				else{
					assertFalse(cellArray[row][column].isAlive());
				}
			}
		}
	}
	
	@Test
	public void shouldKillCells(){
		Cell[][] cellArray = cellGrid.getCellArray();
		Cell cell1 = cellArray[0][1];
		Cell cell2 = cellArray[1][2];
		Cell cell3 = cellArray[2][0];
		
		List<Cell> cellsToKill = new ArrayList<Cell>();
		cellsToKill.add(cell1);
		cellsToKill.add(cell2);
		cellsToKill.add(cell3);
		
		cellGrid.killCells(cellsToKill);
		
		for(int row = 0; row < cellArray.length; row++){
			for(int column = 0; column < cellArray[0].length; column++){
				assertFalse(cellArray[row][column].isAlive());
			}
		}
	}
	

}
