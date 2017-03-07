package dkeep.logic;

public class Hero extends Character {
	
	private boolean hasKey = false;	// future: update with array of items - Inventory (?)
	
	public Hero(int[] initial_pos, char... symbs) {
		super(initial_pos, symbs.length == 0 ? new char[] {'H'} : symbs);
		
		if (symbs.length > 0)
			armed = true;
	}
	
	public void update(Map map) {
		//Stays in the same Position
	}
	
	public void keyFoundStatus(boolean status) {	// future: picks key item
		if ( (hasKey = status) == true )
			this.setSymbIdx(1);
	}
	
	public boolean hasKey() {
		return hasKey;
	}
	
}
