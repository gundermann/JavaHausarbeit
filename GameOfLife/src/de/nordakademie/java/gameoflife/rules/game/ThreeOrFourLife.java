package de.nordakademie.java.gameoflife.rules.game;

public class ThreeOrFourLife implements GameRule {

	@Override
	public boolean isCellStayingAlive(Integer neighbours) {
		return (neighbours == 4 || neighbours == 3);
	}

	@Override
	public boolean isCellBorn(Integer neighbours) {
		return (neighbours == 4 || neighbours == 3);
	}

}
