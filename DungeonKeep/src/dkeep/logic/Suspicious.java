package dkeep.logic;
import java.util.Random;


/**
 *  Class responsible for Guards' Behavior of type Suspicious
 *
 */
public class Suspicious extends GuardBehaviour implements Behaviour, java.io.Serializable {
	
	/**
	 * long SerialVersionUID. Class's ID for serialization.
	 */
	private static final long serialVersionUID = 17L;
	
	/**
	 * Constant Probability used as probability for Guard inverting trajectory
	 */
	private final static double CONST_PROB = 0.1;
	
	/**
	 * Probability of Guard inverting trajectory.
	 */
	private double probability;	
		
	/**
	 * Implementation of Randomness
	 */
	private Random rand = new Random();
	
	/**
	 * default Constructor.
	 * Initializes superclass count, initial direction, and the the probability of inverting trajectory
	 */
	public Suspicious() {

		direction = 1;
		count = -1;
		probability = CONST_PROB;
	}

	/** 
	 * Function responsible for updating Suspicious guard's movement
	 * 
	 * @see dkeep.logic.Behaviour#getMovement()
	 */
	@Override
	public final int[] getMovement() {
		
		// Randomly reverses patrolling direction, after a while
		if (rand.nextDouble() < probability) {
			invertDirection();
		}
		
		return updateMovement();
	}
}
