package de.nordakademie.java.gameoflife.gui;

public interface GameFieldGuiHandler {

	void updateGameFieldGui(boolean[][] cellArray, Integer cellGeneration);

	long getSliderPosition();
}
