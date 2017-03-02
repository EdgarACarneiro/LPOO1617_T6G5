package dkeep.logic;
import java.lang.IllegalArgumentException;


// HANDLED!! semi?
public abstract class Character {
	private final char[] symbols;
	private int symb_idx;
	private int[] pos;
	
	public Character(int[] initial_pos, char...symb) {
		if (initial_pos.length != 2)
			throw new IllegalArgumentException("Invalid Position");
		
		pos = initial_pos;
		if (symb.length == 0) {
			System.err.println("No symbol provided for character constructor. Using placeholder 'P'.");
			symbols = new char[] {'P'};
		} else {
			symbols = symb;
		}
		
		symb_idx = 0;		
	}
	
	public abstract boolean update();
	
	public boolean setSymbIdx(int i) {
		if (i >= 0 && i < symbols.length) {
			symb_idx = i;
			return true;
		} else {
			return false;
		}
	}

	public void draw(char[][] board) {
		board[pos[0]][pos[1]] = symbols[symb_idx];
	}
}
