package dkeep.cli;
import java.util.Scanner;

import dkeep.logic.Level;
import dkeep.logic.LevelOne;

public final class Game {

	private static Level level;
	private static Scanner input;
	
	public static void main(String[] args) {
		level = new LevelOne();
		//level = new LevelTwo();
		input = new Scanner(System.in);
		
		while (updateGame());
		level.draw();
	}

	private static boolean updateGame() {
		
		level.draw();
		
		String s = input.next();
		int[] hero_move = {0, 0};
		
		switch (s.toUpperCase()) {
		case "W":
			hero_move[0] = -1;
			break;
		case "A":
			hero_move[1] = -1;
			break;
		case "S":
			hero_move[0] = 1;
			break;
		case "D":
			hero_move[1] = 1;
			break;
		default:
			System.out.println("Invalid input! Was: " + s);
		}
		
		Level.State ret = level.update(hero_move[0], hero_move[1]);
		
		if ( ret == Level.State.WON ) {
			Level new_level;
			if ( (new_level = level.nextLevel()) == null )
				return false;
			else
				level = new_level;
		}

		
		return ret != Level.State.LOST;
	}
}
