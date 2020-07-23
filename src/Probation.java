
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
		student.incrementTurnsInProbation();
		return student.withdrawMoney(probationCost);
	}
}
