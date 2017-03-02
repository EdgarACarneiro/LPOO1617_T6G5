package dkeep.logic;

// TODO
public class OgreMap {

	private final char[][] map = {
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

	private boolean doors_open = false;
	
	private static int[] key_pos = {8, 7};
	
	private static int[][] victory_pos = {
			{5, 0}, {6, 0}
	};
	
	private static int[] hero_pos = {1, 1};	// initial hero position
	
	
	public OgreMap() {
		// TODO Auto-generated constructor stub
	}

	

	public char[][] getMap() {
		return map;
	}

	public void openDoors() {
		doors_open = true;
		
		map[5][0] = 'S';
		map[5][1] = 'S';
	}

}