package de.nordakademie.java.gameoflife.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


	class GameFieldCanvas extends JPanel {
//TODO: ein Canvas ist eine AWT-Komponente, der Rest der verwendeten Komponenten ist Swing, Vermischung ist jedoch nicht erwünscht!
		Integer [][] cellsAliveArray = new Integer [0][0];
				
		public GameFieldCanvas(Integer [][] initialCellArray){
			cellsAliveArray = initialCellArray;
		}
		
	   public void updateGameFieldCanvas(Integer [][] currentCellArray) {
		   cellsAliveArray = currentCellArray;
	   }
	   
	   public void paint(Graphics g){
		  super.paint(g);
		  Graphics2D g2d = (Graphics2D) g;
		  g2d.setColor(Color.black); 
		  		   
		  int colums = cellsAliveArray.length;
		  int rows=0;
		  //TODO: wird im Uploader abgefangen, muss es falls unterwegs ein Fehler passiert trotzdem getestet werden?
		  if(colums>0){ 
			  rows = cellsAliveArray[0].length;
		  }
		  
	    for(int i=0; i<colums; i++){
			   for(int x=0; x<rows; x++){
				   if(cellsAliveArray[i][x] == 1){
					   paintCell( i, x, g2d);
				   }
			   }
		   }  
	   }
	   
	 @Override
	   public void repaint(){
	 }
	   
	   private void paintCell(Integer xCoordinate, Integer yCoordinate, Graphics2D graph){
		  graph.fillRect(xCoordinate, yCoordinate, 1, 1);
	   }
	}
