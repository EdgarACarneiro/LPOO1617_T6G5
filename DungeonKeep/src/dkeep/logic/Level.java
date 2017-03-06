package dkeep.logic;

import java.util.ArrayList;

public abstract class Level {
	public enum state {RUNNING, WON, LOST};
	
	protected Hero hero;
	protected boolean enemies_activity;
	protected ArrayList<Character> enemies = new ArrayList();
	protected Map board;
	
	public Level(char[][] map, boolean activity, int[][] victory_pos) {
		
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
					break;
				case 'H':
					own_map[i][j] = 'B';
					hero = new Hero(new int[] {i, j}, 'H', 'K', 'A');
					break;
				case 'G':
					own_map[i][j] = 'B';
					enemies.add(new Guard(new int[] {i, j}));
					break;
				case 'O':
					own_map[i][j] = 'B';
					enemies.add(new Ogre(new int[] {i, j}));
					break;
				}	
			}
		}
		
		this.board = new Map (own_map, victory_pos);
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
