package de.nordakademie.java.gameoflife.rules.game;

import de.nordakademie.java.gameoflife.rules.GameRule;

public class GameWithoutDeath implements GameRule {

	@Override
	public boolean isCellStayingAlive(Integer neighbours) {
		return true;
	}

	@Override
	public boolean isCellBorn(Integer neighbours) {
		return neighbours == 3;
	}

}
