package dkeep.logic;

import dkeep.logic.Guard.Personality;

public class LevelOne extends Level {
		
	public LevelOne() {
		enemies_activity = true;
		map = new DungeonMap();
		hero = new Hero(DungeonMap.hero_pos);
		enemies.add(new Guard(DungeonMap.guard_pos));
	}
	
	public LevelOne(char[][] charArray, boolean activity, int[][] victory_pos) {
		super(charArray, activity, victory_pos);
		
		this.map = new DungeonMap(map.getMap(), victory_pos);
		
	}

	public LevelOne(Personality gp) {
		enemies_activity = true;
		map = new DungeonMap();
		hero = new Hero(DungeonMap.hero_pos);
		enemies.add(new Guard(DungeonMap.guard_pos, gp));
	}

	@Override
	public Level nextLevel() {
		return new LevelTwo();
	}

	@Override
	public State update(int row, int col) {
		hero.update(map, row, col);
		
		if (enemies_activity) {
			for (Character e : enemies)
				e.update(map);
		}
		
		for (Character e : enemies) {
			if (e.attack(hero)) {
				System.out.println("You lost...");
				return State.LOST;
			}
		}
		
		if (map.update(hero))
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
