package dkeep.logic;

public abstract class GameCharacter implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 17L;
	protected boolean active;
	protected boolean armed;
	
	private final char[] symbols;
	private int symb_idx;
	protected int[] pos = new int[2];
	
	public GameCharacter(int[] initial_pos, char...symb) {
		if (initial_pos.length != 2)
			throw new IllegalArgumentException("Invalid Position");
		
		active = true;
		armed = false;
		pos[0] = initial_pos[0];
		pos[1] = initial_pos[1];
		
		if (symb.length == 0) {
			System.err.println("No symbol provided for character constructor. Using placeholder 'P'.");
			symbols = new char[] {'P'};
		} else {
			symbols = symb;
		}
		
		symb_idx = 0;
	}

	// For Player Controlled Movement
	public void update(GameMap gameMap, int row, int col) {
		if (! this.active)
			return;
		
		int[] new_pos = new int[] {pos[0] + row, pos[1] + col};
		if (gameMap.isValid(new_pos[0], new_pos[1]))
			this.pos = new_pos;
	}
	
	// For NPC Movement
	public abstract void update(GameMap gameMap);
	
	public boolean attack(GameCharacter c) {
		if (! armed || ! this.isAdjacent(c) || ! active)
			return false;
		
		c.setInactive();
		return true;
	}
	
	protected boolean isAdjacent(GameCharacter c) {
		if (! c.active)
			return false;
		
		if ( (Math.abs(this.pos[0] - c.pos[0]) <= 1 && this.pos[1] == c.pos[1]) ||
				(Math.abs(this.pos[1] - c.pos[1]) <= 1 && this.pos[0] == c.pos[0]) ) {
			return true;
		}

		return false;
	}
	
	protected boolean isAdjacent(int[] pos) {
		if ( (Math.abs(this.pos[0] - pos[0]) <= 1 && this.pos[1] == pos[1]) ||
				(Math.abs(this.pos[1] - pos[1]) <= 1 && this.pos[0] == pos[0]) ) {
			return true;
		}
		else
			return false;
	}
	
	public boolean isAt(int[] pos) {
		return (this.pos[0] == pos[0] && this.pos[1] == pos[1]);
	}
	
	public boolean setSymbIdx(int i) {
		if (i >= 0 && i < symbols.length) {
			symb_idx = i;
			return true;
		} else {
			return false;
		}
	}

	public void nextSymb() {
		symb_idx = (symb_idx + 1) % symbols.length;
	}
	
	public void draw(char[][] board) {
		board[pos[0]][pos[1]] = this.getSymb();
	}
	
	public char getSymb() {
		return symbols[symb_idx];
	}
	
	public void setInactive() {
		this.active = false;
	}
	
	public void setActive() {
		this.active = true;
		//this.setSymbIdx(0);
	}
	
	public int[] getPos() {
		return pos;
	}
}
