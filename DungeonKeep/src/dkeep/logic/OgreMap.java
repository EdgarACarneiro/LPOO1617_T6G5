package dkeep.logic;

import java.util.Arrays;

// TODO
public class OgreMap extends Map{
	
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
	}
	
	@Override
	public char[][] getMap() {
		char[][] ret = super.getMap();
		
		if (! key_found)
			ret[key_pos[0]][key_pos[1]] = 'k';
		
		return ret;
	}
	
	@Override
	public boolean update(Hero hero) {
		if (Arrays.equals(key_pos, hero.pos))
			key_found = true;
		
		//if (hero.isAdjacent(victory_pos))
		
		return super.update(hero);
	}

}