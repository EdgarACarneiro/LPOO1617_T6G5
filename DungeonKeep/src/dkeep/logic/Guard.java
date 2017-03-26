package dkeep.logic;

import java.util.Random;

/**
 * Class Responsible for handling all Guards. Extends from GameChracter.
 */
public class Guard extends GameCharacter implements java.io.Serializable {

	/**
	 * long SerialVersionUID. Class's ID for serialization.
	 */
	private static final long serialVersionUID = 7L;

	/**
	 * Enumeration containing the different types of guard personalities.
	 *
	 */
	public enum Personality {
		ROOKIE, DRUNKEN, SUSPICIOUS
	};

	/**
	 * Strategy of the current guard. Related to it's personality.
	 */
	private Behaviour strategy;

	/**
	 * Guard Constructor. Creates a new Guard in the given position, with a
	 * random personality.
	 *
	 * @param initial_pos
	 *            Initial Guard position
	 */
	public Guard(int[] initial_pos) {
		this(initial_pos, Personality.values()[(new Random()).nextInt(3)]);
	}

	public Guard(int[] initial_pos, Personality gp) {
		super(initial_pos, 'G', 'g');
		armed = true;

		switch (gp) {
		case ROOKIE:
			strategy = new Rookie();
			break;
		case DRUNKEN:
			strategy = new Drunken();
			break;
		case SUSPICIOUS:
			strategy = new Suspicious();
			break;
		default:
			System.err.println("Error in Guard Constructor!!");
		}
	}

	/**
	 * Function responsible for updating the guard position in the given map.
	 * The position of the guard is dependent of gameMap characteristics.
	 * 
	 * @param gameMap
	 *            Map used for updating guard position
	 * 
	 * @see dkeep.logic.GameCharacter#update(dkeep.logic.GameMap)
	 */
	@Override
	public void update(GameMap gameMap) {
		int[] tmp = strategy.getMovement();

		if (tmp == null) {
			this.setInactive();
		} else {
			this.setActive();

			if (gameMap.isValid(tmp[0] + pos[0], tmp[1] + pos[1])) {
				pos[0] += tmp[0];
				pos[1] += tmp[1];
			}
		}
	}

	/**
	 * @return Current Guard Personality
	 */
	public Personality getPersonality() {
		if (strategy instanceof Rookie)
			return Personality.ROOKIE;

		else if (strategy instanceof Suspicious)
			return Personality.SUSPICIOUS;

		else if (strategy instanceof Drunken)
			return Personality.DRUNKEN;

		else
			return null;
	}
}
