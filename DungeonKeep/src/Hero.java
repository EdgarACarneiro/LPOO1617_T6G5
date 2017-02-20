
public class Hero extends Character {
	
	public Hero(int[] initial_pos) {
		super('H', initial_pos);
	}
	
	public Boolean isNear(int[] position) {
		if ((pos[0] + 1 == position[0] && pos[1] == position[1]) ||
			(pos[0] - 1 == position[0] && pos[1] == position[1]) ||
			(pos[1] + 1 == position[1] && pos[0] == position[0]) ||
			(pos[1] - 1 == position[1] && pos[0] == position[0]) ||
			(pos[0] == position[0] && pos[1] == position[1]))
			return true;
		
		return false;
	}
}
