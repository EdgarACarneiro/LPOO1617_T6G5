import java.util.Scanner;


public class Board {
	public final static char[][] board = {
		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
		{'X', 'B', 'B', 'B', 'I', 'B', 'X', 'B', 'B', 'X'},
		{'X', 'X', 'X', 'B', 'X', 'X', 'X', 'B', 'B', 'X'},
		{'X', 'B', 'I', 'B', 'I', 'B', 'X', 'B', 'B', 'X'},
		{'X', 'X', 'X', 'B', 'X', 'X', 'X', 'B', 'B', 'X'},
		{'I', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'},
		{'I', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'},
		{'X', 'X', 'X', 'B', 'X', 'X', 'X', 'X', 'B', 'X'},
		{'X', 'B', 'I', 'B', 'I', 'B', 'X', 'k', 'B', 'X'},
		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
	};
	
	public static Hero hero;
	public static Guard guard;

	public static Scanner s = new Scanner(System.in);

	public static enum State { WIN, LOSS, NONE };

	public static void main(String[] args) {
		hero = new Hero(new int[]{1, 1});
		guard = new Guard(new int[] {1, 8});
		
		printBoard();
	}
	
	
	public static void printBoard() {
		for (char[] s : board) {
			for (char c : s) {
				if (c == 'B')
					System.out.print("  ");
				else
					System.out.print(c + " ");
			}
			System.out.println();
		}
	}
	
	public static Boolean moveHero(int[] delta) {
		
		return true;
	}
	
	public static State checkState() {
		
		return State.NONE;
	}
	
	public static State gameCycle() {
		String input = s.next();
		switch (input.toUpperCase()) {
		case "W":
			break;
		case "A":
			break;
		case "S":
			break;
		case "D":
			break;
		default:
							
		}
		
		
		return checkState();
	}
}