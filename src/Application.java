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
		} else if (purchaseResult == 1) {
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
		for (Course course : student.getCoursesOwned()) {
			courseList.removeFromCoursesOwned(course);
		}
		UI.removePlayerFromUI(student);
		student.studentOut();
		UI.updateBoard(student);
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
			boolean wantsToBuy = UI.displayPurchaseScreen();
			if (wantsToBuy) {
				int buyAttempt = purchaseCourse(student, courseOn);
				if (buyAttempt == 1) {
					;
				} else if (buyAttempt == -2) {
					UI.displayMustMortgageScreen(student);
					sellCourseMenu(student);
					purchaseMenu(student, courseOn);
				}
			}
		} else {
			UI.displayInsufficientAssets(student);
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
		int parkingFeeResult = parkingOn.payParkingFee(student);
		if (parkingFeeResult == 1) {
			;
		} else if (parkingFeeResult == -2) {
			UI.displayMustMortgageScreen(student);
			sellCourseMenu(student);
			parkingMenu(student, parkingOn);
		} else if (parkingFeeResult == -1) {
			removeStudentFromGame(student);
		}
	}

	private void probationMenu(Student student, Probation probationOn) {
		int probationResult = probationOn.probationPayment(student);
		if (probationResult == 1) {
			;
		} else if (probationResult == -2) {
			UI.displayMustMortgageScreen(student);
			sellCourseMenu(student);
			probationMenu(student, probationOn);
		} else if (probationResult == -1) {
			removeStudentFromGame(student);
		}
	}

	private void completeTurn(Student student) {
		if (student.isInJail()) {
			boolean initialChoice = UI.turnMainMenu(student);
			if (initialChoice == true) {
				UI.rollDiceMenu(student);
				Tile landingTile = rollDice(student);

				if (landingTile instanceof Course) {
					Course courseOn = courseList.getCourseAt(landingTile.getTileID());
					if (courseOn.getOwnedStatus()) {
						tutorialPayment(student, courseOn);
					} else {
						purchaseMenu(student, courseOn);
					}
				} else if (landingTile instanceof Community) {
					Community communityOn = courseList.getCommunityAt(landingTile.getTileID());
					int randCommunity = new Random().nextInt(communityOn.getCommunityOptions().length);
					UI.displayCommunityOption(communityOn, randCommunity);
					communityMenu(student, communityOn, randCommunity);
				} else if (landingTile instanceof Chance) {
					Chance chanceOn = courseList.getChanceAt(landingTile.getTileID());
					int randChance = new Random().nextInt(chanceOn.getChanceOptions().length);
					UI.displayChanceOption(chanceOn, randChance);
					chanceMenu(student, chanceOn, randChance);
				} else if (landingTile instanceof Parking) {
					Parking parkingOn = courseList.getParkingAt(landingTile.getTileID());
					parkingMenu(student, parkingOn);
				} else if (landingTile instanceof Probation) {
					Probation probationOn = courseList.getProbation();
					probationMenu(student, probationOn);
				} else if (landingTile instanceof Go) {
					Go goOn = courseList.getGo();
					goOn.depositGoAmmount(student);
				}
			} else {
				sellCourseMenu(student);
				completeTurn(student);
			}
		} else {
			UI.displayStillInJail(student);
			Probation probationOn = courseList.getProbation();
			probationMenu(student, probationOn);
		}
	}
}
