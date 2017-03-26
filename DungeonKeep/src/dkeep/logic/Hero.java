package dkeep.logic;

/**
 * Class Responsible for handling the Hero. Extends from GameChracter.
 *
 */
public class Hero extends GameCharacter implements java.io.Serializable {

	/**
	 * long SerialVersionUID. Class's ID for serialization.
	 */
	private static final long serialVersionUID = 12L;

	/**
	 * Set if Hero has key.
	 */
	private boolean hasKey = false;

	/**
	 * Hero Constructor. Creates a new Hero in the given position, with the
	 * given symbols.
	 * 
	 * @param initial_pos
	 *            Initial Hero Position
	 * @param symbs
	 *            Symbols used to represent the Hero
	 */
	public Hero(int[] initial_pos, char... symbs) {
		super(initial_pos, symbs.length == 0 ? new char[] { 'H' } : symbs);

		if (symbs.length > 0)
			armed = true;

		checkSelfSymb();
	}

	/**
	 * Hero stays in the same position, not moving.
	 * 
	 * @see dkeep.logic.GameCharacter#update(dkeep.logic.GameMap)
	 */
	public void update(GameMap gameMap) {
		// Stays in the same Position
	}

	/**
	 * Function responsible for updating the hero position in the given map, to
	 * the position (row, col). The position of the hero is dependent of gameMap
	 * characteristics.
	 * 
	 * @param gameMap
	 *            Map used for updating hero position
	 * @param row
	 *            row of the Matrix to be updated
	 * @param col
	 *            col of the Matrix to be updated
	 * 
	 * @see dkeep.logic.GameCharacter#update(dkeep.logic.GameMap, int, int)
	 */
	@Override
	public void update(GameMap gameMap, int row, int col) {
		checkSelfSymb();
		if (!this.active)
			return;

		int[] new_pos = new int[] { pos[0] + row, pos[1] + col };

		if (hasKey() && gameMap.openDoorAt(new_pos[0], new_pos[1]))
			return;
		else
			super.update(gameMap, row, col);
	}

	/**
	 * Hero symbol gets updated. Updates hasKey, if Hero finds key.
	 * 
	 * @param status
	 *            boolean. IF set, hero found key, otherwise did not.
	 */
	public void keyFoundStatus(boolean status) {
		if ((hasKey = status) == true)
			this.setSymbIdx(1);
	}

	/**
	 * Updates hero current symbol on the map.
	 */
	private void checkSelfSymb() {
		if (hasKey)
			setSymbIdx(1);
		else if (armed)
			setSymbIdx(2);
		else
			setSymbIdx(0);
	}

	/**
	 * @return true if Hero has key, false otherwise.
	 */
	public boolean hasKey() {
		return hasKey;
	}

}
