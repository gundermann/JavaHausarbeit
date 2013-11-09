package de.nordakademie.java.gameoflife.business.rules.game;

import de.nordakademie.java.gameoflife.business.rules.GameRule;

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
