package dkeep.logic;

public class Rookie extends GuardBehaviour implements Behaviour, java.io.Serializable {
	
	private static final long serialVersionUID = 15L;
	
	private int count;

	public Rookie() {
		System.out.print("Rookie Guard!\n");
		count = -1;
	}

	@Override
	public final int[] getMovement() {
		
		++count;
		if (count >= guard_mov.length)
			count = 0;
		
		return guard_mov[count];
	}

}
