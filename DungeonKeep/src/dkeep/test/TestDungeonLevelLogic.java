package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.Level.state;
import dkeep.logic.LevelOne;

public class TestDungeonLevelLogic {

	char[][] board = {{'X', 'X', 'X', 'X', 'X'},
					  {'X', 'H', 'B', 'G', 'X'},
					  {'I', 'B', 'B', 'B', 'X'},
					  {'I', 'k', 'B', 'B', 'X'},
					  {'X', 'X', 'X', 'X', 'X'}};
	
	@Test
	public void testMoveHeroIntoFreeCell() {
		
		LevelOne level = new LevelOne (board, false);
			
		assertEquals(1, level.getHero().getPos()[0]);
		assertEquals(1, level.getHero().getPos()[1]);
		
		level.update(0, 1);
		assertEquals(1, level.getHero().getPos()[0]);
		assertEquals(2, level.getHero().getPos()[1]);
	}
	
	@Test
	public void testMoveHeroIntoWall() {
		
		LevelOne level = new LevelOne (board, false);
		
		assertEquals(1, level.getHero().getPos()[0]);
		assertEquals(1, level.getHero().getPos()[1]);
		
		level.update(-1, 0);
		assertEquals(1, level.getHero().getPos()[0]);
		assertEquals(1, level.getHero().getPos()[1]);
	}
	
	@Test
	public void testMoveHeroToGuard() {
		
		LevelOne level = new LevelOne (board, false);
		
		//One step to the right
		assertEquals(state.LOST, level.update(0, 1));
	}
	
	@Test
	public void testMoveHeroToClosedDoor() {
		
		LevelOne level = new LevelOne (board, false);
		
		//One step down
		assertEquals(state.RUNNING, level.update(1,  0));
		
		//One step to the left -> did not move
		assertEquals(state.RUNNING, level.update(0, -1));
		assertEquals(2, level.getHero().getPos()[0]);
		assertEquals(1, level.getHero().getPos()[1]);
	}
	
	@Test
	public void testMoveHeroToLever() {
		
		LevelOne level = new LevelOne (board, false);
		
		//One step down
		assertEquals(state.RUNNING, level.update(1,  0));
		//One step down
		assertEquals(state.RUNNING, level.update(1,  0));
		
		//Check if doors open
		assertEquals(level.getMap()[2][0], 'S');
	}
	
	@Test
	public void testMoveHeroToOpenDoor() {
		
		LevelOne level = new LevelOne (board, false);
		
		//One step down
		assertEquals(state.RUNNING, level.update(1, 0));
		//One step down
		assertEquals(state.RUNNING, level.update(1, 0));
		
		//One step to the left -> Over Open Door
		assertEquals(state.WON, level.update(0, -1));
	}
}
