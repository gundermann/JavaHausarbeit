package de.nordakademie.java.gameoflife.gui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * Zusammenfassung allgemeiner GUI-Methoden
 * 
 * @author Niels Gundermann
 */
public abstract class GolGui extends JFrame {

	protected void closeGui() {
		this.dispose();
	}

	protected void showGui() {
		this.pack();
		this.setLocation(getCenteredPoint());
		this.setVisible(true);
	}

	private Point getCenteredPoint() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width / 2 - this.getSize().width / 2);
		int y = (d.height / 2 - this.getSize().height / 2);
		Point centerPoint = new Point(x, y);
		return centerPoint;
	}
}
