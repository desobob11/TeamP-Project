package application;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Chance;
import model.Community;
import model.Course;
import model.Parking;
import model.Probation;
import model.Student;

public class GUI extends Application implements UI {
	
	
	private PlayerViewController player1View;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/PlayerView.fxml"));
		Parent root = (Parent) loader.load();
		player1View = loader.getController();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		Logic logic = new Logic(this);
		logic.run();
	}

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
	public void displaySuccessfulSell(Student student, Course aCourse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaySuccessfulPurchase(Student student, Course aCourse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaySuccessfulUpgrade(String faculty) {
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
	public String upgradeFacultyMenu(ArrayList<ArrayList<Course>> upgradableFaculties) {
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
	public int initialOptions(boolean ownsProperty, ArrayList<ArrayList<Course>> upgradableFaculties) {
		// TODO Auto-generated method stub
		return 0;
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
		Platform.runLater(()->{
			player1View.setPlayerMoney(student.getPlayerMoney());
			player1View.changePlayerNameText("Student " + student.getPlayerNumber());
			player1View.clearAppText();
		});
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
		ArrayList<String> choices = new ArrayList<String>();
		choices.add("2");
		choices.add("3");
		choices.add("4");
		
		ChoiceDialog<String> dialog = new ChoiceDialog<> ("", choices);
		dialog.setTitle("Number of Players");
		dialog.setHeaderText(null);
		dialog.setContentText("Choose the number of players: ");
		
		
		
		Optional<String> result = dialog.showAndWait();
		String strResult = dialog.getSelectedItem();
		return Integer.parseInt(strResult);
	}

	@Override
	public boolean isStudentHuman() {
		// TODO Auto-generated method stub
		return false;
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
		System.out.println(student.getPlayerNumber() + "");
	}

	@Override
	public void closeScanner() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
