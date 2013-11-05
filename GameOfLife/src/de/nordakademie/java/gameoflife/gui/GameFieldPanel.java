package de.nordakademie.java.gameoflife.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/*
 * GUI-Komponente auf der das Universum abgebildet wird.
 * 
 * @author Christian Leppelt
 */

class GameFieldPanel extends JPanel {

	private boolean[][] cellsArray;
	private final int CELL_DRAWING_SIZE = 10;

	public GameFieldPanel(boolean[][] currentCellArray) {
		cellsArray = currentCellArray;
	}

	public void updateCellArray(boolean[][] currentCellArray) {
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

		for (int currentColumn = 0; currentColumn < rows; currentColumn++) {
			for (int currentRow = 0; currentRow < columns; currentRow++) {
				if (cellsArray[currentColumn][currentRow]) {
					g.setColor(Color.RED);
					// TODO würde ich gerne rauslassen, aber wie sonst?
					// } else {
					// g.setColor(Color.BLACK);
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
