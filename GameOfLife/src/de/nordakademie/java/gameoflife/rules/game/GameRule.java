package de.nordakademie.java.gameoflife.rules.game;

public interface GameRule {
	
	public int getMaxNeighboursToStayAlive();
	public boolean getNeighboursToBear(Integer numberOfNeighbours);
	public int getMinNeigboursToStayAlive();
	
	

}
