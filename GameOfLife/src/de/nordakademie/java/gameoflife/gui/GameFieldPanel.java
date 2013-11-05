package de.nordakademie.java.gameoflife.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import de.nordakademie.java.gameoflife.business.Cell;

/*
 * GUI-Komponente auf der das Universum abgebildet wird.
 * 
 * @author Christian Leppelt
 */

class GameFieldPanel extends JPanel {

	private Cell[][] cellsArray;
	private final int CELL_DRAWING_SIZE = 10;

	public GameFieldPanel(Cell[][] currentCellArray) {
		cellsArray = currentCellArray;
	}

	public void updateCellArray(Cell[][] currentCellArray) {
		cellsArray = currentCellArray;
	}

	public int getCellsDrawingSize() {
		return CELL_DRAWING_SIZE;
	}

	@Override
	public void paintComponent(Graphics g) {

		int rows = cellsArray.length;
		int columns = 0;
		if (rows > 0) {
			columns = cellsArray[0].length;
		}

		g.setColor(Color.RED);
		for (int currentColumn = 0; currentColumn < rows; currentColumn++) {
			for (int currentRow = 0; currentRow < columns; currentRow++) {
				if (cellsArray[currentColumn][currentRow].isAlive()) {
					paintCell(currentRow * CELL_DRAWING_SIZE, currentColumn
							* CELL_DRAWING_SIZE, g);
				}
			}
		}
	}

	private void paintCell(Integer xCoordinate, Integer yCoordinate, Graphics g) {
		g.fillRect(xCoordinate, yCoordinate, CELL_DRAWING_SIZE,
				CELL_DRAWING_SIZE);
	}
}
