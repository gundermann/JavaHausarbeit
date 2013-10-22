package de.nordakademie.java.gameoflife.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;


	class GameFieldCanvas extends Canvas {

		Integer [][] cellsAliveArray = new Integer [0][0];
				
		public GameFieldCanvas(Integer [][] initialCellArray){
			cellsAliveArray = initialCellArray;
		}
		
	   public void updateGameFieldCanvas(Integer [][] currentCellArray) {
		   cellsAliveArray = currentCellArray;
	   }
	   
	   public void paint(Graphics g){
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
					   paintCell((float) i,(float) x, g2d);
				   }
			   }
		   }  
	   }
	   
	   private void paintCell(Float xCoordinate, Float yCoordinate, Graphics2D graph){
		  graph.fill(new Rectangle2D.Float(xCoordinate,yCoordinate,1.0f,1.0f));
	   }
	}
