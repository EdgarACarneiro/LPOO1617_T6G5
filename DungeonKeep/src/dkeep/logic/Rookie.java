package dkeep.logic;

/**
 * Class responsible for Guard Behaviours of type Rookie
 *
 */
public class Rookie extends GuardBehaviour implements Behaviour, java.io.Serializable {
	
	/**
	 * long SerialVersionUID. Class's ID for serialization.
	 */
	private static final long serialVersionUID = 15L;
	
	/**
	 * int count. Integer responsible for controlling guard's movement.
	 */
	private int count;

	/**
	 * Default Constructor:
	 * Initializes count.
	 */
	public Rookie() {
		count = -1;
	}

	/** 
	 * Function responsible for updating Rookie guard's movement-
	 * 
	 * @see dkeep.logic.Behaviour#getMovement()
	 */
	@Override
	public final int[] getMovement() {
		
		++count;
		if (count >= guard_mov.length)
			count = 0;
		
		return guard_mov[count];
	}

}
