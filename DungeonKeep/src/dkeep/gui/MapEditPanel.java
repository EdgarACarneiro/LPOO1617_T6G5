package dkeep.gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MapEditPanel extends JPanel implements MouseListener, MouseMotionListener {

	/**
	 * Auto-generated
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String BASE_NAME = "images/img";
	private static final String IMG_FORMAT = ".png";
	
	private static final int MAX_LINES = 12;
	
	// TODO load images in GameCharacter class, make logic as invisible as possible outside its package -- tried it, it's tough not to duplicate
	// Static Singleton with all images ? how to correctly access them? logic sees that singleton?
	private static final char[] characters = {'B', 'X', 'H', 'G', 'O', '*', 'k', 'I', 'S', 'A', 'K', '8', '$', 'g'};
	// only some characters are necessary - clumsy programming :/
	
	/**
	 * Characters to Images HashMap 
	 */
	private HashMap<Character, Image> images = new HashMap<Character, Image>();	// same as GamePanel's -- how to solve duplication ? singleton ?
	
	private Character selection = null;
	
	private int rows = 10;
	private int cols = 10;
	
	private int IMG_EDGE = 480 / (cols > rows ? cols : rows);
	
	private char[][] map;
	
	Point mousePos = new Point();
	
	/**
	 * Create the panel.
	 */
	public MapEditPanel() {
		initializeMap();
		
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
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	private void initializeMap() {
		map = new char[rows][cols];
		
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				map[r][c] = 'B';
			}
		}
	}
	
	private void rescaleImages() {
		Integer max = Math.max(rows, cols);
		
		IMG_EDGE = Math.min(this.getWidth(), this.getHeight()) / max;
			
		repaint();
	}
	
	public char[][] getMap() {
		return map;
	}
	
	public void setSelection(char c) {
		selection = c;
		this.requestFocusInWindow();
	}
	
	public boolean setRows(Object n) {
		Integer i = (Integer) n;
		if (i == null || i < 1 || i > MAX_LINES)
			return false;
		rows = i;
		rescaleImages();
		initializeMap();
		
		System.out.println("" + n);
		
		return true;
	}
	
	public boolean setCols(Object n) {
		Integer i = (Integer) n;
		if (i == null || i < 1 || i > MAX_LINES)
			return false;
		cols = i;
		rescaleImages();
		initializeMap();
		
		System.out.println("" + n);
		
		return true;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		// Draw background map
		for (int row = 0, y = 0; row < map.length; row++, y += IMG_EDGE) {
			for (int col = 0, x = 0; col < map[row].length; col++, x += IMG_EDGE) {
				
				g.drawImage(images.get('B'), x, y, this);
				char c = map[row][col];
				Image img = images.get(c);
				
				if (img == null)
					System.err.println("Invalid Character in map. Was " + c);
				else {
					g.drawImage(img, x, y, this);
				}
				
			}
		}
		
		if (selection != null)
			g.drawImage(images.get(selection), mousePos.x - IMG_EDGE / 2, mousePos.y - IMG_EDGE / 2, this);
				
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();

		if (e.getButton() == MouseEvent.BUTTON3) {
			map[p.y / this.getHeight()][p.x / this.getWidth()] = 'B';
		}
		
		if (selection == null)
			return;
		
		System.out.println("" + e.getX() + ", " + e.getY());
		
		map[p.y / IMG_EDGE][p.x / IMG_EDGE] = selection;
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
