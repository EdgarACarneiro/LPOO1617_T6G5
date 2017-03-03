package dkeep.logic;

import java.util.Arrays;

public class DungeonMap extends Map {
	
	private boolean doors_open = false;
	
	private static final int[] lever_pos = {8, 7};
	
	public static final int[] hero_pos = {1, 1};	// initial hero position
	
	public static final int[] guard_pos = {1, 8};	// initial guard position
	
	public DungeonMap() {
		super.setVictoryPos(new int[][] {{5, 0}, {6, 0}});
		
		this.map = new char[][] {
				{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
				{'X', 'B', 'B', 'B', 'I', 'B', 'X', 'B', 'B', 'X'},
				{'X', 'X', 'X', 'B', 'X', 'X', 'X', 'B', 'B', 'X'},
				{'X', 'B', 'I', 'B', 'I', 'B', 'X', 'B', 'B', 'X'},
				{'X', 'X', 'X', 'B', 'X', 'X', 'X', 'B', 'B', 'X'},
				{'I', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'},
				{'I', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'},
				{'X', 'X', 'X', 'B', 'X', 'X', 'X', 'X', 'B', 'X'},
				{'X', 'B', 'I', 'B', 'I', 'B', 'X', 'k', 'B', 'X'},
				{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
			};
	}

	private void openDoors() {		
		if (doors_open)
			return;
		
		for (int r = 0; r < map.length; r++)
			for (int c = 0; c < map[r].length; c++)
				if (map[r][c] == 'I')
					map[r][c] = 'S';
		
		doors_open = true;
	}
	
	@Override
	public boolean update(Hero hero) {
		if (Arrays.equals(lever_pos, hero.pos))
			this.openDoors();
		
		return super.update(hero);		
	}

}
