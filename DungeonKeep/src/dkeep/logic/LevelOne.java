package dkeep.logic;

import dkeep.logic.Guard.Personality;

public class LevelOne extends Level {
		
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
	public State update(int row, int col) {
		hero.update(gameMap, row, col);
		
		if (enemies_activity) {
			for (GameCharacter e : enemies)
				e.update(gameMap);
		}
		
		for (GameCharacter e : enemies) {
			if (e.attack(hero)) {
				System.out.println("You lost...");
				return State.LOST;
			}
		}
		
		if (gameMap.update(hero))
			return State.RUNNING;
		else {
			System.out.println("You Won!!");
			return State.WON;
		}
	}
	
	@Override
	public Hero getHero() {
		return hero;
	}
	
}
