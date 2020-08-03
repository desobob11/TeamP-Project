import java.util.*;

/**
 * This is the class that has all of the game's ruleset/logic. It takes from all
 * the classes previously built, as well as the UI, and makes the entire game
 * happen
 * 
 * @author Arnuv Mayank
 *
 */
public class Application {
	int numStudents;
	final int startingMoney = 1000;
	ArrayList<Student> students;
	CourseList courseList;
	TextVisualizer UI = new TextVisualizer();
	Dice dice = new Dice();

	public Application() {
		students = new ArrayList<Student>();
		courseList = new CourseList();
	}

	public CourseList getCourseList() {
		return courseList;
	}
	
	public UI getUI() {
		return UI;
	}
	
	public ArrayList<Student> getStudents() {
		return students;
	}
	
	/*
	private int purchaseCourse(Student student, Course aCourse) {
		int purchaseResult = student.purchaseCourse(aCourse);

		if (purchaseResult == -2) {
			UI.insufficientMoneyError();
		} else if (purchaseResult == 1) {
			courseList.addToCoursesOwned(aCourse);
		}

		return purchaseResult;
	}
	*/

	private Tile rollDice(Student student) {
		int roll = dice.rollDice();
		student.moveForward(roll, courseList.getBoardSize());
		UI.showRoll(roll);
		return courseList.getTileAt(student.getPlayerPosition());
	}

	private void removeStudentFromGame(Student student) {
		for (Course course : student.getCoursesOwned()) {
			course.resetCourseLevel();
			courseList.removeFromCoursesOwned(course);
		}
		students.remove(student);
		UI.removePlayerFromUI(student);
		student.studentOut();
		UI.updateBoard(student);
	}

	private boolean sellCourse(Student student) {
		boolean ownsProperty = student.doesStudentOwnProperty();
		if (ownsProperty) {
			Course courseToSell = UI.sellCourseMenu(student);
			if (Objects.nonNull(courseToSell)) {
				student.sellCourse(courseToSell);
				courseList.removeFromCoursesOwned(courseToSell);
				return true;
			}
			return false;
		} else {
			UI.displayNoProperty();
			return false;
		}
	}

	private void sellCourseMenu(Student student) {
		while (sellCourse(student)) {
			;
		}
	}
	
	private void upgradeLevelMenu(Student student, ArrayList<ArrayList<Course>> upgradableFaculties) {
		String faculty = UI.upgradeFacultyMenu(upgradableFaculties);
		int upgradeResult = student.upgradeFacultyLevel(faculty);
		if (upgradeResult == 1) {
			UI.displaySuccessfulUpgrade(faculty);
		} else if (upgradeResult == -2) {
			UI.displayMustMortgageScreen(student);
			sellCourseMenu(student);
			this.upgradeLevelMenu(student, student.getUpgradableFaculties());
		}
	}
	
	private void displayStudentStats() {
		for (Student aStudent : students) {
			UI.displayStudentStats(aStudent);
		}
	}
	
	/*
	private void tutorialPayment(Student student, Course courseOn) {
		int amountOwed = courseOn.getTutorialPriceOwed();
		UI.displayCourseOwnedMenu(student, courseOn.getOwner(), amountOwed);
		int withdrawalResult = student.withdrawMoney(amountOwed);
		if (withdrawalResult == 1) {
			courseOn.getOwner().depositMoney(amountOwed);
			UI.displayTutorialPaidScreen(student, courseOn.getOwner(), amountOwed);
		} else if (withdrawalResult == -1) {
			UI.displayBankruptcyScreen(student);
			removeStudentFromGame(student);
		} else {
			UI.displayMustMortgageScreen(student);
			sellCourseMenu(student);
			tutorialPayment(student, courseOn);
		}
	}

	private void purchaseMenu(Student student, Course courseOn) {
		if (student.getNetWorth() >= courseOn.getBuyPrice()) {
			boolean wantsToBuy = UI.displayPurchaseScreen(courseOn);
			if (wantsToBuy) {
				int buyAttempt = purchaseCourse(student, courseOn);
				if (buyAttempt == 1) {
					;
				} else if (buyAttempt == -2) {
					if (UI.chooseToSell(true)) {
						sellCourseMenu(student);
						purchaseMenu(student, courseOn);
					}
				}
			}
		} else {
			UI.displayInsufficientAssets(student, courseOn);
		}
	}

	private void communityMenu(Student student, Community communityOn, int randCommunity) {
		int communityOptionResult = communityOn.performCommunityOption(randCommunity, student, students);
		if (communityOptionResult == 1) {
			;
		} else if (communityOptionResult == -2) {
			UI.displayMustMortgageScreen(student);
			sellCourseMenu(student);
			communityMenu(student, communityOn, randCommunity);
		} else if (communityOptionResult == -1) {
			removeStudentFromGame(student);
		}
	}

	private void chanceMenu(Student student, Chance chanceOn, int randChance) {
		int chanceOptionResult = chanceOn.performChanceOption(randChance, student, students,
				courseList.getParkingTiles(), courseList.getProbation());
		if (chanceOptionResult == 1) {
			;
		} else if (chanceOptionResult == -2) {
			UI.displayMustMortgageScreen(student);
			sellCourseMenu(student);
			chanceMenu(student, chanceOn, randChance);
		} else if (chanceOptionResult == -1) {
			removeStudentFromGame(student);
		}
	}

	private void parkingMenu(Student student, Parking parkingOn) {
		UI.displayInParking(student, parkingOn);
		int parkingFeeResult = parkingOn.payParkingFee(student);
		if (parkingFeeResult == 1) {
			;
		} else if (parkingFeeResult == -2) {
			sellCourseMenu(student);
			parkingMenu(student, parkingOn);
		} else if (parkingFeeResult == -1) {
			removeStudentFromGame(student);
		}
	}

	private void probationMenu(Student student, Probation probationOn, int counter) {
		UI.displayInProbation(student, probationOn);
		int probationResult = probationOn.probationPayment(student, counter);
		if (probationResult == 1) {
			;
		} else if (probationResult == -2) {
			UI.displayMustMortgageScreen(student);
			sellCourseMenu(student);
			probationMenu(student, probationOn, 2);
		} else if (probationResult == -1) {
			removeStudentFromGame(student);
		}
	}
	*/

	private void completeTurn(Student student, int counter) {
		UI.displayBoard();
		UI.displayStudentStats(student);
		Tile landingTile = courseList.getTileAt(student.getPlayerPosition());
		UI.turnMainMenu(student);
		boolean ownsProperty = student.doesStudentOwnProperty();
		ArrayList<ArrayList<Course>> upgradableFaculties = student.getUpgradableFaculties();
		int initialChoice = UI.initialOptions(ownsProperty, upgradableFaculties); 
		if (initialChoice == 1) {
			if (!student.isInJail()) {
				UI.rollDiceMenu(student);
				landingTile = rollDice(student);
				UI.updateBoard(student);
				UI.displayBoard();
			}
			int tileActionResult = landingTile.performTileAction(student, students, UI, courseList);
			landingTile.setPerformedTileAction(true);
			while (tileActionResult == -2) {
				UI.displayMustMortgageScreen(student);
				sellCourseMenu(student);
				tileActionResult = landingTile.performTileAction(student, students, UI, courseList);
			}
			if (tileActionResult == -1) {
				removeStudentFromGame(student);
			}
			UI.updateBoard(student);
		} else if (initialChoice == 2) {
			sellCourseMenu(student);
			completeTurn(student, 2);
		} else {
			upgradeLevelMenu(student, upgradableFaculties);
			completeTurn(student, 2);
		}

		if (counter == 1) {
			displayStudentStats();
			UI.displayTurnComplete();
			UI.continuePlaying();
		}
		landingTile.setPerformedTileAction(false);
	}

	public void run() {
		numStudents = UI.askForNumPlayers();
		students = new ArrayList<Student>();
		Student student;
		int turn = 0;

		for (int i = 1; i <= numStudents; i++) {
			students.add(new Student(i, startingMoney));
			UI.updateBoard(students.get(i - 1));
		}

		while (students.size() != 1) {
			student = students.get(turn);
			completeTurn(student, 1);
			if (student.getPlayerPosition() == -1) {
				students.remove(student);
				numStudents--;
				turn--;
			}

			turn++;
			turn %= numStudents;
		}
		UI.closeScanner();
		UI.displayWinner(students.get(0));
	}
}
