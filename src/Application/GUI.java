package Application;

import model.Chance;
import model.Community;
import model.Course;
import model.Parking;
import model.Probation;
import model.Student;

public class GUI implements UI{

	@Override
	public void showRoll(int roll) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insufficientMoneyError() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayPurchasedScreen(Course theCourse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rollDiceMenu(Student student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayAlreadyOwned(Student student, Course theCourse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayCourseOwnedMenu(Student ower, Student owner, int amountOwed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayTutorialPaidScreen(Student ower, Student owner, int amountOwed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayBankruptcyScreen(Student student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePlayerFromUI(Student student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMustMortgageScreen(Student student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Course sellCourseMenu(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean displayPurchaseScreen(Course theCourse) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void displayNoProperty() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayInsufficientAssets(Student student, Course theCourse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnMainMenu(Student student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean chooseToSell(boolean ownsProperty) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void displayCommunityOption(Community communityOn, int communityOption) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayBoard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBoard(Student student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayChanceOption(Chance chanceOn, int chanceOption) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayLandedInProbation(Student student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayInProbation(Student student, Probation probation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayInParking(Student student, Parking parking) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayStillInJail(Student student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayStudentCoursesOwned(Student student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayStudentMoney(Student student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayStudentStats(Student student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayOnGo(int goAmount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int askForNumPlayers() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void displayTurnComplete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void continuePlaying() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayWinner(Student student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeScanner() {
		// TODO Auto-generated method stub
		
	}

}
