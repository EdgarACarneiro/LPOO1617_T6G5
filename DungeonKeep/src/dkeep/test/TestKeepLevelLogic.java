package dkeep.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import dkeep.logic.LevelTwo;
import dkeep.logic.Level.state;

public class TestKeepLevelLogic {
	
	char[][] board = {{'X', 'X', 'X', 'X', 'X'},
			  		  {'X', 'H', 'B', 'O', 'X'},
			  		  {'I', 'B', 'B', 'B', 'X'},
			  		  {'I', 'k', 'B', 'B', 'X'},
			  		  {'X', 'X', 'X', 'X', 'X'}};
	
	boolean enemy_activity, hero_armed = false;

	int[][] victory_pos = {{ 2, 0}, { 3, 0}};
	
	@Test
	public void testMoveHeroToOgre() {
	
		LevelTwo level = new LevelTwo (board, enemy_activity, hero_armed, victory_pos);
		
		//One step to the right
		assertEquals(state.LOST, level.update(0, 1));
	}
	
	@Test
	public void testMoveHeroToKey() {
	
		LevelTwo level = new LevelTwo (board, enemy_activity, hero_armed, victory_pos);
		
		//One step down
		assertEquals(state.RUNNING, level.update(1,  0));
		//One step down
		assertEquals(state.RUNNING, level.update(1,  0));
		
		//Check if Hero symbol is 'K'
		assertEquals(level.getHero().getSymb(), 'K');
	}
	
	@Test
	public void testMoveHeroToClosedDoor() {
	
		LevelTwo level = new LevelTwo (board, enemy_activity, hero_armed, victory_pos);
		
		//One step down
		assertEquals(state.RUNNING, level.update(1,  0));
		//One step to the left -> did not move
		assertEquals(state.RUNNING, level.update(0, -1));
		assertEquals(2, level.getHero().getPos()[0]);
		assertEquals(1, level.getHero().getPos()[1]);
	}
	
	@Test
	public void testHeroOpenedDoor() {

		LevelTwo level = new LevelTwo (board, enemy_activity, hero_armed, victory_pos);
		
		//One step down
		assertEquals(state.RUNNING, level.update(1, 0));
		//One step down
		assertEquals(state.RUNNING, level.update(1, 0));
		
		//One step to the left -> Open Door
		assertEquals(state.RUNNING, level.update(0, -1));
		assertEquals(3, level.getHero().getPos()[0]);
		assertEquals(1, level.getHero().getPos()[1]);
		
		//Check if doors open
		assertEquals(level.getMap()[3][0], 'S');
	}
	
	@Test
	public void testHeroWon() {
		
		LevelTwo level = new LevelTwo (board, enemy_activity, hero_armed, victory_pos);
		
		//One step down
		assertEquals(state.RUNNING, level.update(1, 0));
		//One step down
		assertEquals(state.RUNNING, level.update(1, 0));
		
		//One step to the left -> Open Door
		assertEquals(state.RUNNING, level.update(0, -1));
		assertEquals(3, level.getHero().getPos()[0]);
		assertEquals(1, level.getHero().getPos()[1]);
		
		//Finish Game -> Move to Open Door
		assertEquals(state.WON, level.update(0, -1));
	}
	
	@Test
	public void testHeroStunOgre() {
		
		hero_armed = true;
		LevelTwo level = new LevelTwo (board, enemy_activity, hero_armed, victory_pos);
		
		//One step to the right -> Stun Ogre
		assertEquals(state.RUNNING, level.update(0, 1));
	}
	
	@Test
	public void testClubKillsHero() {
		
		LevelTwo level = new LevelTwo (board, enemy_activity, hero_armed, victory_pos);
		
		//One step Down
		assertEquals(state.RUNNING, level.update(1, 0));
		//One step to the right -> Next to Club
		assertEquals(state.LOST, level.update(0, 1));
	}
	
}
