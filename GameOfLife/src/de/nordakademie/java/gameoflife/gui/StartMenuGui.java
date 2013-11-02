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

import de.nordakademie.java.gameoflife.StartGOLHandler;

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
	private StartGOLHandler handler; 
	

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
				handler.handleStartButtonPressedEvent();
				closeStartGui();
			}
		});

		buttonPanel.add(startButton);
		buttonPanel.add(closeButton);
		return buttonPanel;
	}
	
	private void closeStartGui(){
		//TODO: gibt es eine bessere Variante?
		frame.setVisible(false);
	}

	public String getSelectedGameRule(){
		return chooseGameRule.getSelectedItem().toString();
	}
	
	public String getSelectedBorderRule(){
		return chooseBorderRule.getSelectedItem().toString();
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
		gameChooseOptionLayout.setConstraints(chooseBorderRule, set(0, 3, 0, 3));
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
	
	public void setHandler(StartGOLHandler startGOLHandler){
		handler = startGOLHandler;
	}
}
