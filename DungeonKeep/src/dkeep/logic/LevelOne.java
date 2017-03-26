package dkeep.logic;

import dkeep.logic.Guard.Personality;

/**
 * Class responsible for handling Level's of type Level One methods.
 *
 */
public class LevelOne extends Level implements java.io.Serializable {
	
	/**
	 * long SerialVersionUID. Class's ID for serialization.
	 */
	private static final long serialVersionUID = 4L;

	/**
	 * Default Constructor.
	 * Sets enemies active.
	 * Sets gameMap as a default DungeonMap.
	 * Sets Hero Position to the DungeonMap's hero position.
	 * Adds a single Guard, as a enemy, at the DungeonMaps' guard position.
	 */
	public LevelOne() {
		enemies_activity = true;
		gameMap = new DungeonMap();
		hero = new Hero(DungeonMap.hero_pos);
		enemies.add(new Guard(DungeonMap.guard_pos));
	}
	
	/**
	 * Level One Constructor:
	 * Constructor from a map, it's victory positions and the enemies activity.
	 * Calls a superclass constructor.
	 * 
	 * @param map A matrix of chars containing the map's visual representation.
	 * @param victory_pos A array of positions containing the victory positions of the map.
	 * @param activity Boolean that if set all enemies are active, otherwise all enemies are inactive.
	 */
	public LevelOne(char[][] charArray, boolean activity, int[][] victory_pos) {
		super(charArray, victory_pos, activity);
		
		this.gameMap = new DungeonMap(gameMap.getMap(), victory_pos);
		
	}

	/**
	 * Level's One Constructor:
	 * Sets enemies active.
	 * Sets gameMap as a default DungeonMap.
	 * Sets Hero Position to the DungeonMap's hero position.
	 * Adds a single Guard, as a enemy, at the DungeonMaps' guard position, with Personality gp.
	 * 
	 * @param gp. Level One gurad's personality.
	 */
	public LevelOne(Personality gp) {
		enemies_activity = true;
		gameMap = new DungeonMap();
		hero = new Hero(DungeonMap.hero_pos);
		enemies.add(new Guard(DungeonMap.guard_pos, gp));
	}

	/** 
	 * Function returning a Level Two.
	 * 
	 * @see dkeep.logic.Level#nextLevel()
	 */
	@Override
	public Level nextLevel() {
		return new LevelTwo();
	}
	
	/** 
	 * Function returning this level's Hero.
	 * 
.	 * @see dkeep.logic.Level#getHero()
	 */
	@Override
	public Hero getHero() {
		return hero;
	}
	
}
