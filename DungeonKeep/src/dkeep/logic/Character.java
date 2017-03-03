package dkeep.logic;
import java.lang.IllegalArgumentException;
import java.lang.Math;


// HANDLED!! semi?
public abstract class Character {
	
	protected boolean active;
	
	private final char[] symbols;
	private int symb_idx;
	protected int[] pos;
	
	public Character(int[] initial_pos, char...symb) {
		if (initial_pos.length != 2)
			throw new IllegalArgumentException("Invalid Position");
		
		active = true;
		pos = initial_pos;
		if (symb.length == 0) {
			System.err.println("No symbol provided for character constructor. Using placeholder 'P'.");
			symbols = new char[] {'P'};
		} else {
			symbols = symb;
		}
		
		symb_idx = 0;
	}
	
//	public abstract boolean update();
	
//	public boolean update(int[] delta);
	
	public boolean isAdjacent(Character c) {
		if (! c.active)
			return false;
		
		if ( (Math.abs(this.pos[0] - c.pos[0]) <= 1 && this.pos[1] == c.pos[1]) ||
				(Math.abs(this.pos[1] - c.pos[1]) <= 1 && this.pos[0] == c.pos[0]) ) {
			return true;
		}
		else
			return false;
	}
	
	public boolean isAdjacent(int[] pos) {
		if ( (Math.abs(this.pos[0] - pos[0]) <= 1 && this.pos[1] == pos[1]) ||
				(Math.abs(this.pos[1] - pos[1]) <= 1 && this.pos[0] == pos[0]) ) {
			return true;
		}
		else
			return false;
	}
	
	public boolean isAt(int[] pos) {
		return (this.pos.equals(pos));
	}
	
	public boolean setSymbIdx(int i) {
		if (i >= 0 && i < symbols.length) {
			symb_idx = i;
			return true;
		} else {
			return false;
		}
	}

	public void nextSymb() {
		symb_idx = (symb_idx + 1) % symbols.length;
	}
	
	public void draw(char[][] board) {
		board[pos[0]][pos[1]] = this.getSymb();
	}
	
	private char getSymb() {
		return symbols[symb_idx];
	}
	
	public void setInactive() {
		this.active = false;
		this.setSymbIdx(1);
	}
	
	public void setActive() {
		this.active = true;
		this.setSymbIdx(0);
	}
	
	public final int[][] adjacentCells() {
		return new int[][] {
			{pos[0] + 1, pos[1]},
			{pos[0] - 1, pos[1]},
			{pos[0], pos[1] + 1},
			{pos[0], pos[1] - 1}
		};
	}
}
