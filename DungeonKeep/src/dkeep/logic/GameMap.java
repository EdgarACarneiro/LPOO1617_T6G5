package dkeep.logic;

import java.util.Arrays;


public class GameMap implements java.io.Serializable {
	
	private static final long serialVersionUID = 6L;
	
	protected char[][] map;
	
	protected char[] valid_symbs = {'B', 'S', 'k'};
	
	protected int[] key_pos = new int[2];
	
	private int[][] victory_pos;
	
	GameMap (char[][] map, int[][] pos_vic) {
		this.map = map;
		this.victory_pos = pos_vic;
	}
	
	GameMap() {};
		
	protected void setVictoryPos(int[][] positions) {
		victory_pos = positions;
	}
			
	public char[][] getMap() {
		if (map == null)
			System.err.println("NULL map requested!");
		return map;
	}
	
	public boolean isValid(int row, int col) {
		for (char c : valid_symbs)
			if (map[row][col] == c)
				return true;
		return false;
	}
	
	// Returns false when game is over (hero reached victory position)
	public boolean update(Hero hero) {
		for (int[] v_pos : victory_pos) {
			if (Arrays.equals(v_pos, hero.pos))
				return false;
		}
				
		return true;		
	}
	
	public boolean openDoorAt(int row, int col) {
		if (map[row][col] == 'I') {
			map[row][col] = 'S';
			return true;
		}
		
		return false;
	}
	
	// Worth a method ?
	public int[] getKeyPos() {
		return key_pos;
	}
}