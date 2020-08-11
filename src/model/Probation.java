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
	
	/**
	 * Constructs a new Probation with specified position.
	 * 
	 * @param position the desired position on the board.
	 */
	public Probation(int position) {
		super(position, "Probation");
	}
	
	/**
	 * Constructs a new Probation with a specified position and a specified
	 * probation cost.
	 * 
	 * @param position      the desired position on the board.
	 * @param probationCost the desired cost of landing on Probation.
	 */
	public Probation(int position, int probationCost) {
		super(position, "Probation");
		this.probationCost = probationCost;
	}
	
	/**
	 * Removes the Probation cost from the bank of the player in Probation.
	 * 
	 * @param student the Student in Probation.
	 * 
	 * @return numerical confirmation of withdrawal from {@code withdrawMoney}
	 */
	private int probationPayment(Student student) {
		//puts them in jail in case they just arrived
		student.goToJail();
		//should only increment the students turns in probation once per turn
		if (!this.getPerformedTileAction()) {	
			student.incrementTurnsInProbation();
		}
		return student.withdrawMoney(probationCost);
	}
	
	/**
	 * Fetches the cost of landing on Probation.
	 * 
	 * @return cost of Probation.
	 */
	public int getProbationCost() {
		return probationCost;
	}

	/**
	 * Calls the appropriate methods to complete full Probation tile action.
	 * 
	 * @param student the student on the Probation tile.
	 * @param students the list of students on the board.
	 * @param UI the UI to modify.
	 * @param courseList the list of tiles on the board.
	 * 
	 * @return the numerical confirmation of tile action: 1 (if action can be completed), -1 (if player has not enough money
	 * and courses), or -2 (if player could sell courses to get enough money).
	 */
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
