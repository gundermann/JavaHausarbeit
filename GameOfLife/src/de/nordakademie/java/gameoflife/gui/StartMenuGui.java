package de.nordakademie.java.gameoflife.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import de.nordakademie.java.gameoflife.StartGOLHandler;

/**
 * GUI die beim Starten des Programms angezeigt wird.
 * 
 * @author Frauke Trautmann
 */
public class StartMenuGui extends GolGui {

	private JComboBox<String> chooseGameRule;
	private JComboBox<String> chooseBorderRule;
	private GridBagLayout gameChooseOptionLayout;
	private JButton fileUploadButton;
	private JTextField fileUploadPath;
	private JLabel gameChoose;
	private JLabel borderChoose;
	private JLabel gameConstructions;
	private StartGOLHandler handler;

	public StartMenuGui(Map<String, Class> gameRuleMap,
			Map<String, Class> borderRuleMap) {
		this.setTitle("Game of Life");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		JLabel headline = initHeadline();
		JPanel buttonPanel = initButtonPanel();
		JPanel gameChooseOptions = initGameChooseOptions(gameRuleMap,
				borderRuleMap);

		this.add(headline, BorderLayout.NORTH);
		this.add(gameChooseOptions, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.showGui();
	}

	private JPanel initButtonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 2));

		JButton closeButton = new JButton("Beenden");
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				closeGui();
			}
		});

		JButton startButton = new JButton("Spielstart");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				handler.handleStartButtonPressedEvent();
			}
		});

		buttonPanel.add(startButton);
		buttonPanel.add(closeButton);
		return buttonPanel;
	}

	public String getSelectedGameRule() {
		return chooseGameRule.getSelectedItem().toString();
	}

	public String getSelectedBorderRule() {
		return chooseBorderRule.getSelectedItem().toString();
	}

	private JLabel initHeadline() {
		JLabel headline = new JLabel("Game of Life");
		headline.setHorizontalAlignment(JLabel.CENTER);
		return headline;
	}

	private JPanel initGameChooseOptions(Map<String, Class> gameRuleMap,
			Map<String, Class> borderRuleMap) {
		JPanel gameChooseOptions = new JPanel();
		gameChooseOptionLayout = new GridBagLayout();
		gameChooseOptions.setLayout(gameChooseOptionLayout);
		gameChooseOptions.setBorder(new EtchedBorder(Color.BLACK, Color.BLUE));

		initChooseGameAndBorderVariants(gameRuleMap, borderRuleMap);
		initGameChooseOptionLabels();
		initGameChooseOptionButtons();
		initGameChooseOptionUploadField();

		gameChooseOptions.add(fileUploadPath);
		gameChooseOptions.add(fileUploadButton);
		gameChooseOptions.add(gameConstructions);
		gameChooseOptions.add(gameChoose);
		gameChooseOptions.add(borderChoose);
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
		fileUploadButton = new JButton("Datei hochladen ...");
		fileUploadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setFileUploadPathText(handler.handleFileUplod());
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

	private void initChooseGameAndBorderVariants(
			Map<String, Class> gameRuleMap, Map<String, Class> borderRuleMap) {
		chooseGameRule = new JComboBox<String>();
		for (String ruleTitle : gameRuleMap.keySet()) {
			chooseGameRule.addItem(ruleTitle.toString());
		}
		gameChooseOptionLayout.setConstraints(chooseGameRule, set(0, 2, 0, 3));
		chooseBorderRule = new JComboBox<String>();
		for (String ruleTitle : borderRuleMap.keySet()) {
			chooseBorderRule.addItem(ruleTitle.toString());
		}
		gameChooseOptionLayout
				.setConstraints(chooseBorderRule, set(0, 3, 0, 3));
	}

	public void setFileUploadPathText(String path) {
		fileUploadPath.setText(path);
	}

	public JComboBox<String> getChooseGameRule() {
		return chooseGameRule;
	}

	public JComboBox<String> getChooseBorderRule() {
		return chooseBorderRule;
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

	public void setHandler(StartGOLHandler startGOLHandler) {
		handler = startGOLHandler;
	}
}
