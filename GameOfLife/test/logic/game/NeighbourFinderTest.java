package logic.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.nordakademie.java.gameoflife.business.Cell;
import de.nordakademie.java.gameoflife.business.CellGrid;
import de.nordakademie.java.gameoflife.utils.NeighbourFinder;

public class NeighbourFinderTest {

	private NeighbourFinder neighbourFinder;
	private CellGrid cellGrid;
	private Cell cell1;
	private Cell cell2;
	private Cell cell3;
	private Cell cell4;
	private Cell cell5;
	private Cell cell6;
	private Cell cell7;
	private Cell cell8;
	private Cell cell9;

	@Before
	public void setUp() {
		cellGrid = Mockito.mock(CellGrid.class);
		cell1 = Mockito.mock(Cell.class);
		cell2 = Mockito.mock(Cell.class);
		cell3 = Mockito.mock(Cell.class);
		cell4 = Mockito.mock(Cell.class);
		cell5 = Mockito.mock(Cell.class);
		cell6 = Mockito.mock(Cell.class);
		cell7 = Mockito.mock(Cell.class);
		cell8 = Mockito.mock(Cell.class);
		cell9 = Mockito.mock(Cell.class);
		initMockedMethod();
	}

	private void initMockedMethod() {
		initGetCellAtPosition();
		initRowAndColumnCount();
		initGetColumnOfCell();
		initGetRowOfCell();
	}

	private void initGetCellAtPosition() {
		Mockito.when(cellGrid.getCellAtCoordinates(0, 0)).thenReturn(cell1);
		Mockito.when(cellGrid.getCellAtCoordinates(0, 1)).thenReturn(cell2);
		Mockito.when(cellGrid.getCellAtCoordinates(0, 2)).thenReturn(cell3);
		Mockito.when(cellGrid.getCellAtCoordinates(1, 0)).thenReturn(cell4);
		Mockito.when(cellGrid.getCellAtCoordinates(1, 1)).thenReturn(cell5);
		Mockito.when(cellGrid.getCellAtCoordinates(1, 2)).thenReturn(cell6);
		Mockito.when(cellGrid.getCellAtCoordinates(2, 0)).thenReturn(cell7);
		Mockito.when(cellGrid.getCellAtCoordinates(2, 1)).thenReturn(cell8);
		Mockito.when(cellGrid.getCellAtCoordinates(2, 2)).thenReturn(cell9);
	}

	private void initRowAndColumnCount() {
		Mockito.when(cellGrid.getColumnCount()).thenReturn(3);
		Mockito.when(cellGrid.getRowCount()).thenReturn(3);
	}

	private void initGetColumnOfCell() {
		Mockito.when(cellGrid.getColumnOfCell(cell1)).thenReturn(0);
		Mockito.when(cellGrid.getColumnOfCell(cell2)).thenReturn(1);
		Mockito.when(cellGrid.getColumnOfCell(cell3)).thenReturn(2);
		Mockito.when(cellGrid.getColumnOfCell(cell4)).thenReturn(0);
		Mockito.when(cellGrid.getColumnOfCell(cell5)).thenReturn(1);
		Mockito.when(cellGrid.getColumnOfCell(cell6)).thenReturn(2);
		Mockito.when(cellGrid.getColumnOfCell(cell7)).thenReturn(0);
		Mockito.when(cellGrid.getColumnOfCell(cell8)).thenReturn(1);
		Mockito.when(cellGrid.getColumnOfCell(cell9)).thenReturn(2);
	}

	private void initGetRowOfCell() {
		Mockito.when(cellGrid.getRowOfCell(cell1)).thenReturn(0);
		Mockito.when(cellGrid.getRowOfCell(cell2)).thenReturn(0);
		Mockito.when(cellGrid.getRowOfCell(cell3)).thenReturn(0);
		Mockito.when(cellGrid.getRowOfCell(cell4)).thenReturn(1);
		Mockito.when(cellGrid.getRowOfCell(cell5)).thenReturn(1);
		Mockito.when(cellGrid.getRowOfCell(cell6)).thenReturn(1);
		Mockito.when(cellGrid.getRowOfCell(cell7)).thenReturn(2);
		Mockito.when(cellGrid.getRowOfCell(cell8)).thenReturn(2);
		Mockito.when(cellGrid.getRowOfCell(cell9)).thenReturn(2);
	}

	@Test
	public void shouldFindNeighboursInTheMiddle() {
		Cell cell = cellGrid.getCellAtCoordinates(1, 1);
		List<Cell> neighbours = new ArrayList<>();
		neighbourFinder = new NeighbourFinder(cellGrid, false);
		neighbours.addAll(neighbourFinder.getNeighbours(cell));

		assertTrue(neighbours.size() == 8);
		assertTrue(neighbours.contains(cellGrid.getCellAtCoordinates(0, 0)));
		assertTrue(neighbours.contains(cellGrid.getCellAtCoordinates(0, 1)));
		assertTrue(neighbours.contains(cellGrid.getCellAtCoordinates(0, 2)));
		assertTrue(neighbours.contains(cellGrid.getCellAtCoordinates(1, 0)));
		assertTrue(neighbours.contains(cellGrid.getCellAtCoordinates(1, 2)));
		assertTrue(neighbours.contains(cellGrid.getCellAtCoordinates(2, 0)));
		assertTrue(neighbours.contains(cellGrid.getCellAtCoordinates(2, 1)));
		assertTrue(neighbours.contains(cellGrid.getCellAtCoordinates(2, 2)));
	}

	@Test
	public void shouldFindNoNeighbours() {
		cellGrid = Mockito.mock(CellGrid.class);
		Mockito.when(cellGrid.getCellAtCoordinates(0, 0)).thenReturn(cell1);
		Mockito.when(cellGrid.getRowOfCell(cell1)).thenReturn(0);
		Mockito.when(cellGrid.getColumnOfCell(cell1)).thenReturn(0);
		Mockito.when(cellGrid.getColumnCount()).thenReturn(1);
		Mockito.when(cellGrid.getRowCount()).thenReturn(1);

		Cell cell = cellGrid.getCellAtCoordinates(0, 0);
		List<Cell> neighbours = new ArrayList<>();
		neighbourFinder = new NeighbourFinder(cellGrid, true);
		neighbours.addAll(neighbourFinder.getNeighbours(cell));

		assertTrue(neighbours.size() == 8);
		for (Cell neighbour : neighbours) {
			assertNull(neighbour);
		}
	}

	@Test
	public void shouldFindNeighboursOnTheBorderWithWallOfDeath() {
		Cell cell = cellGrid.getCellAtCoordinates(0, 0);
		List<Cell> neighbours = new ArrayList<>();
		neighbourFinder = new NeighbourFinder(cellGrid, true);
		neighbours.addAll(neighbourFinder.getNeighbours(cell));

		assertTrue(neighbours.size() == 8);
		assertTrue(neighbours.contains(cellGrid.getCellAtCoordinates(1, 1)));
		assertTrue(neighbours.contains(cellGrid.getCellAtCoordinates(0, 1)));
		assertFalse(neighbours.contains(cellGrid.getCellAtCoordinates(0, 2)));
		assertTrue(neighbours.contains(cellGrid.getCellAtCoordinates(1, 0)));
		assertFalse(neighbours.contains(cellGrid.getCellAtCoordinates(1, 2)));
		assertFalse(neighbours.contains(cellGrid.getCellAtCoordinates(2, 0)));
		assertFalse(neighbours.contains(cellGrid.getCellAtCoordinates(2, 1)));
		assertFalse(neighbours.contains(cellGrid.getCellAtCoordinates(2, 2)));
	}

	@Test
	public void shouldFindNeighboursOnTheBorderWithPacman() {
		Cell cell = cellGrid.getCellAtCoordinates(0, 0);
		List<Cell> neighbours = new ArrayList<>();
		neighbourFinder = new NeighbourFinder(cellGrid, false);
		neighbours.addAll(neighbourFinder.getNeighbours(cell));

		assertTrue(neighbours.size() == 8);
		assertTrue(neighbours.contains(cellGrid.getCellAtCoordinates(1, 1)));
		assertTrue(neighbours.contains(cellGrid.getCellAtCoordinates(0, 1)));
		assertTrue(neighbours.contains(cellGrid.getCellAtCoordinates(0, 2)));
		assertTrue(neighbours.contains(cellGrid.getCellAtCoordinates(1, 0)));
		assertTrue(neighbours.contains(cellGrid.getCellAtCoordinates(1, 2)));
		assertTrue(neighbours.contains(cellGrid.getCellAtCoordinates(2, 0)));
		assertTrue(neighbours.contains(cellGrid.getCellAtCoordinates(2, 1)));
		assertTrue(neighbours.contains(cellGrid.getCellAtCoordinates(2, 2)));
	}

}
