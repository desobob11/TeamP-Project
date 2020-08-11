package application;
import java.util.ArrayList;

import model.Chance;
import model.Community;
import model.Course;
import model.Parking;
import model.Probation;
import model.Student;

public interface UI {
	/**
	 * Shows what the student rolled
	 * 
	 * @param roll what the student rolled
	 */
	void showRoll(int roll);
	/**
	 * Displays a message that the user does not have sufficient money to perform the task
	 */
	void insufficientMoneyError();
	/**
	 * Prompts the student to roll the dice
	 * 
	 * @param student the student whose turn it is
	 */
	void rollDiceMenu(Student student);
	/**
	 * Creates a player in the UI
	 * 
	 * @param student the new player
	 */
	void createPlayer(Student student);
	/**
	 * Updates a player's info in the UI
	 * 
	 * @param student the student whose info is being updated
	 */
	void updatePlayer(Student student);
	/**
	 * Displays a message that the student already owns this course
	 * 
	 * @param student the student who landed on the course
	 * 
	 * @param theCourse the course on which they landed
	 */
	void displayAlreadyOwned(Student student, Course theCourse);
	/**
	 * Displays a message that the course the student has landed on is owned by someone else, so they owe them a tutorial fee
	 * 
	 * @param ower the student who owes money
	 * 
	 * @param owner the student who will receive the money
	 * 
	 * @param amountOwed the amount owed
	 */
	void displayCourseOwnedMenu(Student ower, Student owner, int amountOwed);
	/**
	 * Displays a message that the ower successfully paid the owner the tutorial fee
	 * 
	 * @param ower the student who owed money
	 * 
	 * @param owner the student who received the money
	 * 
	 * @param amountOwed the amount transferred
	 */
	void displayTutorialPaidScreen(Student ower, Student owner, int amountOwed);
	/**
	 * Displays a message that the student has successfully sold a course
	 * 
	 * @param student the student who sold the course
	 * 
	 * @param aCourse the course they sold
	 */
	void displaySuccessfulSell(Student student, Course aCourse);
	/**
	 * Displays a message that the student has successfully bought a course
	 * 
	 * @param student the student who bought the course
	 * 
	 * @param aCourse the course they bought
	 */
	void displaySuccessfulPurchase(Student student, Course aCourse);
	/**
	 * Displays a message that a faculty has successfully been upgraded
	 * 
	 * @param faculty the faculty that was upgraded
	 */
	void displaySuccessfulUpgrade(String faculty);
	/**
	 * Displays a message that a student is bankrupt and will be removed
	 * 
	 * @param student the student who is bankrupt
	 */
	void displayBankruptcyScreen(Student student);
	/**
	 * Removes player from the UI
	 * 
	 * @param student the student to be removed
	 */
	void removePlayerFromUI(Student student);
	/**
	 * Displays a message that the student must mortgage courses to get the money required to perform the action
	 * 
	 * @param student the student who must mortgage
	 */
	void displayMustMortgageScreen(Student student);
	/**
	 * Provides the user with a list of all of their courses, and they pick one to sell
	 * 
	 * @param student the student selling a cousre
	 * 
	 * @return the course they want to sell
	 */
	Course sellCourseMenu(Student student);
	/**
	 * Gives a list of faculties that the student can upgrade, and the student chooses
	 * 
	 * @param upgradableFaculties a list of the faculties (list of 3 courses) that they can upgrade
	 * 
	 * @return the faculty the student chose
	 */
	String upgradeFacultyMenu(ArrayList<ArrayList<Course>> upgradableFaculties);
	/**
	 * Prompts the user if they would like to buy a course
	 * 
	 * @param theCourse the course that they've landed on
	 * 
	 * @return true if they want to buy, false if not
	 */
	boolean displayPurchaseScreen(Course theCourse);
	/**
	 * Displays that the student owns no property
	 */
	void displayNoProperty();
	/**
	 * Displays that the student has insufficient assets to buy the course on which they've landed
	 * 
	 * @param student the student who landed on the course
	 * 
	 * @param theCourse the course on which they've landed
	 */
	void displayInsufficientAssets(Student student, Course theCourse);
	/**
	 * Displays a message that it's this student's turn
	 * 
	 * @param student the student whose turn it is
	 */
	void turnMainMenu(Student student);
	/**
	 * Offers the student their initial turn options (proceed, sell courses, upgrade faculty) based on what's available to them
	 * 
	 * @param ownsProperty whether or not the student owns property
	 * 
	 * @param upgradableFaculties the list of faculties (list of courses) that are upgradable
	 * 
	 * @return 1 to proceed, 2 for selling courses, 3 for upgrading a faculty
	 */
	int initialOptions(boolean ownsProperty, ArrayList<ArrayList<Course>> upgradableFaculties);
	/**
	 * Displays the randomly selected community option
	 * 
	 * @param communityOn the community tile that triggered this call
	 * 
	 * @param communityOption the option that was chosen
	 */
	void displayCommunityOption(Community communityOn, int communityOption);
	/**
	 * Displays the board
	 */
	void displayBoard();
	/**
	 * Updates the student's location on the board and removes them if they're out
	 * 
	 * @param student the student whose location is being updated
	 */
	void updateBoard(Student student);
	/**
	 * Displays the randomly selected chance option
	 * 
	 * @param chanceOn the chance tile that triggered this call
	 * 
	 * @param chanceOption the option that was chosen
	 */
	void displayChanceOption(Chance chanceOn, int chanceOption);
	/**
	 * Displays a message that the student has landed in probation
	 * 
	 * @param student the student who landed in probation
	 */
	void displayLandedInProbation(Student student);
	/**
	 * Displays information about how much longer the student is in probation for and how much they owe
	 * 
	 * @param student the student in probation
	 * 
	 * @param probation the Probation tile that triggered this call
	 */
	void displayInProbation(Student student, Probation probation);
	/**
	 * Displays that the student is in parking and how much they owe
	 * 
	 * @param student the student in parking
	 * 
	 * @param parking the Parking tile that triggered this call
	 */
	void displayInParking(Student student, Parking parking);
	/**
	 * Displays that the student is still in probation
	 * 
	 * @param student the student in probation
	 */
	void displayStillInJail(Student student);
	/**
	 * Displays all of the student's courses owned in order
	 * 
	 * @param student the student whose courses are being displayed
	 */
	void displayStudentCoursesOwned(Student student);
	/**
	 * Displays the student's current money
	 * 
	 * @param student the student whose money is being displayed
	 */
	void displayStudentMoney(Student student);
	/**
	 * Displays the student's money and courses owned
	 * 
	 * @param student the student whose stats are being displayed
	 */
	void displayStudentStats(Student student);
	/**
	 * Displays a message that the student has landed on Go and will receive money
	 * 
	 * @param goAmount the amount of money the student receives when landing on Go
	 */
	void displayOnGo(int goAmount);
	/**
	 * Prompts the user for the number of players in the game
	 * 
	 * @return the number of players (2 to 4)
	 */
	int askForNumPlayers();
	/**
	 * Asks the user if student i is a human or computer
	 * 
	 * @param i the number of the student
	 * 
	 * @return true if human, false if computer
	 */
	boolean isStudentHuman(int i);
	/**
	 * Displays a message that the turn is complete
	 */
	void displayTurnComplete();
	/**
	 * Lets the user perform any action to continue playing the game
	 */
	void continuePlaying();
	/**
	 * Displays the winner of the game
	 * 
	 * @param student the student who won
	 */
	void displayWinner(Student student);
	/**
	 * Closes the scanner that was taking input
	 */
	void closeScanner();
}
