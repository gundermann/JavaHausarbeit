package de.nordakademie.java.gameoflife.gui;

import javax.swing.JFrame;

import de.nordakademie.java.gameoflife.utils.WindowPositionHelper;

public abstract class GolGui extends JFrame {

	protected void closeGui() {
		this.dispose();
	}

	protected void showGui() {
		this.pack();
		this.setLocation(WindowPositionHelper.getCenterPoint(this));
		this.setVisible(true);
	}
}
