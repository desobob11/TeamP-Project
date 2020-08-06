import java.util.ArrayList;
import java.util.Random;

/**
 * This is a Chance Tile, called when the player lands on the tile of type
 * "Chance". Draws a Chance card at random and with a specific impact on the
 * player's game.
 * 
 * @author Arnuv Mayank
 * @author Victor Manuel Campos Goitia Campos
 *
 */
public class Chance extends Tile {
	String[] chanceOptions = { "SU Donation: Pay $100", "Grants have been doubled everyone collect $300",
			"Your offered a ride from your friend, move to the nearest free parking",
			"Your student loans have caught up with you, pay $250",
			"You withdrew from a course, past the deadline! Pay $150",
			"You have been caught for cheating, move to academic probation", };

	private int randChance;

	/**
	 * Creates a Tile object called Chance.
	 * 
	 * @param position Tile number on the board.
	 */
	public Chance(int position) {
		super(position, "Chance");
	}

	/**
	 * Fetches all strings of text telling user what card they've gotten.
	 * 
	 * @return The string array with chance messages.
	 */
	public String[] getChanceOptions() {
		return chanceOptions;
	}

	/**
	 * Calls the appropriate methods to act on each possible Chance card.
	 * 
	 * @param selection The number referencing which card case should be used.
	 * 
	 * @param studentOn The student currently invoking Chance.
	 * 
	 * @param allStudents The list of all students.
	 * 
	 * @param parkingTiles The list of Tiles of type "Parking".
	 * 
	 * @param probationTile The Tile of type "Probation".
	 * 
	 * @return 1 (if action can be completed), -1 (if player has not enough money
	 * and courses), or -2 (if player could sell courses to get enough money).
	 */
	public int performChanceOption(int selection, Student studentOn, ArrayList<Student> allStudents,
			ArrayList<Parking> parkingTiles, Probation probationTile) {
		switch (selection) {
		case 0:
			return studentOn.withdrawMoney(100);
		case 1:
			for (Student student : allStudents) {
				student.depositMoney(300);
			}
			return 1;
		case 2:
			int tileToMoveTo = studentOn.moveToClosestParking(parkingTiles.get(0).getTileID(),
					parkingTiles.get(1).getTileID());
			return parkingTiles.get(tileToMoveTo).payParkingFee(studentOn);
		case 3:
			return studentOn.withdrawMoney(250);
		case 4:
			return studentOn.withdrawMoney(150);
		case 5:
			studentOn.moveToProbation(probationTile.getTileID());
			return 1;
		}
		return 1;
	}

	/**
	 * Invokes the performChanceOption method to perform the Tile action of the Tile
	 * type "Chance".
	 * 
	 * @param student The current student invoking Chance.
	 * 
	 * @param students The list of all students on the board.
	 * 
	 * @param UI The UI object currently running.
	 * 
	 * @param courseList The courseList object currently handling the board info.
	 * 
	 * @return 1 (if action can be completed), -1 (if player has not enough money
	 * and courses), or -2 (if player could sell courses to get enough money).
	 */
	@Override
	public int performTileAction(Student student, ArrayList<Student> students, UI UI, CourseList courseList) {
		if (!this.getPerformedTileAction()) {
			randChance = new Random().nextInt(this.getChanceOptions().length);
		}
		UI.displayChanceOption(this, randChance);
		return this.performChanceOption(randChance, student, students, courseList.getParkingTiles(),
				courseList.getProbation());
	}

}
