package model;
import java.util.Objects;

import application.UI;

public class HumanStudent extends Student {

	public HumanStudent(int playerNumber, int playerMoney) {
		// TODO Auto-generated constructor stub
		super(playerNumber, playerMoney);
	}

	@Override
	public int studentInitialOption(UI UI) {
		// TODO Auto-generated method stub
		return UI.initialOptions(this.doesStudentOwnProperty(), this.getUpgradableFaculties());
	}

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
	
	@Override
	public void studentSellCourse(UI UI, CourseList courseList) {
		// TODO Auto-generated method stub
		//will continue to prompt them to sell courses until they choose to stop or they have no more courses left to sell
		while (sellACourse(UI, courseList)) {
			;
		}
	}

	@Override
	public boolean studentBuyCourse(UI UI, Course aCourse) {
		// TODO Auto-generated method stub
		return UI.displayPurchaseScreen(aCourse);
	}

	@Override
	public String studentUpgradeFaculty(UI UI) {
		// TODO Auto-generated method stub
		return UI.upgradeFacultyMenu(this.getUpgradableFaculties());
	}

}
