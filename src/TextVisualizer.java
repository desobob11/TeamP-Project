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
	
	public void displayTutorialPaidScreen(Student ower, Student owner, Course tutorial) {
		System.out.println(ower.getPlayerNumber() + "pays " + owner.getPlayerNumber() + tutorial.getTutorialPriceOwed());
	}
	
	public void displayBankruptcyScreen(Student student) {
		System.out.println("You are bakrupt! Nothing can help you now, you're exprelled from the University");
	}
	
	public void removePlayerFromUI(Student student) {
		System.out.println(student.getPlayerNumber() + " has been removed from the game");
	}
	
	public void displayMustMortgageScreen(Student student) {
		System.out.println("You don't have enough available assets, you'll need to mortgage some courses");
	}
	
	public Course sellCourseMenu(Student student) {
		System.out.println("Please choose any courses you wish to sell");
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
		System.out.println("You have insufficient assets to purchase your desired course");
	}
	
	public boolean turnMainMenu(Student student) {
		System.out.println("It is now" + student.getPlayerNumber() + "'s turn");
		return true;
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
}
	
	
