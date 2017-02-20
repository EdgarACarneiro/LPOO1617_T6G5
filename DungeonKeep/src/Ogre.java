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
	
	public void overKey(Key key) {
		if (pos[0] == key.pos[0] && pos[1] ==key.pos[1])
			symb = '$';
		else
			symb = 'O';
	}
}
