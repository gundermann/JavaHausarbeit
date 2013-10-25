package de.nordakademie.java.gameoflife.rules;

public interface GameRule {
	
	boolean isCellStayingAlive(Integer neighbours);
	boolean isCellBorn(Integer neighbours);
}
