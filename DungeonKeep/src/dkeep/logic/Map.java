package dkeep.logic;

import java.util.Arrays;

public abstract class Map {
	
	protected char[][] map;
	
	protected char[] valid_symbs;
	
	private int[][] victory_pos;
		
	protected void setVictoryPos(int[][] positions) {
		victory_pos = positions;
	}
			
	public final char[][] getMap() {
		return map;
	}
	
	public boolean isValid(int row, int col) {
		for (char c : valid_symbs)
			if (map[row][col] == c)
				return true;
		return false;
	}
	
	// Returns false when game is over (hero reached victory position)
	public boolean update(int[] hero_pos) {
		for (int[] v_pos : victory_pos) {
			if (Arrays.equals(v_pos, hero_pos))
				return false;
		}
				
		return true;		
	}
	
}
