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
		
		return true;
	}

	public void draw() {
		Board.board[pos[0]][pos[1]] = symb;
	}
}
