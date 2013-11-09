package de.nordakademie.java.gameoflife.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

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

		for (int currentRow = 0; currentRow < rows; currentRow++) {
			for (int currentColumn = 0; currentColumn < columns; currentColumn++) {
				paintCell(currentRow, currentColumn, (Graphics2D) g);
			}
		}

	}

	private void paintCell(Integer row, Integer column, Graphics2D g) {
		if (lifeStatus[row][column]) {
			g.setColor(Color.RED);
		} else {
			g.setColor(Color.BLACK);
		}

		g.fillRect(row * CELL_DRAWING_SIZE, column * CELL_DRAWING_SIZE,
				CELL_DRAWING_SIZE, CELL_DRAWING_SIZE);
	}
}
