
public interface UI {
	void showRoll(int roll);
	void insufficientMoneyError();
	void displayPurchasedScreen(Course theCourse);
	void rollDiceMenu(Student student);
	void displayCourseOwnedMenu(Student ower, Student owner, int amountOwed);
	void displayTutorialPaidScreen(Student ower, Student owner, int amountOwed);
	void displayBankruptcyScreen(Student student);
	void removePlayerFromUI(Student student);
	void displayMustMortgageScreen(Student student);
	Course sellCourseMenu(Student student);
	boolean displayPurchaseScreen(Course theCourse);
	void displayNoProperty();
	void displayInsufficientAssets(Student student, Course theCourse);
	void turnMainMenu(Student student);
	boolean chooseToSell(boolean ownsProperty);
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
	int askForNumPlayers();
	void displayTurnComplete();
	void continuePlaying();
	void displayWinner(Student student);
	void closeScanner();
}
