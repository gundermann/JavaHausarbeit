package de.nordakademie.java.gameoflife.business.rules.game;

import de.nordakademie.java.gameoflife.business.rules.GameRule;

/**
 * Game of Life Regeln. Zelle wir belebt wenn sie 3 oder 4 lebende Nachbarn hat.
 * Zelle bleibt am leben wenn sie 3 oder 4 Nachbarn hat.
 * 
 * @autor Frauke Trautmann
 */
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
