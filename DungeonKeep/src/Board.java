import java.util.Scanner;


public class Board {
	public final static char[][] map = {
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
	
	public static int[] key_pos = { 8, 7 };
	public static int[][] victory_pos = {
			{ 5, 0 },
			{ 6, 0 }
	};

	public static Scanner s = new Scanner(System.in);

	public static enum State { WIN, LOSS, NONE };

	public static void main(String[] args) {
		hero = new Hero(new int[]{1, 1});
		guard = new Guard(new int[] {1, 8});
				
		do {
			
			// Draw game state
			char[][] tmp_board = map.clone();
			hero.draw(tmp_board);
			guard.draw(tmp_board);
			
			printBoard(tmp_board);			
			
		} while (gameCycle() == State.NONE);
	}
	
	
	public static void printBoard(char[][] local_board) {
		for (char[] s : local_board) {
			for (char c : s) {
				if (c == 'B')
					System.out.print("  ");
				else
					System.out.print(c + " ");
			}
			System.out.println();
		}
	}
	
	public static State checkState() {
		
		// Check Lever
		if (hero.pos == key_pos) {
			System.out.println("KEY FOUND");
			for (char[] s : map) {
				for (char c : s) {
					if (c == 'I')
						c = 'S';
				}
			}
		}
		
		// Check Loss
		if (hero.isNear(guard)) {
			System.out.println("YOU LOST");
			return State.LOSS;
		}
		
		// Check Win
		for (int[] pos : victory_pos) {
			if (pos.equals(hero.pos)) {
				System.out.println("YOU WON!");
				return State.WIN;
			}
		}
		
		return State.NONE;
	}
	
	public static State gameCycle() {
		
		String input = s.next();
		switch (input.toUpperCase()) {
		case "W":
			hero.move(new int[] {-1, 0});
			break;
		case "A":
			hero.move(new int[] {0, -1});
			break;
		case "S":
			hero.move(new int[] {1, 0});
			break;
		case "D":
			hero.move(new int[] {0, 1});
			break;
		default:
			System.err.println("Invalid input. Use one of \"WASD\".");		
		}
		
		return checkState();
	}
}