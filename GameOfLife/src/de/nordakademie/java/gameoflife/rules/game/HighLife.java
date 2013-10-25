package de.nordakademie.java.gameoflife.rules.game;

import de.nordakademie.java.gameoflife.rules.GameRule;

public class HighLife implements GameRule {

	@Override
	public boolean isCellStayingAlive(Integer neighbours) {
		return (neighbours == 3 || neighbours == 2);
	}

	@Override
	public boolean isCellBorn(Integer neighbours) {
		return (neighbours == 3 || neighbours == 6);
	}

}
