package dkeep.gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import dkeep.logic.Level;
import dkeep.logic.LevelTwo;

public class MapEditPanel extends JPanel implements MouseListener, MouseMotionListener {

	/**
	 * Auto-generated
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String BASE_NAME = "images/img";
	private static final String IMG_FORMAT = ".png";
	
	public static final int MIN_LINES = 5;
	public static final int MAX_LINES = 15;
	public static final int MAX_IMG_EDGE = 50;
	
	// TODO load images in GameCharacter class, make logic as invisible as possible outside its package -- tried it, it's tough not to duplicate
	// Static Singleton with all images ? how to correctly access them? logic sees that singleton?
	private static final char[] characters = {'B', 'X', 'H', 'G', 'O', '*', 'k', 'I', 'S', 'A', 'K', '8', '$', 'g'};
	// only some characters are necessary - clumsy programming :/
	
	/**
	 * Characters to Images HashMap 
	 */
	private HashMap<Character, Image> images = new HashMap<Character, Image>();
	
	private Character selection = null;
	
	private int rows = 10;
	private int cols = 10;
	
	private Integer IMG_EDGE = null;
	
	private char[][] map;
	private ArrayList<int[]> victory_pos = new ArrayList<int[]>();
	private int[] hero_pos = new int[] {1, 1};
	
	Point mousePos = new Point();
	
	/**
	 * Create the panel.
	 */
	public MapEditPanel() {
		initializeMap();
		setImgEdge();
		rescaleImages();
		
		this.repaint();
		this.requestFocusInWindow();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	private boolean setImgEdge() {
		int n = Math.min(getWidth(), getHeight()) / Math.max(cols, rows);
		if (n > 0 && n < MAX_IMG_EDGE) {
			IMG_EDGE = n;
			return true;
		} else if (IMG_EDGE == null) {
			IMG_EDGE = MAX_IMG_EDGE;
			return true;
		}
		
		return false;
	}
	
	private void loadImages() {
		if ( ! images.isEmpty() )
			images.clear();
			
		for (int i = 0; i < characters.length; i++) {
			try {
				Image img = ImageIO.read(new File(BASE_NAME + Integer.toString(i) + IMG_FORMAT));
				
				if (images.put(characters[i], img) != null)
					System.err.println("Image character was already mapped.");
				
			} catch (IOException e) {
				System.err.println("Invalid image path.");
			}
		}
	}
	
	private void initializeMap() {
		map = new char[rows][cols];
		hero_pos = new int[] {1, 1};
		
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if ( r == 0 || r == rows - 1 || c == 0 || c == cols -1 )
					map[r][c] = 'X';
				else
					map[r][c] = 'B';
			}
		}
	}
	
	private void rescaleImages() {		
		loadImages();

		for (Map.Entry<Character, Image> entry : images.entrySet()) {
			entry.setValue(entry.getValue().getScaledInstance(IMG_EDGE, IMG_EDGE, Image.SCALE_DEFAULT));
		}
		
		
		repaint();
	}
	
	public Level getLevel() {
		if (hero_pos == null)
			return null;
		
		map[hero_pos[0]][hero_pos[1]] = 'A';
		int[][] temp_array = new  int[victory_pos.size()][];
		 victory_pos.toArray(temp_array);
		Level l = new LevelTwo(map, temp_array, true, true);
		
		return l;
	}
	
	public void setSelection(char c) {
		selection = c;
		this.requestFocusInWindow();
	}
	
	public boolean setRows(Object n) {
		Integer i = (Integer) n;
		if (i == null || i < MIN_LINES || i > MAX_LINES)
			return false;
		rows = i;
		setImgEdge();
		rescaleImages();
		initializeMap();
				
		return true;
	}
	
	public boolean setCols(Object n) {
		Integer i = (Integer) n;
		if (i == null || i < MIN_LINES || i > MAX_LINES)
			return false;
		cols = i;
		setImgEdge();
		rescaleImages();
		initializeMap();
				
		return true;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Draw background map
		for (int row = 0, y = 0; row < map.length; row++, y += IMG_EDGE) {
			for (int col = 0, x = 0; col < map[row].length; col++, x += IMG_EDGE) {
				
				char c = map[row][col];
				
				if (c != 'X' && c != 'B')
					g.drawImage(images.get('B'), x, y, this);
				
				Image img = images.get(c);
				
				if (img == null)
					System.err.println("Invalid Character in map. Was " + c);
				else {
					g.drawImage(img, x, y, this);
				}
				
			}
		}
		
		// Draw hero
		if (hero_pos != null)
			g.drawImage(images.get('A'), hero_pos[1] * IMG_EDGE, hero_pos[0] * IMG_EDGE, this);
		
		// Draw selection image
		if (selection != null)
			g.drawImage(images.get(selection), mousePos.x - IMG_EDGE / 2, mousePos.y - IMG_EDGE / 2, this);
				
	}
	
	private boolean setInMap(int[] pos, char select) {
		if (pos == null || pos.length != 2 || Arrays.equals(hero_pos, pos))
			return false;
		
		if (pos[0] == 0 || pos[0] == rows - 1 || pos[1] == 0 || pos[1] == cols -1) {
			if (select == 'S' || select == 'I') {
				map[pos[0]][pos[1]] = 'I';
				return true;
			} else {
				return false;
			}
		} else if (select == 'S' || select == 'I')
			return false;
		
		map[pos[0]][pos[1]] = select;
		
		return true;
	}
	
	private boolean setHeroPos(int[] pos) {
		if (setInMap(pos, 'B')) {
			hero_pos = pos;
			return true;
		} else
			return false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (selection == null)
			return;
		
		int[] pos = { e.getY() / IMG_EDGE, e.getX() / IMG_EDGE };
		
		// Restore Floor on LMB (left mouse button)
		if (e.getButton() == MouseEvent.BUTTON3) {
			setInMap(pos, 'B');
			return;
		}
						
		switch (selection) {
		case 'H': case 'A':
			setHeroPos(pos);
			break;
		case 'S': case 'I':
			if (setInMap(pos, 'I'))
				victory_pos.add(pos);
			break;
		default:
			setInMap(pos, selection);
		}
		
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		mousePos.setLocation(e.getPoint());
		repaint();
	}

}
