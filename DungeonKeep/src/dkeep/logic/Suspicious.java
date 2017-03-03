package dkeep.logic;
import java.util.Random;
import java.lang.IllegalArgumentException;

// HANDLED!!
public class Suspicious implements Behaviour {
	
	private final static double CONST_PROB = 0.1;
	
	private final static int[][] guard_mov = { 
			{ 0, -1}, { 1,  0}, { 1,  0}, { 1,  0}, { 1,  0},
			{ 0, -1}, { 0, -1}, { 0, -1}, { 0, -1}, { 0, -1},
			{ 0, -1}, { 1,  0}, { 0,  1}, { 0,  1}, { 0,  1},
			{ 0,  1}, { 0,  1}, { 0,  1}, { 0,  1}, {-1,  0},
			{-1,  0}, {-1,  0}, {-1,  0}, {-1,  0}
	};
	
	private int direction;
	private int count;
	private double probability;	// probability of inverting direction
		
	private Random rand = new Random();
	
	public Suspicious() {
		System.out.print("Suspicious Guard!\n");
		direction = 1;
		count = -1;
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
		if (rand.nextDouble() < probability) {
			invertDirection();
			
			//Because we want him to reverse the last move and not the next move
			if (direction == -1)
				++count;
			else --count;
		}
		
		count += direction;
		if (count < 0)
			count = guard_mov.length-1;
		else if (count >= guard_mov.length)
			count = 0;
		
		
		if (direction == -1) {
			final int[] reverse = new int[2];
			reverse[0] = -guard_mov[count][0];
			reverse[1] = -guard_mov[count][1];
			return reverse;
		}		
		else return guard_mov[count];
	}
}
