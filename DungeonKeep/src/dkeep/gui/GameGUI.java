package dkeep.gui;

import dkeep.logic.*;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GameGUI {

	private JFrame frame;
	private JTextField textField;
	
	private GameHandler game;
	
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
		frame.setBounds(100, 100, 600, 480);
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
		comboBox.setBounds(160, 59, 118, 27);
		frame.getContentPane().add(comboBox);
		
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
				
				int numOgres = Integer.parseInt(textField.getText());
				if (numOgres >= 0 && numOgres <= 4 && gp != null)
					game = new GameHandler(gp, numOgres);
				else
					textField.setText("Invalid Game Parameters");
			}
		});
		btnNewGame.setBounds(428, 82, 131, 29);
		frame.getContentPane().add(btnNewGame);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(428, 389, 131, 29);
		frame.getContentPane().add(btnExit);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 25));
		textArea.setBounds(22, 105, 390, 300);
		frame.getContentPane().add(textArea);
		
		JLabel lblNewLabel = new JLabel("Game Status Placeholder");
		lblNewLabel.setFont(new Font("Malayalam MN", Font.PLAIN, 13));
		lblNewLabel.setBounds(22, 417, 390, 19);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game == null) {
					textArea.setText("Game not yet initialized");
				} else {
					game.update(-1, 0);
				}

				textArea.setText(game.getMapStr());
			}
		});
		btnUp.setBounds(463, 185, 62, 30);
		frame.getContentPane().add(btnUp);
		
		JButton btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game == null) {
					textArea.setText("Game not yet initialized");
				} else {
					game.update(0, -1);
				}

				textArea.setText(game.getMapStr());
			}
		});
		btnLeft.setBounds(428, 223, 62, 30);
		frame.getContentPane().add(btnLeft);
		
		JButton btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game == null) {
					textArea.setText("Game not yet initialized");
				} else {
					game.update(1, 0);
				}

				textArea.setText(game.getMapStr());
			}
		});
		btnDown.setBounds(463, 258, 62, 30);
		frame.getContentPane().add(btnDown);
		
		JButton btnRight = new JButton("Right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game == null) {
					textArea.setText("Game not yet initialized");
				} else {
					game.update(0, 1);
				}

				textArea.setText(game.getMapStr());
			}
		});
		btnRight.setBounds(497, 223, 62, 30);
		frame.getContentPane().add(btnRight);
	}
}
