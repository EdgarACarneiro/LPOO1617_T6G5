package dkeep.logic;

public abstract class Level {
	public enum state {RUNNING, WON, LOST};
	
	protected boolean enemies_activity;
	
	public Level(char[][] map, boolean activity) {
		
		char[][] own_map = new char[map.length][map[0].length];
		
		enemies_activity = activity;
		
		for (int i = 0; i < map.length; ++i) {
			for (int j = 0; j < map[0].length; ++j) {
				
				switch(map[i][j]) {
				case 'B':
					own_map[i][j] = 'B';
					break;
				case 'X':
					own_map[i][j] = 'X';
					break;
				case 'I':
					own_map[i][j] = 'I';
					break;
				case 'S':
					own_map[i][j] = 'S';
					break;
				case 'k':
					own_map[i][j] = 'k';
					//setLeverPos(new int[] {i, j});
					break;
				case 'H':
					own_map[i][j] = 'B';
					Hero hero = new Hero(new int[] {i, j}, 'H', 'K', 'A');
					break;
				case 'G':
					own_map[i][j] = 'B';
					Guard guard = new Guard(new int[] {i, j});
					break;
				case 'O':
					own_map[i][j] = 'B';
					Ogre ogre = new Ogre(new int[] {i, j});
					break;
				}	
			}
		}
		
		Map board = new Map (own_map);
	}
	
	public Level() {};
	
	/*
	 * Update game-state with hero movement
	 */
	public abstract state update(int row, int col);
	
	public abstract Level nextLevel();
	
	public abstract void draw();
	
	public abstract Hero getHero();
	
	public abstract char[][] getMap();
	
}
