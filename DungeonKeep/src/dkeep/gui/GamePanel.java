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

public class GamePanel extends JPanel implements KeyListener {

	/**
	 * Auto-generated
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String BASE_NAME = "img";
	private static final String IMG_FORMAT = ".png";
	
	private static final char[] characters = {'B', 'X', 'H', 'G', 'O'};

	private GameHandler gh;
	
	/**
	 * Characters to Images HashMap 
	 */
	private HashMap<Character, BufferedImage> images;

	/**
	 * Create the panel.
	 */
	public GamePanel(GameHandler gh) {
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
		
		String map = gh.getMapStr();
		
		for (int idx = 0, x = 0, y = 0; idx < map.length(); idx++) {
			char c = map.charAt(idx);
			
			if (c == '\n') {
				x = 0;
				y++;
				continue;
			}
			
			BufferedImage img = images.get(c);
			
			if (img == null)
				System.err.println("Invalid Character in map !!!");
			
			g.drawImage(img, x, y, this);
			
			x += img.getWidth();
			y += img.getHeight();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
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
