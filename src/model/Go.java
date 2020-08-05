package model;
import java.util.ArrayList;

import Application.UI;

/**
 * This is the Go tile, the simplest child of Tile. It simply gives the student
 * a fixed amount for landing on it, and, by definition, is always at position 0
 * 
 * @author Desmond O'Brien
 *
 */
public class Go extends Tile {

	private int landDeposit = 200;

	public Go() {
		super(0, "Go");
	}

	
	
	@Override
	public int performTileAction(Student student, ArrayList<Student> students, UI UI, CourseList courseList) {
		// TODO Auto-generated method stub
		UI.displayOnGo(landDeposit);
		student.depositMoney(landDeposit);
		return 1;
	}

}
