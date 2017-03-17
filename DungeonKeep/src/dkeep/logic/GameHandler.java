package dkeep.logic;

import java.util.ArrayList;

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
	
	private boolean updateLevel() {
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
			return false;

		}
		
		return true;
	}
	
	public boolean update(int row, int col) {
		Level.State state = level.update(row, col);
		
		switch (state) {
		case RUNNING:
			statusInfo = "You can play now.";
			break;

		case WON:
			return(updateLevel());
			
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
	
	public ArrayList<Character> getCharacters() {
		return level.getCharacters();
	}
	
	public char[][] getMap() {
		return level.getMap();
	}
	
}
	