package dkeep.logic;

// HANDLED!! semi?
public class DungeonMap extends Map {

	private final char[][] map = {
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
			{'X', 'B', 'B', 'B', 'I', 'B', 'X', 'B', 'B', 'X'},
			{'X', 'X', 'X', 'B', 'X', 'X', 'X', 'B', 'B', 'X'},
			{'X', 'B', 'I', 'B', 'I', 'B', 'X', 'B', 'B', 'X'},
			{'X', 'X', 'X', 'B', 'X', 'X', 'X', 'B', 'B', 'X'},
			{'I', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'},
			{'I', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'},
			{'X', 'X', 'X', 'B', 'X', 'X', 'X', 'X', 'B', 'X'},
			{'X', 'B', 'I', 'B', 'I', 'B', 'X', 'B', 'B', 'X'},
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
		};
	
	//private boolean doors_open = false;
	
	private static final int[] key_pos = {8, 7};
	
	public static final int[] hero_pos = {1, 1};	// initial hero position
	
	public static final int[] guard_pos = {1, 8};
	
	public DungeonMap() {
		super.setVictoryPos(new int[][] {{5, 0}, {6, 0}});
	}

	private void openDoors() {
		// TODO abrir todas as portas?
		//doors_open = true;
		map[5][0] = 'S';
		map[5][1] = 'S';
	}
	
	@Override
	public char[][] getMap() {
		return map;
	}
	

	@Override
	public boolean isValid(int row, int col) {
		return (map[row][col] == 'B' || map[row][col] == 'S');
	}

	@Override
	public boolean update(int[] hero_pos) {
		if (super.isWon(hero_pos))
			return true;
		return false;
	}

}
