
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

	public void depositGoAmmount(Student student) {
		student.depositMoney(landDeposit);
	}

}
