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
	
	//Move Buttons
	private JButton btnLeft;
	private JButton btnRight;
	private JButton btnUp;
	private JButton btnDown;
	
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
		frame.setBounds(100, 100, 716, 616);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setFont(new Font("Malayalam MN", Font.PLAIN, 13));
		lblNumberOfOgres.setBounds(22, 32, 137, 19);
		frame.getContentPane().add(lblNumberOfOgres);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("2");
		textField.setBounds(160, 27, 118, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setFont(new Font("Malayalam MN", Font.PLAIN, 13));
		lblGuardPersonality.setBounds(22, 63, 118, 16);
		frame.getContentPane().add(lblGuardPersonality);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Rookie", "Drunken", "Suspicious"}));
		comboBox.setToolTipText("Persona");
		comboBox.setBounds(160, 59, 118, 26);
		frame.getContentPane().add(comboBox);
		
		panel = new GamePanel(game);
		panel.setBounds(22, 101, 474, 430);
		frame.getContentPane().add(panel);
		
		lblStatus = new JLabel("Game Status Placeholder");
		lblStatus.setFont(new Font("Malayalam MN", Font.PLAIN, 13));
		lblStatus.setBounds(22, 554, 390, 19);
		frame.getContentPane().add(lblStatus);			
		
		
		btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGUI(-1, 0);
			}
		});
		btnUp.setBounds(548, 187, 68, 30);
		frame.getContentPane().add(btnUp);
		
		btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGUI(0, -1);
			}
		});
		btnLeft.setBounds(508, 226, 68, 30);
		frame.getContentPane().add(btnLeft);
		
		btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGUI(1, 0);
			}
		});
		btnDown.setBounds(548, 265, 68, 30);
		frame.getContentPane().add(btnDown);
		
		btnRight = new JButton("Right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGUI(0, 1);
			}
		});
		btnRight.setBounds(588, 226, 68, 30);
		frame.getContentPane().add(btnRight);
		
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
					enableMoveBtns();
				}
				else
					lblStatus.setText("Invalid number of Ogres.");
				
				((GamePanel) panel).setGameHandler(game);
				panel.requestFocus();
			}
		});
		btnNewGame.setBounds(526, 82, 130, 30);
		frame.getContentPane().add(btnNewGame);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(526, 380, 130, 30);
		frame.getContentPane().add(btnExit);
		
		
		if (game == null)
			disableMoveBtns();
		
		panel.requestFocus();
	}
	
	/**
	 * Updates the GUI text area and status label, with given Hero position delta
	 */
	private void updateGUI(int row, int col) {
		if (game == null) {
			lblStatus.setText("You can start a new game.");
			return;
		}
		
		boolean ret = game.update(row, col);
		lblStatus.setText(game.getStatusInfo());
		panel.repaint();
		
		if (! ret) {
			disableMoveBtns();
			game = null;
		}
	}
	
	//Enables all the 4 Move Buttons
	private void enableMoveBtns() {
		btnUp.setEnabled(true);
		btnRight.setEnabled(true);
		btnLeft.setEnabled(true);
		btnDown.setEnabled(true);
	}
	
	//Disables all the 4 Move Buttons
	private void disableMoveBtns() {
		btnUp.setEnabled(false);
		btnRight.setEnabled(false);
		btnLeft.setEnabled(false);
		btnDown.setEnabled(false);
	}
}