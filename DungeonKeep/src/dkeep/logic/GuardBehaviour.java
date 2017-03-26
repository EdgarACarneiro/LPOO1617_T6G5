package dkeep.logic;

/**
 * Abstract generic class responsible for controlling all Guard Behaviors.
 *
 */
public abstract class GuardBehaviour implements java.io.Serializable {

	/**
	 * long SerialVersionUID. Class's ID for serialization.
	 */
	private static final long serialVersionUID = 19L;

	/**
	 * Matrix containing the sequence of guard movements.
	 */
	protected final static int[][] guard_mov = { 
			{ 0, -1}, { 1,  0}, { 1,  0}, { 1,  0}, { 1,  0},
			{ 0, -1}, { 0, -1}, { 0, -1}, { 0, -1}, { 0, -1},
			{ 0, -1}, { 1,  0}, { 0,  1}, { 0,  1}, { 0,  1},
			{ 0,  1}, { 0,  1}, { 0,  1}, { 0,  1}, {-1,  0},
			{-1,  0}, {-1,  0}, {-1,  0}, {-1,  0}
	};
	
	/**
	 * Counter used to iterate through the guard_mov Matrix.
	 */
	protected int count;
	/**
	 * Controller of the direction of iteration through the guard_mov Matrix.
	 */
	protected int direction;

	/**
	 * Inverts the current direction of the Guard's movement.
	 * 
	 * @return current direction.
	 */
	protected int invertDirection() {
		direction *= -1;
		count -= direction;
		return direction;
	}
	
	/**
	 * Function responsible for updating the Guard's Movement
	 * 
	 * @return Current guard movement.
	 */
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
