package model;
import java.util.ArrayList;

import Application.UI;

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
	
	public abstract int performTileAction(Student student, ArrayList<Student> students, UI UI, CourseList courseList);
	
}
