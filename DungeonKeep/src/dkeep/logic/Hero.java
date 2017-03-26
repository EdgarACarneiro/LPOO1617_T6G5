package dkeep.logic;

public class Hero extends GameCharacter implements java.io.Serializable {
	
	private static final long serialVersionUID = 12L;
	
	private boolean hasKey = false;	// future: update with array of items - Inventory (?)
	
	public Hero(int[] initial_pos, char... symbs) {
		super(initial_pos, symbs.length == 0 ? new char[] {'H'} : symbs);
		
		if (symbs.length > 0)
			armed = true;
		
		checkSelfSymb();
	}
	
	public void update(GameMap gameMap) {
		//Stays in the same Position
	}
	
	@Override
	public void update(GameMap gameMap, int row, int col) {
		checkSelfSymb();
		if (! this.active)
			return;
		
		int[] new_pos = new int[] {pos[0] + row, pos[1] + col};
		
		if (hasKey() && gameMap.openDoorAt(new_pos[0], new_pos[1]))
			return;
		else 
			super.update(gameMap, row, col);
	}
	
	public void keyFoundStatus(boolean status) {	// future: picks key item
		if ( (hasKey = status) == true )
			this.setSymbIdx(1);
	}
	
	private void checkSelfSymb() {
		if (hasKey)
			setSymbIdx(1);
		else if (armed)
			setSymbIdx(2);
		else
			setSymbIdx(0);
	}
	
	public boolean hasKey() {
		return hasKey;
	}
	
}
