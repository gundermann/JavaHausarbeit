package de.nordakademie.java.gameoflife.business.impl;

import de.nordakademie.java.gameoflife.business.Cell;

/*
 * Diese Klasse bildet eine Zelle ab.
 * 
 * @author niels.gundermann
 */
public class CellImpl implements Cell {

	private boolean alive;

	public CellImpl() {
		alive = false;
	}

	@Override
	public boolean isAlive() {
		return alive;
	}

	@Override
	public void bear() {
		alive = true;
	}

	@Override
	public void killYourself() {
		alive = false;
	}

}
