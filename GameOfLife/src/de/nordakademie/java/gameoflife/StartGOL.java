package de.nordakademie.java.gameoflife;


import de.nordakademie.java.gameoflife.gui.ErrorGui;
import de.nordakademie.java.gameoflife.gui.GameFieldGui;
import de.nordakademie.java.gameoflife.gui.StartMenuGui;
import de.nordakademie.java.gameoflife.logic.game.Cell;


public class StartGOL {
	public static void main( String[] args)	{
		StartMenuGui gui = new StartMenuGui();
		gui.initStartMenuGUI();
		ErrorGui error = new ErrorGui();
		error.initErrorGui(" Dies ist ein etwas längerer Test Fehlertext. \n da muss noch was hin, und noch ein bisschen ...");
		GameFieldGui gameField = new GameFieldGui();
		gameField.setCellGeneration(3);
		
		Integer [][] array = new Integer[1][250000];
		for(int i = 0; i<1; i++){
			for(int x = 0; x<250000; x++){
				array[i][x]=0;
			}
		}
		array[0][3]=1;
		array[0][249999]=1;
		array[0][249998]=1;
		array[0][249997]=1;
 		gameField.initGameFieldGui(array);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i<1; i++){
			for(int x = 0; x<10; x++){
				array[i][x]=1;
			}
		}
		gameField.updateGameFieldGui(array);
		
	}
	
}
