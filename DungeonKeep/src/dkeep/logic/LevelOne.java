package dkeep.logic;

import dkeep.logic.Guard.Personality;

public class LevelOne extends Level {
		
	private static final long serialVersionUID = 4L;

	public LevelOne() {
		enemies_activity = true;
		gameMap = new DungeonMap();
		hero = new Hero(DungeonMap.hero_pos);
		enemies.add(new Guard(DungeonMap.guard_pos));
	}
	
	public LevelOne(char[][] charArray, boolean activity, int[][] victory_pos) {
		super(charArray, victory_pos, activity);
		
		this.gameMap = new DungeonMap(gameMap.getMap(), victory_pos);
		
	}

	public LevelOne(Personality gp) {
		enemies_activity = true;
		gameMap = new DungeonMap();
		hero = new Hero(DungeonMap.hero_pos);
		enemies.add(new Guard(DungeonMap.guard_pos, gp));
	}

	@Override
	public Level nextLevel() {
		return new LevelTwo();
	}
	
	@Override
	public Hero getHero() {
		return hero;
	}
	
}
