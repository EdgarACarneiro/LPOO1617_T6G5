import java.util.Random;

public class Ogre extends Character {
	public int[] cub;

	public Ogre(int[] initial_pos) {
		super('O', initial_pos);
		cub = new int[] {initial_pos[0]+1, initial_pos[1]};
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
		if (((pos[0] == key.pos[0] && pos[1] ==key.pos[1]) || (cub[0] == key.pos[0] && cub[1] ==key.pos[1])) 
				&& key.picked_up == false)
			symb = '$';
		else
			symb = 'O';
	}
	
	public void swingCub() {
		int[] cub_dir = randomMove();
		
		cub[0] = pos[0] + cub_dir[0];
		cub[1] = pos[1] + cub_dir[1];
	}
	
	public Boolean move (int[] delta) {
		Boolean result = super.move(delta);
		swingCub();
		return result;
	}
	
	public void drawCub(char[][] board) {
		board[cub[0]][cub[1]] = '*';
	}
}
