package dkeep.logic;

import java.util.Random;
import java.lang.IllegalArgumentException;

public class Drunken implements Behaviour {
	
	private final static double CONST_PROB1 = 0.2;
	private final static double CONST_PROB2 = 0.4;
	private final static int[] NO_MOVE = {0, 0};
	
	private final static int[][] guard_mov = { 
			{ 0, -1}, { 1,  0}, { 1,  0}, { 1,  0}, { 1,  0},
			{ 0, -1}, { 0, -1}, { 0, -1}, { 0, -1}, { 0, -1},
			{ 0, -1}, { 1,  0}, { 0,  1}, { 0,  1}, { 0,  1},
			{ 0,  1}, { 0,  1}, { 0,  1}, { 0,  1}, {-1,  0},
			{-1,  0}, {-1,  0}, {-1,  0}, {-1,  0}
	};
	
	private int direction;
	private int count;
	private double probSleep;		// probability of falling asleep
	private double probWake;		// probability of waking up, if asleep
	private double probDirection;	// probability of inverting direction
	private boolean asleep;
		
	private Random rand = new Random();
	
	public Drunken() {
		System.out.print("Drunken Guard!\n");
		direction = 1;
		count = -1;
		probSleep = CONST_PROB1;
		probWake = CONST_PROB2;
		probDirection = CONST_PROB2;
		asleep = false;
	}
	
	public Drunken(double prob_sleep, double prob_dir) {
		this();

		if (prob_dir > 1 || prob_dir < 0 || prob_sleep > 1 || prob_sleep < 0)
			throw new IllegalArgumentException("Probability must be in range [0, 1]");
		
		probSleep = prob_sleep;
		probDirection = prob_dir;
	}
	
	private int invertDirection() {
		direction *= -1;
		return direction;
	}

	@Override
	public int[] getMovement() {
		// Randomly falls asleep
		if (rand.nextDouble() < probSleep) {
			asleep = true;
			return NO_MOVE;
		}
		
		// Check if still sleeping
		if (asleep)
			if (rand.nextDouble() < probWake)
				return NO_MOVE;
		
		//Waked up -> Might change direction
		asleep = false;
		if (rand.nextDouble() < probDirection) {
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
