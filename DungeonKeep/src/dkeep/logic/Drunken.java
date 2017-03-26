package dkeep.logic;

import java.util.Random;

/**
 * Class responsible for Guards' Behavior of type Drunken
 *
 */
public class Drunken extends GuardBehaviour implements Behaviour, java.io.Serializable {

	/**
	 * long SerialVersionUID. Class's ID for serialization.
	 */
	private static final long serialVersionUID = 16L;

	/**
	 * Constant Probability o Guard falling asleep
	 */
	private final static double CONST_PROB_SLEEP = 0.2;
	/**
	 * Constant Probability of Guard waking up, when he is sleeping
	 */
	private final static double CONST_PROB_WAKE = 0.5;
	/**
	 * Constant Probability of Guard inverting trajectory, when wakes up.
	 */
	private final static double CONST_PROB_INVERT = 0.7;

	/**
	 * Boolean, if set Guard is sleeping, otherwise guard is awake.
	 */
	private boolean asleep;
	/**
	 * Probability of Guard waking up, when he is sleeping
	 */
	private double probSleep;
	/**
	 * Probability of Guard waking up, when he is sleeping
	 */
	private double probWake;
	/**
	 * Probability of Guard inverting trajectory, when wakes up.
	 */
	private double probInvert;

	/**
	 * Implementation of Randomness
	 */
	private Random rand = new Random();

	/**
	 * default Constructor. Initializes superclass count, initial direction, the
	 * probabilities of waking up, falling asleep and inverting direction.
	 */
	public Drunken() {

		direction = 1;
		count = -1;
		probSleep = CONST_PROB_SLEEP;
		probWake = CONST_PROB_WAKE;
		probInvert = CONST_PROB_INVERT;
		asleep = false;
	}

	/**
	 * Function responsible for updating Drunken guard's movement
	 * 
	 * @see dkeep.logic.Behaviour#getMovement()
	 */
	@Override
	public int[] getMovement() {
		// Randomly falls asleep
		if (rand.nextDouble() < probSleep) {
			asleep = true;
			return null;
		}

		// Check if still sleeping
		if (asleep) {
			// Continue sleeping ?
			if (rand.nextDouble() < (1 - probWake))
				return null;

			// Wake-up
			asleep = false;
			if (rand.nextDouble() < probInvert)
				invertDirection();
		}

		return updateMovement();
	}

}
