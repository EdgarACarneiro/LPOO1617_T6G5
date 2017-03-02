package dkeep.logic;
import java.lang.IllegalArgumentException;


public class Key {
	public static enum k_type {KEY, LEVER};
	
	public k_type type;
	public int[] pos;
	Boolean picked_up;
	
	public Key(k_type type, int[] initial_pos) {
		if (initial_pos.length != 2)
			throw new IllegalArgumentException("Invalid Position");
		
		this.type = type;
		pos = initial_pos;
		picked_up = false;
	}
	
	//Draws Key if it was not picked up
	public void draw(char[][] board) {
		if (picked_up.equals(false) || type.equals(k_type.LEVER))
			board[pos[0]][pos[1]] = 'k';
	}
}
