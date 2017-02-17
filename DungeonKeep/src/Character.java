import java.lang.IllegalArgumentException;

public class Character {
	public char symb;
	public int[] pos;
	
	public Character(char symbol, int[] initial_pos) {
		if (initial_pos.length != 2)
			throw new IllegalArgumentException("Invalid Position");
		
		symb = symbol;
		pos = initial_pos;		
	}
	
	public Boolean move(int[] delta) {
		char new_pos_char = Board.board[pos[0] + delta[0]][pos[1] + delta[1]];
		
		if (new_pos_char == 'X' || new_pos_char == 'S')
			return false;
		else {
			pos[0] += delta[0];
			pos[1] += delta[1];
		}
		return true;
	}

	public void draw(char[][] board) {
		board[pos[0]][pos[1]] = symb;
	}
}
