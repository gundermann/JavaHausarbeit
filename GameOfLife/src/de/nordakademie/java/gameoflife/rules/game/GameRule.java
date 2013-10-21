package de.nordakademie.java.gameoflife.rules.game;

public interface GameRule {
	
	public boolean iaCellStayingAlive(Integer numberOfNeighbours);
	public boolean isCellBorn(Integer numberOfNeighbours);
}
