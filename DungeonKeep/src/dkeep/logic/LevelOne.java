package dkeep.logic;

public class LevelOne extends Level {
	
	private Hero hero;
	private Guard guard;
	private DungeonMap map;
	
	public LevelOne() {
		map = new DungeonMap();
		hero = new Hero(map.hero_pos);
		guard = new Guard(map.guard_pos);
	}

	@Override
	public Level nextLevel() {
		return new LevelTwo();
	}

	@Override
	public state update(int row, int col) {
		if (map.isValid(hero.pos[0]+row, hero.pos[1]+col))
			hero.update(row, col);
		guard.update();
		draw();		//TODO: Check -> Shouldnt be draw Game on cli?
		
		if (hero.isAdjacent(guard))
			return state.LOST;
		else if (map.update(hero.pos))
			return state.RUNNING;
		else
			return state.WON;
	}
	
	@Override
	public void draw() {
		
		// Creating a modifiable version of the map
		char[][] map_copy = new char[map.getMap().length][map.getMap()[0].length];
		
		for (int i = 0; i < map.getMap().length; ++i)
			for (int j = 0; j < map.getMap()[i].length; ++j)
				map_copy[i][j] = map.getMap()[i][j];
		
		hero.draw(map_copy);
		guard.draw(map_copy);
		
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
