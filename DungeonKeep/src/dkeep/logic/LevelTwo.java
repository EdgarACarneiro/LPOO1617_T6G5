package dkeep.logic;

import java.util.Random;

/**
 * Class responsible for handling Level's of type Level Two methods.
 * 
 */
public class LevelTwo extends Level {
	
	/**
	 * long SerialVersionUID. Class's ID for serialization
	 */
	private static final long serialVersionUID = 5L;
		
	/**
	 * Default Constructor:
	 * Set enemies active.
	 * Sets game Map as a new Ogre Map.
	 * Sets Hero Position to the OgreMap's hero position.
	 * Adds a random number of ogres, as enemies, at the OgreMap's enemies position.
	 */
	public LevelTwo() {
		Random rand = new Random();
		
		enemies_activity = true;
		gameMap = new OgreMap();  
		hero = new Hero(OgreMap.hero_pos, 'A', 'K');
		
		//Initializing all the ogres in the same place
		int ogres_number = rand.nextInt(2) + 1;
		for (int i = 0; i <= ogres_number; ++i)
			enemies.add(new Ogre(OgreMap.ogre_pos));
	}
	
	/**
	 * Level Two Constructor:
	 * Constructor from a map, it's victory positions , the enemies activity and if the hero is armed.
	 * Calls a superclass constructor.
	 * 
	 * @param map A matrix of chars containing the map's visual representation.
	 * @param victory_pos A array of positions containing the victory positions of the map.
	 * @param activity Boolean that if set all enemies are active, otherwise all enemies are inactive.
	 * @param hero_armed Booleean the if set the hero is armed, otherwise the hero is unarmed.
	 */
	public LevelTwo(char[][] charArr, int[][] victory_pos, boolean activity, boolean hero_armed) {
		super(charArr, victory_pos, activity);
		
		hero.armed = hero_armed;
		
		this.gameMap = new OgreMap(gameMap.getMap(), victory_pos);
	}
	
	/**
	 * Level Two Constructor:
	 * Sets enemies active.
	 * Sets gameMap as a default OgreMap.
	 * Sets Hero Position to the DungeonMap's hero position.
	 * Adds numOgres number of Ogres, as enemies, at the OgreMap's enemy position.
	 *
	 * @param numOgres. Level Two number of Ogres.
	 */
	public LevelTwo(int numOgres) {
		
		enemies_activity = true;
		gameMap = new OgreMap();  
		hero = new Hero(OgreMap.hero_pos, 'A', 'K');
		
		//Initializing all the ogres in the same place
		for (int i = 1; i <= numOgres; ++i)
			enemies.add(new Ogre(OgreMap.ogre_pos));
	}
	
	/** 
	 * Returning next Level, but in this case there is no next Level.
	 * 
	 * @see dkeep.logic.Level#nextLevel()
	 */
	@Override
	public Level nextLevel() {
		// there's no next level - return null
		return null;
	}

	/** 
	 * Function returning this level's Hero.
	 * 
	 * @see dkeep.logic.Level#getHero()
	 */
	@Override
	public Hero getHero() {
		return hero;
	}

}
