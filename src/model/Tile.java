package model;
import java.util.ArrayList;

import application.UI;

/**
 * Tile - this represents a piece on the board. There will never be an instance
 * of Tile itself, but rather one of its many children.
 * 
 * @author Desmond O'Brien and Arnuv Mayank
 *
 */
public abstract class Tile {
	private int tileID;
	private String tileName;
	//this indicates if the tile action has been performed this turn yet
	private boolean performedTileAction = false;

	public Tile() {

	}

	public Tile(int tileID, String tileName) {
		this.tileID = tileID;
		this.tileName = tileName;
	}

	public int getTileID() {
		return tileID;
	}

	public String getTileName() {
		return tileName;
	}

	public boolean getPerformedTileAction() {
		return performedTileAction;
	}
	
	public void setPerformedTileAction(boolean performedTileAction) {
		this.performedTileAction = performedTileAction;
	}
	
	//this method is abstract because each type of tile will have a different action to perform, so it will be defined separately there
	public abstract int performTileAction(Student student, ArrayList<Student> students, UI UI, CourseList courseList);
	
}
