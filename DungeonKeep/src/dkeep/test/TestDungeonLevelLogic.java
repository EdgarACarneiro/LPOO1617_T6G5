package dkeep.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dkeep.logic.Level.state;
import dkeep.logic.LevelOne;

public class TestDungeonLevelLogic {

	char[][] board = {{'X', 'X', 'X', 'X', 'X'},
					  {'X', 'H', 'B', 'G', 'X'},
					  {'I', 'B', 'B', 'B', 'X'},
					  {'I', 'k', 'B', 'B', 'X'},
					  {'X', 'X', 'X', 'X', 'X'}};
	
	int[][] victory_pos = {{ 2, 0}, { 3, 0}};
	
	@Test
	public void testMoveHeroIntoFreeCell() {
		
		LevelOne level = new LevelOne (board, false, victory_pos);
			
		assertEquals(1, level.getHero().getPos()[0]);
		assertEquals(1, level.getHero().getPos()[1]);
		
		level.update(0, 1);
		assertEquals(1, level.getHero().getPos()[0]);
		assertEquals(2, level.getHero().getPos()[1]);
	}
	
	@Test
	public void testMoveHeroIntoWall() {
		
		LevelOne level = new LevelOne (board, false, victory_pos);
		
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
		assertEquals(state.RUNNING, level.update(1, 0));
		level.draw();
		assertEquals(state.RUNNING, level.update(1, 0));
	}
	
	@Test
	public void testMoveHeroToGuard() {
		
		LevelOne level = new LevelOne (board, false, victory_pos);
		
		//One step to the right
		assertEquals(state.LOST, level.update(0, 1));
	}
	
	@Test
	public void testMoveHeroToClosedDoor() {
		
		LevelOne level = new LevelOne (board, false, victory_pos);
		
		//One step down
		assertEquals(state.RUNNING, level.update(1,  0));
		
		//One step to the left -> did not move
		assertEquals(state.RUNNING, level.update(0, -1));
		assertEquals(2, level.getHero().getPos()[0]);
		assertEquals(1, level.getHero().getPos()[1]);
	}
	
	@Test
	public void testMoveHeroToLever() {
		
		LevelOne level = new LevelOne (board, false, victory_pos);
		
		//One step down
		assertEquals(state.RUNNING, level.update(1,  0));
		//One step down
		assertEquals(state.RUNNING, level.update(1,  0));
		
		//Check if doors open
		assertEquals(level.getMap()[2][0], 'S');
	}
	
	@Test
	public void testMoveHeroToOpenDoor() {
		
		LevelOne level = new LevelOne (board, false, victory_pos);
		
		//One step down
		assertEquals(state.RUNNING, level.update(1, 0));
		//One step down
		assertEquals(state.RUNNING, level.update(1, 0));
		
		//One step to the left -> Over Open Door
		assertEquals(state.WON, level.update(0, -1));
	}
	
	@Test
	public void testGuardCatchHero() {
		
		LevelOne level = new LevelOne (board, true, victory_pos);
		
		//One step down
		assertEquals(state.RUNNING, level.update(1, 0));
		//One step down
		assertEquals(state.RUNNING, level.update(1, 0));
		//One step down -> Does not Move -> Hero Catches
		assertEquals(state.LOST, level.update(1, 0));
	}
	
	@Test
	public void testDefaultCoonstrcutor() {
		
		LevelOne level = new LevelOne();
		
		/*
		  		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
				{'X', 'H', 'B', 'B', 'I', 'B', 'X', 'B', 'B', 'X'}
				{'X', 'X', 'X', 'B', 'X', 'X', 'X', 'B', 'G', 'X'}
				{'X', 'B', 'I', 'B', 'I', 'B', 'X', 'B', 'B', 'X'}
				{'X', 'X', 'X', 'B', 'X', 'X', 'X', 'B', 'B', 'X'}
				{'I', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'}
				{'I', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'}
				{'X', 'X', 'X', 'B', 'X', 'X', 'X', 'X', 'B', 'X'}
				{'X', 'B', 'I', 'B', 'I', 'B', 'X', 'k', 'B', 'X'}
				{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
		 */
		
		assertEquals(1, level.getHero().getPos()[0]);
		assertEquals(1, level.getHero().getPos()[1]);
		
		level.update(0, 1);
		assertEquals(1, level.getHero().getPos()[0]);
		assertEquals(2, level.getHero().getPos()[1]);
		
		level.update(-1, 0);
		assertEquals(1, level.getHero().getPos()[0]);
		assertEquals(2, level.getHero().getPos()[1]);
		
	}
}
