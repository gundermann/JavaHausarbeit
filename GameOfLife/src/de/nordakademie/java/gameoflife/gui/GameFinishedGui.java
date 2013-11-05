package de.nordakademie.java.gameoflife.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.nordakademie.java.gameoflife.utils.WindowPositionHelper;

public class GameFinishedGui extends JFrame{

	public GameFinishedGui() {
		this.setTitle("Spielende");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 200);
		this.setLayout(new BorderLayout());
		this.setResizable(false);

		this.setLocation(WindowPositionHelper.getCenterPoint(this));

		JPanel title = createTitlePanel();
		JPanel textPanel = createErrorTextPanel("Der Spielstand \u00e4ndert sich nicht mehr. Das Spiel ist zuende.");

		this.add(title, BorderLayout.NORTH);
		this.add(textPanel, BorderLayout.CENTER);
		this.add(initOkButton(), BorderLayout.SOUTH);

		this.setVisible(true);
	}

	private JButton initOkButton() {
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				closeFinishedGui();
			}
		});
		return okButton;
	}
	
	private void closeFinishedGui() {
		this.dispose();
	}

	private JPanel createErrorTextPanel(String errortext) {
		JPanel textPanel = new JPanel();
		textPanel.add(new JTextArea(errortext));
		return textPanel;
	}

	private JPanel createTitlePanel() {
		JPanel title = new JPanel();
		title.add(new JLabel("Das Spiel ist beendet!"));
		return title;
	}
}


