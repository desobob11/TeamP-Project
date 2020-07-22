import java.util.Scanner;
public class TextVisualizer {
	
	private TextBoard board = new TextBoard();
	
	
	public TextVisualizer() {
		
	}
	
	public void showRoll(int roll) {
		
	}
	
	
	public void insufficientMoneyError() {
		System.out.println(insufficientMoneyError());
	}
	
	public void rollDiceMenu(Student student) {
		System.out.println(student.getPlayerNumber() + "rolls a : " + Application.rollDice(student).getTileName());
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
		System.out.println()
	}
	
	public void removePlayerFromUI(Student student) {
		System.out.println(student.getPlayerNumber() + " has been removed from the game");p
		student = null;
	}
	
	public void displayMustMortgageScreen(Student student) {
		
	}
	
	public Course sellCourseMenu(Student student) {
		return student.getCoursesOwned().get(0);
	}
	
	public String courseStartMenu(Student student) {
		return "Roll";
	}
	
	public boolean displayPurchaseScreen() {
		return true;
	}
	
	public void displayInsufficientAssets(Student student) {
		
	}
	
	public boolean turnMainMenu(Student student) {
		return true;
	}
	
	public void displayCommunityOption(Community communityOn, int communityOption) {
		System.out.println("")
	}

	public void displayBoard() {
		System.out.println(board.stringConverter());
		
	}

	public void updateBoard(Student student) {
		board.updateBoard(student);
	}

}
	
	
