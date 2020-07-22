import java.util.ArrayList;

public class Chance extends Tile {
	String[] chanceOptions = {
            "SU Donation: Pay $100",
            "Grants have been doubled everyone collect $300",
            "Your offered a ride from your friend, move to the nearest free parking",
            "Your student loans have caught up with you, pay $250",
            "You withdrew from a course, past the deadline! Pay $150",
            "You have been caught for cheating, move to academic probation",
    };
	
	public Chance(int position) {
		super(position, "Chance");
	}

	public String[] getChanceOptions() {
		return chanceOptions;
	}

	public int performChanceOption(int selection, Student studentOn, ArrayList<Student> allStudents, int parking1Pos,
			Integer parking2Pos, int probationPos) {
		switch (selection) {
		case 1:
			return studentOn.withdrawMoney(100);
		case 2:
			for (Student student: allStudents) {
				student.depositMoney(300);
			}
			return 1;
		case 3:
			studentOn.moveToClosestParking(parking1Pos, parking2Pos);
			return 1;
		case 4:
			return studentOn.withdrawMoney(250);
		case 5:
			return studentOn.withdrawMoney(150);
		case 6:
			studentOn.moveToProbation(probationPos);
			return 1;
		}
		return 1;
	}
	
	


}
