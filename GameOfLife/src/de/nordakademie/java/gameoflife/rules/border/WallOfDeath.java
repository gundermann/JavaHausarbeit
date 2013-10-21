package de.nordakademie.java.gameoflife.rules.border;

public class WallOfDeath implements BorderRule {

	@Override
	public boolean isGridBoarderDead() {
		return true;
	}

}
