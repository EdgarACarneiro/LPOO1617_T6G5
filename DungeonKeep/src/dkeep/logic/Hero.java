package dkeep.logic;

public class Hero extends Character {
	
	private boolean hasKey = false;	// future: update with array of items - Inventory (?)
	
	public Hero(int[] initial_pos, char... symbs) {
		super(initial_pos, symbs.length == 0 ? new char[] {'H'} : symbs);
		
		if (symbs.length > 0)
			armed = true;
	}

	public void update(int row, int col) {
		pos[0] += row;
		pos[1] += col;
	}
	
	public void keyFoundStatus(boolean status) {	// future: picks key item
		hasKey = status;
	}
	
	public boolean hasKey() {
		return hasKey;
	}
	
}
