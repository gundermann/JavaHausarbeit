package de.nordakademie.java.gameoflife.rules.game;

public class GameWithoutDeath implements GameRule {

	@Override
	public boolean iaCellStayingAlive(Integer numberOfNeighbours) {
		return true;
	}

	@Override
	public boolean isCellBorn(Integer numberOfNeighbours) {
		return numberOfNeighbours == 3;
	}

}
