package de.nordakademie.java.gameoflife.business.rules.border;

import de.nordakademie.java.gameoflife.business.rules.BorderRule;

/**
 * Regeln fürs Universum. Der Rand des Universums wird nicht überlaufen.
 * 
 * @autor Frauke Trautmann
 */
public class WallOfDeath implements BorderRule {

	@Override
	public boolean isGridBorderDead() {
		return true;
	}

}
