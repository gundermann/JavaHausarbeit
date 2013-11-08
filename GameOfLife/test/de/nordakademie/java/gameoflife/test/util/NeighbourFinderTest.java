package de.nordakademie.java.gameoflife.test.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.nordakademie.java.gameoflife.business.Cell;
import de.nordakademie.java.gameoflife.business.CellGrid;
import de.nordakademie.java.gameoflife.test.CellGridTestHelper;
import de.nordakademie.java.gameoflife.utils.NeighbourFinder;

public class NeighbourFinderTest {

	private NeighbourFinder neighbourFinder;
	private CellGrid cellGrid;

	@Before
	public void setUp() {
		cellGrid = CellGridTestHelper.getMockedCellGrid();
	}

	@Test
	public void shouldFindNeighboursInTheMiddle() {
		List<Cell> neighbours = new ArrayList<>();
		neighbourFinder = new NeighbourFinder(false);
		neighbours.addAll(neighbourFinder.getNeighbours(1, 1, cellGrid));

		assertTrue(neighbours.size() == 8);
		assertTrue(neighbours.contains(cellGrid.getCellAtPosition(0, 0)));
		assertTrue(neighbours.contains(cellGrid.getCellAtPosition(0, 1)));
		assertTrue(neighbours.contains(cellGrid.getCellAtPosition(0, 2)));
		assertTrue(neighbours.contains(cellGrid.getCellAtPosition(1, 0)));
		assertTrue(neighbours.contains(cellGrid.getCellAtPosition(1, 2)));
		assertTrue(neighbours.contains(cellGrid.getCellAtPosition(2, 0)));
		assertTrue(neighbours.contains(cellGrid.getCellAtPosition(2, 1)));
		assertTrue(neighbours.contains(cellGrid.getCellAtPosition(2, 2)));
	}

	@Test
	public void shouldFindNoNeighbours() {
		Cell cell1 = new Cell();

		cellGrid = Mockito.mock(CellGrid.class);
		Mockito.when(cellGrid.getColumnCount()).thenReturn(1);
		Mockito.when(cellGrid.getRowCount()).thenReturn(1);
		Mockito.when(cellGrid.getCellAtPosition(0, 0)).thenReturn(cell1);

		List<Cell> neighbours = new ArrayList<>();
		neighbourFinder = new NeighbourFinder(true);
		neighbours.addAll(neighbourFinder.getNeighbours(0, 0, cellGrid));

		assertTrue(neighbours.size() == 8);
		for (Cell neighbour : neighbours) {
			assertNotNull(neighbour);
		}
	}

	@Test
	public void shouldFindYourselfAsNeighbours() {
		Cell cell1 = new Cell();

		cellGrid = Mockito.mock(CellGrid.class);
		Mockito.when(cellGrid.getColumnCount()).thenReturn(1);
		Mockito.when(cellGrid.getRowCount()).thenReturn(1);
		Mockito.when(cellGrid.getCellAtPosition(0, 0)).thenReturn(cell1);

		List<Cell> neighbours = new ArrayList<>();
		neighbourFinder = new NeighbourFinder(false);
		neighbours.addAll(neighbourFinder.getNeighbours(0, 0, cellGrid));

		assertTrue(neighbours.size() == 8);
		for (Cell neighbour : neighbours) {
			assertTrue(neighbour == cell1);
		}
	}

	@Test
	public void shouldFindNeighboursOnTheBorderWithWallOfDeath() {
		List<Cell> neighbours = new ArrayList<>();
		neighbourFinder = new NeighbourFinder(true);
		neighbours.addAll(neighbourFinder.getNeighbours(0, 0, cellGrid));

		assertTrue(neighbours.size() == 8);
		assertTrue(neighbours.contains(cellGrid.getCellAtPosition(1, 1)));
		assertTrue(neighbours.contains(cellGrid.getCellAtPosition(0, 1)));
		assertFalse(neighbours.contains(cellGrid.getCellAtPosition(0, 2)));
		assertTrue(neighbours.contains(cellGrid.getCellAtPosition(1, 0)));
		assertFalse(neighbours.contains(cellGrid.getCellAtPosition(1, 2)));
		assertFalse(neighbours.contains(cellGrid.getCellAtPosition(2, 0)));
		assertFalse(neighbours.contains(cellGrid.getCellAtPosition(2, 1)));
		assertFalse(neighbours.contains(cellGrid.getCellAtPosition(2, 2)));
	}

	@Test
	public void shouldFindNeighboursOnTheBorderWithPacman() {
		List<Cell> neighbours = new ArrayList<>();
		neighbourFinder = new NeighbourFinder(false);
		neighbours.addAll(neighbourFinder.getNeighbours(0, 0, cellGrid));

		assertTrue(neighbours.size() == 8);
		assertTrue(neighbours.contains(cellGrid.getCellAtPosition(1, 1)));
		assertTrue(neighbours.contains(cellGrid.getCellAtPosition(0, 1)));
		assertTrue(neighbours.contains(cellGrid.getCellAtPosition(0, 2)));
		assertTrue(neighbours.contains(cellGrid.getCellAtPosition(1, 0)));
		assertTrue(neighbours.contains(cellGrid.getCellAtPosition(1, 2)));
		assertTrue(neighbours.contains(cellGrid.getCellAtPosition(2, 0)));
		assertTrue(neighbours.contains(cellGrid.getCellAtPosition(2, 1)));
		assertTrue(neighbours.contains(cellGrid.getCellAtPosition(2, 2)));
	}

}
