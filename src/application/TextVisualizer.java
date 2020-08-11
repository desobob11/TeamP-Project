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
	
	/**
	 * Shows what the student rolled
	 * 
	 * @param roll what the student rolled
	 */
	public void showRoll(int roll) {
		System.out.println("You rolled a " + roll + ".");
	}
	
	/**
	 * Displays a message that the user does not have sufficient money to perform the task
	 */
	public void insufficientMoneyError() {
		System.out.println("You do not have the required funds to purchase that course!");
	}
	
	/**
	 * Prompts the student to roll the dice
	 * 
	 * @param student the student whose turn it is
	 */
	public void rollDiceMenu(Student student) {
		System.out.println("Enter any character to roll the die");
		input.nextLine();
	}
	
	/**
	 * Creates a player in the UI
	 * 
	 * @param student the new player
	 */
	public void createPlayer(Student student) {
		//this UI does not keep live time stats for a player, so this is blank
	};
	
	/**
	 * Updates a player's info in the UI
	 * 
	 * @param student the student whose info is being updated
	 */
	public void updatePlayer(Student student) {
		//this UI does not keep live time stats for a player, so this is blank
	};

	/**
	 * Displays a message that the student already owns this course
	 * 
	 * @param student the student who landed on the course
	 * 
	 * @param theCourse the course on which they landed
	 */
	public void displayAlreadyOwned(Student student, Course theCourse) {
		System.out.println("You already own " + theCourse.getTileName());
	}
	
	/**
	 * Displays a message that the course the student has landed on is owned by someone else, so they owe them a tutorial fee
	 * 
	 * @param ower the student who owes money
	 * 
	 * @param owner the student who will receive the money
	 * 
	 * @param amountOwed the amount owed
	 */
	public void displayCourseOwnedMenu(Student ower, Student owner, int amountOwed) {
		System.out.println("Student " + ower.getPlayerNumber() + " has landed on Student " + owner.getPlayerNumber()
				+ "'s course and owes them $" + amountOwed + " for a tutorial.");
	}

	/**
	 * Displays a message that the ower successfully paid the owner the tutorial fee
	 * 
	 * @param ower the student who owed money
	 * 
	 * @param owner the student who received the money
	 * 
	 * @param amountOwed the amount transferred
	 */
	public void displayTutorialPaidScreen(Student ower, Student owner, int amountOwed) {
		System.out.println("Student " + ower.getPlayerNumber() + " has paid Student " + owner.getPlayerNumber() + " $"
				+ amountOwed + " for the tutorial.");
	}

	/**
	 * Displays a message that the student has successfully sold a course
	 * 
	 * @param student the student who sold the course
	 * 
	 * @param aCourse the course they sold
	 */
	public void displaySuccessfulSell(Student student, Course aCourse) {
		System.out.println("Student " + student.getPlayerNumber() + " has successfully sold " + aCourse.getTileName()
				+ " for $" + aCourse.getSellPrice());
	}

	/**
	 * Displays a message that the student has successfully bought a course
	 * 
	 * @param student the student who bought the course
	 * 
	 * @param aCourse the course they bought
	 */
	public void displaySuccessfulPurchase(Student student, Course aCourse) {
		System.out.println("Student " + student.getPlayerNumber() + " has successfully bought " + aCourse.getTileName()
		+ " for $" + aCourse.getBuyPrice());
	}

	/**
	 * Displays a message that a faculty has successfully been upgraded
	 * 
	 * @param faculty the faculty that was upgraded
	 */
	public void displaySuccessfulUpgrade(String faculty) {
		System.out.println("The " + faculty + " faculty was successfully upgraded.");
	}

	/**
	 * Displays a message that a student is bankrupt and will be removed
	 * 
	 * @param student the student who is bankrupt
	 */
	public void displayBankruptcyScreen(Student student) {
		System.out.println(student.getPlayerNumber() + " is bankrupt! No money, no education, you're expelled!");
	}

	/**
	 * Removes player from the UI
	 * 
	 * @param student the student to be removed
	 */
	public void removePlayerFromUI(Student student) {
		System.out.println("Student " + student.getPlayerNumber() + " has been removed from the game");
	}

	/**
	 * Displays a message that the student must mortgage courses to get the money required to perform the action
	 * 
	 * @param student the student who must mortgage
	 */
	public void displayMustMortgageScreen(Student student) {
		System.out.println("Student " + student.getPlayerNumber()
				+ " does not have enough available assets, please mortgage some courses");
	}

	/**
	 * Gives a list of faculties that the student can upgrade, and the student chooses
	 * 
	 * @param upgradableFaculties a list of the faculties (list of 3 courses) that they can upgrade
	 * 
	 * @return the faculty the student chose
	 */
	public String upgradeFacultyMenu(ArrayList<ArrayList<Course>> upgradableFaculties) {
		System.out.println("Which faculty would you like to upgrade? Enter -1 if you don't want to upgrade any: ");
		//iterate through the courses in their upgradable faculties and grab and display the faculties that they can upgrade
		for (int i = 0; i < upgradableFaculties.size(); i++) {
			System.out.println(i + ") " + upgradableFaculties.get(i).get(0).getFaculty());
		}
		int userinp = Integer.parseInt(input.nextLine());
		return upgradableFaculties.get(userinp).get(0).getFaculty();
	}

	/**
	 * Provides the user with a list of all of their courses, and they pick one to sell
	 * 
	 * @param student the student selling a cousre
	 * 
	 * @return the course they want to sell
	 */
	public Course sellCourseMenu(Student student) {
		displayStudentCoursesOwned(student);
		System.out
				.println("Student " + student.getPlayerNumber() + ", please choose a course to sell, or -1 to exit: ");
		int userinp = Integer.parseInt(input.nextLine());

		return (userinp != -1 ? student.getCoursesOwned().get(userinp - 1) : null);
	}

	/**
	 * Prompts the user if they would like to buy a course
	 * 
	 * @param theCourse the course that they've landed on
	 * 
	 * @return true if they want to buy, false if not
	 */
	public boolean displayPurchaseScreen(Course theCourse) {
		System.out.println("Would you like to purchase the following course: " + theCourse.getTileName() + " for $"
				+ theCourse.getBuyPrice() + " [y/n] ");
		String a = input.nextLine();
		if (a.equals("y")) {
			return true;
		}
		return false;
	}

	/**
	 * Displays that the student owns no property
	 */
	public void displayNoProperty() {
		System.out.println("You have no property to sell. ");
	}

	/**
	 * Displays that the student has insufficient assets to buy the course on which they've landed
	 * 
	 * @param student the student who landed on the course
	 * 
	 * @param theCourse the course on which they've landed
	 */
	public void displayInsufficientAssets(Student student, Course theCourse) {
		System.out.println("Student " + student.getPlayerNumber()
				+ " has insufficient assets to purchase the following course: " + theCourse.getTileName());
	}

	/**
	 * Displays a message that it's this student's turn
	 * 
	 * @param student the student whose turn it is
	 */
	public void turnMainMenu(Student student) {
		System.out.println("It is now Student " + student.getPlayerNumber() + "'s turn");
	}

	/**
	 * Offers the student their initial turn options (proceed, sell courses, upgrade faculty) based on what's available to them
	 * 
	 * @param ownsProperty whether or not the student owns property
	 * 
	 * @param upgradableFaculties the list of faculties (list of courses) that are upgradable
	 * 
	 * @return 1 to proceed, 2 for selling courses, 3 for upgrading a faculty
	 */
	public int initialOptions(boolean ownsProperty, ArrayList<ArrayList<Course>> upgradableFaculties) {
		//if they own property, then they have the option of selling it
		if (ownsProperty) {
			//if they have upgradable faculties, then they have the option of upgrading it
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

	/**
	 * Displays the randomly selected community option
	 * 
	 * @param communityOn the community tile that triggered this call
	 * 
	 * @param communityOption the option that was chosen
	 */
	public void displayCommunityOption(Community communityOn, int communityOption) {
		System.out.println(
				"You have landed on a community tile!" + "\n" + communityOn.getCommunityOptions()[communityOption]);
	}

	/**
	 * Displays the board
	 */
	public void displayBoard() {
		System.out.println(board.stringConverter());
	}

	/**
	 * Updates the student's location on the board and removes them if they're out
	 * 
	 * @param student the student whose location is being updated
	 */
	public void updateBoard(Student student) {
		board.updateBoard(student);
	}

	/**
	 * Displays the randomly selected chance option
	 * 
	 * @param chanceOn the chance tile that triggered this call
	 * 
	 * @param chanceOption the option that was chosen
	 */
	public void displayChanceOption(Chance chanceOn, int chanceOption) {
		System.out.println("You have landed on a chance tile!" + "\n" + chanceOn.getChanceOptions()[chanceOption]);
	}

	/**
	 * Displays a message that the student has landed in probation
	 * 
	 * @param student the student who landed in probation
	 */
	public void displayLandedInProbation(Student student) {
		System.out.println("You have landed in probation");
	}

	/**
	 * Displays information about how much longer the student is in probation for and how much they owe
	 * 
	 * @param student the student in probation
	 * 
	 * @param probation the Probation tile that triggered this call
	 */
	public void displayInProbation(Student student, Probation probation) {
		System.out.println("You are stuck for " + (3 - student.getDurationInProbation()) + " turn(s) and owe $"
				+ probation.getProbationCost() + " every turn.");
	}

	/**
	 * Displays that the student is in parking and how much they owe
	 * 
	 * @param student the student in parking
	 * 
	 * @param parking the Parking tile that triggered this call
	 */
	public void displayInParking(Student student, Parking parking) {
		System.out.println("You have landed in parking. You owe $" + parking.getParkingCost());
	}

	/**
	 * Displays that the student is still in probation
	 * 
	 * @param student the student in probation
	 */
	public void displayStillInJail(Student student) {
		System.out.println("Student " + student.getPlayerNumber() + " is still in jail and cannot move.");
	}

	/**
	 * Displays all of the student's courses owned in order
	 * 
	 * @param student the student whose courses are being displayed
	 */
	public void displayStudentCoursesOwned(Student student) {
		ArrayList<Course> coursesOwned = student.getCoursesOwned();

		System.out.println("Courses owned: ");
		//iterate through the courses owned and display the course name like this: 1) SOCI 201
		for (int i = 0; i < coursesOwned.size(); i++) {
			System.out.println((i + 1) + ") " + coursesOwned.get(i).getTileName());
		}
	}

	/**
	 * Displays the student's current money
	 * 
	 * @param student the student whose money is being displayed
	 */
	public void displayStudentMoney(Student student) {
		System.out.println("Money: " + student.getPlayerMoney());
	}

	/**
	 * Displays the student's money and courses owned
	 * 
	 * @param student the student whose stats are being displayed
	 */
	public void displayStudentStats(Student student) {
		System.out.println("Student " + student.getPlayerNumber() + "'s assets: ");
		displayStudentMoney(student);
		displayStudentCoursesOwned(student);
		System.out.println();
	}

	/**
	 * Displays a message that the student has landed on Go and will receive money
	 * 
	 * @param goAmount the amount of money the student receives when landing on Go
	 */
	public void displayOnGo(int goAmount) {
		System.out.println("You have landed on Go! Collect $" + goAmount);
	}

	/**
	 * Prompts the user for the number of players in the game
	 * 
	 * @return the number of players (2 to 4)
	 */
	public int askForNumPlayers() {
		System.out.println("How many players will be in this game? ");
		int userinp = Integer.parseInt(input.nextLine());

		return userinp;
	}

	/**
	 * Asks the user if student i is a human or computer
	 * 
	 * @param i the number of the student
	 * 
	 * @return true if human, false if computer
	 */
	public boolean isStudentHuman(int i) {
		System.out.println("Is Student " + i + " a [h]uman or a [c]omputer? ");
		String userinp = input.nextLine();

		return (userinp.equals("h") ? true : false);
	}

	/**
	 * Displays a message that the turn is complete
	 */
	public void displayTurnComplete() {
		System.out.println("Turn complete\n\n");
	}

	/**
	 * Lets the user perform any action to continue playing the game
	 */
	public void continuePlaying() {
		System.out.println("Enter anything to continue: ");
		input.nextLine();
	}

	/**
	 * Displays the winner of the game
	 * 
	 * @param student the student who won
	 */
	public void displayWinner(Student student) {
		System.out.println("Student " + student.getPlayerNumber() + " is the winner!");
	}

	/**
	 * Closes the scanner that was taking input
	 */
	public void closeScanner() {
		input.close();
	}
}
