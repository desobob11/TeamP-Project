package model;
import java.util.ArrayList;
import java.util.Random;

import application.UI;

/**
 * This is a Chance Tile that randomly chooses something for the student to do.
 * @author Arnuv Mayank and Victor Manuel Campos Goitia Campos
 *
 */

public class Chance extends Tile {
	String[] chanceOptions = { "SU Donation: Pay $100", "Grants have been doubled everyone collect $300",
			"Your offered a ride from your friend, move to the nearest free parking",
			"Your student loans have caught up with you, pay $250",
			"You withdrew from a course, past the deadline! Pay $150",
			"You have been caught for cheating, move to academic probation", };

	private int randChance;
	
	public Chance(int position) {
		super(position, "Chance");
	}

	public String[] getChanceOptions() {
		return chanceOptions;
	}

	public int performChanceOption(int selection, Student studentOn, ArrayList<Student> allStudents,
			ArrayList<Parking> parkingTiles, Probation probationTile, UI UI) {
		switch (selection) {
		case 0:
			return studentOn.withdrawMoney(100);
		case 1:
			for (Student student : allStudents) {
				student.depositMoney(300);
			}
			return 1;
		case 2:
			int tileToMoveTo = studentOn.moveToClosestParking(parkingTiles.get(0).getTileID(), parkingTiles.get(1).getTileID());
			UI.updateBoard(studentOn);
			return parkingTiles.get(tileToMoveTo).payParkingFee(studentOn);
		case 3:
			return studentOn.withdrawMoney(250);
		case 4:
			return studentOn.withdrawMoney(150);
		case 5:
			studentOn.moveToProbation(probationTile.getTileID());
			UI.updateBoard(studentOn);
			return 1;
		}
		return 1;
	}

	@Override
	public int performTileAction(Student student, ArrayList<Student> students, UI UI, CourseList courseList) {
		if (!this.getPerformedTileAction()) {
			randChance = new Random().nextInt(this.getChanceOptions().length);
		}
		UI.displayChanceOption(this, randChance);
		return this.performChanceOption(randChance, student, students, courseList.getParkingTiles(), courseList.getProbation(), UI);
	}

}
