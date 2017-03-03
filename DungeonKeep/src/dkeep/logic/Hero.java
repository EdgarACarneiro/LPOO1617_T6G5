package dkeep.logic;

public class Hero extends Character {
	
	public Hero(int[] initial_pos) {
		super(initial_pos, 'H', 'A');
	}

	public void update(int row, int col) {
		pos[0] += row;
		pos[1] += col;
	}
	
}
