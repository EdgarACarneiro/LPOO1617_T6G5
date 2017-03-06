package dkeep.logic;

import java.util.Random;

public class LevelTwo extends Level {
	
	OgreMap map;
	
	public LevelTwo() {
		Random rand = new Random();
		
		enemies_activity = true;
		//enemies.add(new Ogre(OgreMap.ogre_pos));
		map = new OgreMap();  
		hero = new Hero(OgreMap.hero_pos, 'A', 'K');
		
		//Initializing all the ogres in the same place
		int ogres_number = rand.nextInt(2) + 2;
		for (int i = 0; i <= ogres_number; ++i)
			enemies.add(new Ogre(OgreMap.ogre_pos));
		
		System.out.println(enemies.size() + " wild Ogres appear !!");
	}
	
	public LevelTwo(char[][] map, boolean activity, int[][] victory_pos) {
		super(map, activity, victory_pos);
		
		this.map = new OgreMap(board.getMap(), victory_pos);
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
		
		for (Character e : enemies) {
			
			if (hero.attack(e))
				System.out.println("Hero stunned an Ogre at " + e.pos[0] + ", " + e.pos[1]);
			
			if (this.enemies_activity)
				e.update(map);
			
			if (e.attack(hero)) {
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

	@Override
	public Hero getHero() {
		return hero;
	}
	
	@Override
	public char[][] getMap() {
		return this.map.getMap();
	}
}
