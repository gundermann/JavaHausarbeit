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

public class GameFinishedGui {
	private final JFrame frame;

	public GameFinishedGui() {
		frame = new JFrame("Spielende");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 200);
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);

		setWindowIntoScreenCenter(frame);

		JPanel title = createTitlePanel();
		JPanel textPanel = createErrorTextPanel("Der Spielstand \u00e4ndert sich nicht mehr. Das Spiel ist zuende.");

		frame.add(title, BorderLayout.NORTH);
		frame.add(textPanel, BorderLayout.CENTER);
		frame.add(initOkButton(), BorderLayout.SOUTH);

		frame.setVisible(true);
	}

	private JButton initOkButton() {
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		return okButton;
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

	private void setWindowIntoScreenCenter(JFrame frame) {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width) / 2,
				(d.height - frame.getSize().height) / 2);
	}
}


