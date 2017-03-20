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
import dkeep.logic.GameCharacter;
import dkeep.logic.Ogre;



public class GamePanel extends JPanel implements KeyListener {

	/**
	 * Auto-generated
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String BASE_NAME = "images/img";
	private static final String IMG_FORMAT = ".png";
	
	// TODO load images in GameCharacter class, make logic as invisible as possible outside its package -- tried it, it's tough not to duplicate
	// Static Singleton with all images ? how to correctly access them? logic sees that singleton?
	private static final char[] characters = {'B', 'X', 'H', 'G', 'O', '*', 'k', 'I', 'S', 'A', 'K', '8', '$', 'g'};
	
	private int IMG_EDGE = 48;

	private GameHandler gh;
	
	/**
	 * Characters to Images HashMap 
	 */
	private HashMap<Character, Image> images = new HashMap<Character, Image>();

	/**
	 * Create the panel.
	 */
	public GamePanel(GameHandler gh) {
		this.gh = gh;
		
		for (int i = 0; i < characters.length; i++) {
			try {
				Image img = ImageIO.read(new File(BASE_NAME + Integer.toString(i) + IMG_FORMAT)).getScaledInstance(IMG_EDGE, IMG_EDGE, BufferedImage.SCALE_DEFAULT);
				
				if (images.put(characters[i], img) != null)
					System.err.println("Image character was already mapped.");
				
			} catch (IOException e) {
				System.err.println("Invalid image path.");
			}
		}
		
		this.repaint();
		this.requestFocusInWindow();
		
	}
	
	public void setGameHandler(GameHandler gh) {
		this.removeKeyListener(this);
		
		this.gh = gh;
		
		if (gh != null)
			this.addKeyListener(this);
		
		this.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);	// clear background

		if (gh == null)
			return;
		
		char[][] map = gh.getMap();
		
		Image floor = images.get('B');
		// Draw background map
		for (int row = 0, y = 0; row < map.length; row++, y += IMG_EDGE) {
			for (int col = 0, x = 0; col < map[row].length; col++, x += IMG_EDGE) {
				g.drawImage(floor, x, y, this);
				char c = map[row][col];
				
				if (c == 'B')
					continue;
				
				Image img = images.get(c);
				
				if (img == null)
					System.err.println("Invalid Character in map. Was " + c);
				else {
					g.drawImage(img, x, y, this);
				}
				
			}
		}
		
		// Draw Characters
		for (GameCharacter gc : gh.getCharacters()) {
			Image img = images.get(gc.getSymb());
			
			if (img == null)
				continue;
			
			// row,col tuple in matrix -> y,x in referential
			g.drawImage(img, gc.getPos()[1] * IMG_EDGE, gc.getPos()[0] * IMG_EDGE, this);
			
			// in case of Ogre, draw club
			if (gc instanceof Ogre) {	// TODO draw method and images in characters ?
				g.drawImage(images.get('*'), ((Ogre) gc).getClubPos()[1] * IMG_EDGE, ((Ogre) gc).getClubPos()[0] * IMG_EDGE, this);
			}
				
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (gh == null)
			return;
		
		Boolean ret = null;
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			ret = gh.update(-1, 0);
			break;
		case KeyEvent.VK_LEFT:
			ret = gh.update(0, -1);
			break;
		case KeyEvent.VK_DOWN:
			ret = gh.update(1, 0);
			break;
		case KeyEvent.VK_RIGHT:
			ret = gh.update(0, 1);
			break;
		default:
			System.err.println("Invalid Key Pressed.");
		}
		
		if (ret != null && ret == false)
			this.removeKeyListener(this);
		
		this.repaint();

	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyTyped(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		default:
			System.out.println("Key released: " + e.getKeyCode());
		}
	}
}
