package dkeep.logic;

import java.util.ArrayList;

/**
 * Abstract class responsible for handling Level's generic methods.
 * 
 */
public abstract class Level implements java.io.Serializable {
	
	/**
	 * long SerialVersionUID. Class's ID for serialization.
	 */
	private static final long serialVersionUID = 3L;
	
	/**
	 * Enumeration containing all the possibilities of the game cycle update output
	 *
	 */
	public enum State {RUNNING, WON, LOST};
	
	/**
	 * Hero hero. Level's hero. 
	 */
	protected Hero hero;
	/**
	 * boolean enemies_activity. Boolean containing information regarding enemies activity.
	 * If true, enemies are active. Otherwise, enemies are inactive.
	 * 
	 */
	protected boolean enemies_activity;
	/**
	 * ArrayList<GameCharacter> enemies. ArrayList containing all the enemy character's in the Level.
	 */
	protected ArrayList<GameCharacter> enemies = new ArrayList<GameCharacter>();
	/**
	 * GameMap gameMap. GameMap containing information regarding the Level's map.
	 */
	protected GameMap gameMap;
	
	/**
	 * Level's Constructor
	 * Constructor from a map, it's victory positions and the enemies activity.
	 * 
	 * @param map A matrix of chars containing the map's visual representation.
	 * @param victory_pos A array of positions containing the victory positions of the map.
	 * @param activity Boolean that if set all enemies are active, otherwise all enemies are inactive.
	 */
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
	
	/**
	 * Default Constructor
	 */
	public Level() {};
	

	/**
	 * Updates game State with Hero Movement
	 * 
	 * @param row Hero Position on the Horizontal Axis
	 * @param col Hero Position on the Vertical Axis
	 * @return State of the game cycle after all the processing of the game variables. 
	 */
	public State update(int row, int col) {
		
		hero.update(gameMap, row, col);
		
		for (GameCharacter e : enemies) {
			
			if (this.enemies_activity)
				e.update(gameMap);
			
			if (hero.armed)
				hero.attack(e);
			
			if (e.attack(hero)) {
				System.out.println("You lost...");
				return State.LOST;
			}	
		}
		
		if (gameMap.update(hero)) {
			return State.RUNNING;
		} else {
			System.out.println("You Won!!");
			return State.WON;
		}
	}
	
	/**
	 * Function returning the next Level when the Hero reaches the victory position.
	 * 
	 * @return next Level, when hero wins current Level.
	 */
	public abstract Level nextLevel();
	
	/**
	 * Function responsible for Drawing the game on the console
	 */
	public void draw() {
		char[][] map_copy = getMapCopy();
		
		hero.draw(map_copy);
		for (GameCharacter e : enemies)
			e.draw(map_copy);
		
		printMap(map_copy);
	}
	
	/**
	 * Function returning a copy of the current gameMap
	 * 
	 * @return char[][] copy of current gameMap
	 */
	private char[][] getMapCopy() {
		char[][] map = gameMap.getMap();
		
		char[][] map_copy = new char[map.length][map[0].length];
		
		for (int i = 0; i < map.length; ++i)
			for (int j = 0; j < map[i].length; ++j)
				map_copy[i][j] = map[i][j];
		
		return map_copy;
	}
	
	
	/**
	 * Function that prints the current map on the console
	 * 
	 * @param map Matrix of chars containing a visual copy of the gameMap
	 */
	private void printMap(char[][] map) {
		for (char[] s : map) {
			for (char c : s) {
				if (c == 'B')
					System.out.print("  ");
				else
					System.out.print(c + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * @return Current Level's Hero
	 */
	public abstract Hero getHero();
	
	/**
	 * @return Current Level's gameMap
	 */
	public char[][] getMap() {
		return this.gameMap.getMap();
	}
	
	/**
	 * @return ArrayList containing all the enemy characters.
	 */
	public ArrayList<GameCharacter> getEnemies() {
		return enemies;
	}
	
	/**
	 * @return ArrayList containing all the game characters: enemies and hero.
	 */
	public ArrayList<GameCharacter> getCharacters() {
		ArrayList<GameCharacter> ret = new ArrayList<GameCharacter>();
		
		ret.addAll(enemies);
		ret.add(hero);
		
		return ret;
	}
		
}
