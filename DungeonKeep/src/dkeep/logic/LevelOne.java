package dkeep.logic;

public class LevelOne extends Level {
	
	private Hero hero;
	private Guard guard;
	private DungeonMap map;
	
	public LevelOne() {
		enemies_activity = true;
		map = new DungeonMap();
		hero = new Hero(DungeonMap.hero_pos);
		guard = new Guard(DungeonMap.guard_pos);
	}
	
	public LevelOne(char[][] map, boolean activity) {
		super(map, activity);
	}

	@Override
	public Level nextLevel() {
		return new LevelTwo();
	}

	@Override
	public state update(int row, int col) {
		if (map.isValid(hero.pos[0]+row, hero.pos[1]+col))
			hero.update(row, col);
		
		if (enemies_activity)
			guard.update();
		
		if (guard.attack(hero)) {
			System.out.println("You lost...");
			return state.LOST;
		} else if (map.update(hero)) {
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
	
	@Override
	public Hero getHero() {
		return hero;
	}
	
	@Override
	public char[][] getMap() {
		return this.map.getMap();
	}
}
