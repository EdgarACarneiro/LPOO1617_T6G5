package dkeep.logic;

/**
 * Class responsible for Guards' Behaviour of type Rookie
 *
 */
public class Rookie extends GuardBehaviour implements Behaviour, java.io.Serializable {

	/**
	 * long SerialVersionUID. Class's ID for serialization.
	 */
	private static final long serialVersionUID = 15L;

	/**
	 * Default Constructor: Initializes count.
	 */
	public Rookie() {
		count = -1;
	}

	/**
	 * Function responsible for updating Rookie guard's movement
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
