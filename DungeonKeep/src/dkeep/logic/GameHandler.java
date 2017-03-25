package dkeep.logic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GameHandler implements java.io.Serializable {
	
	private static final long serialVersionUID = 2L;
	
	private static final String gameFile = "savedGames/Game01.ser";
	
	private Level level;
	private int current_lvl;
	private String statusInfo;
	
	private Guard.Personality gp;
	private int numOgres;

	public GameHandler(Guard.Personality gp, int numOgres) {
		this.gp = gp;
		this.numOgres = numOgres;
		
		current_lvl = 1;
		level = new LevelOne(this.gp);
	}
	
	public GameHandler(Level l) {
		if (l == null) {
			System.err.println("GH constructor called with null Level");
			return;
		}
		
		level = l;
		
		if (level instanceof LevelOne) {
			current_lvl = 1;
			numOgres = 3;
		} else if (level instanceof LevelTwo) {
			current_lvl = 2;
		} else
			System.err.println("INVALID LEVEL");
	}
	
	public GameHandler() {
		GameHandler game = null;
	     
      try {
         FileInputStream fileIn = new FileInputStream(gameFile);
         ObjectInputStream in = new ObjectInputStream(fileIn);
         game = (GameHandler) in.readObject();
         in.close();
         fileIn.close();
         
         if (game == null) {
        	 throw new ClassNotFoundException();
         } else {
	         //Creating current game
	         this.level = game.level;
	         this.gp = game.gp;
	         this.current_lvl = game.current_lvl;
	         this.numOgres = game.numOgres;
	         this.statusInfo = game.statusInfo;
         }

	  }catch(IOException i) {
		 i.printStackTrace();
         return;
      }catch(ClassNotFoundException c) {
         c.printStackTrace();
         return;
      }
	}
	
	private boolean updateLevel() {
		switch (current_lvl) {	
		case 1:
			statusInfo = "New Level reached!";
			++current_lvl;
			level = new LevelTwo(numOgres);
			break;
		
		case 2:
			statusInfo = "You Won! Congratulations!";
			current_lvl = 0;
			return false;
		}
		
		return true;
	}
	
	public boolean update(int row, int col) {
		Level.State state = level.update(row, col);
		
		switch (state) {
		case RUNNING:
			statusInfo = "You can play now.";
			break;

		case WON:
			return(updateLevel());
			
		case LOST:
			statusInfo = "Game Over.";
			return false;
		}
		
		return true;
	}
		
	//Saving the current Game session
	public void saveGame() {
		try {
	        FileOutputStream fileOut = new FileOutputStream(gameFile);
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(this);
	        out.close();
	        fileOut.close();
	        
	      }catch(IOException i) {
	    	 //System.err.println("Unable to save GameHandler");
	         i.printStackTrace();
	      }
	}	
	
	public String getStatusInfo() {
		return statusInfo;
	}
	
	public ArrayList<GameCharacter> getCharacters() {
		if (level != null)
			return level.getCharacters();
		else
			return null;
	}
	
	public char[][] getMap() {
		if (level != null)
			return level.getMap();
		
		System.err.println("GH NULL LEVEL - map (char[][]) requested!");
		return null;
	}
	
}
	