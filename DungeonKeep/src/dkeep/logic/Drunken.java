package dkeep.logic;

import java.util.Random;

public class Drunken implements Behaviour {
	
	private final static double CONST_PROB_SLEEP = 0.2;
	private final static double CONST_PROB_WAKE = 0.5;
	private final static double CONST_PROB_INVERT = 0.7;
	
	private final static int[][] guard_mov = { 
			{ 0, -1}, { 1,  0}, { 1,  0}, { 1,  0}, { 1,  0},
			{ 0, -1}, { 0, -1}, { 0, -1}, { 0, -1}, { 0, -1},
			{ 0, -1}, { 1,  0}, { 0,  1}, { 0,  1}, { 0,  1},
			{ 0,  1}, { 0,  1}, { 0,  1}, { 0,  1}, {-1,  0},
			{-1,  0}, {-1,  0}, {-1,  0}, {-1,  0}
	};
	
	private boolean asleep;
	private int direction;
	private int count;
	private double probSleep;		// probability of falling asleep
	private double probWake;		// probability of waking up, if asleep
	private double probInvert;		// probability of inverting direction
		
	private Random rand = new Random();
	
	public Drunken() {
		System.out.print("Drunken Guard!\n");
		direction = 1;
		count = -1;
		probSleep = CONST_PROB_SLEEP;
		probWake = CONST_PROB_WAKE;
		probInvert = CONST_PROB_INVERT;
		asleep = false;
	}
	
	/*
	public Drunken(double prob_sleep, double prob_wake, double prob_inv) {
		this();

		if ( prob_sleep > 1 || prob_sleep < 0 || prob_wake > 1 || prob_wake < 0 || prob_inv > 1 || prob_inv < 0 )
			throw new IllegalArgumentException("Probability must be in range [0, 1]");
		
		probSleep = prob_sleep;
		probWake = prob_wake;
		probInvert = prob_inv;
	}
	*/
	
	private int invertDirection() {
		direction *= -1;
		count -= direction;
		return direction;
	}

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
