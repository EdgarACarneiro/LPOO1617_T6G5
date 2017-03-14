package dkeep.logic;

public class GameHandler {
	
	private Level level;
	private int current_lvl;
	private String statusInfo;
	
	private Guard.Personality gp;
	private int numOgres;

	public GameHandler(Guard.Personality gp, int numOgres) {
		this.gp = gp;
		this.numOgres = numOgres;
		
		current_lvl = 1;
		level = new LevelOne(this.gp);
	}
	
	private void updateLevel() {
		switch (current_lvl) {	
		case 1:
			statusInfo = "New Level reached!";
			++current_lvl;
			level = new LevelTwo(numOgres);
			break;
		
		case 2:
			statusInfo = "You Won! Congratulations!";
			current_lvl = 0;
			level = null;
			break;
		}
	}
	
	public boolean update(int row, int col) {
		Level.State state = level.update(row, col);
		
		switch (state) {
		case RUNNING:
			statusInfo = "You can play now.";
			break;

		case WON:
			updateLevel();
			break;
			
		case LOST:
			statusInfo = "Game Over.";
			return false;
		}
		
		return true;
	}
	
	public String getMapStr() {
		if (level != null)
			return level.getMapStr();
		else
			return "";
	}
	
	public String getStatusInfo() {
		return statusInfo;
	}
}
	