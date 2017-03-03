package dkeep.logic;
import java.util.Random;

public class Guard extends Character {
	
	private Behaviour strategy;
		
	public Guard(int[] initial_pos) {
		super(initial_pos, 'G', 'g');
		
		Random rand = new Random();
		switch (rand.nextInt(3)) {
		case 0:
			strategy = new Drunken();
			break;
		case 1:
			strategy = new Suspicious();
			break;
		case 2:
			strategy = new Rookie();
			break;
		default:
			System.err.println("Error in Guard Constructor!!");
		}
	}

	public void update() {
		int[] tmp = strategy.getMovement();
		
		pos[0] += tmp[0];
		pos[1] += tmp[1];
	}

}