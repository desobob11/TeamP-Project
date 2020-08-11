package model;
import java.util.ArrayList;

import application.UI;

/**
 * This is the Go tile, the simplest child of Tile. It simply gives the student
 * a fixed amount for landing on it, and, by definition, is always at position 0
 * 
 * @author Desmond O'Brien
 *
 */
public class Go extends Tile {

	private int landDeposit = 200;

	/**
	* Constructs a new Go tile.
	*/
	public Go() {
		super(0, "Go");
	}

	
	/**
	* Calls the appropriate methods for the Go tile to deposit money into specified student's account and display the result.
	* 
	* @param student the student who landed on the tile.
	* @param students the list of students in the game.
	* @param UI the UI to modify.
	* @param courseList the list of tiles on the board.
	*
	* @return Numerical confirmation of action completed: (1) if completed.
	*/
	@Override
	public int performTileAction(Student student, ArrayList<Student> students, UI UI, CourseList courseList) {
		UI.displayOnGo(landDeposit);
		student.depositMoney(landDeposit);
		return 1;
	}

}
