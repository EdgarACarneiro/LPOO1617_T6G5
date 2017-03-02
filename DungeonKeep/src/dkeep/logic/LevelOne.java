package dkeep.logic;

public class LevelOne extends Level {
	
	private Hero hero;
	private 
	
	public LevelOne() {
		
	}

	@Override
	public Level nextLevel() {
		return new LevelTwo();
	}

}
