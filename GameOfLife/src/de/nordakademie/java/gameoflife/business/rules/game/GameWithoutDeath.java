package de.nordakademie.java.gameoflife.business.rules.game;

import de.nordakademie.java.gameoflife.business.rules.GameRule;

/**
 * Game of Life Regeln. Zelle wir immer belebt wenn sie 3 lebende Nachbarn hat.
 * Zelle bleibt immer am leben.
 * 
 * @autor Kathrin Kurtz
 */
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
