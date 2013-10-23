package de.nordakademie.java.gameoflife.rules.game;

public interface GameRule {
	
	boolean isCellStayingAlive(Integer neighbours);
	boolean isCellBorn(Integer neighbours);
}
