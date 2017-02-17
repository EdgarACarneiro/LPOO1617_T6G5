
public class Hero extends Character {
	
	public Hero(int[] initial_pos) {
		super('H', initial_pos);
	}
	
	public Boolean isNear(Character other) {
		if (other.pos[0] == pos[0]+1)
			return true;
		else if (other.pos[1] == pos[1]+1)
			return true;
		else if (other.pos[0] == pos[1]-1)
			return true;
		else if (other.pos[1] == pos[1]-1)
			return true;
		
		return false;
	}
}
