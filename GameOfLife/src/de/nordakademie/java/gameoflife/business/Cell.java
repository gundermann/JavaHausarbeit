package de.nordakademie.java.gameoflife.business;

public class Cell {

	private boolean alive;
	private int[] position;

	public Cell(int row, int column) {
		alive = false;
		position = new int[]{row, column};
	}
	
	public int[] getPosition(){
		return position;
	}

	public boolean isAlive() {
		return alive;
	}

	public void bear() {
		alive = true;
	}

	public void killYourself() {
		alive = false;
	}
}
