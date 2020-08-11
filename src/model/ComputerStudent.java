package model;

import java.util.ArrayList;
import application.UI;


/**
 * An AI computer player that chooses game actions during gameplay.
 * 
 * @author Arnuv Mayank 
 *
 */
public class ComputerStudent extends Student {

	/**
	 * Constructs a new ComputerStudent.
	 */
	public ComputerStudent() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a new ComputerStudent with a specified player number and money.
	 * 
	 * @param playerNumber The assigned player number.
	 * @param playerMoney The amount of money to start with.
	 */
	public ComputerStudent(int playerNumber, int playerMoney) {
		super(playerNumber, playerMoney);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Automatically selects primary action for turn. Will return one of: continue with turn, upgrade faculties or sell courses owned.
	 * 
	 * @return The numerical representation of the action chosen.
	 */
	@Override
	public int studentInitialOption(UI UI) {
		//AI algorithm: never sell courses unless you need to, but if you have enough money to upgrade a faculty, always do it
		ArrayList<ArrayList<Course>> upgradableFaculties = this.getUpgradableFaculties();
		//checking if there are faculties to upgrade
		if (upgradableFaculties.size() != 0) {
			for (ArrayList<Course> facultyList : upgradableFaculties) {
				//checking if they have sufficient money to upgrade a faculty
				if (facultyList.get(0).getUpgradeCost() <= this.getPlayerMoney()) {
					return 3;
				}
			}
		}
		return 1;
	}

	
	/**
	 * Sells a course owned by the AI and updates the screen.
	 * 
	 * @param UI The UI to modify.
	 * @param courseList The list of courses on the board.
	 */
	@Override
	public void studentSellCourse(UI UI, CourseList courseList) {
		// TODO Auto-generated method stub
		//AI algorithm: only brought to this menu if FORCED to sell courses
		//if this is the case, sell all of them
		while (!this.getCoursesOwned().isEmpty()) {
			Course course = this.getCoursesOwned().get(0);
			this.sellCourse(course);
			courseList.removeFromCoursesOwned(course);
			UI.displaySuccessfulSell(this, course);
		}
	}

	/**
	 * Chooses whether AI buys a course.
	 *  
	 * @param UI The UI to modify.
	 * @param aCourse The course to be bought.
	 */
	@Override
	public boolean studentBuyCourse(UI UI, Course aCourse) {
		// TODO Auto-generated method stub
		//AI algorithm: only buy a course if you have sufficient money, never sell a course to buy another
		return (this.getPlayerMoney() >= aCourse.getBuyPrice() ? true : false);
	}

	
	/**
	 * Chooses whether AI upgrades faculties in its possession.
	 * 
	 * @param UI The UI to modify.
	 *
	 * @return String of faculty name to upgrade or "-1" if they choose not to upgrade
	 */
	@Override
	public String studentUpgradeFaculty(UI UI) {
		// TODO Auto-generated method stub
		//AI algorithm: if you have sufficient money to upgrade a faculty do it
		ArrayList<ArrayList<Course>> upgradableFaculties = this.getUpgradableFaculties();
		//initial options in Logic already confirms that there are upgradable faculties
		for (ArrayList<Course> facultyList : upgradableFaculties) {
			//checking if there is enough money to upgrade the faculty
			if (facultyList.get(0).getUpgradeCost() <= this.getPlayerMoney()) {
				return facultyList.get(0).getFaculty();
			}
		}
		return "-1";
	}

}
