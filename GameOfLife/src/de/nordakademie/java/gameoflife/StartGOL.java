package de.nordakademie.java.gameoflife;


import de.nordakademie.java.gameoflife.gui.ErrorGui;
import de.nordakademie.java.gameoflife.gui.GameFieldGui;
import de.nordakademie.java.gameoflife.gui.StartMenuGui;


public class StartGOL {
	public static void main( String[] args)	{
		StartMenuGui gui = new StartMenuGui();
		gui.initStartMenuGUI();
		ErrorGui error = new ErrorGui();
		error.initErrorGui("Test Fehlertext");
		GameFieldGui gameField = new GameFieldGui();
		gameField.initGameFieldGui();
	}
	
}
