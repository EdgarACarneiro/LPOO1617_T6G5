package dkeep.gui;

import java.util.HashMap;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import dkeep.logic.GameHandler;
import dkeep.logic.Character;


public class GamePanel extends JPanel implements KeyListener {

	/**
	 * Auto-generated
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String BASE_NAME = "images/img";
	private static final String IMG_FORMAT = ".png";
	
	private static final char[] characters = {'B', 'X', 'H', 'G', 'O', '*', 'k'};
	
	private final int IMG_WIDTH = 32;
	private final int IMG_HEIGHT = 32;

	private GameHandler gh;
	
	/**
	 * Characters to Images HashMap 
	 */
	private HashMap<Character, BufferedImage> images = new HashMap<Character, BufferedImage>();

	/**
	 * Create the panel.
	 */
	public GamePanel(GameHandler gh) {
		super();
				
		this.gh = gh;
		
		for (int i = 0; i < characters.length; i++) {
			try {
				BufferedImage img = ImageIO.read(new File(BASE_NAME + Integer.toString(i) + IMG_FORMAT));
				
				if (images.put(characters[i], img) != null)
					System.err.println("Image character was already mapped.");
				
			} catch (IOException e) {
				System.err.println("Invalid image path.");
			}
		}
		
	}
	
	public void setGameHandler(GameHandler gh) {
		this.gh = gh;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);	// clear background

		if (gh == null)
			return;
		
		char[][] map = gh.getMap();
		
		// Draw background map
		for (int row = 0, y = 0; row < map.length; row++, y += IMG_HEIGHT) {
			for (int col = 0, x = 0; col < map[row].length; col++, x += IMG_WIDTH) {
				
				g.setColor(Color.BLUE);
				g.drawRect(x, y, IMG_WIDTH, IMG_HEIGHT);
				
				char c = map[row][col];
				BufferedImage img = images.get(c);
				
				if (img == null)
					System.err.println("Invalid Character in map. Was " + c);
				else {
					g.drawImage(img, x, y, IMG_WIDTH, IMG_HEIGHT, this); // Run-Time scale, bad?
				}
				
			}
		}
		
		// Draw Characters
		for (Character c : gh.getCharacters()) {
			switch (c.getSymb()) {
			// TODO
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (gh == null)
			return;
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			gh.update(0, -1);
			break;
		case KeyEvent.VK_LEFT:
			gh.update(-1, 0);
			break;
		case KeyEvent.VK_DOWN:
			gh.update(0, 1);
			break;
		case KeyEvent.VK_RIGHT:
			gh.update(1, 0);
			break;
		default:
			System.err.println("Invalid Key Pressed.");
		}		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyTyped(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		default:
			System.err.println("Key released: " + e.getKeyCode());
		}
	}
}
