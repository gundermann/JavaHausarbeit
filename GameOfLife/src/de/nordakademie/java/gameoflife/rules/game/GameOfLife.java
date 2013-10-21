package de.nordakademie.java.gameoflife.rules.game;

public class GameOfLife implements GameRule{

	@Override
	public boolean iaCellStayingAlive(Integer numberOfNeighbours) {
		return (numberOfNeighbours == 3 || numberOfNeighbours == 2);
	}

	@Override
	public boolean isCellBorn(Integer numberOfNeighbours) {
		return numberOfNeighbours == 3;
	}
	
	

}
