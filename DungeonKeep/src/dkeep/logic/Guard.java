package dkeep.logic;
import java.util.Random;

public class Guard extends GameCharacter implements java.io.Serializable {
	
	private static final long serialVersionUID = 7L;
	
	public enum Personality {
		ROOKIE, DRUNKEN, SUSPICIOUS
	};
	
	private Behaviour strategy;
		
	public Guard(int[] initial_pos) {				
		this(initial_pos, Personality.values()[(new Random()).nextInt(3)]);		
	}

	public Guard(int[] initial_pos, Personality gp) {
		super(initial_pos, 'G', 'g');
		armed = true;
		
		switch (gp) {
		case ROOKIE:
			strategy = new Rookie();
			break;
		case DRUNKEN:
			strategy = new Drunken();
			break;
		case SUSPICIOUS:
			strategy = new Suspicious();
			break;
		default:
			System.err.println("Error in Guard Constructor!!");
		}
	}


	@Override
	public void update(GameMap gameMap) {
		int[] tmp = strategy.getMovement();
		
		if (tmp == null) {
			this.setInactive();
		} else {
			this.setActive();
			
			if (gameMap.isValid(tmp[0] + pos[0], tmp[1] + pos[1])) {
				pos[0] += tmp[0];
				pos[1] += tmp[1];
			}
		}
	}
	
	public Personality getPersonality() {
		if (strategy instanceof Rookie)
			return Personality.ROOKIE;
		
		else if (strategy instanceof Suspicious)
			return Personality.SUSPICIOUS;
		
		else if (strategy instanceof Drunken)
			return Personality.DRUNKEN;
		
		else
			return null;
	}
}
