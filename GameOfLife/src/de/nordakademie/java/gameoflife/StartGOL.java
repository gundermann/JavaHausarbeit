package de.nordakademie.java.gameoflife;

import java.util.HashMap;
import java.util.Map;

import de.nordakademie.java.gameoflife.business.rules.border.PacmanStyle;
import de.nordakademie.java.gameoflife.business.rules.border.WallOfDeath;
import de.nordakademie.java.gameoflife.business.rules.game.GameOfLife;
import de.nordakademie.java.gameoflife.business.rules.game.GameWithoutDeath;
import de.nordakademie.java.gameoflife.business.rules.game.HighLife;
import de.nordakademie.java.gameoflife.business.rules.game.ThreeOrFourLife;

public class StartGOL {

	public static final Map<String, Class> DEFINED_GAME_RULES = new HashMap<String, Class>();
	public static final Map<String, Class> DEFINED_BORDER_RULES = new HashMap<String, Class>();

	public static void main(String[] args) {
		DEFINED_GAME_RULES.put("Three or four life", ThreeOrFourLife.class);
		DEFINED_GAME_RULES.put("High life", HighLife.class);
		DEFINED_GAME_RULES.put("Game without death", GameWithoutDeath.class);
		DEFINED_GAME_RULES.put("Game of Life", GameOfLife.class);

		DEFINED_BORDER_RULES.put("Pacman style", PacmanStyle.class);
		DEFINED_BORDER_RULES.put("Wall of death", WallOfDeath.class);
		new GameInitializer();
	}

}
