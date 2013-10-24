package de.nordakademie.java.gameoflife.rules.game;

public class GameWithoutDeath implements GameRule {

	@Override
	public boolean isCellStayingAlive(Integer numberOfNeighbours) {
		return true;
	}

	@Override
	public boolean isCellBorn(Integer numberOfNeighbours) {
		return numberOfNeighbours == 3;
	}

}
