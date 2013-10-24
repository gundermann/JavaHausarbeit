package de.nordakademie.java.gameoflife.logic.game;

import java.util.List;

public interface NeighbourFinder {
	List<Cell> getNeighbours(Cell cell, CellGrid cellGrid);
}
