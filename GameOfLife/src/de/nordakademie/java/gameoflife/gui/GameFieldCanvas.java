package de.nordakademie.java.gameoflife.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import de.nordakademie.java.gameoflife.business.Cell;

class GameFieldCanvas extends Canvas {

	private Cell[][] cellsArray;
	private final int cellDrawingSize = 10;

	public void updateGameFieldCanvas(Cell[][] currentCellArray) {
		cellsArray = currentCellArray;
	}

	public int getCellsDrawingSize() {
		return cellDrawingSize;
	}

	public void paintCellGrid() {
		Graphics2D g = (Graphics2D) getBufferStrategy().getDrawGraphics();

		int rows = cellsArray.length;
		int columns = 0;
		if (rows > 0) {
			columns = cellsArray[0].length;
		}
		for (int currentColumn = 0; currentColumn < rows; currentColumn++) {
			for (int currentRow = 0; currentRow < columns; currentRow++) {
				if (cellsArray[currentColumn][currentRow].isAlive()) {
					g.setColor(Color.RED);
				} else {
					g.setColor(Color.BLACK);
				}
				paintCell(currentColumn * cellDrawingSize, currentRow
						* cellDrawingSize, g);
			}
		}

		getBufferStrategy().show();
		g.dispose();

	}

	private void paintCell(Integer xCoordinate, Integer yCoordinate, Graphics g) {
		g.fillRect(xCoordinate, yCoordinate, cellDrawingSize, cellDrawingSize);
	}
}
