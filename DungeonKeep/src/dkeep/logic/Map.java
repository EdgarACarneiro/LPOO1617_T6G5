package dkeep.logic;

public abstract class Map {
	
	protected char[][] map;
	
	private int[][] victory_pos;
	
	protected void setVictoryPos(int[][] positions) {
		victory_pos = positions;
	}
	
	public abstract boolean isValid(int row, int col);
	
	public abstract boolean update(int[] hero_pos);
	
	public char[][] getMap() {
		return map;
	}
	
	protected boolean isWon(int[] hero_pos) {
		for (int[] pos : victory_pos) {
			if (hero_pos.equals(pos))
				return true;
		}
		
		return false;		
	}
	
}
