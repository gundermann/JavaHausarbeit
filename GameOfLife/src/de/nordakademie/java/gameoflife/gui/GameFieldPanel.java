package de.nordakademie.java.gameoflife.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import de.nordakademie.java.gameoflife.business.Cell;

class GameFieldPanel extends JPanel {

	private Cell[][] cellsArray;
	private final int cellDrawingSize = 10;
	
	public GameFieldPanel(Cell[][] currentCellArray){
		cellsArray = currentCellArray;
	}
	
	public void updateCellArray(Cell[][] currentCellArray) {
		cellsArray = currentCellArray;
	}

	public int getCellsDrawingSize() {
		return cellDrawingSize;
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
				if (cellsArray[currentColumn][currentRow].isAlive()) {
					g.setColor(Color.RED);
				} else {
					g.setColor(Color.BLACK);
				}
				paintCell(currentRow * cellDrawingSize, currentColumn
						* cellDrawingSize, g);
			}
		}
	}

	private void paintCell(Integer xCoordinate, Integer yCoordinate, Graphics g) {
		g.fillRect(xCoordinate, yCoordinate, cellDrawingSize, cellDrawingSize);
	}
}
