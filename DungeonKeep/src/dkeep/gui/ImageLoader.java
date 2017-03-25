package dkeep.gui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * Image Loader - implemented as a global singleton
 */
public class ImageLoader {
	private static ImageLoader instance = null;
	
	private final char[] characters = {'B', 'X', 'H', 'G', 'O', '*', 'k', 'I', 'S', 'A', 'K', '8', '$', 'g'};
	
	private static final String BASE_NAME = "images/img";
	private static final String IMG_FORMAT = ".png";
	
	private Integer IMG_EDGE = null;
	
	public static final int MAX_IMG_EDGE = 50;
	
	/**
	 * Characters to Images HashMap 
	 */
	private HashMap<Character, Image> images = new HashMap<Character, Image>();

	
	// Private constructor prevents instantiation from other classes
	private ImageLoader() {
		loadImages();
	}
	  
	public static ImageLoader getInstance() {
		if (instance == null)
			instance = new ImageLoader();
		
		return ImageLoader.instance;
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
	
	public void setImgEdge(int n) {
		if (n > 0 && n < MAX_IMG_EDGE) {
			IMG_EDGE = n;
		} else {
			IMG_EDGE = MAX_IMG_EDGE;
		}
	}
	
	public boolean setImgEdge(JComponent comp, int numLines) {
		int n = Math.min(comp.getWidth(), comp.getHeight()) / numLines;
		
		if (n > 0 && n < MAX_IMG_EDGE) {
			IMG_EDGE = n;
			return true;
		} else if (IMG_EDGE == null) {
			IMG_EDGE = MAX_IMG_EDGE;
			return true;
		}
		
		return false;
	}
		
	public Integer getImgEdge() {
		return IMG_EDGE;
	}
	
	public void rescaleImages() {		
		loadImages();

		for (Map.Entry<Character, Image> entry : images.entrySet()) {
			entry.setValue(entry.getValue().getScaledInstance(IMG_EDGE, IMG_EDGE, Image.SCALE_DEFAULT));
		}
	}
	
	public void reset() {
		setImgEdge(MAX_IMG_EDGE);
		rescaleImages();
	}
	
	public Image get(char c) {
		return images.get(c);
	}
	
}
