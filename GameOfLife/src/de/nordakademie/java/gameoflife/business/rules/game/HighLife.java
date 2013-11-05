package de.nordakademie.java.gameoflife.business.rules.game;

import de.nordakademie.java.gameoflife.business.rules.GameRule;

/*
 * Game of Life Regeln
 * Zelle wir belebt wenn sie 3 oder 6 lebende Nachbarn hat.
 * Zelle bleibt am leben wenn sie 2 oder 3 Nachbarn hat.
 * 
 * @autor Frauke Trautmann
 */
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
