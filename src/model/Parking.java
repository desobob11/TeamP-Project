package model;
import java.util.ArrayList;

import Application.UI;

/**
 * This is a parking tile, which forces the student to pay a fixed fee and ends their turn.
 * @author Arnuv Mayank and Desmond O'Brien
 *
 */

public class Parking extends Tile {
	private int parkingCost = 250;
	
	public Parking(int position) {
		super(position, "Parking");
	}
	
	public Parking (int position, int parkingCost) {
		super(position, "Parking");
		this.parkingCost = parkingCost;
	}
	
	public int payParkingFee(Student student) {
		return student.withdrawMoney(parkingCost);
	}
	
	public int getParkingCost() {
		return parkingCost;
	}

	@Override
	public int performTileAction(Student student, ArrayList<Student> students, UI UI, CourseList courseList) {
		// TODO Auto-generated method stub
		UI.displayInParking(student, this);
		return this.payParkingFee(student);
	}
}