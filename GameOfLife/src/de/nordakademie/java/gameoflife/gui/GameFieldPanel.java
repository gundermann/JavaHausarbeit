package de.nordakademie.java.gameoflife.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

class GameFieldPanel extends JPanel {

	private boolean[][] lifeStatus;
	private final int CELL_DRAWING_SIZE = 10;

	public GameFieldPanel(boolean[][] currentCellArray) {
		lifeStatus = currentCellArray;
	}

	public void updateCellArray(boolean[][] currentCellArray) {
		lifeStatus = currentCellArray;
	}

	public int getCellsDrawingSize() {
		return CELL_DRAWING_SIZE;
	}

	@Override
	public void paintComponent(Graphics g) {

		int rows = lifeStatus.length;
		int columns = 0;
		if (rows > 0) {
			columns = lifeStatus[0].length;
		}

		for (int currentColumn = 0; currentColumn < rows; currentColumn++) {
			for (int currentRow = 0; currentRow < columns; currentRow++) {
				if (lifeStatus[currentColumn][currentRow]) {
					g.setColor(Color.RED);
					paintCell(currentRow * CELL_DRAWING_SIZE, currentColumn
							* CELL_DRAWING_SIZE, g);
				} else {
					g.setColor(Color.BLACK);
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
