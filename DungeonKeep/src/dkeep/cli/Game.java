package dkeep.cli;
import java.util.Scanner;
import dkeep.logic.*;

public final class Game {

	private static Level level;
	private static Scanner input;
	
	public static void main(String[] args) {
		level = new LevelOne();
		input = new Scanner(System.in);
		
		while (updateGame());
	}

	private static boolean updateGame() {
		
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
		
		Level.state ret = level.update(hero_move[0], hero_move[1]);
		level.draw();
		if ( ret == Level.state.WON )
			level = level.nextLevel();
		
		return ((level != null) && ret == Level.state.RUNNING);
	}
}
