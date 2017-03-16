package dkeep.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Test;

import dkeep.logic.Level.State;
import dkeep.logic.LevelTwo;
import dkeep.logic.Ogre;

public class TestKeepLevelRandomLogic {
	
	char[][] board = {{'X', 'X', 'X', 'X', 'X', 'X'},
					  {'X', 'B', 'B', 'B', 'O', 'X'},
					  {'I', 'B', 'B', 'B', 'B', 'X'},
					  {'I', 'B', 'B', 'B', 'B', 'X'},
					  {'X', 'X', 'X', 'B', 'B', 'X'},
					  {'X', 'H', 'X', 'B', 'k', 'X'},
					  {'X', 'X', 'X', 'X', 'X', 'X'}};
	
	char[][] board1 = {{'X', 'X', 'X', 'X', 'X', 'X'},
					   {'X', 'B', 'B', 'B', 'O', 'X'},
					   {'I', 'B', 'B', 'B', 'B', 'X'},
					   {'I', 'B', 'B', 'B', 'B', 'X'},
					   {'X', 'B', 'B', 'B', 'B', 'X'},
					   {'X', 'H', 'B', 'B', 'k', 'X'},
					   {'X', 'X', 'X', 'X', 'X', 'X'}};

	boolean enemy_activity = true, hero_armed = false;

	int[][] victory_pos = {{ 2, 0}, { 3, 0}};
	
	@Test(timeout=1000)
	public void testOgreRandomBehaviour() {
		
		LevelTwo level = new LevelTwo (board, enemy_activity, hero_armed, victory_pos);
		
		boolean movedLeft = false, movedRight = false, movedUp = false, movedDown = false;
		
		while( !movedLeft || !movedRight || !movedUp || !movedDown)  {
			
			int[] oPos = new int[] {level.getEnemies().get(0).getPos()[0], level.getEnemies().get(0).getPos()[1]};
			level.update(0 , 0);

			if(level.getEnemies().get(0).isAt(new int [] {oPos[0] - 1, oPos[1]}))
				movedLeft = true;
			
			else if(level.getEnemies().get(0).isAt(new int [] {oPos[0] + 1, oPos[1]}))
				movedRight = true;
			
			else if(level.getEnemies().get(0).isAt(new int [] {oPos[0], oPos[1] - 1}))
				movedUp = true;
			
			else if(level.getEnemies().get(0).isAt(new int [] {oPos[0], oPos[1] + 1}))
				movedDown = true;
			
			else 
				fail("Ogre's move is unexpected!\n");
		}
	}
	
	@Test(timeout=1000)
	public void testClubRandomBehaviour() {
		
		LevelTwo level = new LevelTwo (board1, enemy_activity, hero_armed, victory_pos);
		
		boolean swingedLeft = false, swingedRight = false, swingedUp = false, swingedDown = false;
		
		while( !swingedLeft || !swingedRight || !swingedUp || !swingedDown)  {
			
			level.update(0 , 0);
			int[] oPos = new int[] {level.getEnemies().get(0).getPos()[0], level.getEnemies().get(0).getPos()[1]};
				
			if(Arrays.equals(((Ogre) level.getEnemies().get(0)).getClubPos(),(new int [] {oPos[0] - 1, oPos[1]})))
				swingedLeft = true;
			
			else if(Arrays.equals(((Ogre) level.getEnemies().get(0)).getClubPos(),(new int [] {oPos[0] + 1, oPos[1]})))
				swingedRight = true;
			
			else if(Arrays.equals(((Ogre) level.getEnemies().get(0)).getClubPos(),(new int [] {oPos[0], oPos[1] - 1})))
				swingedUp = true;
			
			else if(Arrays.equals(((Ogre) level.getEnemies().get(0)).getClubPos(),(new int [] {oPos[0], oPos[1] + 1})))
				swingedDown = true;
			
			else
				fail("Swinged club to unexpected position!\n");
		}
	}
	
	@Test
	public void testDraw() {
		
		LevelTwo level = new LevelTwo (board, enemy_activity, hero_armed, victory_pos);
		
		//Draw does not crash even with ogre moving -> No other way to test draw.
		
		level.draw();
		assertEquals(State.RUNNING, level.update(1, 0));
		level.draw();
		assertEquals(State.RUNNING,level.update(-1, 0));
	}
	
	@Test
	public void testDefaultConstructor() {
		
		LevelTwo level = new LevelTwo();
		
		/*
		  	{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
			{'I', 'B', 'B', 'B', 'O', 'B', 'B', 'B', 'B', 'X'}
			{'X', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'}
			{'X', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'}
			{'X', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'}
			{'X', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'}
			{'X', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'}
			{'X', 'A', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'X'}
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
		 */
		
		assertEquals(7, level.getHero().getPos()[0]);
		assertEquals(1, level.getHero().getPos()[1]);
		
		level.update(0, 1);
		assertEquals(7, level.getHero().getPos()[0]);
		assertEquals(2, level.getHero().getPos()[1]);
		
		level.update(1, 0);
		assertEquals(7, level.getHero().getPos()[0]);
		assertEquals(2, level.getHero().getPos()[1]);
		
	}
}
