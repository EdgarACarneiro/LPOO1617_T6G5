package dkeep.logic;

public class LevelOne extends Level {
	
	DungeonMap map;
	
	public LevelOne() {
		enemies_activity = true;
		map = new DungeonMap();
		hero = new Hero(DungeonMap.hero_pos);
		enemies.add(new Guard(DungeonMap.guard_pos));
	}
	
	public LevelOne(char[][] map, boolean activity, int[][] victory_pos) {
		super(map, activity, victory_pos);
		
		this.map = new DungeonMap(board.getMap(), victory_pos);
		
	}

	@Override
	public Level nextLevel() {
		return new LevelTwo();
	}

	@Override
	public state update(int row, int col) {
		hero.update(map, row, col);
		
		if (enemies_activity) {
			for (Character e : enemies)
				e.update(map);
		}
		
		for (Character e : enemies) {
			if (e.attack(hero)) {
				System.out.println("You lost...");
				return state.LOST;
			}
		}
		
		if (map.update(hero))
			return state.RUNNING;
		else {
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
