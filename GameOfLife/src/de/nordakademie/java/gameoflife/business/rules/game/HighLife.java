package de.nordakademie.java.gameoflife.business.rules.game;

import de.nordakademie.java.gameoflife.business.rules.GameRule;

public class HighLife implements GameRule {

	public HighLife() {

	}

	@Override
	public boolean isCellStayingAlive(Integer neighbours) {
		return (neighbours == 3 || neighbours == 2);
	}

	@Override
	public boolean isCellBorn(Integer neighbours) {
		return (neighbours == 3 || neighbours == 6);
	}

}
