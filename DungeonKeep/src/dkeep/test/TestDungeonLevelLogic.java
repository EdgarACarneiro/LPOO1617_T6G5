package dkeep.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dkeep.logic.Level.State;
import dkeep.logic.LevelOne;

public class TestDungeonLevelLogic {

	char[][] board = {{'X', 'X', 'X', 'X', 'X'},
					  {'X', 'H', 'B', 'G', 'X'},
					  {'I', 'B', 'B', 'B', 'X'},
					  {'I', 'k', 'B', 'B', 'X'},
					  {'X', 'X', 'X', 'X', 'X'}};
	
	int[][] victory_pos = {{ 2, 0}, { 3, 0}};
	
	boolean enemy_activity = false;
	
	@Test
	public void testMoveHeroIntoFreeCell() {
		
		LevelOne level = new LevelOne (board, enemy_activity, victory_pos);
			
		assertEquals(1, level.getHero().getPos()[0]);
		assertEquals(1, level.getHero().getPos()[1]);
		
		level.update(0, 1);
		assertEquals(1, level.getHero().getPos()[0]);
		assertEquals(2, level.getHero().getPos()[1]);
	}
	
	@Test
	public void testMoveHeroIntoWall() {
		
		LevelOne level = new LevelOne (board, enemy_activity, victory_pos);
		
		assertEquals(1, level.getHero().getPos()[0]);
		assertEquals(1, level.getHero().getPos()[1]);
		
		level.update(-1, 0);
		assertEquals(1, level.getHero().getPos()[0]);
		assertEquals(1, level.getHero().getPos()[1]);
	}
	
	@Test
	public void testDraw() {
		
		LevelOne level = new LevelOne (board, true, victory_pos);
		
		//Draw does not crash even with guard moving -> No other way to test draw.
		
		level.draw();
		assertEquals(State.RUNNING, level.update(1, 0));
		level.draw();
		assertEquals(State.RUNNING, level.update(1, 0));
	}
	
	@Test
	public void testMoveHeroToGuard() {
		
		LevelOne level = new LevelOne (board, enemy_activity, victory_pos);
		
		//One step to the right -> Hero next to Guard
		assertEquals(State.LOST, level.update(0, 1));
	}
	
	@Test
	public void testMoveHeroToClosedDoor() {
		
		LevelOne level = new LevelOne (board, enemy_activity, victory_pos);
		
		//One step down
		assertEquals(State.RUNNING, level.update(1,  0));
		
		//One step to the left -> did not move
		assertEquals(State.RUNNING, level.update(0, -1));
		assertEquals(2, level.getHero().getPos()[0]);
		assertEquals(1, level.getHero().getPos()[1]);
		assertEquals(level.getMap()[2][0], 'I');
	}
	
	@Test
	public void testMoveHeroToLever() {
		
		LevelOne level = new LevelOne (board, enemy_activity, victory_pos);
		
		//One step down
		assertEquals(State.RUNNING, level.update(1,  0));
		//One step down
		assertEquals(State.RUNNING, level.update(1,  0));
		
		//Check if doors open
		assertEquals(level.getMap()[2][0], 'S');
		assertEquals(level.getMap()[3][0], 'S');
	}
	
	@Test
	public void testMoveHeroToOpenDoor() {
		
		LevelOne level = new LevelOne (board, enemy_activity, victory_pos);
		
		//One step down
		assertEquals(State.RUNNING, level.update(1, 0));
		//One step down -> Open Door
		assertEquals(State.RUNNING, level.update(1, 0));
		
		//One step to the left -> Over Open Door
		assertEquals(State.WON, level.update(0, -1));
		assertEquals(3, level.getHero().getPos()[0]);
		assertEquals(0, level.getHero().getPos()[1]);
	}
}
