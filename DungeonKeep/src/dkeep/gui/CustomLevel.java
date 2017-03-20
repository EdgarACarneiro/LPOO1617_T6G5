package dkeep.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JInternalFrame;
import java.awt.Font;

public class CustomLevel extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomLevel frame = new CustomLevel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CustomLevel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 112, 480, 480);
		contentPane.add(panel);
		
		JButton btnDone = new JButton("Done!");
		btnDone.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnDone.setBounds(432, 11, 117, 51);
		contentPane.add(btnDone);
		
		JLabel lblWidth = new JLabel("Width:");
		lblWidth.setBounds(16, 11, 61, 16);
		contentPane.add(lblWidth);
		
		JLabel lblHeight = new JLabel("Height:");
		lblHeight.setBounds(191, 11, 61, 16);
		contentPane.add(lblHeight);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("10");
		textField.setBounds(71, 6, 66, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("10");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(245, 6, 66, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnAddFloor = new JButton("Add Floor");
		btnAddFloor.setBounds(516, 117, 117, 29);
		contentPane.add(btnAddFloor);
		
		JButton btnAddWall = new JButton("Add Wall");
		btnAddWall.setBounds(516, 158, 117, 29);
		contentPane.add(btnAddWall);
		
		JButton btnAddDoor = new JButton("Add Door");
		btnAddDoor.setBounds(516, 199, 117, 29);
		contentPane.add(btnAddDoor);
		
		JButton btnAddOgre = new JButton("Add Ogre");
		btnAddOgre.setBounds(516, 240, 117, 29);
		contentPane.add(btnAddOgre);
		
		JButton btnAddKey = new JButton("Add Key");
		btnAddKey.setBounds(516, 281, 117, 29);
		contentPane.add(btnAddKey);
		
		JButton btnAddWinningPosition = new JButton("Add Winning Position");
		btnAddWinningPosition.setBounds(498, 393, 169, 29);
		contentPane.add(btnAddWinningPosition);
		
		JButton btnSetStartPosition = new JButton("Set Start Position");
		btnSetStartPosition.setBounds(497, 363, 170, 29);
		contentPane.add(btnSetStartPosition);
		
		JLabel lblRightClickTo = new JLabel("Right click to restore floor!");
		lblRightClickTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblRightClickTo.setBounds(6, 88, 480, 16);
		contentPane.add(lblRightClickTo);
		
		JButton btnSetSize = new JButton("Set Size");
		btnSetSize.setBounds(6, 39, 308, 29);
		contentPane.add(btnSetSize);
	}
}
