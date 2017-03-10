package dkeep.logic;

public class GameHandler {
	
	private Level level;
	
	private Guard.Personality gp;
	private int numOgres;

	public GameHandler(Guard.Personality gp, int numOgres) {
		this.gp = gp;
		this.numOgres = numOgres;
		
		level = new LevelOne(gp);
	}
	
	public boolean update(int row, int col) {
		Level.State state = level.update(row, col);
		
		return false;
	}
}
