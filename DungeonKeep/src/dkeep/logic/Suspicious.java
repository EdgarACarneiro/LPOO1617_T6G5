package dkeep.logic;
import java.util.Random;


/**
 * @author 
 *
 */
public class Suspicious extends GuardBehaviour implements Behaviour, java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 17L;
	
	/**
	 * 
	 */
	private final static double CONST_PROB = 0.1;
	
	/**
	 * 
	 */
	private double probability;	// probability of inverting direction
		
	/**
	 * 
	 */
	private Random rand = new Random();
	
	/**
	 * 
	 */
	public Suspicious() {

		direction = 1;
		count = -1;
		probability = CONST_PROB;
	}

	/** (non-Javadoc)
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
