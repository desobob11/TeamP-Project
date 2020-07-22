import java.util.*;

public class Application {
	int numStudents;
	ArrayList<Student> students;
	CourseList courseList;
	TextVisualizer UI = new TextVisualizer();
	Dice dice = new Dice();
	
	public Application(int numStudents) {
		this.numStudents = numStudents;
		students = new ArrayList<Student>();
		courseList = new CourseList();
	}
	
	private int purchaseCourse(Student student, Course aCourse) {
		int purchaseResult = student.purchaseCourse(aCourse);
		
		if (purchaseResult == -2) {
			UI.insufficientMoneyError();
		}
		else if (purchaseResult == 1) {
			courseList.addToCoursesOwned(aCourse);
		}
		return purchaseResult;
	}
	
	private Tile rollDice(Student student) {
		int roll = dice.rollDice();
		student.moveForward(roll, courseList.getBoardSize());
		return courseList.getTileAt(student.getPlayerPosition());
	}
	
	private void courseWalkthrough(Student student) {
		UI.displayCourseOptions();
	}
	
	private void removeStudentFromGame(Student student) {
		for (Course course: student.getCoursesOwned()) {
			courseList.removeFromCoursesOwned(course);
		}
		UI.removePlayerFromUI(student);
		student = null;
	}
	
	private boolean sellCourse(Student student) {
		Course courseToSell = UI.sellCourseMenu(student);
		if (courseToSell != null) {	
			student.sellCourse(courseToSell);
			courseList.removeFromCoursesOwned(courseToSell);
			return true;
		}
		return false;
	}
	
	private void sellCourseMenu(Student student) {
		while (!sellCourse(student)) {
			
		}
	}
	
	private void tutorialPayment(Student student, Course courseOn) {
		UI.displayCourseOwnedMenu(student, courseOn.getOwner());
		int amountOwed = courseOn.getTutorialPriceOwed();
		int withdrawalResult = student.withdrawMoney(amountOwed);
		if (withdrawalResult == 1) {
			courseOn.getOwner().depositMoney(amountOwed);
			UI.displayTutorialPaidScreen(student, courseOn.getOwner());
		}
		else if (withdrawalResult == -1) {
			UI.displayBankruptcyScreen(student);
			removeStudentFromGame(student);
		}
		else {
			UI.displayMustMortgageScreen(student);
			sellCourseMenu(student);
			tutorialPayment(student, courseOn);
		}
	}
	
	private void purchaseMenu(Student student, Course courseOn) {
		if (student.getNetWorth() >= courseOn.getBuyPrice()) {
			boolean wantsToBuy = UI.displayPurchaseScreen();
			if (wantsToBuy) {
				int buyAttempt = student.purchaseCourse(courseOn);
				if (buyAttempt == 1) {
					;
				}
				else if (buyAttempt == -1) {
					UI.displayMustMortgageScreen(student);
					sellCourseMenu(student);
					purchaseMenu(student, courseOn);
				}
			}
		}
		else {
			UI.displayInsufficientAssets(student);
		}
	}
	
	private communityOption
	
	private void completeTurn(Student student) {
		boolean initialChoice = UI.turnMainMenu(student);
		if (initialChoice == true) {
			UI.rollDiceMenu();
			Tile landingTile = rollDice(student);
			if (landingTile instanceof Course) {
				Course courseOn = (Course) landingTile;
				if (courseOn.getOwnedStatus()) {
					tutorialPayment(student, courseOn);
				}
				else {
					purchaseMenu(student, courseOn);
				}
			}
			else if (landingTile instanceof Community) {
				Community communityOn = (Community) landingTile;
				int randCommunity = new Random().nextInt(communityOn.getCommunityOptions().length);
				UI.displayCommunityOption(communityOn, randCommunity);
				int communityOptionResult = communityOn.performCommunityOption(randCommunity, student, students);
				if (communityOptionResult == 1) {
					;
				}
				else if (communityOptionResult == -2) {
					UI.displayMustMortgageScreen(student);
					sellCourseMenu(student);
				}
				else if (communityOptionResult == -1) {
					removeStudentFromGame(student);
				}
			}
		}
		else {
			sellCourseMenu(student);
			completeTurn(student);
		}
	}
}
