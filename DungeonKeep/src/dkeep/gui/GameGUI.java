package dkeep.gui;

import dkeep.logic.*;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;


public class GameGUI {

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
		
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfOgres.setFont(new Font("Malayalam MN", Font.PLAIN, 13));
		lblNumberOfOgres.setBounds(560, 364, 120, 19);
		frame.getContentPane().add(lblNumberOfOgres);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("2");
		textField.setBounds(560, 395, 118, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuardPersonality.setFont(new Font("Malayalam MN", Font.PLAIN, 13));
		lblGuardPersonality.setBounds(560, 462, 120, 16);
		frame.getContentPane().add(lblGuardPersonality);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Rookie", "Drunken", "Suspicious"}));
		comboBox.setToolTipText("Persona");
		comboBox.setBounds(560, 490, 118, 26);
		frame.getContentPane().add(comboBox);
		
		panel = new GamePanel(game);
		panel.setBounds(27, 19, 500, 500);
		frame.getContentPane().add(panel);
		
		lblStatus = new JLabel("Game Status Placeholder");
		lblStatus.setFont(new Font("Malayalam MN", Font.PLAIN, 20));
		lblStatus.setBounds(47, 531, 390, 28);
		frame.getContentPane().add(lblStatus);
		
		JButton btnSaveGame = new JButton("Save Game");
		btnSaveGame.setEnabled(false);
		btnSaveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.saveGame();
			}
		});
		btnSaveGame.setBounds(560, 100, 120, 30);
		frame.getContentPane().add(btnSaveGame);
		
		JButton btnLoadGame = new JButton("Load Game");
		btnLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				game = new GameHandler();
				
				btnSaveGame.setEnabled(true);
				((GamePanel) panel).setGameHandler(game);
				
				panel.requestFocusInWindow();
			}
		});
		btnLoadGame.setBounds(560, 60, 120, 30);
		frame.getContentPane().add(btnLoadGame);
		
		JButton btnNewGame = new JButton("New Game");
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
				((GamePanel) panel).setGameHandler(game);
				
				panel.requestFocusInWindow();
			}
		});
		btnNewGame.setBounds(560, 20, 120, 30);
		frame.getContentPane().add(btnNewGame);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				}
		});
		btnExit.setBounds(560, 140, 120, 30);
		frame.getContentPane().add(btnExit);
		
		panel.requestFocusInWindow();
	}
}