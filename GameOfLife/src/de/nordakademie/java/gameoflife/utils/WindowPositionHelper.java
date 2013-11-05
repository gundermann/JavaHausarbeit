package de.nordakademie.java.gameoflife.utils;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

/*
 * Berechnet die Position eines Frame sodass dieses in der Mitte des Bildschrims steht.
 * 
 * @author Niels Gundermann
 */
public class WindowPositionHelper {

	public static Point getCenterPoint(JFrame frame) {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width / 2 - frame.getSize().width / 2);
		int y = (d.height / 2 - frame.getSize().height / 2);
		Point centerPoint = new Point(x, y);
		return centerPoint;
	}
}
