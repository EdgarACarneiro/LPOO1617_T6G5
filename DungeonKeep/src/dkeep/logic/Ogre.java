package dkeep.logic;

import java.util.Arrays;
import java.util.Random;

/**
 * Class Responsible for handling all Ogres. Extends from GameChracter.
 *
 */
public class Ogre extends GameCharacter implements java.io.Serializable {

	/**
	 * long SerialVersionUID. Class's ID for serialization.
	 */
	private static final long serialVersionUID = 8L;

	/**
	 * Implements Randomness
	 */
	private static Random rand = new Random();
	/**
	 * Number of turns it takes to the Ogre to wake up, when he is stunned
	 */
	private int TURN_WAKE_UP = 2;

	/**
	 * Possible random moves for Ogres (Down, Up, Right, Left).
	 */
	private static final int[][] moves = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	/**
	 * Array containing the club's position.
	 */
	private int[] club;
	/**
	 * If Ogre is stunned saves the number of turns he has already been sleeping
	 */
	private int sleep_turn;

	/**
	 * Ogre Constructor. Creates a new Ogre in the given position, with a random
	 * position for the club.
	 * 
	 * @param initial_pos
	 *            Initil Ogre Position
	 */
	public Ogre(int[] initial_pos) {
		super(initial_pos, 'O', '8', '$');
		club = new int[] { initial_pos[0] + 1, initial_pos[1] };
		armed = true;
	}

	/**
	 * @return a random move from the possible moves.
	 */
	public static int[] randomMove() {
		return moves[rand.nextInt(moves.length)];
	}

	/**
	 * Swings club in a random direction.
	 */
	private void swingClub() {
		int[] club_dir = randomMove();

		club[0] = pos[0] + club_dir[0];
		club[1] = pos[1] + club_dir[1];
	}

	/**
	 * Function used to update Ogre activity (active / inactive). Is activity
	 * depends of gameMap characteristics
	 * 
	 * @param gameMap
	 *            Map used for updating ogre activity
	 */
	private void checkSelfSymb(GameMap gameMap) {
		if (Arrays.equals(gameMap.getKeyPos(), pos)) {
			this.setSymbIdx(2);
		} else {
			if (this.active)
				this.setActive();
			else
				this.setInactive();
		}
	}

	/**
	 * @return club current position
	 */
	public int[] getClubPos() {
		return club;
	}

	/**
	 * Function responsible for updating the ogre position in the given map, to
	 * the position (row, col). The position of the ogre is dependent of
	 * gameMap characteristics.
	 * 
	 * @param gameMap
	 *            Map used for updating ogre position
	 * @param row
	 *            row of the Matrix to be updated
	 * @param col
	 *            Col of the Matrix to be updated
	 * 
	 * @see dkeep.logic.GameCharacter#update(dkeep.logic.GameMap, int, int)
	 */
	@Override
	public void update(GameMap gameMap, int row, int col) {
		super.update(gameMap, row, col);
		swingClub();

		checkSelfSymb(gameMap);
	}

	/**
	 * Function responsible for updating the ogre position in the given map. The
	 * position of the ogre is dependent of gameMap characteristics.
	 * 
	 * @param gameMap
	 *            Map used for updating ogre position
	 * 
	 * @see dkeep.logic.GameCharacter#update(dkeep.logic.GameMap)
	 */
	@Override
	public void update(GameMap gameMap) {

		if (!active) {
			if (sleep_turn == TURN_WAKE_UP) {
				sleep_turn = 0;
				this.setActive();
			} else {
				++sleep_turn;
			}
		}

		int[] tmp;
		int[] new_pos = new int[2];

		do {
			tmp = randomMove();
			new_pos[0] = pos[0] + tmp[0];
			new_pos[1] = pos[1] + tmp[1];
		} while (!gameMap.isValid(new_pos[0], new_pos[1]) && this.active);
		if (this.active)
			pos = new_pos;

		// Swing Club, whether active or not
		do {
			swingClub();
		} while (!gameMap.isValid(club[0], club[1]));

		checkSelfSymb(gameMap);
	}

	/**
	 * Draws the current Ogre in the matrix of chars board, using the char 'O'.
	 * 
	 * @param board
	 *            Matrix of chars where the Ogre will be drawn
	 * 
	 * @see dkeep.logic.GameCharacter#draw(char[][])
	 */
	@Override
	public void draw(char[][] board) {
		super.draw(board);
		board[club[0]][club[1]] = '*';
	}

	/**
	 * Check if either the Ogre or the club are adjacent to the given Character.
	 * 
	 * @param c
	 *            Game Character used
	 * 
	 * @return true if they are adjacent, false if not.
	 * 
	 * @see dkeep.logic.GameCharacter#isAdjacent(dkeep.logic.GameCharacter)
	 */
	@Override
	public boolean isAdjacent(GameCharacter c) {
		return (c.isAdjacent(this) || c.isAdjacent(club));
	}

	/**
	 * Check if Ogre attacked the given character.
	 * 
	 * @param c
	 *            Game Character used
	 * 
	 * @return true if Ogre attacked, false if not.
	 * 
	 * @see dkeep.logic.GameCharacter#attack(dkeep.logic.GameCharacter)
	 */
	@Override
	public boolean attack(GameCharacter c) {
		if (!armed || !this.isAdjacent(c))
			return false;

		c.setInactive();
		return true;
	}

	/**
	 * Set Ogre inactive
	 * 
	 * @see dkeep.logic.GameCharacter#setInactive()
	 */
	@Override
	public void setInactive() {
		this.active = false;
		this.setSymbIdx(1);
	}

	/**
	 * Set Ogre active
	 * 
	 * @see dkeep.logic.GameCharacter#setInactive()
	 */
	@Override
	public void setActive() {
		this.active = true;
		this.setSymbIdx(0);
	}
}
