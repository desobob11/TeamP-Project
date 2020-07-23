import java.util.ArrayList;

public class Chance extends Tile {
	String[] chanceOptions = { "SU Donation: Pay $100", "Grants have been doubled everyone collect $300",
			"Your offered a ride from your friend, move to the nearest free parking",
			"Your student loans have caught up with you, pay $250",
			"You withdrew from a course, past the deadline! Pay $150",
			"You have been caught for cheating, move to academic probation", };

	public Chance(int position) {
		super(position, "Chance");
	}

	public String[] getChanceOptions() {
		return chanceOptions;
	}

	public int performChanceOption(int selection, Student studentOn, ArrayList<Student> allStudents,
			ArrayList<Parking> parkingTiles, Probation probationTile) {
		switch (selection) {
		case 1:
			return studentOn.withdrawMoney(100);
		case 2:
			for (Student student : allStudents) {
				student.depositMoney(300);
			}
			return 1;
		case 3:
			int tileToMoveTo = studentOn.moveToClosestParking(parkingTiles.get(0).getTileID(), parkingTiles.get(1).getTileID());
			return parkingTiles.get(tileToMoveTo).payParkingFee(studentOn);
		case 4:
			return studentOn.withdrawMoney(250);
		case 5:
			return studentOn.withdrawMoney(150);
		case 6:
			studentOn.moveToProbation(probationTile.getTileID());
			return 1;
		}
		return 1;
	}

}
