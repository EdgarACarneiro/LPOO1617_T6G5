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

	private GameHandler gh;
	
	private ImageLoader images = ImageLoader.getInstance();
	
	/**
	 * Create the panel.
	 */
	public GamePanel(GameHandler gh) {
		setGameHandler(gh);
	}
	
	public void setGameHandler(GameHandler gh) {
		this.removeKeyListener(this);
		
		this.gh = gh;
		
		if (gh != null)
			this.addKeyListener(this);
		else
			images.reset();
		
		this.repaint();
		this.requestFocusInWindow();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);	// clear background

		if (gh == null)
			return;
		
		char[][] map = gh.getMap();
		
		Image floor = images.get('B');
		// Draw background map
		for (int row = 0, y = 0; row < map.length; row++, y += images.getImgEdge()) {
			for (int col = 0, x = 0; col < map[row].length; col++, x += images.getImgEdge()) {
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
			g.drawImage(img, gc.getPos()[1] * images.getImgEdge(), gc.getPos()[0] * images.getImgEdge(), this);
			
			// in case of Ogre, draw club
			if (gc instanceof Ogre) {
				g.drawImage(images.get('*'), ((Ogre) gc).getClubPos()[1] * images.getImgEdge(), ((Ogre) gc).getClubPos()[0] * images.getImgEdge(), this);
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
