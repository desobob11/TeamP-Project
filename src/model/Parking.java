package model;
import java.util.ArrayList;

import application.UI;

/**
 * This is a parking tile, which forces the student to pay a fixed fee and ends their turn.
 * @author Arnuv Mayank 
 * @author Desmond O'Brien
 */
public class Parking extends Tile {
	private int parkingCost = 250;
	
	/**
	 * Constructs a new Parking tile with specified position.
	 * 
	 * @param position the desired position.
	 */
	public Parking(int position) {
		super(position, "Parking");
	}
	
	/**
	 * Constructs a new Parking tile with a specified position and specified parking cost.
	 * 
	 * @param position the desired position
	 * @param parkingCost the desired parking cost.
	 */
	public Parking (int position, int parkingCost) {
		super(position, "Parking");
		this.parkingCost = parkingCost;
	}
	
	/**
	 * Takes the parking fee from the Student.
	 * 
	 * @param student the Student who landed on Parking.
	 * @return numerical confirmation of withdrawal from {@code withdrawMoney}
	 */
	public int payParkingFee(Student student) {
		//parking is just a simple payment
		return student.withdrawMoney(parkingCost);
	}
	
	/**
	 * Fetches the cost of Parking.
	 * 
	 * @return the parking cost
	 */
	public int getParkingCost() {
		return parkingCost;
	}

	/**
	 * Calls the appropriate methods to complete full Parking tile action.
	 * 
	 * @param student the student on the Parking tile.
	 * @param students the list of students on the board.
	 * @param UI the UI to modify.
	 * @param courseList the list of tiles on the board.
	 * 
	 * @return the numerical confirmation of tile action: 1 (if action can be completed), -1 (if player has not enough money
	 * and courses), or -2 (if player could sell courses to get enough money).
	 */
	@Override
	public int performTileAction(Student student, ArrayList<Student> students, UI UI, CourseList courseList) {
		// TODO Auto-generated method stub
		UI.displayInParking(student, this);
		return this.payParkingFee(student);
	}
}
