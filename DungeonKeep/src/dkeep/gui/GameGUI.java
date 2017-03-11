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
		comboBox.setBounds(160, 59, 118, 26);
		frame.getContentPane().add(comboBox);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 25));
		textArea.setBounds(22, 105, 354, 300);
		textArea.setEditable(false);
		frame.getContentPane().add(textArea);
		
		JLabel lblNewLabel = new JLabel("Game Status Placeholder");
		lblNewLabel.setFont(new Font("Malayalam MN", Font.PLAIN, 13));
		lblNewLabel.setBounds(22, 417, 390, 19);
		frame.getContentPane().add(lblNewLabel);			
		
		//Setting this Game Status Text and Buttons
		if (game == null)
			lblNewLabel.setText("You can start a new game.");
		else
			lblNewLabel.setText(game.getStatusInfo());
		
		JButton btnUp = new JButton("Up");
		if (game == null)
			btnUp.setEnabled(false);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (game != null) {
					game.update(-1, 0);
					textArea.setText(game.getMapStr());
				}
				
			}
		});
		btnUp.setBounds(451, 186, 68, 30);
		frame.getContentPane().add(btnUp);
		
		JButton btnLeft = new JButton("Left");
		btnLeft.setEnabled(false);
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (game != null) {
					game.update(0, -1);
					textArea.setText(game.getMapStr());
				}
			}
		});
		btnLeft.setBounds(411, 225, 68, 30);
		frame.getContentPane().add(btnLeft);
		
		JButton btnDown = new JButton("Down");
		btnDown.setEnabled(false);
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (game != null) {
					game.update(1, 0);
					textArea.setText(game.getMapStr());
				}
			}
		});
		btnDown.setBounds(451, 264, 68, 30);
		frame.getContentPane().add(btnDown);
		
		JButton btnRight = new JButton("Right");
		btnRight.setEnabled(false);
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (game != null) {
					game.update(0, 1);
					textArea.setText(game.getMapStr());
				}
			}
		});
		btnRight.setBounds(491, 225, 68, 30);
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
				
				int numOgres = Integer.parseInt(textField.getText());
				if (numOgres >= 0 && numOgres <= 4 && gp != null) {
					game = new GameHandler(gp, numOgres);
					
					//Enable Move Buttons
					btnUp.setEnabled(true);
					btnRight.setEnabled(true);
					btnLeft.setEnabled(true);
					btnDown.setEnabled(true);
					
					textArea.setText(game.getMapStr());
				}
				else
					textArea.setText("Invalid Game Parameters");
			}
		});
		btnNewGame.setBounds(428, 82, 130, 30);
		frame.getContentPane().add(btnNewGame);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(428, 389, 130, 30);
		frame.getContentPane().add(btnExit);
	}
}
