package de.nordakademie.java.gameoflife.business.rules.border;

import de.nordakademie.java.gameoflife.business.rules.BorderRule;

public class PacmanStyle implements BorderRule {

	@Override
	public boolean isGridBorderDead() {
		return false;
	}

}
