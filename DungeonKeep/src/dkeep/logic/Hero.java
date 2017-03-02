package dkeep.logic;

public class Hero extends Character {
	
	public Hero(int[] initial_pos) {
		super(initial_pos, 'H', 'A');
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
	
	@Override
	public Boolean move (int[] delta) {
		Boolean result = super.move(delta);
		
		//If key was picked up, open door
		if (!result) {
			char new_pos_char = Game.maps[Game.current_lvl][pos[0]+ delta[0]][pos[1]+ delta[1]];	
			
			if (new_pos_char == 'I' && Game.keys[Game.current_lvl].picked_up)
				Game.maps[Game.current_lvl][pos[0] + delta[0]][pos[1] + delta[1]] = 'S';
		}
	
		return result;
	}
}
