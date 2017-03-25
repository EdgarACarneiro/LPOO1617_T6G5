package dkeep.logic;

public abstract class GuardBehaviour {

	protected final static int[][] guard_mov = { 
			{ 0, -1}, { 1,  0}, { 1,  0}, { 1,  0}, { 1,  0},
			{ 0, -1}, { 0, -1}, { 0, -1}, { 0, -1}, { 0, -1},
			{ 0, -1}, { 1,  0}, { 0,  1}, { 0,  1}, { 0,  1},
			{ 0,  1}, { 0,  1}, { 0,  1}, { 0,  1}, {-1,  0},
			{-1,  0}, {-1,  0}, {-1,  0}, {-1,  0}
	};
	
	protected int count;
	protected int direction;

	protected int invertDirection() {
		direction *= -1;
		count -= direction;
		return direction;
	}
	
	protected int[] updateMovement() {
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
