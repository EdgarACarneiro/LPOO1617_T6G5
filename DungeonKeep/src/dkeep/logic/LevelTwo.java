package dkeep.logic;

import java.util.Random;

public class LevelTwo extends Level {

	private Random rand = new Random();
	
	private Hero hero;
	int ogreNumber ;
	private Ogre[] ogres;
	private OgreMap map;
	
	public LevelTwo() {
		map = new OgreMap();
		hero = new Hero(map.hero_pos);
		ogreNumber = rand.nextInt(3) + 2;
		ogres = new Ogre[ogreNumber];
		
		//Initializing all the ogres in the same place
		for (int i = 0; i < ogres.length; ++i)
			ogres[i] = new Ogre(map.ogre_pos);
		
	}

	@Override
	public state update(int row, int col) {
		
		if (map.isValid(hero.pos[0]+row, hero.pos[1]+col))
			hero.update(row, col);
		
		for (int i = 0; i < ogres.length; ++i) {
			ogres[i].update(map);
			
			if (hero.isAdjacent(ogres[i])) {
				System.out.println("You lost...");
				return state.LOST;
			}			
		}
		
		if (map.update(hero.pos)) {
			return state.RUNNING;
		} else {
			System.out.println("You Won!!");
			return state.WON;
		}
	}

	@Override
	public Level nextLevel() {
		// there's no next level - return null
		return null;
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
