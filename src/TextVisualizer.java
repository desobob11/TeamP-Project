import java.util.*;

public class TextVisualizer {

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

	public void displayCourseOptions() {
	}

	public void displayCourseOwnedMenu(Student ower, Student owner, int amountOwed) {
		System.out.println("Student " + ower.getPlayerNumber() + " has landed on Student " + owner.getPlayerNumber()
				+ "'s course and owes them $" + amountOwed + " for a tutorial.");
	}

	public void displayTutorialPaidScreen(Student ower, Student owner, int amountOwed) {
		System.out.println("Student " + ower.getPlayerNumber() + " has paid Student " + owner.getPlayerNumber() + " $"
				+ amountOwed + " for the tutorial.");
	}

	public void displayBankruptcyScreen(Student student) {
		System.out.println(student.getPlayerNumber() + " is bankrupt! No money, no education, you're expelled!");
	}

	public void removePlayerFromUI(Student student) {
		System.out.println(student.getPlayerNumber() + " has been removed from the game");
	}

	public void displayMustMortgageScreen(Student student) {
		System.out.println("Student " + student.getPlayerNumber()
				+ "does not have enough available assets, please mortgage some courses");
	}

	public Course sellCourseMenu(Student student) {
		displayStudentCoursesOwned(student);
		System.out.println("Student " + student.getPlayerNumber() + ", please choose a course to sell, or -1 to exit: ");
		int userinp = Integer.parseInt(input.nextLine());

		return student.getCoursesOwned().get(userinp - 1);
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

	public void displayInsufficientAssets(Student student) {
		System.out.println(student.getPlayerNumber() + "has insufficient assets to purchase the desired course");
	}

	public void turnMainMenu(Student student) {
		System.out.println("It is now Student " + student.getPlayerNumber() + "'s turn");
	}

	public boolean chooseToSell() {
		System.out.println("Would you like to sell some courses (1) or proceed with your turn (2) ");
		String userinp = input.nextLine();

		if (userinp.equals("2")) {
			return true;
		} else {
			return false;
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

	public int askForNumPlayers() {
		System.out.println("How many players will be in this game? ");
		int userinp = Integer.parseInt(input.nextLine());

		return userinp;
	}

	public void displayTurnComplete() {
		System.out.println("Turn complete\n\n");
	}
	
	public void continuePlaying() {
		System.out.println("Enter anything to continue: ");
		input.nextLine();
	}
	
	public void closeScanner() {
		input.close();
	}
}
