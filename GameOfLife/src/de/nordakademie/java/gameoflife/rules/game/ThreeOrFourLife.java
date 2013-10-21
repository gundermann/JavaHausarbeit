package de.nordakademie.java.gameoflife.rules.game;

public class ThreeOrFourLife implements GameRule {

	@Override
	public int getMaxNeighboursToStayAlive() {
		return 4;
	}

	@Override
	public boolean getNeighboursToBear(Integer numberOfNeighbours) {
		return (numberOfNeighbours == 4 || numberOfNeighbours == 3);
	}

	@Override
	public int getMinNeigboursToStayAlive() {
		return 3;
	}

}
