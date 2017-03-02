package dkeep.logic;
import java.util.Random;
import java.lang.IllegalArgumentException;


public class Suspicious implements Behaviour {
	
	private final static double CONST_PROB = 0.1;
	
	private final static int[][] guard_mov = { 
			{ 0, -1}, { 1,  0}, { 1,  0}, { 1,  0}, { 1,  0},
			{ 0, -1}, { 0, -1}, { 0, -1}, { 0, -1}, { 0, -1},
			{ 0, -1}, { 1,  0}, { 0,  1}, { 0,  1}, { 0,  1},
			{ 0,  1}, { 0,  1}, { 0,  1}, { 0,  1}, {-1,  0},
			{-1,  0}, {-1,  0}, {-1,  0}, {-1,  0}, {-1,  0}
	};
	
	private int direction;
	private int count;
	private double probability;	// probability of inverting direction
		
	private Random rand = new Random();
	
	public Suspicious() {
		direction = 1;
		count = 0;
		probability = CONST_PROB;
	}
	
	public Suspicious(double prob) {
		this();

		if (prob > 1 || prob < 0)
			throw new IllegalArgumentException("Probability must be in range [0, 1]");
		
		probability = prob;
	}

	private int invertDirection() {
		direction *= -1;
		return direction;
	}

	@Override
	public final int[] getMovement() {
		// Randomly reverses patrolling direction, after a while
		if (rand.nextDouble() < probability)
			invertDirection();
		
		count += direction;
		if (count < 0) {
			invertDirection();
			count = 0;
			return guard_mov[count];
		} else if (count >= guard_mov.length) {
			invertDirection();
			count = guard_mov.length - 1;
			return guard_mov[count];
		} else {
			return guard_mov[count];
		}
	}
}
