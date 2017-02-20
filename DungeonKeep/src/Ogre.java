import java.util.Random;

public class Ogre extends Character {
	public int[] club;

	public Ogre(int[] initial_pos) {
		super('O', initial_pos);
		club = new int[] {initial_pos[0]+1, initial_pos[1]};
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
		if (((pos[0] == key.pos[0] && pos[1] ==key.pos[1]) || (club[0] == key.pos[0] && club[1] ==key.pos[1])) 
				&& key.picked_up == false)
			symb = '$';
		else
			symb = 'O';
	}
	
	public void swingclub() {
		int[] club_dir = randomMove();
		
		club[0] = pos[0] + club_dir[0];
		club[1] = pos[1] + club_dir[1];
	}
	
	public Boolean move (int[] delta) {
		Boolean result = super.move(delta);
		swingclub();
		return result;
	}
	
	public void drawclub(char[][] board) {
		board[club[0]][club[1]] = '*';
	}
}
