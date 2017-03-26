package dkeep.logic;
import java.util.Arrays;

/**
 * @author 
 *
 */
public class OgreMap extends GameMap implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9L;
	
	/**
	 * 
	 */
	public static final int[] ogre_pos = {1, 4};
	/**
	 * 
	 */
	public static final int[] hero_pos = {7, 1};	// initial hero position
	
	/**
	 * 
	 */
	private boolean key_found = false;
	
	/**
	 * 
	 */
	public OgreMap() {
		super.setVictoryPos(new int[][] {{1, 0}});
		key_pos[0] = 1; key_pos[1] = 8;
		
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
	
	/**
	 * @param board
	 * @param victory_pos
	 */
	public OgreMap(char[][] board, int[][] victory_pos) {
		
		super.setVictoryPos(victory_pos);
		
		this.map = board;
		
		for (int i = 0 ; i < map.length; ++i) {
			for (int j = 0; j < map[0].length; ++j) {
				if (board[i][j] == 'k') {
					key_pos = new int[] {i, j};
					map[i][j] = 'B';
				}
			}
		}
		
		this.valid_symbs = new char[] {'B', 'S', 'k', 'O', '*'};
	}
	
	/** (non-Javadoc)
	 * @see dkeep.logic.GameMap#getMap()
	 */
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
	
	/** (non-Javadoc)
	 * @see dkeep.logic.GameMap#update(dkeep.logic.Hero)
	 */
	@Override
	public boolean update(Hero hero) {
		if (Arrays.equals(key_pos, hero.pos)) {
			key_found = true;
			hero.keyFoundStatus(true);
			key_pos = null;
		}
		
		return super.update(hero);
	}

}