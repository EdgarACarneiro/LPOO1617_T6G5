package dkeep.logic;

import java.util.ArrayList;

public abstract class Level implements java.io.Serializable{
	
	private static final long serialVersionUID = 3L;
	
	public enum State {RUNNING, WON, LOST};
	
	protected Hero hero;
	protected boolean enemies_activity;
	protected ArrayList<GameCharacter> enemies = new ArrayList<GameCharacter>();
	protected GameMap gameMap;
	
	public Level(char[][] map, int[][] victory_pos, boolean activity) {
		
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
				case 'k':
					own_map[i][j] = 'k';
					break;
				case 'A':
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
		
		this.gameMap = new GameMap (own_map, victory_pos);
	}
	
	public Level() {};
	
	/*
	 * Update game-state with hero movement
	 */
	public abstract State update(int row, int col);
	
	public abstract Level nextLevel();
	
	public void draw() {
		char[][] map = gameMap.getMap();
		
		// Creating a modifiable version of the map
		char[][] map_copy = new char[map.length][map[0].length];
		
		for (int i = 0; i < map.length; ++i)
			for (int j = 0; j < map[i].length; ++j)
				map_copy[i][j] = map[i][j];
		
		hero.draw(map_copy);
		for (GameCharacter e : enemies)
			e.draw(map_copy);
		
		// Printing the modified map
		for (char[] s : map_copy) {
			for (char c : s) {
				if (c == 'B')
					System.out.print("  ");
				else
					System.out.print(c + " ");
			}
			System.out.println();
		}
	}
	
	public abstract Hero getHero();
	
	public char[][] getMap() {
		return this.gameMap.getMap();
	}
	
	public ArrayList<GameCharacter> getEnemies() {
		return enemies;
	}
	
	public ArrayList<GameCharacter> getCharacters() {
		ArrayList<GameCharacter> ret = new ArrayList<GameCharacter>();
		
		ret.addAll(enemies);
		ret.add(hero);
		
		return ret;
	}
		
}
