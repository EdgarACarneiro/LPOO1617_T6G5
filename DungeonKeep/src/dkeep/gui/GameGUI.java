package dkeep.gui;

import dkeep.logic.*;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SpinnerNumberModel;


public class GameGUI {
	
	private static enum State {
		INITIAL, GAME, EDIT
	};
	
	private State state = State.INITIAL;

	private JFrame frame;
	private JTextField textField;
	
	private GameHandler game;
	
	private JLabel lblStatus;
	
	private JPanel Initial;
	private JPanel Game;
	private JPanel Edit;
	
	private JPanel gamePanel;
	private JPanel editPanel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameGUI window = new GameGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 710, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/**
		 * Game JPanel
		 */
		Initial = new JPanel();
		Initial.setBounds(0, 0, 710, 578);
		frame.getContentPane().add(Initial);
		Initial.setLayout(null);
		
		JButton btnStdGame = new JButton("Standard Game");
		btnStdGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchState(State.GAME);
			}
		});
		
				btnStdGame.setBounds(280, 68, 138, 60);
				Initial.add(btnStdGame);
				
				JButton btnCustomGame = new JButton("Custom Game");
				btnCustomGame.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						switchState(State.EDIT);
					}
				});
				btnCustomGame.setBounds(280, 181, 138, 60);
				Initial.add(btnCustomGame);
				
				JButton btnExit = new JButton("Exit");
				btnExit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				btnExit.setBounds(280, 302, 138, 60);
				Initial.add(btnExit);
				
				JButton btnLoadGame = new JButton("Load Game");
				btnLoadGame.setBounds(0, 0, 120, 30);
				Initial.add(btnLoadGame);
				btnLoadGame.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						game = new GameHandler();
						
						((GamePanel) gamePanel).setGameHandler(game);
						switchState(State.GAME);
						
						gamePanel.requestFocusInWindow();
					}
				});
		Game = new JPanel();
		Game.setBounds(0, 0, 710, 578);
		frame.getContentPane().add(Game);
		Game.setLayout(null);
		
		gamePanel = new GamePanel(game);
		gamePanel.setBounds(16, 78, 500, 500);
		Game.add(gamePanel);
		
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setBounds(547, 231, 109, 19);
		Game.add(lblNumberOfOgres);
		lblNumberOfOgres.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfOgres.setFont(new Font("Malayalam MN", Font.PLAIN, 13));
		
		textField = new JTextField();
		textField.setBounds(536, 262, 130, 26);
		Game.add(textField);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("2");
		textField.setColumns(10);
		
		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setBounds(549, 380, 107, 19);
		Game.add(lblGuardPersonality);
		lblGuardPersonality.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuardPersonality.setFont(new Font("Malayalam MN", Font.PLAIN, 13));
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(542, 411, 124, 27);
		Game.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Rookie", "Drunken", "Suspicious"}));
		comboBox.setToolTipText("Persona");
		
		lblStatus = new JLabel("Game Status Placeholder");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setBounds(16, 35, 485, 29);
		Game.add(lblStatus);
		lblStatus.setFont(new Font("Malayalam MN", Font.PLAIN, 20));	

		
		JButton btnSaveGame = new JButton("Save Game");
		btnSaveGame.setBounds(547, 78, 109, 30);
		Game.add(btnSaveGame);
		btnSaveGame.setEnabled(false);
		btnSaveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.saveGame();
				gamePanel.requestFocusInWindow();
			}
		});
		
		JButton btnExitGame = new JButton("Exit");
		btnExitGame.setBounds(567, 514, 75, 29);
		Game.add(btnExitGame);
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchState(State.INITIAL);
			}
		});
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(547, 37, 110, 29);
		Game.add(btnNewGame);
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Guard.Personality gp = null;
				
				switch ((String) comboBox.getSelectedItem()) {
				case "Rookie":
					gp = Guard.Personality.ROOKIE;
					break;
				case "Drunken":
					gp = Guard.Personality.DRUNKEN;
					break;
				case "Suspicious":
					gp = Guard.Personality.SUSPICIOUS;
					break;
				default:
					System.err.println("Invalid Personality");
				}
				
				int numOgres;
				try {
					numOgres = Integer.parseInt(textField.getText());
				} catch (NumberFormatException exc) {
					lblStatus.setText("Invalid input as number of Ogres!");
					return;
				}
				if (numOgres >= 0 && numOgres <= 5 && gp != null) {
					game = new GameHandler(gp, numOgres);
					lblStatus.setText("Game in progress!");
				} else {
					lblStatus.setText("Invalid number of Ogres.");
					return;
				}
				
				btnSaveGame.setEnabled(true);
				((GamePanel) gamePanel).setGameHandler(game);
				
				gamePanel.requestFocusInWindow();
			}
		});
		
		
		/**
		 * Edit JPanel
		 */
		Edit = new JPanel();
		Edit.setBounds(0, 0, 710, 578);
		frame.getContentPane().add(Edit);
		Edit.setLayout(null);
		
		editPanel = new MapEditPanel();
		editPanel.setBounds(17, 62, 500, 500);
		Edit.add(editPanel);
		
		JLabel lblRows = new JLabel("Rows:");
		lblRows.setBounds(38, 6, 61, 16);
		Edit.add(lblRows);
		
		JLabel lblColumns = new JLabel("Columns:");
		lblColumns.setBounds(38, 34, 61, 16);
		Edit.add(lblColumns);
		
		JSpinner spinnerRows = new JSpinner();
		spinnerRows.setModel(new SpinnerNumberModel(10, MapEditPanel.MIN_LINES, MapEditPanel.MAX_LINES, 1));
		spinnerRows.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				((MapEditPanel) editPanel).setRows(spinnerRows.getValue());
			}
		});
		spinnerRows.setBounds(111, 1, 48, 26);
		spinnerRows.setValue(10);
		Edit.add(spinnerRows);
		
		JSpinner spinnerCols = new JSpinner();
		spinnerCols.setModel(new SpinnerNumberModel(10, MapEditPanel.MIN_LINES, MapEditPanel.MAX_LINES, 1));
		spinnerCols.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				((MapEditPanel) editPanel).setCols(spinnerCols.getValue());
			}
		});
		spinnerCols.setBounds(111, 29, 48, 26);
		spinnerCols.setValue(10);
		Edit.add(spinnerCols);
		
		JLabel lblFloor = new JLabel(new ImageIcon("images/img0.png"));
		lblFloor.setText("Floor");
		lblFloor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((MapEditPanel) editPanel).setSelection('B');
			}
		});
		lblFloor.setBounds(588, 100, 60, 60);
		Edit.add(lblFloor);
		
		JLabel lblWall = new JLabel(new ImageIcon("images/img1.png"));
		lblWall.setText("Wall");
		lblWall.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((MapEditPanel) editPanel).setSelection('X');
			}
		});
		lblWall.setBounds(588, 170, 60, 60);
		Edit.add(lblWall);
		
		JLabel lblOgre = new JLabel(new ImageIcon("images/img4.png"));
		lblOgre.setText("Ogre");
		lblOgre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((MapEditPanel) editPanel).setSelection('O');
			}
		});
		lblOgre.setBounds(588, 240, 60, 60);
		Edit.add(lblOgre);
		
		JLabel lblDoor = new JLabel("Door", new ImageIcon("images/img8.png"), JLabel.CENTER);
		lblDoor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((MapEditPanel) editPanel).setSelection('I');
			}
		});
		lblDoor.setBounds(588, 310, 60, 60);
		Edit.add(lblDoor);
		
		JLabel lblKey = new JLabel("Key", new ImageIcon("images/img6.png"), JLabel.CENTER);
		lblKey.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((MapEditPanel) editPanel).setSelection('k');
			}
		});
		lblKey.setBounds(588, 380, 60, 60);
		Edit.add(lblKey);
		
		JLabel lblHero = new JLabel("Hero", new ImageIcon("images/img9.png"), JLabel.CENTER);
		lblHero.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((MapEditPanel) editPanel).setSelection('A');
			}
		});
		lblHero.setBounds(588, 450, 60, 60);
		Edit.add(lblHero);
		
		JButton btnDone = new JButton("Done!");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Level new_level = ((MapEditPanel) editPanel).getLevel();
				game = new GameHandler(new_level);
				
				btnSaveGame.setEnabled(true);
				((GamePanel) gamePanel).setGameHandler(game);
				
				switchState(State.GAME);
			}
		});
		btnDone.setBounds(554, 34, 117, 58);
		Edit.add(btnDone);
		Edit.setVisible(false);
		
		/**
		 * Initial JPanel
		 */

		
		switchState(State.INITIAL);
		
	}

	
	// State-Machine - used to control possible transitions and their associated actions (e.g. visible panels)
	private void switchState(State st) {
				
		switch (st) {
		case INITIAL:
			state = State.INITIAL;
			Initial.setVisible(true);
			Game.setVisible(false);
			Edit.setVisible(false);
			break;
		case GAME:
			state = State.GAME;
			gamePanel.requestFocusInWindow();
			Initial.setVisible(false);
			Game.setVisible(true);
			Edit.setVisible(false);
			break;
		case EDIT:
			state = State.EDIT;
			Initial.setVisible(false);
			Game.setVisible(false);
			Edit.setVisible(true);
			break;
		}
		
	}

}