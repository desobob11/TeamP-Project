import java.util.*;

public class TextVisualizer {

	private TextBoard board = new TextBoard();

	public TextVisualizer() {
	}

	public void showRoll(int roll) {
		System.out.println("You rolled a " + roll + ".");
	}

	public void insufficientMoneyError() {
		System.out.println("You do not have the required funds to purchase that course!");
	}

	public void rollDiceMenu(Student student) {
		System.out.println("Enter any character to roll the die");
		Scanner input = new Scanner(System.in);
		input.nextLine();
		input.close();
	}

	public void displayCourseOptions() {
	}

	public void displayCourseOwnedMenu(Student ower, Student owner, int amountOwed) {
		System.out.println("Student " + ower.getPlayerNumber() + " has landed on Student " + owner.getPlayerNumber()
				+ "'s course and owes them $" + amountOwed + "for a tutorial.");
	}

	public void displayTutorialPaidScreen(Student ower, Student owner, int amountOwed) {
		System.out.println("Student " + ower.getPlayerNumber() + "has paid Student" + owner.getPlayerNumber() + " $"
				+ amountOwed + "for the tutorial.");
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
		Scanner input = new Scanner(System.in);

		displayStudentCoursesOwned(student);
		System.out.println(student.getPlayerNumber() + ", please choose a course to sell, or -1 to exit: ");
		int userinp = Integer.parseInt(input.nextLine());
		input.close();

		return student.getCoursesOwned().get(userinp);
	}

	public boolean displayPurchaseScreen() {
		Scanner input = new Scanner(System.in);

		System.out.println("Would you like to purchase this course? [y/n] ");
		String a = input.nextLine();

		input.close();

		if (a == "y") {
			return true;
		}
		return false;
	}

	public void displayInsufficientAssets(Student student) {
		System.out.println(student.getPlayerNumber() + "has insufficient assets to purchase the desired course");
	}

	public void turnMainMenu(Student student) {
		System.out.println("It is now" + student.getPlayerNumber() + "'s turn");
	}

	public boolean chooseToSell() {
		Scanner input = new Scanner(System.in);

		System.out.println("Would you like to sell some courses (1) or proceed with your turn (2) ");
		String userinp = input.nextLine();

		input.close();

		if (userinp.equals("1")) {
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
		System.out.println("You have landed on a community tile!" + "\n" + chanceOn.getChanceOptions()[chanceOption]);
	}

	public void displayStillInJail(Student student) {
		System.out.println("Player " + student.getPlayerNumber() + " is still in jail and cannot move.");
	}

	public void displayStudentCoursesOwned(Student student) {
		ArrayList<Course> coursesOwned = student.getCoursesOwned();

		System.out.println("Courses owned: ");
		for (int i = 1; i <= coursesOwned.size(); i++) {
			System.out.println(i + ") " + coursesOwned.get(i).getTileName());
		}
	}

	public void displayStudentMoney(Student student) {
		System.out.println("Money: " + student.getPlayerMoney());
	}

	public void displayStudentStats(Student student) {
		System.out.println("Student " + student.getPlayerNumber() + "'s assets: ");
		displayStudentMoney(student);
		displayStudentCoursesOwned(student);
	}
	
	public int askForNumPlayers() { 
		Scanner input = new Scanner(System.in);
		
		System.out.println("How many players will be in this game? ");
		int userinp = Integer.parseInt(input.nextLine());
		
		return userinp;
	}
}
