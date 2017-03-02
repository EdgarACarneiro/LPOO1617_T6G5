package dkeep.logic;

public abstract class Map {
	
	protected char[][] map;
	
	public abstract boolean isValid(int row, int col);
	
	public abstract boolean update(int row, int col);
	
	public char[][] getMap() {
		return map;
	}
	
}
