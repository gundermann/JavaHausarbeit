package de.nordakademie.java.gameoflife.rules.game;

public class ThreeOrFourLife implements GameRule {

	@Override
	public boolean isCellStayingAlive(Integer numberOfNeighbours) {
		return (numberOfNeighbours == 4 || numberOfNeighbours == 3);
	}

	@Override
	public boolean isCellBorn(Integer numberOfNeighbours) {
		return (numberOfNeighbours == 4 || numberOfNeighbours == 3);
	}

}
