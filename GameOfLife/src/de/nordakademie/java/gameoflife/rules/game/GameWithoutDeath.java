package de.nordakademie.java.gameoflife.rules.game;

public class GameWithoutDeath implements GameRule {

	@Override
	public int getMaxNeighboursToStayAlive() {
		return 8;
	}

	@Override
	public boolean getNeighboursToBear(Integer numberOfNeighbours) {
		return numberOfNeighbours == 3;
	}

	@Override
	public int getMinNeigboursToStayAlive() {
		return 0;
	}

}
