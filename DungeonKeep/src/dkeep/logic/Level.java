package dkeep.logic;

import java.util.ArrayList;

public abstract class Level {
	public enum State {RUNNING, WON, LOST};
	
	protected Hero hero;
	protected boolean enemies_activity;
	protected ArrayList<Character> enemies = new ArrayList<Character>();
	protected Map map;
	
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
		
		this.map = new Map (own_map, victory_pos);
	}
	
	public Level() {};
	
	/*
	 * Update game-state with hero movement
	 */
	public abstract State update(int row, int col);
	
	public abstract Level nextLevel();
	
	public void draw() {
		
		// Creating a modifiable version of the map
		char[][] map_copy = new char[map.getMap().length][map.getMap()[0].length];
		
		for (int i = 0; i < map.getMap().length; ++i)
			for (int j = 0; j < map.getMap()[i].length; ++j)
				map_copy[i][j] = map.getMap()[i][j];
		
		hero.draw(map_copy);
		for (Character e : enemies)
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
	
	public String getMapStr() {
		String string = "";
		
		// Creating a modifiable version of the map
		char[][] map_copy = new char[map.getMap().length][map.getMap()[0].length];
		
		for (int i = 0; i < map.getMap().length; ++i)
			for (int j = 0; j < map.getMap()[i].length; ++j)
				map_copy[i][j] = map.getMap()[i][j];
		
		hero.draw(map_copy);
		for (Character e : enemies)
			e.draw(map_copy);
		
		//Saving the content of the map to the return string
		for (char[] s : map_copy) {
			for (char c : s) {
				if (c == 'B')
					string += "  ";
				else
					string += c + " ";
			}
			string += "\n";
		}
		
		return string;
	}
	
	public abstract Hero getHero();
	
	public char[][] getMap() {
		return this.map.getMap();
	}
	
	public ArrayList<Character> getEnemies() {
		return enemies;
	}
	
}
