package dkeep.gui;

import java.awt.*;
import java.awt.event.*;

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
	
	private int IMG_EDGE;
	
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

		IMG_EDGE = images.getImgEdge();		
		this.repaint();
		this.requestFocusInWindow();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);	// clear background
		if (gh == null) return;
		
		
		drawBackgroundMap(g);
		drawCharacters(g);
	}
	
	private void drawCharacters(Graphics g) {
		for (GameCharacter gc : gh.getCharacters()) {
			Image img = images.get(gc.getSymb());
			if (img == null)
				continue;
			
			int[] pos = gc.getPos();
			// row,col tuple in matrix -> y,x in referential
			g.drawImage(img, pos[1] * IMG_EDGE, pos[0] * IMG_EDGE, this);
			
			// in case of Ogre, draw club
			if (gc instanceof Ogre) {
				g.drawImage(images.get('*'), ((Ogre) gc).getClubPos()[1] * IMG_EDGE, ((Ogre) gc).getClubPos()[0] * IMG_EDGE, this);
			}
		}
	}

	private void drawBackgroundMap(Graphics g) {
		Image floor = images.get('B');

		char[][] map = gh.getMap();
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
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (gh == null)
			return;
		Boolean ret = null;
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:	ret = gameUpdate(-1, 0);break;
		case KeyEvent.VK_LEFT:	ret = gameUpdate(0, -1);break;
		case KeyEvent.VK_DOWN:	ret = gameUpdate(1, 0);	break;
		case KeyEvent.VK_RIGHT:	ret = gameUpdate(0, 1);	break;
		}
		
		if (ret != null && ret == false)
			this.removeKeyListener(this);
		
		repaint();
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
	
	private boolean gameUpdate(int row, int col) {
		return gh.update(row, col);
	}
}
