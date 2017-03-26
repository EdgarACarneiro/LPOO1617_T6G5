package dkeep.logic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * Class responsible for handling lower-level game state handling.
 */
public class GameHandler implements java.io.Serializable {
	
	/**
	 * Inner class to encapsulate optional arguments for extra functionality:
	 * updates a status label's text and disables/enables buttons appropriately.
	 */
	public static class OptionalArgs {
		
		/**
		 * Label to be changed on game state changes.
		 */
		public JLabel statusLbl;
		
		/**
		 * Buttons to be enabled/disabled according to game state.
		 */
		public JButton[] movBtns;

		/**
		 * Constructor w/ optional number of JButtons as arguments
		 * 
		 * @param statusLbl Status Label
		 * @param movBtns Gameplay buttons
		 */
		public OptionalArgs(JLabel statusLbl, JButton... movBtns) {
			this.statusLbl = statusLbl;
			this.movBtns = movBtns;
		}
		
		public void enableBtns() {
			for (JButton btn : movBtns)
				btn.setEnabled(true);
		}
		
		public void disableBtns() {
			for (JButton btn : movBtns)
				btn.setEnabled(false);
		}
	}
	
	/**
	 * long SerialVersionUID. Class's ID for serialization.
	 */
	private static final long serialVersionUID = 2L;
	
	/**
	 * File path for serialization.
	 */
	private static final String gameFile = "savedGames/Game01.ser";
	
	/**
	 * Level currently in play.
	 */
	private Level level;
	
	/**
	 * Int representing the current level's number.
	 */
	private int current_lvl;
	
	/**
	 * Status Information regarding the game's state.
	 */
	private String statusInfo;
	
	/**
	 * OptionalArgs object for extra functionality.
	 */
	private OptionalArgs optArgs = null;

	
	/**
	 * Chosen guard's personality. Saved parameter for LevelOne.
	 */
	private Guard.Personality gp;
	
	/**
	 * Number of Ogres for LevelTwo.
	 */
	private int numOgres;

	/**
	 * Constructor with specific parameters for each level.
	 * @param gp	Guard's personality
	 * @param numOgres	Number of Ogres
	 */
	public GameHandler(Guard.Personality gp, int numOgres) {
		this.gp = gp;
		this.numOgres = numOgres;
		
		current_lvl = 1;
		level = new LevelOne(this.gp);
	}
	
	/**
	 * Constructor with already constructed level.
	 * @param l Level to be played.
	 */
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
	
	/**
	 * Default constructor with serialization.
	 * Used to load a previously saved GameHandler.
	 */
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

	  } catch(IOException i) {
		 i.printStackTrace();
         return;
      } catch(ClassNotFoundException c) {
         c.printStackTrace();
         return;
      }
	}
	
	/**
	 * Sets self OptionalArgs thus activating extra functionality.
	 * @param args	OptionalArgs properly populated
	 */
	public void setOptionalArgs(OptionalArgs args) {
		optArgs = args;
		
		if (level == null || args == null)
			return;
		
		if (optArgs.movBtns != null)
			optArgs.enableBtns();
		
		if (optArgs.statusLbl != null)
			optArgs.statusLbl.setText("New Game!");
	}
	
	/**
	 * Updates current level and status label.
	 * @return whether current level is still in play.
	 */
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
	
	/**
	 * Updates current level with given hero movement.
	 * Appropriately sets the status and enables/disables the gameplay buttons.
	 * 
	 * @param row Rows hero moved.
	 * @param col Columns hero moved.
	 * @return whether current level is still in play.
	 */
	public boolean update(int row, int col) {
		Level.State state = level.update(row, col);
		boolean ret = true;
		
		switch (state) {
		case RUNNING:
			statusInfo = "You can play now.";
			break;
		case WON:
			ret = updateLevel();
			break;
		case LOST:
			statusInfo = "Game Over.";
			ret = false;
		}
		
		if (optArgs != null) {
			optArgs.statusLbl.setText(statusInfo);
			if (! ret)
				optArgs.disableBtns();
		}
		
		return ret;
	}
		
	/**
	 * Saves the current game session.
	 */
	public void saveGame() {
		try {
	        FileOutputStream fileOut = new FileOutputStream(gameFile);
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(this);
	        out.close();
	        fileOut.close();
	        
	      } catch(IOException i) {
	         i.printStackTrace();
	      }
	}
	
	/**
	 * Getter method for level's characters.
	 * @return List of characters in play.
	 */
	public ArrayList<GameCharacter> getCharacters() {
		if (level != null)
			return level.getCharacters();
		else
			return null;
	}
	
	/**
	 * Getter method for the current Map.
	 * @return The current map in a 2D char array.
	 */
	public char[][] getMap() {
		if (level != null)
			return level.getMap();
		
		System.err.println("GH NULL LEVEL -- map (char[][]) requested!");
		return null;
	}
	
}
	