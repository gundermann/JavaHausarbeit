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
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import de.nordakademie.java.gameoflife.StartGOL;
import de.nordakademie.java.gameoflife.business.CellGrid;
import de.nordakademie.java.gameoflife.business.GamePad;
import de.nordakademie.java.gameoflife.business.rules.BorderRule;
import de.nordakademie.java.gameoflife.business.rules.GameRule;
import de.nordakademie.java.gameoflife.business.rules.border.PacmanStyle;
import de.nordakademie.java.gameoflife.business.rules.border.WallOfDeath;
import de.nordakademie.java.gameoflife.business.rules.game.GameOfLife;
import de.nordakademie.java.gameoflife.business.rules.game.GameWithoutDeath;
import de.nordakademie.java.gameoflife.business.rules.game.HighLife;
import de.nordakademie.java.gameoflife.business.rules.game.ThreeOrFourLife;
import de.nordakademie.java.gameoflife.constants.ErrorCodes;
import de.nordakademie.java.gameoflife.constants.ErrorTexts;
import de.nordakademie.java.gameoflife.utils.fileLoader.FileLoader;

public class StartMenuGui {

	private JFrame frame;
	private JComboBox<String> chooseGameRule;
	private JComboBox<String> chooseBorderRule;
	private GridBagLayout gameChooseOptionLayout;
	private JButton fileUploadButton;
	private JTextField fileUploadPath;
	private JLabel gameChoose;
	private JLabel borderChoose;
	private JLabel gameConstructions;
	private JButton explaneGameRules;
	private JButton explaneBorderRules;
	private int[][] cellArray;

	public StartMenuGui(StartGOL startGOL) {
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

		JButton closeButton = new JButton("Beenden");
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		JButton startButton = new JButton("Spielstart");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (cellArray != null) {

					GamePad gamePad = new GamePad(new CellGrid(cellArray),
							getSelectedGameRule(), getSelectedBorderRule());
					frame.dispose();
					gamePad.startGame();
				}
			}
		});

		buttonPanel.add(startButton);
		buttonPanel.add(closeButton);
		return buttonPanel;
	}

	private GameRule getSelectedGameRule() {
		String ruleName = (String) chooseGameRule.getSelectedItem();
		if (ruleName.equals("Game of Life")) {
			return new GameOfLife();
		}
		if (ruleName.equals("Game without Death")) {
			return new GameWithoutDeath();
		}
		if (ruleName.equals("Three or four to life")) {
			return new ThreeOrFourLife();
		} else {
			return new HighLife();
		}
	}

	private BorderRule getSelectedBorderRule() {
		String ruleName = (String) chooseBorderRule.getSelectedItem();
		if (ruleName.equals("Wall of Death")) {
			return new WallOfDeath();
		} else {
			return new PacmanStyle();
		}
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
		gameChooseOptions.add(chooseGameRule);
		gameChooseOptions.add(chooseBorderRule);
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
		fileUploadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(frame);
				File file = chooser.getSelectedFile();
				int errorCode = FileLoader.readFileAndReturnErrorCode(file);
				if (errorCode == ErrorCodes.No_Error) {
					fileUploadPath.setText(file.getName());
					cellArray = FileLoader.getCells();
				} else if (errorCode != ErrorCodes.Filechoosing_Was_Aborted) {
					new ErrorGui(ErrorTexts.getTextToErrorCode(errorCode));
				}
			}
		});
		gameChooseOptionLayout
				.setConstraints(fileUploadButton, set(3, 1, 0, 2));

	}

	private void initGameChooseOptionLabels() {
		gameConstructions = new JLabel(
				"Bitte w\u00e4hlen sie Spiel- und Randvariante aus");
		gameChooseOptionLayout.setConstraints(gameConstructions,
				set(0, 0, 0, 6));
		gameChoose = new JLabel("Spielvarianten");
		gameChooseOptionLayout.setConstraints(gameChoose, set(3, 2, 0, 1));
		borderChoose = new JLabel("Randvarianten");
		gameChooseOptionLayout.setConstraints(borderChoose, set(3, 3, 0, 1));
	}

	private void initChooseGameAndBorderVariants() {
		chooseGameRule = new JComboBox<String>();
		chooseGameRule.addItem("Game of Life");
		chooseGameRule.addItem("Game without Death");
		chooseGameRule.addItem("Three or four to life");
		chooseGameRule.addItem("HighLife");
		gameChooseOptionLayout.setConstraints(chooseGameRule, set(0, 2, 0, 3));

		chooseBorderRule = new JComboBox<String>();
		chooseBorderRule.addItem("Wall of Death");
		chooseBorderRule.addItem("Pacman Sytle");
		gameChooseOptionLayout
				.setConstraints(chooseBorderRule, set(0, 3, 0, 3));
	}

	public JComboBox<String> getChooseGameRule() {
		return chooseGameRule;
	}

	public JComboBox<String> getChooseBorderRule() {
		return chooseBorderRule;
	}

	public int[][] getCellArray() {
		return cellArray;
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
