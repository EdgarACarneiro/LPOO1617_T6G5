package dkeep.logic;

// TODO
public class OgreMap extends Map{

	//private boolean doors_open = false;
	
	private static int[] key_pos = {1, 8};
	
	public static int[] ogre_pos = {1, 4};
	
	public static int[] hero_pos = {7, 1};	// initial hero position
	
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

	public void openDoors() {
		//doors_open = true;
		
		//map[1][0] = 'S';
	}
	
	@Override
	public boolean isValid(int row, int col) {
		return (map[row][col] == 'B' || map[row][col] == 'S');
	}


}