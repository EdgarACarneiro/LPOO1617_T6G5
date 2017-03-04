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
	
	public boolean isValid(Hero hero, int[] delta) {
		int[] new_pos = {hero.pos[0] + delta[0], hero.pos[1] + delta[1]};
		
		if (hero.hasKey() && map[new_pos[0]][new_pos[1]] == 'I') {
			map[new_pos[0]][new_pos[1]] = 'S';
			return false;
		}
		
		return super.isValid(new_pos[0], new_pos[1]);
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