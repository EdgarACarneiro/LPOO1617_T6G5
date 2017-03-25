package dkeep.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Test;

import dkeep.logic.GameCharacter;
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
		
		LevelTwo level = new LevelTwo (board, victory_pos, enemy_activity, hero_armed);
		GameCharacter ogre = level.getEnemies().get(0);
		
		boolean movedLeft = false, movedRight = false, movedUp = false, movedDown = false;
		
		while( !movedLeft || !movedRight || !movedUp || !movedDown)  {
			
			int[] oPos = new int[] {ogre.getPos()[0], ogre.getPos()[1]};
			level.update(0 , 1);

			if(ogre.isAt(new int [] {oPos[0] - 1, oPos[1]}))
				movedLeft = true;
			
			else if(ogre.isAt(new int [] {oPos[0] + 1, oPos[1]}))
				movedRight = true;
			
			else if(ogre.isAt(new int [] {oPos[0], oPos[1] - 1}))
				movedUp = true;
			
			else if(ogre.isAt(new int [] {oPos[0], oPos[1] + 1}))
				movedDown = true;
			
			else 
				fail("Ogre's move is unexpected!\n");
		}
	}
	
	@Test(timeout=1000)
	public void testClubRandomBehaviour() {
		
		LevelTwo level = new LevelTwo (board1, victory_pos, enemy_activity, hero_armed);
		GameCharacter ogre = level.getEnemies().get(0);
		
		boolean swingedLeft = false, swingedRight = false, swingedUp = false, swingedDown = false;
		
		while( !swingedLeft || !swingedRight || !swingedUp || !swingedDown)  {
			
			level.update(0 , 1);
			int[] oPos = new int[] {ogre.getPos()[0], ogre.getPos()[1]};
				
			if(Arrays.equals(((Ogre) ogre).getClubPos(),(new int [] {oPos[0] - 1, oPos[1]})))
				swingedLeft = true;
			
			else if(Arrays.equals(((Ogre) ogre).getClubPos(),(new int [] {oPos[0] + 1, oPos[1]})))
				swingedRight = true;
			
			else if(Arrays.equals(((Ogre) ogre).getClubPos(),(new int [] {oPos[0], oPos[1] - 1})))
				swingedUp = true;
			
			else if(Arrays.equals(((Ogre) ogre).getClubPos(),(new int [] {oPos[0], oPos[1] + 1})))
				swingedDown = true;
			
			else
				fail("Swinged club to unexpected position!\n");
		}
	}
	
	@Test
	public void testDraw() {
		
		LevelTwo level = new LevelTwo (board, victory_pos, enemy_activity, hero_armed);
		
		//Draw does not crash even with ogre moving -> No other way to test draw.
		
		level.draw();
		assertEquals(State.RUNNING, level.update(1, 0));
		level.draw();
		assertEquals(State.RUNNING,level.update(-1, 0));
	}
	
	@Test
	public void testVariableNumberOgres() {
		
		int numOgres;
		int validate;
		LevelTwo level;
		
		//Test for 0 Ogres
		numOgres = 0;
		validate = 0;
		level = new LevelTwo(numOgres);
		
		for (int i = 0; i < level.getEnemies().size(); ++i) {
			if (level.getEnemies().get(i) instanceof Ogre)
				++validate;
		}
		assertEquals(numOgres, validate);
		
		//Test for 1 Ogre
		numOgres = 1;
		validate = 0;
		level = new LevelTwo(numOgres);
		
		for (int i = 0; i < level.getEnemies().size(); ++i) {
			if (level.getEnemies().get(i) instanceof Ogre)
				++validate;
		}
		assertEquals(numOgres, validate);
		
		//Test for 2 Ogres
		numOgres = 2;
		validate = 0;
		level = new LevelTwo(numOgres);
		
		for (int i = 0; i < level.getEnemies().size(); ++i) {
			if (level.getEnemies().get(i) instanceof Ogre)
				++validate;
		}
		assertEquals(numOgres, validate);
		
		//Test for 3 Ogres
		numOgres = 3;
		validate = 0;
		level = new LevelTwo(numOgres);
		
		for (int i = 0; i < level.getEnemies().size(); ++i) {
			if (level.getEnemies().get(i) instanceof Ogre)
				++validate;
		}
		assertEquals(numOgres, validate);
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
