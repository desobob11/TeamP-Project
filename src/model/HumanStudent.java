package model;
import java.util.Objects;
import application.*;

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
		if (ownsProperty) {
			Course courseToSell = UI.sellCourseMenu(this);
			if (Objects.nonNull(courseToSell)) {
				this.sellCourse(courseToSell);
				courseList.removeFromCoursesOwned(courseToSell);
				UI.displaySuccessfulSell(this, courseToSell);
				return true;
			}
			return false;
		} else {
			UI.displayNoProperty();
			return false;
		}
	}
	
	@Override
	public void studentSellCourse(UI UI, CourseList courseList) {
		// TODO Auto-generated method stub
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
