package dkeep.logic;

public abstract class Level {
	public enum state {RUNNING, WON, LOST};
		
	/*
	 * Update game-state with hero movement
	 */
	public abstract state update(int row, int col);
	
	public abstract Level nextLevel();
	
	public abstract void draw();
	
}
