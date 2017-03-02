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
	public boolean update(int row, int col) {
		hero.update(row, col)
		
		return map.update(hero.pos);
	}

}
