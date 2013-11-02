package de.nordakademie.java.gameoflife.business;

public interface GameFieldGuiHandler {
   
    void updateGameFieldGui(Cell[][] cells);
    long getSliderPosition();
}
