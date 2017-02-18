import java.util.Scanner;


public class Board {
	public final static char[][] map1 = {
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
	
	public final static char[][] map2 = {
		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
		{'I', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'k', 'X'},
		{'X', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'},
		{'X', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'},
		{'X', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'},
		{'X', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'},
		{'X', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'},
		{'X', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'},
		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
	};
	
	public final static int[][] guard_mov = { 
			{0, -1}, {1, 0} , {1, 0} , {1, 0} , {1, 0} ,
			{0, -1}, {0, -1}, {0, -1}, {0, -1}, {0, -1},
			{0, -1}, {1, 0} , {0, 1} , {0, 1} , {0, 1} ,
			{0, 1} , {0, 1} , {0, 1} , {0, 1} , {-1, 0},
			{-1, 0}, {-1, 0}, {-1, 0}, {-1, 0}, {-1, 0}
	};
	
	public static Hero hero;
	public static Guard guard;
	public static int guard_mov_counter = 0;
	
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
			char[][] tmp_board = copyBoard(map1);
			
			hero.draw(tmp_board);
			guard.draw(tmp_board);
			
			guard.move(guard_mov[guard_mov_counter++ % guard_mov.length]);
			
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
	
	public static char[][] copyBoard(char[][] board) {
		char[][] new_board = new char[board.length][board[0].length];
		
		for (int i = 0; i < board.length; ++i)
			for (int j = 0; j < board[i].length; ++j)
				new_board[i][j] = board[i][j];
		
		return new_board;
	}
	
	public static State checkState() {
		
		// Check Lever
		if (hero.pos[0] == key_pos[0] && hero.pos[1] == key_pos[1]) {
			System.out.println("KEY FOUND");
			for (int i = 0;  i < map1.length; ++i) {
				for (int j = 0;  j < map1[i].length; ++j) {
					if (map1[i][j] == 'I')
						map1[i][j] = 'S';
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
			if (pos[0] == hero.pos[0] && pos[1] == hero.pos[1]) {
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