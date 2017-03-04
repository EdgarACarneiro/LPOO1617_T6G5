package dkeep.logic;

public class Hero extends Character {
	
	public Hero(int[] initial_pos, char... symbs) {
		super(initial_pos, symbs.length == 0 ? new char[] {'H'} : symbs);
		
		if (symbs.length > 0)
			armed = true;
	}

	public void update(int row, int col) {
		pos[0] += row;
		pos[1] += col;
	}
	
}
