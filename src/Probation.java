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
	
	public int probationPayment(Student student, int counter) {
		student.goToJail();
		if (counter == 1) {	
			student.incrementTurnsInProbation();
		}
		return student.withdrawMoney(probationCost);
	}
	
	public int getProbationCost() {
		return probationCost;
	}
}
