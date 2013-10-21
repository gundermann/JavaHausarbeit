package de.nordakademie.java.gameoflife.rules.game;

public class HighLife implements GameRule {

	@Override
	public int getMaxNeighboursToStayAlive() {
		return 3;
	}

	@Override
	public boolean getNeighboursToBear(Integer numberOfNeighbours) {
		
		return (numberOfNeighbours == 3 || numberOfNeighbours == 6);
	}

	@Override
	public int getMinNeigboursToStayAlive() {
		return 2;
	}

}
