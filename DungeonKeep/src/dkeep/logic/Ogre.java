package dkeep.logic;
import java.util.Arrays;
import java.util.Random;

public class Ogre extends GameCharacter {
	
	private static final long serialVersionUID = 8L;
	
	private static Random rand = new Random();
	private int TURN_WAKE_UP = 2;
		
	private static final int[][] moves = {
			{ 1, 0},
			{-1, 0},
			{ 0, 1},
			{ 0,-1}
	};
	
	private int[] club;
	private int sleep_turn;

	public Ogre(int[] initial_pos) {
		super(initial_pos, 'O', '8', '$');
		club = new int[] {initial_pos[0]+1, initial_pos[1]};
		armed = true;
	}
	
	public static int[] randomMove() {
		return moves[rand.nextInt(moves.length)];
	}
	
	private void swingClub() {
		int[] club_dir = randomMove();
		
		club[0] = pos[0] + club_dir[0];
		club[1] = pos[1] + club_dir[1];
	}
	
	private void checkSelfSymb(Map map) {
		if ( Arrays.equals(map.getKeyPos(), pos) ) {
			this.setSymbIdx(2);
		} else {			
			if (this.active)
				this.setActive();
			else
				this.setInactive();
		}
	}
	
	public int[] getClubPos() {
		return club;
	}
	
	@Override
	public void update(Map map, int row, int col) {
		super.update(map, row, col);
		swingClub();
		
		checkSelfSymb(map);
	}
	
	@Override
	public void update(Map map) {
		
		if (! active) {
			if (sleep_turn == TURN_WAKE_UP) {
				sleep_turn = 0;
				this.setActive();
			} else {
				++sleep_turn;
			}
		}
			
		int[] tmp;
		int[] new_pos = new int[2];
		
		do {
			tmp = randomMove();
			new_pos[0] = pos[0] + tmp[0];
			new_pos[1] = pos[1] + tmp[1];
		} while (! map.isValid(new_pos[0], new_pos[1]) && this.active);
		if (this.active)
			pos = new_pos;
		
		// Swing Club, whether active or not
		do {
			swingClub();
		} while(! map.isValid(club[0], club[1]));
		
		checkSelfSymb(map);
	}
	
	@Override
	public void draw(char[][] board) {
		super.draw(board);
		board[club[0]][club[1]] = '*';
	}
	
	@Override
	public boolean isAdjacent(GameCharacter c) {
		return (c.isAdjacent(this) || c.isAdjacent(club));
	}
	
	@Override
	public boolean attack(GameCharacter c) {
		if (! armed || ! this.isAdjacent(c) )
			return false;
		
		c.setInactive();
		return true;
	}
	
}
