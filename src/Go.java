
public class Go extends Tile {

	private int landDeposit = 200;
	
	public Go() {
		super(0, "Go");
	}
	
	
	public void depositGoAmmount(Student student) {
		student.depositMoney(landDeposit);
	}
	
}
