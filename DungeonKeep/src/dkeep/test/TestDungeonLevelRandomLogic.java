package dkeep.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import dkeep.logic.GameCharacter;
import dkeep.logic.Guard;
import dkeep.logic.Guard.Personality;
import dkeep.logic.LevelOne;
import dkeep.logic.LevelTwo;
import dkeep.logic.Ogre;
import dkeep.logic.Level.State;

public class TestDungeonLevelRandomLogic {

	char[][] board = {{'X', 'X', 'X', 'X', 'X'},
					  {'X', 'H', 'B', 'G', 'X'},
					  {'I', 'B', 'B', 'B', 'X'},
					  {'I', 'k', 'B', 'B', 'X'},
					  {'X', 'X', 'X', 'X', 'X'}};

	int[][] victory_pos = {{ 2, 0}, { 3, 0}};
	
	boolean enemy_activity = false;
	
	@Test
	public void testGuardCatchHero() {
		
		LevelOne level = new LevelOne (Personality.ROOKIE);
		GameCharacter guard = level.getEnemies().get(0);
/*
  		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
		{'X', 'H', 'B', 'B', 'I', 'B', 'X', 'B', 'G', 'X'}
		{'X', 'X', 'X', 'B', 'X', 'X', 'X', 'B', 'B', 'X'}
		{'X', 'B', 'I', 'B', 'I', 'B', 'X', 'B', 'B', 'X'}
		{'X', 'X', 'X', 'B', 'X', 'X', 'X', 'B', 'B', 'X'}
		{'I', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'}
		{'I', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'}
		{'X', 'X', 'X', 'B', 'X', 'X', 'X', 'X', 'B', 'X'}
		{'X', 'B', 'I', 'B', 'I', 'B', 'X', 'k', 'B', 'X'}
		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
 */
		//One step to the right
		assertEquals(State.RUNNING, level.update(0, 1));
		//One step to the right
		assertEquals(State.RUNNING, level.update(0, 1));
		
		//Four steps down
		for (int i = 0; i < 4; ++i)
			assertEquals(State.RUNNING, level.update(1, 0));
		
		//One step to the right -> Next to Guard -> Lost
		assertEquals(State.LOST, level.update(0, 1));
		assertEquals(5, level.getHero().getPos()[0]);
		assertEquals(4, level.getHero().getPos()[1]);
		assertEquals(5, guard.getPos()[0]);
		assertEquals(5, guard.getPos()[1]);
	}
	
	@Test (timeout=1000)
	public void testGuardPersonalities() {
		
		boolean drunken = false, suspicious = false, rookie = false;
		
		while( !drunken || !suspicious || !rookie)  {
			
			//Generating new game sessions
			LevelOne level = new LevelOne (board, enemy_activity, victory_pos);
			GameCharacter guard = level.getEnemies().get(0);

			if(guard instanceof Guard) {
				if (((Guard) guard).getPersonality() == Personality.ROOKIE)
					rookie = true;
				
				else if (((Guard) guard).getPersonality() == Personality.SUSPICIOUS)
					suspicious = true;
				
				else if (((Guard) guard).getPersonality() == Personality.DRUNKEN)
					drunken = true;
			
				else 
					fail("Guards's personality is unexpected!\n");
			}
		}
	}
	
	//Guard's Movement Tests
	
	@Test
	public void testRookieMovement() {
		
		LevelOne level = new LevelOne (Personality.ROOKIE);
		GameCharacter guard = level.getEnemies().get(0);
		
/*
  		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
		{'X', 'H', 'B', 'B', 'I', 'B', 'X', 'B', 'G', 'X'}
		{'X', 'X', 'X', 'B', 'X', 'X', 'X', 'B', 'B', 'X'}
		{'X', 'B', 'I', 'B', 'I', 'B', 'X', 'B', 'B', 'X'}
		{'X', 'X', 'X', 'B', 'X', 'X', 'X', 'B', 'B', 'X'}
		{'I', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'}
		{'I', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'}
		{'X', 'X', 'X', 'B', 'X', 'X', 'X', 'X', 'B', 'X'}
		{'X', 'B', 'I', 'B', 'I', 'B', 'X', 'k', 'B', 'X'}
		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
 */
		
		assertEquals(1, guard.getPos()[0]);
		assertEquals(8, guard.getPos()[1]);
		
		//Checking Rookie Position
		level.update(0, -1);
		assertEquals(1, guard.getPos()[0]);
		assertEquals(7, guard.getPos()[1]);
		
		level.update(0, -1);
		assertEquals(2, guard.getPos()[0]);
		assertEquals(7, guard.getPos()[1]);
		
		//Checking Various Positions
		for(int i = 0; i < 10; ++i)
			level.update(0, -1);
		
		assertEquals(6, guard.getPos()[0]);
		assertEquals(1, guard.getPos()[1]);
		
		
		for(int i = 0; i < 5; ++i)
			level.update(0, -1);
		
		assertEquals(6, guard.getPos()[0]);
		assertEquals(6, guard.getPos()[1]);
	}
	
	@Test(timeout=1000)
	public void testSuspiciousMovement() {
		
		LevelOne level = new LevelOne (Personality.SUSPICIOUS);
		GameCharacter guard = level.getEnemies().get(0);
		
/*
  		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
		{'X', 'H', 'B', 'B', 'I', 'B', 'X', 'B', 'G', 'X'}
		{'X', 'X', 'X', 'B', 'X', 'X', 'X', 'B', 'B', 'X'}
		{'X', 'B', 'I', 'B', 'I', 'B', 'X', 'B', 'B', 'X'}
		{'X', 'X', 'X', 'B', 'X', 'X', 'X', 'B', 'B', 'X'}
		{'I', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'}
		{'I', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'}
		{'X', 'X', 'X', 'B', 'X', 'X', 'X', 'X', 'B', 'X'}
		{'X', 'B', 'I', 'B', 'I', 'B', 'X', 'k', 'B', 'X'}
		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
 */
		boolean changeDirection = false;
		int[] changedDirPos = { guard.getPos()[0], guard.getPos()[1]};
		
		while (!changeDirection) {
			
			level.update(0, -1);
			level.update(0, -1);
			
			if (changedDirPos[0] == guard.getPos()[0] && changedDirPos[1] == guard.getPos()[1])
				changeDirection = true;
			else {
				changedDirPos[0] = guard.getPos()[0];
				changedDirPos[1] = guard.getPos()[1];
			}
		}
	}
	
	@Test(timeout=1000)
	public void testDrunkenMovement() {
		
		LevelOne level = new LevelOne (Personality.DRUNKEN);
		GameCharacter guard = level.getEnemies().get(0);
		
/*
  		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
		{'X', 'H', 'B', 'B', 'I', 'B', 'X', 'B', 'G', 'X'}
		{'X', 'X', 'X', 'B', 'X', 'X', 'X', 'B', 'B', 'X'}
		{'X', 'B', 'I', 'B', 'I', 'B', 'X', 'B', 'B', 'X'}
		{'X', 'X', 'X', 'B', 'X', 'X', 'X', 'B', 'B', 'X'}
		{'I', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'}
		{'I', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'}
		{'X', 'X', 'X', 'B', 'X', 'X', 'X', 'X', 'B', 'X'}
		{'X', 'B', 'I', 'B', 'I', 'B', 'X', 'k', 'B', 'X'}
		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
 */
		boolean changeDirection = false, sleeped = false;
		int[] changedDirPos = { guard.getPos()[0], guard.getPos()[1]};
		int[] sleepPos = { guard.getPos()[0], guard.getPos()[1]};
		
		while (!changeDirection || !sleeped) {
			
			level.update(0, -1);
			
			sleepPos[0] = guard.getPos()[0];
			sleepPos[1] = guard.getPos()[1];
			level.update(0, -1);
			
			//Guard is sleeping
			while (sleepPos[0] == guard.getPos()[0] && sleepPos[1] == guard.getPos()[1]) {
				sleeped = true;
				level.update(0, -1);
			}
			
			//Woke Up -> Changed direction?
			if (changedDirPos[0] == guard.getPos()[0] && changedDirPos[1] == guard.getPos()[1])
				changeDirection = true;
			else {
				changedDirPos[0] = guard.getPos()[0];
				changedDirPos[1] = guard.getPos()[1];
			}
		}
	}
	
	@Test
	public void testDefaultConstructor() {
		
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
		
		//Moving Right
		level.update(0, 1);
		assertEquals(1, level.getHero().getPos()[0]);
		assertEquals(2, level.getHero().getPos()[1]);
		
		//Moving Up -> Wall
		level.update(-1, 0);
		assertEquals(1, level.getHero().getPos()[0]);
		assertEquals(2, level.getHero().getPos()[1]);
		
		//Moving Right
		level.update(0, 1);
		assertEquals(1, level.getHero().getPos()[0]);
		assertEquals(3, level.getHero().getPos()[1]);
		
		//Moving Down
		level.update(1, 0);
		assertEquals(2, level.getHero().getPos()[0]);
		assertEquals(3, level.getHero().getPos()[1]);
		
		//Moving Down
		level.update(1, 0);
		assertEquals(3, level.getHero().getPos()[0]);
		assertEquals(3, level.getHero().getPos()[1]);
		
		//Moving Left -> Close Door
		level.update(0, -1);
		assertEquals(3, level.getHero().getPos()[0]);
		assertEquals(3, level.getHero().getPos()[1]);
	}
}
