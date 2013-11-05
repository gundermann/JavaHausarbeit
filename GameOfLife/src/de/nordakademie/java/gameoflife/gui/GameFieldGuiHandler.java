package de.nordakademie.java.gameoflife.gui;

import de.nordakademie.java.gameoflife.business.Cell;

public interface GameFieldGuiHandler {
   
    void updateGameFieldGui(Cell[][] cells, Integer cellGeneration);
    long getSliderPosition();
}
