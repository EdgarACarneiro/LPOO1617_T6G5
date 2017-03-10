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
	
	// TODO
	public boolean update(int row, int col) {
		Level.State state = level.update(row, col);
		
		return false;
	}
	
	public String getMapStr() {
		return level.getMapStr();
	}
}
