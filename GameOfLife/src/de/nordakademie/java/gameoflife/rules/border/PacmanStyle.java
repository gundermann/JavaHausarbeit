package de.nordakademie.java.gameoflife.rules.border;

import de.nordakademie.java.gameoflife.rules.BorderRule;

public class PacmanStyle implements BorderRule {

	@Override
	public boolean isGridBorderDead() {
		return false;
	}

}
