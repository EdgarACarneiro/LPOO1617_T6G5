package dkeep.logic;

import java.util.Random;

public class Drunken extends GuardBehaviour implements Behaviour, java.io.Serializable {
	
	private static final long serialVersionUID = 16L;
	
	private final static double CONST_PROB_SLEEP = 0.2;
	private final static double CONST_PROB_WAKE = 0.5;
	private final static double CONST_PROB_INVERT = 0.7;
	
	private boolean asleep;
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
