package dkeep.logic;
import java.util.Arrays;

public class OgreMap extends Map {
	
	public static final int[] ogre_pos = {1, 4};
	public static final int[] hero_pos = {7, 1};	// initial hero position
	
	private int[] key_pos = {1, 8};
	private boolean key_found = false;
	
	public OgreMap() {
		super.setVictoryPos(new int[][] {{1, 0}});
		
		this.map = new char[][] {
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
			{'I', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'},
			{'X', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'},
			{'X', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'},
			{'X', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'},
			{'X', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'},
			{'X', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'},
			{'X', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'},
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
		};
		
		this.valid_symbs = new char[] {'B', 'S', 'k', 'O', '*'};	// Ogres and clubs can overlap
	}
	
	public OgreMap(char[][] board, int[][] victory_pos) {
		this.map = board;
		
		for (int i = 0 ; i < board.length; ++i) {
			for (int j = 0; j < board[0].length; ++j) {
				if (board[i][j] == 'k')
					key_pos = new int[] {i, j};
			}
		}
		
		this.valid_symbs = new char[] {'B', 'S', 'k', 'O', '*'};
		this.setVictoryPos(victory_pos);
	}
	
	@Override
	public char[][] getMap() {
		char[][] ret = new char[map.length][map[0].length];
		
		// get clone of map to draw on top of
		for (int r = 0; r < map.length; r++)
			for (int c = 0; c < map[r].length; c++)
				ret[r][c] = map[r][c];
		
		if (! key_found)
			ret[key_pos[0]][key_pos[1]] = 'k';
		
		return ret;
	}
	
	@Override
	public boolean update(Hero hero) {
		if (Arrays.equals(key_pos, hero.pos)) {
			key_found = true;
			hero.keyFoundStatus(true);
		}
		
		return super.update(hero);
	}

}