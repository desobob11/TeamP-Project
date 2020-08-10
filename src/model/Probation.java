package model;
import java.util.ArrayList;

import application.UI;

/**
 * This is a probation tile, which traps the user in probation for 3 turns and forces them to pay $250 per turn.
 * @author Arnuv Mayank
 *
 */

public class Probation extends Tile {
	private int probationCost = 250;
	
	public Probation(int position) {
		super(position, "Probation");
	}
	
	public Probation(int position, int probationCost) {
		super(position, "Probation");
		this.probationCost = probationCost;
	}
	
	private int probationPayment(Student student) {
		//puts them in jail in case they just arrived
		student.goToJail();
		//should only increment the students turns in probation once per turn
		if (!this.getPerformedTileAction()) {	
			student.incrementTurnsInProbation();
		}
		return student.withdrawMoney(probationCost);
	}
	
	public int getProbationCost() {
		return probationCost;
	}

	@Override
	public int performTileAction(Student student, ArrayList<Student> students, UI UI, CourseList courseList) {
		// TODO Auto-generated method stub
		//if they were already in jail and the start of the turn, then tell them they're still in jail
		if (student.isInJail()) {
			UI.displayStillInJail(student);
		} else {
			//otherwise, we must check if they just arrived at jail or are exiting this turn if they can pay
			if (!this.getPerformedTileAction())	{
				UI.displayLandedInProbation(student);
			} else {
				return student.withdrawMoney(probationCost);
			}
		}
		UI.displayInProbation(student, this);
		
		return this.probationPayment(student);
	}
}
