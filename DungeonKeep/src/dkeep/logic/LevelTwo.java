package dkeep.logic;

import java.util.Random;

public class LevelTwo extends Level {
		
	public LevelTwo() {
		Random rand = new Random();
		
		enemies_activity = true;
		//enemies.add(new Ogre(OgreMap.ogre_pos));
		map = new OgreMap();  
		hero = new Hero(OgreMap.hero_pos, 'A', 'K');
		
		//Initializing all the ogres in the same place
		int ogres_number = rand.nextInt(2) + 1;
		for (int i = 0; i <= ogres_number; ++i)
			enemies.add(new Ogre(OgreMap.ogre_pos));
		
		System.out.println(enemies.size() + " wild Ogres appear !!");
	}
	
	public LevelTwo(char[][] charArr, boolean activity, boolean hero_armed, int[][] victory_pos) {
		super(charArr, activity, victory_pos);
		
		hero.armed = hero_armed;
		
		this.map = new OgreMap(map.getMap(), victory_pos);
	}
	
	public LevelTwo(int numOgres) {
		
		enemies_activity = true;
		map = new OgreMap();  
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
	public State update(int row, int col) {
		
		hero.update(map, row, col);
		
		for (Character e : enemies) {
			
			if (hero.attack(e))
				System.out.println("Hero stunned an Ogre at " + e.pos[0] + ", " + e.pos[1]);
			
			if (this.enemies_activity)
				e.update(map);
			
			if (e.attack(hero)) {
				System.out.println("You lost...");
				return State.LOST;
			}			
		}
		
		if (map.update(hero)) {
			return State.RUNNING;
		} else {
			System.out.println("You Won!!");
			return State.WON;
		}
	}

	@Override
	public Hero getHero() {
		return hero;
	}

}
