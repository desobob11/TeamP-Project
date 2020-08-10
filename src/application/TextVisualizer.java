package application;
import java.util.*;

import model.Chance;
import model.Community;
import model.Course;
import model.Parking;
import model.Probation;
import model.Student;

/**
 * This is the text-based UI class. It trusts Application with all of the logic
 * and only handles UI and I/O.
 * 
 * @author Desmond O'Brien and Arnuv Mayank
 *
 */

public class TextVisualizer implements UI {

	private TextBoard board = new TextBoard();
	private Scanner input = new Scanner(System.in);

	public TextVisualizer() {
	}

	public void showRoll(int roll) {
		System.out.println("You rolled a " + roll + ".");
	}

	public void insufficientMoneyError() {
		System.out.println("You do not have the required funds to purchase that course!");
	}

	public void displayPurchasedScreen(Course theCourse) {
		System.out.println("Congratulations! You have purchased the following course: " + theCourse.getTileName());
	}

	public void rollDiceMenu(Student student) {
		System.out.println("Enter any character to roll the die");
		input.nextLine();
	}
	
	public void createPlayer(Student student) {};
	
	public void updatePlayer(Student student) {};

	public void displayAlreadyOwned(Student student, Course theCourse) {
		System.out.println("You already own " + theCourse.getTileName());
	}
	
	public void displayCourseOwnedMenu(Student ower, Student owner, int amountOwed) {
		System.out.println("Student " + ower.getPlayerNumber() + " has landed on Student " + owner.getPlayerNumber()
				+ "'s course and owes them $" + amountOwed + " for a tutorial.");
	}

	public void displayTutorialPaidScreen(Student ower, Student owner, int amountOwed) {
		System.out.println("Student " + ower.getPlayerNumber() + " has paid Student " + owner.getPlayerNumber() + " $"
				+ amountOwed + " for the tutorial.");
	}

	public void displaySuccessfulSell(Student student, Course aCourse) {
		System.out.println("Student " + student.getPlayerNumber() + " has successfully sold " + aCourse.getTileName()
				+ " for $" + aCourse.getSellPrice());
	}

	public void displaySuccessfulPurchase(Student student, Course aCourse) {
		System.out.println("Student " + student.getPlayerNumber() + " has successfully bought " + aCourse.getTileName()
		+ " for $" + aCourse.getBuyPrice());
	}

	public void displaySuccessfulUpgrade(String faculty) {
		System.out.println("The " + faculty + " faculty was successfully upgraded.");
	}

	public void displayBankruptcyScreen(Student student) {
		System.out.println(student.getPlayerNumber() + " is bankrupt! No money, no education, you're expelled!");
	}

	public void removePlayerFromUI(Student student) {
		System.out.println("Student " + student.getPlayerNumber() + " has been removed from the game");
	}

	public void displayMustMortgageScreen(Student student) {
		System.out.println("Student " + student.getPlayerNumber()
				+ " does not have enough available assets, please mortgage some courses");
	}

	public String upgradeFacultyMenu(ArrayList<ArrayList<Course>> upgradableFaculties) {
		System.out.println("Which faculty would you like to upgrade? Enter -1 if you don't want to upgrade any: ");
		for (int i = 0; i < upgradableFaculties.size(); i++) {
			System.out.println(i + ") " + upgradableFaculties.get(i).get(0).getFaculty());
		}
		int userinp = Integer.parseInt(input.nextLine());
		return upgradableFaculties.get(userinp).get(0).getFaculty();
	}

	public Course sellCourseMenu(Student student) {
		displayStudentCoursesOwned(student);
		System.out
				.println("Student " + student.getPlayerNumber() + ", please choose a course to sell, or -1 to exit: ");
		int userinp = Integer.parseInt(input.nextLine());

		return (userinp != -1 ? student.getCoursesOwned().get(userinp - 1) : null);
	}

	public boolean displayPurchaseScreen(Course theCourse) {
		System.out.println("Would you like to purchase the following course: " + theCourse.getTileName() + " for $"
				+ theCourse.getBuyPrice() + " [y/n] ");
		String a = input.nextLine();
		if (a.equals("y")) {
			return true;
		}
		return false;
	}

	public void displayNoProperty() {
		System.out.println("You have no property to sell. ");
	}

	public void displayInsufficientAssets(Student student, Course theCourse) {
		System.out.println("Student " + student.getPlayerNumber()
				+ " has insufficient assets to purchase the following course: " + theCourse.getTileName());
	}

	public void turnMainMenu(Student student) {
		System.out.println("It is now Student " + student.getPlayerNumber() + "'s turn");
	}

	public int initialOptions(boolean ownsProperty, ArrayList<ArrayList<Course>> upgradableFaculties) {
		if (ownsProperty) {
			if (upgradableFaculties.size() != 0) {
				System.out.println(
						"Would you like to proceed with your turn (1), sell some courses (2), or upgrade a faculty (3)?");
			} else {
				System.out.println("Would you like to proceed with your turn (1) or sell some courses (2)?");
			}
			String userinp = input.nextLine();
			return Integer.parseInt(userinp);
		} else {
			displayNoProperty();
			return 1;
		}
	}

	public void displayCommunityOption(Community communityOn, int communityOption) {
		System.out.println(
				"You have landed on a community tile!" + "\n" + communityOn.getCommunityOptions()[communityOption]);
	}

	public void displayBoard() {
		System.out.println(board.stringConverter());
	}

	public void updateBoard(Student student) {
		board.updateBoard(student);
	}

	public void displayChanceOption(Chance chanceOn, int chanceOption) {
		System.out.println("You have landed on a chance tile!" + "\n" + chanceOn.getChanceOptions()[chanceOption]);
	}

	public void displayLandedInProbation(Student student) {
		System.out.println("You have landed in probation");
	}

	public void displayInProbation(Student student, Probation probation) {
		System.out.println("You are stuck for " + (3 - student.getDurationInProbation()) + " turn(s) and owe $"
				+ probation.getProbationCost() + " every turn.");
	}

	public void displayInParking(Student student, Parking parking) {
		System.out.println("You have landed in parking. You owe $" + parking.getParkingCost());
	}

	public void displayStillInJail(Student student) {
		System.out.println("Student " + student.getPlayerNumber() + " is still in jail and cannot move.");
	}

	public void displayStudentCoursesOwned(Student student) {
		ArrayList<Course> coursesOwned = student.getCoursesOwned();

		System.out.println("Courses owned: ");
		for (int i = 0; i < coursesOwned.size(); i++) {
			System.out.println((i + 1) + ") " + coursesOwned.get(i).getTileName());
		}
	}

	public void displayStudentMoney(Student student) {
		System.out.println("Money: " + student.getPlayerMoney());
	}

	public void displayStudentStats(Student student) {
		System.out.println("Student " + student.getPlayerNumber() + "'s assets: ");
		displayStudentMoney(student);
		displayStudentCoursesOwned(student);
		System.out.println();
	}

	public void displayOnGo(int goAmount) {
		System.out.println("You have landed on Go! Collect $" + goAmount);
	}

	public int askForNumPlayers() {
		System.out.println("How many players will be in this game? ");
		int userinp = Integer.parseInt(input.nextLine());

		return userinp;
	}

	public boolean isStudentHuman(int i) {
		System.out.println("Is Student " + i + " a [h]uman or a [c]omputer? ");
		String userinp = input.nextLine();

		return (userinp.equals("h") ? true : false);
	}

	public void displayTurnComplete() {
		System.out.println("Turn complete\n\n");
	}

	public void continuePlaying() {
		System.out.println("Enter anything to continue: ");
		input.nextLine();
	}

	public void displayWinner(Student student) {
		System.out.println("Student " + student.getPlayerNumber() + " is the winner!");
	}

	public void closeScanner() {
		input.close();
	}
}
