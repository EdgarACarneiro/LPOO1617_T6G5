import java.util.Scanner;


public class Board {
	public static char[][] board = {
		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
		{'X', 'H', 'B', 'B', 'I', 'B', 'X', 'B', 'G', 'X'},
		{'X', 'X', 'X', 'B', 'X', 'X', 'X', 'B', 'B', 'X'},
		{'X', 'B', 'I', 'B', 'I', 'B', 'X', 'B', 'B', 'X'},
		{'X', 'X', 'X', 'B', 'X', 'X', 'X', 'B', 'B', 'X'},
		{'I', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'},
		{'I', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'},
		{'X', 'X', 'X', 'B', 'X', 'X', 'X', 'X', 'B', 'X'},
		{'X', 'B', 'I', 'B', 'I', 'B', 'X', 'k', 'B', 'X'},
		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
	};
	
	public static int[] hero_pos = { 1, 1 };	
	public static String[] moves = { "up", "down", "right", "left" };
	public static Scanner s = new Scanner(System.in);

	public static enum Event { WIN, LOSS, NONE };

	public static void main(String[] args) {
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
	
	public static Event gameCycle() {
		String input = s.next();
		switch (input) {
		case "up":
			break;
		case "down":
			break;
		case "right":
			break;
		case "left":
			break;
		default:
							
		}
		
		
		return Event.NONE;
	}
}