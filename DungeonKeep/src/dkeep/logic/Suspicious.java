package dkeep.logic;
import java.util.Random;


public class Suspicious extends GuardBehaviour implements Behaviour, java.io.Serializable {
	
	private static final long serialVersionUID = 17L;
	
	private final static double CONST_PROB = 0.1;
	
	private double probability;	// probability of inverting direction
		
	private Random rand = new Random();
	
	public Suspicious() {
		System.out.print("Suspicious Guard!\n");
		direction = 1;
		count = -1;
		probability = CONST_PROB;
	}

	@Override
	public final int[] getMovement() {
		// Randomly reverses patrolling direction, after a while
		if (rand.nextDouble() < probability) {
			invertDirection();
			
			System.out.println("Inverted direction.");
			
		}
		
		return updateMovement();
	}
}
