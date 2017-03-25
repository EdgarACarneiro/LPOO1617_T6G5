package dkeep.logic;

import java.util.Random;

public class LevelTwo extends Level {
	
	private static final long serialVersionUID = 5L;
		
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
	
	public LevelTwo(char[][] charArr, int[][] victory_pos, boolean activity, boolean hero_armed) {
		super(charArr, victory_pos, activity);
		
		hero.armed = hero_armed;
		
		this.gameMap = new OgreMap(gameMap.getMap(), victory_pos);
	}
	
	public LevelTwo(int numOgres) {
		
		enemies_activity = true;
		gameMap = new OgreMap();  
		hero = new Hero(OgreMap.hero_pos, 'A', 'K');
		
		//Initializing all the ogres in the same place
		for (int i = 1; i <= numOgres; ++i)
			enemies.add(new Ogre(OgreMap.ogre_pos));
	}
	
	@Override
	public Level nextLevel() {
		// there's no next level - return null
		return null;
	}

	@Override
	public Hero getHero() {
		return hero;
	}

}
