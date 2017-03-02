package dkeep.logic;
import java.util.Random;

public class Ogre extends Character {
	private static Random rand = new Random();
	
	private static final int[][] moves = {
			{1, 0},
			{-1, 0},
			{0, 1},
			{0, -1}
	};
	
	public int[] club;

	public Ogre(int[] initial_pos) {
		super(initial_pos, 'O');
		club = new int[] {initial_pos[0]+1, initial_pos[1]};
	}
	
	public static int[] randomMove() {
		return moves[rand.nextInt(moves.length)];
	}
	
	public void swingclub() {
		int[] club_dir = randomMove();
		
		club[0] = pos[0] + club_dir[0];
		club[1] = pos[1] + club_dir[1];
	}
	
	public void update() {
		int[] tmp = randomMove();
		pos[0] += tmp[0];
		pos[1] += tmp[1];
		swingclub();
	}
	
	@Override
	public void draw(char[][] board) {
		super.draw(board);
		board[club[0]][club[1]] = '*';
	}
}
