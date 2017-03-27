package dkeep.logic;

import java.util.Arrays;


/**
 * Class responsible for handling GameMap's generic methods
 *
 */
public class GameMap implements java.io.Serializable {
	
	/**
	 * long SerialVersionUID. Class's ID for serialization.
	 */
	private static final long serialVersionUID = 6L;
	
	/**
	 * Matrix of chars representing the map
	 */
	protected char[][] map;
	
	/**
	 * Valid symbols where Characters can walk to.
	 */
	protected char[] valid_symbs = {'B', 'S', 'k'};
	
	/**
	 * Current position of the map's key
	 */
	protected int[] key_pos = new int[2];
	
	/**
	 * Array of positions containing the victory positions.
	 */
	private int[][] victory_pos;
	
	/**
	 * Game Map Constructor. 
	 * Matrix of chars containig the map information.
	 * Array of position containing the victory positions.
	 * 
	 * @param map Matrix of chars containing 
	 * @param pos_vic Victory positions
	 */
	GameMap (char[][] map, int[][] pos_vic) {
		this.map = map;
		this.victory_pos = pos_vic;
	}
	
	/**
	 * Default constructor
	 */
	GameMap() {};
		
	/**
	 * Set array of victory positions to the given positions
	 * 
	 * @param positions new victory positions
	 */
	protected void setVictoryPos(int[][] positions) {
		victory_pos = positions;
	}
			
	/**
	 * @return Matrix of chars representing the Map
	 */
	public char[][] getMap() {
		return map;
	}
	
	/**
	 * Check if the position given is valid.
	 * 
	 * @param row row of the Matrix to be checked
	 * @param col col of the Matrix to be checked
	 * @return true if its valid, false otherwise
	 */
	public boolean isValid(int row, int col) {
		for (char c : valid_symbs)
			if (map[row][col] == c)
				return true;
		return false;
	}
	
	/**
	 * Game finishes if hero reaches one of the victory positions
	 * 
	 * @param hero Hero used
	 * @return false if hero reached victory, true otherwise
	 */
	public boolean update(Hero hero) {
		for (int[] v_pos : victory_pos) {
			if (Arrays.equals(v_pos, hero.pos))
				return false;
		}
				
		return true;		
	}
	
	/**
	 * Changes the map, opening the door at the given position.
	 * 
	 * @param row row of the Matrix to be checked
	 * @param col col of the Matrix to be checked
	 * @return True if a door was open, false otherwise
	 */
	public boolean openDoorAt(int row, int col) {
		if (map[row][col] == 'I') {
			map[row][col] = 'S';
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * @return current key position
	 */
	public int[] getKeyPos() {
		return key_pos;
	}
}
