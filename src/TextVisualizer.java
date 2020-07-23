import java.util.Scanner;


public class TextVisualizer {
	
	private TextBoard board = new TextBoard();
	
	
	public TextVisualizer() {
	}
	
	public void showRoll(int roll) {
	}
	
	
	public void insufficientMoneyError() {
		System.out.println("You do not have the required funds to purchase that course!");
	}
	
	public void rollDiceMenu(Student student) {
		System.out.println("Enter any character to roll the die");
		Scanner input = new Scanner(System.in);
		input.next();
	}
	
	public void displayCourseOptions() {
	}
	
	public void displayCourseOwnedMenu(Student ower, Student owner) {
		System.out.println(owner.getCoursesOwned());
	}
	
	public void displayTutorialPaidScreen(Student ower, Student owner, int amountOwed) {
		System.out.println(ower.getPlayerNumber() + "pays " + owner.getPlayerNumber() + " " + amountOwed);
	}
	
	public void displayBankruptcyScreen(Student student) {
		System.out.println(student.getPlayerNumber() +" is bakrupt! No money, no education, you're expelled!");
	}
	
	public void removePlayerFromUI(Student student) {
		System.out.println(student.getPlayerNumber() + " has been removed from the game");
	}
	
	public void displayMustMortgageScreen(Student student) {
		System.out.println(student.getPlayerNumber() + "does not have enough available assets, please mortgage some courses");
	}
	
	public Course sellCourseMenu(Student student) {
		Scanner input = new Scanner(System.in);
		
		System.out.println(student.getPlayerNumber() + ", please choose a course to sell, or -1 to exit: ");
		return student.getCoursesOwned().get(0);
	}
	
	public String courseStartMenu(Student student) {
		return "Roll";
	}
	
	public boolean displayPurchaseScreen() {
		System.out.println("You have purchased the course");
		return true;
	}
	
	public void displayInsufficientAssets(Student student) {
		System.out.println(student.getPlayerNumber() + "has insufficient assets to purchase the desired course");
	}
	
	public boolean turnMainMenu(Student student) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("It is now" + student.getPlayerNumber() + "'s turn");
		System.out.println("Would you like to sell some courses (1) or proceed with your turn (2) ");
		input.nextLine();
		
		if (input.equals("1")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void displayCommunityOption(Community communityOn, int communityOption) {
		System.out.println("You have landed on a community tile!" + "\n" + communityOn.getCommunityOptions()[communityOption]);
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
}
	
	
