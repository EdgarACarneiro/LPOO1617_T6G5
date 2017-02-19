import java.util.Random;

public class Ogre extends Character {

	public Ogre(int[] initial_pos) {
		super('O', initial_pos);
	}
	
	public int[] randomMove() {
		
		//Possible movements
		final int[][] random_mov = { 
				{  0,  1},
				{  0, -1},
				{  1,  0},
				{ -1,  0}				
		};
		
		Random rand = new Random();
		
		return random_mov[rand.nextInt(4)];
	}
}
