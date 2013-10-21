package de.nordakademie.java.gameoflife.rules.game;

public class HighLife implements GameRule {

	@Override
	public boolean isCellStayingAlive(Integer numberOfNeighbours) {
		return (numberOfNeighbours == 3 || numberOfNeighbours == 2);
	}

	@Override
	public boolean isCellBorn(Integer numberOfNeighbours) {
		return (numberOfNeighbours == 3 || numberOfNeighbours == 6);
	}

}
