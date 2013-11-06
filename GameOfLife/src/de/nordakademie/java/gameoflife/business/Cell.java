package de.nordakademie.java.gameoflife.business;

/**
 * Abbildung einer Zelle.
 * 
 * @autor Niels Gundermann
 */

public class Cell {

	private boolean alive;

	public Cell() {
		alive = false;
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
