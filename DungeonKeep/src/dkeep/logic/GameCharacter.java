package dkeep.logic;

/**
 * Abstract class that represents a generic Game Character.
 *
 */
public abstract class GameCharacter implements java.io.Serializable {

	/**
	 * long SerialVersionUID. Class's ID for serialization.
	 */
	private static final long serialVersionUID = 17L;
	/**
	 * Set if Character is active.
	 */
	protected boolean active;
	/**
	 * Set if character is armed
	 */
	protected boolean armed;

	/**
	 * Array of chars containing the Character possible representations.
	 */
	private final char[] symbols;
	/**
	 * Current symbol representing the character in the Map
	 */
	private int symb_idx;
	/**
	 * Character current position in the map
	 */
	protected int[] pos = new int[2];

	/**
	 * Game Character Constructor. Creates a new Character in the given
	 * position, with the given symbols.
	 * 
	 * @param initial_pos
	 *            Initial Character Position
	 * @param symbs
	 *            Symbols used to represent the Character
	 */
	public GameCharacter(int[] initial_pos, char... symb) {
		if (initial_pos.length != 2)
			throw new IllegalArgumentException("Invalid Position");

		active = true;
		armed = false;
		pos[0] = initial_pos[0];
		pos[1] = initial_pos[1];

		if (symb.length == 0) {
			System.err.println("No symbol provided for character constructor. Using placeholder 'P'.");
			symbols = new char[] { 'P' };
		} else {
			symbols = symb;
		}

		symb_idx = 0;
	}

	/**
	 * Function responsible for updating the Character position in the given
	 * map, to the position (row, col). The position of the Character is
	 * dependent of gameMap characteristics. This function is used for player
	 * controlled movement.
	 * 
	 * @param gameMap
	 *            Map used for updating Character position
	 * @param row
	 *            row of the Matrix to be updated
	 * @param col
	 *            Col of the Matrix to be updated
	 */
	public void update(GameMap gameMap, int row, int col) {
		if (!this.active)
			return;

		int[] new_pos = new int[] { pos[0] + row, pos[1] + col };
		if (gameMap.isValid(new_pos[0], new_pos[1]))
			this.pos = new_pos;
	}

	/**
	 * Function responsible for updating the Character position in the given
	 * map. The position of the Character is dependent of gameMap
	 * characteristics.
	 * 
	 * @param gameMap
	 *            Map used for updating character position
	 */
	public abstract void update(GameMap gameMap);

	/**
	 * Check if this Character attacked the given Character.
	 * 
	 * @param c
	 *            Game Character used
	 * 
	 * @return true if this Character attacked, false if not.
	 */
	public boolean attack(GameCharacter c) {
		if (!armed || !this.isAdjacent(c) || !active)
			return false;

		c.setInactive();
		return true;
	}

	/**
	 * Check if either this Character is adjacent to the given Character.
	 * 
	 * @param c
	 *            Game Character used
	 * 
	 * @return true if they are adjacent, false if not.
	 */
	protected boolean isAdjacent(GameCharacter c) {
		if (!c.active)
			return false;

		if ((Math.abs(this.pos[0] - c.pos[0]) <= 1 && this.pos[1] == c.pos[1])
				|| (Math.abs(this.pos[1] - c.pos[1]) <= 1 && this.pos[0] == c.pos[0])) {
			return true;
		}

		return false;
	}

	/**
	 * Check if either this Character is adjacent to the given position.
	 * 
	 * @param pos
	 *            Position used
	 * 
	 * @return true if they are adjacent, false if not.
	 */
	protected boolean isAdjacent(int[] pos) {
		if ((Math.abs(this.pos[0] - pos[0]) <= 1 && this.pos[1] == pos[1])
				|| (Math.abs(this.pos[1] - pos[1]) <= 1 && this.pos[0] == pos[0])) {
			return true;
		} else
			return false;
	}

	/**
	 * @param pos
	 *            Given position
	 * @return true if character is at the given position, false if not
	 */
	public boolean isAt(int[] pos) {
		return (this.pos[0] == pos[0] && this.pos[1] == pos[1]);
	}

	/**
	 * @param i
	 *            index of the symbol
	 * @return True if the Character's symbol was set to the symbol at index i,
	 *         false if not.
	 */
	public boolean setSymbIdx(int i) {
		if (i >= 0 && i < symbols.length) {
			symb_idx = i;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Change current symbol to the next available symbol
	 */
	public void nextSymb() {
		symb_idx = (symb_idx + 1) % symbols.length;
	}

	/**
	 * Draws the current Character in the matrix of chars board, using the
	 * current symbol.
	 * 
	 * @param board
	 *            Matrix of chars where the Character will be drawn
	 */
	public void draw(char[][] board) {
		board[pos[0]][pos[1]] = this.getSymb();
	}

	/**
	 * @return current symbol that represents the Character
	 */
	public char getSymb() {
		return symbols[symb_idx];
	}

	/**
	 * Set Character inactive
	 */
	public void setInactive() {
		this.active = false;
	}

	/**
	 * Set Character active
	 */
	public void setActive() {
		this.active = true;
	}

	/**
	 * @return Character current position
	 */
	public int[] getPos() {
		return pos;
	}
}
