import java.util.ArrayList;

public interface UI {
	void showRoll(int roll);
	void insufficientMoneyError();
	void displayPurchasedScreen(Course theCourse);
	void rollDiceMenu(Student student);
	void displayAlreadyOwned(Student student, Course theCourse);
	void displayCourseOwnedMenu(Student ower, Student owner, int amountOwed);
	void displayTutorialPaidScreen(Student ower, Student owner, int amountOwed);
	void displaySuccessfulUpgrade(String faculty);
	void displayBankruptcyScreen(Student student);
	void removePlayerFromUI(Student student);
	void displayMustMortgageScreen(Student student);
	Course sellCourseMenu(Student student);
	String upgradeFacultyMenu(ArrayList<ArrayList<Course>> upgradableFaculties);
	boolean displayPurchaseScreen(Course theCourse);
	void displayNoProperty();
	void displayInsufficientAssets(Student student, Course theCourse);
	void turnMainMenu(Student student);
	int initialOptions(boolean ownsProperty, ArrayList<ArrayList<Course>> upgradableFaculties);
	void displayCommunityOption(Community communityOn, int communityOption);
	void displayBoard();
	void updateBoard(Student student);
	void displayChanceOption(Chance chanceOn, int chanceOption);
	void displayLandedInProbation(Student student);
	void displayInProbation(Student student, Probation probation);
	void displayInParking(Student student, Parking parking);
	void displayStillInJail(Student student);
	void displayStudentCoursesOwned(Student student);
	void displayStudentMoney(Student student);
	void displayStudentStats(Student student);
	void displayOnGo(int goAmount);
	int askForNumPlayers();
	void displayTurnComplete();
	void continuePlaying();
	void displayWinner(Student student);
	void closeScanner();
}
