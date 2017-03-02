package dkeep.logic;

public abstract class Level {
		
	/*
	 * Update game-state with hero movement
	 */
	public abstract boolean update(int row, int col);
	
	public abstract Level nextLevel();
	
}
