package dkeep.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JPanel;
import dkeep.logic.Level;
import dkeep.logic.LevelTwo;

public class MapEditPanel extends JPanel implements MouseListener, MouseMotionListener {

	/**
	 * Auto-generated
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int MIN_LINES = 5;
	public static final int MAX_LINES = 15;

	private Character selection = null;
	
	private int rows = 10;
	private int cols = 10;
	
	private char[][] map;
	private ArrayList<int[]> victory_pos = new ArrayList<int[]>();
	private int[] hero_pos = new int[] {1, 1};
	private int[] key_pos = null;
	
	Point mousePos = new Point();
	
	private ImageLoader images = ImageLoader.getInstance();
	
	private int IMG_EDGE;
	
	/**
	 * Create the panel.
	 */
	public MapEditPanel() {
		reset();
		
		this.requestFocusInWindow();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	public void reset() {
		images.setImgEdge(this, Math.max(rows, cols));
		images.rescaleImages();
		initializeMap();
		key_pos = null;
		selection = null;
		IMG_EDGE = images.getImgEdge();
		repaint();
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
	
	public Level getLevel() {
		if (hero_pos == null || key_pos == null)
			return null;
		
		map[hero_pos[0]][hero_pos[1]] = 'A';
		map[key_pos[0]][key_pos[1]] = 'k';
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
		
		reset();
				
		return true;
	}
	
	public boolean setCols(Object n) {
		Integer i = (Integer) n;
		if (i == null || i < MIN_LINES || i > MAX_LINES)
			return false;
		cols = i;

		reset();
				
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
		
		// Draw key
		if (key_pos != null)
			g.drawImage(images.get('k'), key_pos[1] * IMG_EDGE, key_pos[0] * IMG_EDGE, this);
		
		// Draw selection image
		if (selection != null)
			g.drawImage(images.get(selection), mousePos.x - IMG_EDGE / 2, mousePos.y - IMG_EDGE / 2, this);
				
	}
	
	private boolean setInMap(int[] pos, char select) {
		if (pos == null || pos.length != 2 || Arrays.equals(hero_pos, pos))
			return false;
		
		if (pos[0] == 0 || pos[0] == rows - 1 || pos[1] == 0 || pos[1] == cols -1) {
			if (select == 'S' || select == 'I' || select == 'X') {
				map[pos[0]][pos[1]] = select;
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
	
	private boolean setKeyPos(int[] pos) {
		if (setInMap(pos, 'B')) {
			key_pos = pos;
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
		case 'k':
			setKeyPos(pos);
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
