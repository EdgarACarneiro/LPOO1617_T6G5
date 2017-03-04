package dkeep.logic;

import java.util.Random;

public class LevelTwo extends Level {
	
	private Hero hero;
	private Ogre[] ogres;
	private OgreMap map;
	
	public LevelTwo() {
		Random rand = new Random();
		
		ogres = new Ogre[rand.nextInt(2) + 2];
//		ogres = new Ogre[1];
		map = new OgreMap();
		hero = new Hero(OgreMap.hero_pos, 'A', 'K');
		
		//Initializing all the ogres in the same place
		for (int i = 0; i < ogres.length; ++i)
			ogres[i] = new Ogre(OgreMap.ogre_pos);
		
		System.out.println(ogres.length + " wild Ogres appear !!");
		
	}
	
	@Override
	public Level nextLevel() {
		// there's no next level - return null
		return null;
	}

	@Override
	public state update(int row, int col) {
		
		if (map.isValid(hero, new int[] {row, col}))
			hero.update(row, col);
		
		for (int i = 0; i < ogres.length; ++i) {
			
			if (hero.attack(ogres[i]))
				System.out.println("Hero stunned an Ogre at " + ogres[i].pos[0] + ", " + ogres[i].pos[1]);
			
			ogres[i].update(map);
			if (ogres[i].attack(hero)) {
				System.out.println("You lost...");
				return state.LOST;
			}			
		}
		
		if (map.update(hero)) {
			return state.RUNNING;
		} else {
			System.out.println("You Won!!");
			return state.WON;
		}
	}
	
	@Override
	public void draw() {

		// Creating a modifiable version of the map
		char[][] map_copy = new char[map.getMap().length][map.getMap()[0].length];
		
		for (int i = 0; i < map.getMap().length; ++i)
			for (int j = 0; j < map.getMap()[i].length; ++j)
				map_copy[i][j] = map.getMap()[i][j];
		
		hero.draw(map_copy);
		for (int i = 0; i < ogres.length; ++i)
			ogres[i].draw(map_copy);
		
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

}
