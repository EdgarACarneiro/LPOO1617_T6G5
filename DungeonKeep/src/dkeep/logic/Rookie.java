package dkeep.logic;

/**
 * @author 
 *
 */
public class Rookie extends GuardBehaviour implements Behaviour, java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 15L;
	
	/**
	 * 
	 */
	private int count;

	/**
	 * 
	 */
	public Rookie() {
		count = -1;
	}

	/** (non-Javadoc)
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
