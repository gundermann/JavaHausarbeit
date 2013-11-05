package de.nordakademie.java.gameoflife.gui;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.nordakademie.java.gameoflife.utils.WindowPositionHelper;

public class ErrorGui extends JFrame{


	public ErrorGui(String errortext) {
		this.setTitle("Fehlermeldung");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 200);
		this.setLayout(new BorderLayout());
		this.setResizable(false);

		Point centerPoint = WindowPositionHelper.getCenterPoint(this);
		this.setLocation(centerPoint);
		
		JPanel title = createTitlePanel();
		JPanel textPanel = createErrorTextPanel(errortext);

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
				closeErrorGui();
			}

		});
		return okButton;
	}

	private void closeErrorGui() {
		this.dispose();
	}

	private JPanel createErrorTextPanel(String errortext) {
		JPanel textPanel = new JPanel();
		textPanel.add(new JTextArea(errortext));
		return textPanel;
	}

	private JPanel createTitlePanel() {
		JPanel title = new JPanel();
		title.add(new JLabel("Achtung, es ist ein Fehler aufgetreten!"));
		return title;
	}

}
