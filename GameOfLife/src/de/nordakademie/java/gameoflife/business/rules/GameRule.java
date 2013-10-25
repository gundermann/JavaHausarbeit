package de.nordakademie.java.gameoflife.business.rules;

public interface GameRule {
	
	boolean isCellStayingAlive(Integer neighbours);
	boolean isCellBorn(Integer neighbours);
}
