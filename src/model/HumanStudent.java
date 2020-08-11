package model;
import java.util.Objects;

import application.UI;

/**
 * HumanStudent - a child of Student that implements all of its abstract methods for a human
 * 
 * @author Arnuv Mayank
 *
 */
public class HumanStudent extends Student {

	/**
	 * Constructor for HumanStudent
	 * 
	 * @param playerNumber the player's number
	 * @param playerMoney the player's starting money
	 */
	public HumanStudent(int playerNumber, int playerMoney) {
		// TODO Auto-generated constructor stub
		//invokes Student constructor
		super(playerNumber, playerMoney);
	}

	/**
	 * Let's the student choose which of their available initial options they'd like to choose
	 * Proceed, sell course, or upgrade faculty
	 * 
	 * @param UI Logic's instance of UI for this to use
	 * 
	 * @return 1 for proceed, 2 for sell courses, 3 for upgrade faculty
	 */
	@Override
	public int studentInitialOption(UI UI) {
		// TODO Auto-generated method stub
		return UI.initialOptions(this.doesStudentOwnProperty(), this.getUpgradableFaculties());
	}

	/**
	 * The human student's menu for selling a course should they own any
	 * 
	 * @param UI Logic's instance of UI for this to use
	 * 
	 * @param courseList Logic's instance of CourseList for this to use
	 * 
	 * @return true for successfully selling, false for not
	 */
	private boolean sellACourse(UI UI, CourseList courseList) {
		boolean ownsProperty = this.doesStudentOwnProperty();
		//can only sell a course if they own it
		if (ownsProperty) {
			Course courseToSell = UI.sellCourseMenu(this);
			//the UI will return a null Course if they opt to not sell any courses at the menu
			//so it must ensure that they have chosen a course to sell
			if (Objects.nonNull(courseToSell)) {
				this.sellCourse(courseToSell);
				courseList.removeFromCoursesOwned(courseToSell);
				//their assets have changed, so the UI must update as well
				UI.updatePlayer(this);
				UI.displaySuccessfulSell(this, courseToSell);
				return true;
			}
			return false;
		} else {
			//if they don't have any courses, then it will let them know they have nothing to sell
			UI.displayNoProperty();
			return false;
		}
	}
	
	/**
	 * Allows user to continue selling a course until the cycle ends
	 * 
	 * @param UI Logic's instance of UI for this to use
	 * 
	 * @param courseList Logic's instance of CourseList for this to use
	 */
	@Override
	public void studentSellCourse(UI UI, CourseList courseList) {
		// TODO Auto-generated method stub
		//will continue to prompt them to sell courses until they choose to stop or they have no more courses left to sell
		while (sellACourse(UI, courseList)) {
			;
		}
	}

	/**
	 * Menu for human student to choose if they want to buy the course they've landed on
	 * 
	 * @param UI Logic's instance of UI for this to use
	 * 
	 * @param aCourse the course that they've landed on
	 * 
	 * @return true if they want to buy the course, false if not
	 */
	@Override
	public boolean studentBuyCourse(UI UI, Course aCourse) {
		// TODO Auto-generated method stub
		return UI.displayPurchaseScreen(aCourse);
	}

	/**
	 * Menu for human student to choose to upgrade a faculty
	 * 
	 * @param UI Logic's instance of UI for this to use
	 */
	@Override
	public String studentUpgradeFaculty(UI UI) {
		// TODO Auto-generated method stub
		return UI.upgradeFacultyMenu(this.getUpgradableFaculties());
	}

}
