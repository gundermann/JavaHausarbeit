package de.nordakademie.java.gameoflife.business.rules.border;

import de.nordakademie.java.gameoflife.business.rules.BorderRule;

/**
 * Regeln fürs Universum. Der Rand des Universums wird überlaufen.
 * 
 * @autor Kathrin Kurtz
 */
public class PacmanStyle implements BorderRule {

	@Override
	public boolean isGridBorderDead() {
		return false;
	}

}
