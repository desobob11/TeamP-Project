package model;
import java.util.ArrayList;

import application.UI;

/**
 * Represents a piece on the board. There will never be an instance
 * of Tile itself, but rather one of its many children.
 * 
 * @author Desmond O'Brien 
 * @author Arnuv Mayank
 *
 */
public abstract class Tile {
	private int tileID;
	private String tileName;
	//this indicates if the tile action has been performed this turn yet
	private boolean performedTileAction = false;

	/**
	 * Constructs a new Tile.
	 */
	public Tile() {
	}

	/**
	 * Constructs a new Tile with a specified tile ID and tile name.
	 *
	 * @param tileID the desired ID of the tile.
	 * @param tileName the desired name of the tile.
	 */
	public Tile(int tileID, String tileName) {
		this.tileID = tileID;
		this.tileName = tileName;
	}

	/**
	 * Fetches the ID of the Tile.
	 * 
	 * @return tile ID.
	 */
	public int getTileID() {
		return tileID;
	}

	/**
	 * Fetches the name of the Tile.
	 * 
	 * @return tile name.
	 */
	public String getTileName() {
		return tileName;
	}

	/**
	 * Checks if the tile action was completed.
	 * 
	 * @return Truth value of check.
	 */
	public boolean getPerformedTileAction() {
		return performedTileAction;
	}
	
	/**
	 * Sets the truth value of the complete Tile action check.
	 * 
	 * @param performedTileAction the desired truth value.
	 */
	public void setPerformedTileAction(boolean performedTileAction) {
		this.performedTileAction = performedTileAction;
	}
	
	/**
	 * Abstract class to perform all actions of the Tile.
	 * 
	 * @param student the student on the Tile.
	 * @param students the list of students on the board.
	 * @param UI the UI to modify.
	 * @param courseList the list of tiles on the board.
	 * 
	 * @return the numerical confirmation of tile action: 1 (if action can be completed), -1 (if player has not enough money
	 * and courses), or -2 (if player could sell courses to get enough money).
	 */
	//this method is abstract because each type of tile will have a different action to perform, so it will be defined separately there
	public abstract int performTileAction(Student student, ArrayList<Student> students, UI UI, CourseList courseList);
	
}
