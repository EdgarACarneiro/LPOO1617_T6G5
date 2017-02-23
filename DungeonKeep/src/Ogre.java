import java.util.Random;

public class Ogre extends Character {
	private Random rand = new Random();
	
	private int[][] moves = {
			{1, 0},
			{-1, 0},
			{0, 1},
			{0, -1}
	};

	public Ogre(int[] initial_pos) {
		super('O', initial_pos);
	}
	
	public int[] randomMove() {
		return moves[rand.nextInt(moves.length)];
	}
}
