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


public class GameGUI {
	
	private static enum State {
		INITIAL, GAME, EDIT
	};
	
	private State state = State.INITIAL;

	private JFrame frame;
	private JTextField textField;
	
	private GameHandler game;
	
	private JLabel lblStatus;
	private JPanel panel;
	
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
		
		JPanel Initial = new JPanel();
		Initial.setBounds(0, 0, 10, 10);
		frame.getContentPane().add(Initial);
		Initial.setLayout(null);
		
		JButton btnStdGame = new JButton("Standard Game");
		btnStdGame.setBounds(280, 60, 138, 60);
		Initial.add(btnStdGame);
		
		JButton btnCustomGame = new JButton("Custom Game");
		btnCustomGame.setBounds(280, 140, 132, 60);
		Initial.add(btnCustomGame);
		
		JPanel Game = new JPanel();
		Game.setBounds(0, 0, 946, 39);
		frame.getContentPane().add(Game);
		
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		Game.add(lblNumberOfOgres);
		lblNumberOfOgres.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfOgres.setFont(new Font("Malayalam MN", Font.PLAIN, 13));
		
		textField = new JTextField();
		Game.add(textField);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("2");
		textField.setColumns(10);
		
		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		Game.add(lblGuardPersonality);
		lblGuardPersonality.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuardPersonality.setFont(new Font("Malayalam MN", Font.PLAIN, 13));
		
		JComboBox<String> comboBox = new JComboBox<String>();
		Game.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Rookie", "Drunken", "Suspicious"}));
		comboBox.setToolTipText("Persona");
		
		panel = new GamePanel(game);
		Game.add(panel);
		
		lblStatus = new JLabel("Game Status Placeholder");
		Game.add(lblStatus);
		lblStatus.setFont(new Font("Malayalam MN", Font.PLAIN, 20));
		
		JButton btnNewGame = new JButton("New Game");
		Game.add(btnNewGame);
		
		JButton btnExit = new JButton("Exit");
		Game.add(btnExit);
		
		JPanel Edit = new JPanel();
		Edit.setBounds(6, 6, 698, 566);
		frame.getContentPane().add(Edit);
		Edit.setLayout(null);
		
		JLabel lblRows = new JLabel("Rows:");
		lblRows.setBounds(38, 6, 61, 16);
		Edit.add(lblRows);
		
		JLabel lblColumns = new JLabel("Columns:");
		lblColumns.setBounds(38, 34, 61, 16);
		Edit.add(lblColumns);
		
		JSpinner spinnerRows = new JSpinner();
		spinnerRows.setBounds(111, 1, 48, 26);
		spinnerRows.setValue(10);
		Edit.add(spinnerRows);
		
		JSpinner spinnerCols = new JSpinner();
		spinnerCols.setBounds(111, 29, 48, 26);
		spinnerCols.setValue(10);
		Edit.add(spinnerCols);
		
		JLabel lblFloor = new JLabel(new ImageIcon("images/img0.png"));
		lblFloor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblFloor.setBounds(588, 104, 55, 55);
		Edit.add(lblFloor);
		
		JLabel lblWall = new JLabel(new ImageIcon("images/img1.png"));
		lblWall.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblWall.setBounds(588, 171, 55, 55);
		Edit.add(lblWall);
		
		JLabel lblOgre = new JLabel(new ImageIcon("images/img4.png"));
		lblOgre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblOgre.setBounds(588, 238, 55, 55);
		Edit.add(lblOgre);
		
		JLabel lblDoor = new JLabel("Door", new ImageIcon("images/img8.png"), JLabel.CENTER);
		lblDoor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblDoor.setBounds(588, 305, 55, 55);
		Edit.add(lblDoor);
		
		JLabel lblKey = new JLabel("Key", new ImageIcon("images/img6.png"), JLabel.CENTER);
		lblKey.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblKey.setBounds(588, 372, 55, 55);
		Edit.add(lblKey);
		
		JLabel lblHero = new JLabel("Hero", new ImageIcon("images/img9.png"), JLabel.CENTER);
		lblHero.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblHero.setBounds(588, 439, 55, 55);
		Edit.add(lblHero);
		
		JPanel EditPanel = new MapEditPanel();
		EditPanel.setBounds(6, 80, 480, 480);
		Edit.add(EditPanel);
		
		JButton btnDone = new JButton("Done!");
		btnDone.setBounds(554, 34, 117, 58);
		Edit.add(btnDone);
		Edit.setVisible(false);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
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
				
				((GamePanel) panel).setGameHandler(game);
				
				panel.requestFocusInWindow();
			}
		});
		
		Game.setVisible(false);
		Edit.setVisible(false);
		Initial.setVisible(true);
		
	}
	
	// State-Machine - used to control possible transitions and their associated actions (e.g. visible panels)
	private void switchState(State st) {
		
		state = st;
		
//		switch (state) {
//		case INITIAL:
//			
//			break;
//		case GAME:
//			
//			break;
//		case EDIT:
//			
//			break;
//		}
	}
}