package dkeep.logic;

public class LevelTwo extends Level {

	public LevelTwo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public state update(int row, int col) {
		// TODO Auto-generated method stub
		return state.RUNNING;
	}

	@Override
	public Level nextLevel() {
		// there's no next level - return null
		return null;
	}
	
	@Override
	public void draw() {
		
	}

}
