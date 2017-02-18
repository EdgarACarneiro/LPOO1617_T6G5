
public class Hero extends Character {
	
	public Hero(int[] initial_pos) {
		super('H', initial_pos);
	}
	
	public Boolean isNear(Character other) {
		if ((pos[0] + 1 == other.pos[0] && pos[1] == other.pos[1]) ||
			(pos[0] - 1 == other.pos[0] && pos[1] == other.pos[1]) ||
			(pos[1] + 1 == other.pos[1] && pos[0] == other.pos[0]) ||
			(pos[1] - 1 == other.pos[1] && pos[0] == other.pos[0]) )
			return true;
		
		return false;
	}
}
