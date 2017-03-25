package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.GameCharacter;
import dkeep.logic.GameHandler;
import dkeep.logic.Guard;

public class testGameHandlerBehaviour {

	@Test
	public void testSaveLoadFunctions() {
		
		Guard.Personality gp = Guard.Personality.DRUNKEN;
		int numOgres = 2;
		
		GameHandler gHandler = new GameHandler(gp, numOgres);
		
		//Level1 with Dungeon Map was created
		gHandler.update(0,1);
		gHandler.update(0, 1);
		GameCharacter hero = gHandler.getCharacters().get(0);
		
		gHandler.saveGame();
		
		GameHandler loadedGame = new GameHandler();
		GameCharacter loadedHero = gHandler.getCharacters().get(0);
		
		assertEquals(hero.getPos()[0], loadedHero.getPos()[0]);
		assertEquals(hero.getPos()[1], loadedHero.getPos()[1]);
	}
}
