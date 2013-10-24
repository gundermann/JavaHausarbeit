package de.nordakademie.java.gameoflife.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class StartMenuGui {

	JFrame frame;
	JComboBox<String> chooseRule;
	JComboBox<String> chooseBorder;
	GridBagLayout gameChooseOptionLayout;
	JButton fileUploadButton;
	JTextField fileUploadPath;
	JLabel gameChoose;
	JLabel borderChoose;
	JLabel gameConstructions;
	JButton explaneGameRules;
	JButton explaneBorderRules;

	public StartMenuGui() {
		frame = new JFrame("Game of Life");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width) / 2,
				(d.height - frame.getSize().height) / 2);

		JLabel headline = initHeadline();
		JPanel buttonPanel = initButtonPanel();
		JPanel gameChooseOptions = initGameChooseOptions();

		frame.add(headline, BorderLayout.NORTH);
		frame.add(gameChooseOptions, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.setVisible(true);
		frame.pack();
	}

	private JPanel initButtonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 2));

		ActionListener closeSide = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		};

		JButton close = new JButton("Beenden");
		close.addActionListener(closeSide);

		JButton button = new JButton("Spielstart");
		buttonPanel.add(button);
		buttonPanel.add(close);
		return buttonPanel;
	}

	private JLabel initHeadline() {
		JLabel headline = new JLabel("Game of Life");
		headline.setHorizontalAlignment(JLabel.CENTER);
		return headline;
	}

	private JPanel initGameChooseOptions() {
		JPanel gameChooseOptions = new JPanel();
		gameChooseOptionLayout = new GridBagLayout();
		gameChooseOptions.setLayout(gameChooseOptionLayout);
		gameChooseOptions.setBorder(new EtchedBorder(Color.BLACK, Color.BLUE));

		initChooseGameAndBorderVariants();
		initGameChooseOptionLabels();
		initGameChooseOptionButtons();
		initGameChooseOptionUploadField();

		gameChooseOptions.add(fileUploadPath);
		gameChooseOptions.add(fileUploadButton);
		gameChooseOptions.add(gameConstructions);
		gameChooseOptions.add(gameChoose);
		gameChooseOptions.add(explaneGameRules);
		gameChooseOptions.add(borderChoose);
		gameChooseOptions.add(explaneBorderRules);
		gameChooseOptions.add(chooseRule);
		gameChooseOptions.add(chooseBorder);
		return gameChooseOptions;
	}

	private void initGameChooseOptionUploadField() {
		fileUploadPath = new JTextField();
		fileUploadPath.setEnabled(false);
		gameChooseOptionLayout.setConstraints(fileUploadPath, set(1, 1, 0, 2));
	}

	private void initGameChooseOptionButtons() {
		explaneGameRules = new JButton("?");
		gameChooseOptionLayout
				.setConstraints(explaneGameRules, set(4, 2, 0, 1));

		explaneBorderRules = new JButton("?");
		gameChooseOptionLayout.setConstraints(explaneBorderRules,
				set(4, 3, 0, 1));

		fileUploadButton = new JButton("Datei hochladen ...");
		gameChooseOptionLayout
				.setConstraints(fileUploadButton, set(3, 1, 0, 2));

	}

	private void initGameChooseOptionLabels() {
		gameConstructions = new JLabel(
				"Bitte wählen sie Spiel- und Randvariante aus");
		gameChooseOptionLayout.setConstraints(gameConstructions,
				set(0, 0, 0, 6));
		gameChoose = new JLabel("Spielvarianten");
		gameChooseOptionLayout.setConstraints(gameChoose, set(3, 2, 0, 1));
		borderChoose = new JLabel("Randvarianten");
		gameChooseOptionLayout.setConstraints(borderChoose, set(3, 3, 0, 1));
	}

	private void initChooseGameAndBorderVariants() {
		chooseRule = new JComboBox<String>();
		chooseRule.addItem("Game of Life");
		chooseRule.addItem("Game without Death");
		chooseRule.addItem("Three or four to life");
		chooseRule.addItem("HighLife");
		gameChooseOptionLayout.setConstraints(chooseRule, set(0, 2, 0, 3));

		chooseBorder = new JComboBox<String>();
		chooseBorder.addItem("Wall of Death");
		chooseBorder.addItem("Pacman Sytle");
		gameChooseOptionLayout.setConstraints(chooseBorder, set(0, 3, 0, 3));
	}

	private static GridBagConstraints set(int gridx, int gridy, int fill,
			int width) {
		GridBagConstraints dummy = new GridBagConstraints();

		dummy.insets = new Insets(15, 10, 15, 10);
		dummy.gridx = gridx;
		dummy.gridy = gridy;
		dummy.fill = GridBagConstraints.HORIZONTAL;
		dummy.gridwidth = width;
		return dummy;
	}
}
