package de.nordakademie.java.gameoflife.rules.game;

public interface GameRule {
	
	public boolean isCellStayingAlive(Integer numberOfNeighbours);
	public boolean isCellBorn(Integer numberOfNeighbours);
}
