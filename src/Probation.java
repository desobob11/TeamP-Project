import java.util.ArrayList;

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
	
	public int probationPayment(Student student) {
		student.goToJail();
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
		if (student.isInJail()) {
			UI.displayStillInJail(student);
		} else {
			UI.displayLandedInProbation(student);
		}
		UI.displayInProbation(student, this);
		
		return this.probationPayment(student);
	}
}
