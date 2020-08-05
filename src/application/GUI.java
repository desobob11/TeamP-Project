package application;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import model.*;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class GUI extends Application implements UI {

	public static final String FXML_FILES_LOCATION = "src/views/";
	
	public static final CountDownLatch latch = new CountDownLatch(1);
    public static GUI gui = null;

    public static GUI waitForStartUpTest() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return gui;
    }

    public static void setStartUpTest(GUI gui0) {
        gui = gui0;
        latch.countDown();
    }

    public GUI() {
        setStartUpTest(this);
    }

    public void printSomething() {
        System.out.println("You called a method on the application");
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
		
	}

	@Override
	public void closeScanner() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane pane = new Pane();
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Parent root = loader.load(new FileInputStream(FXML_FILES_LOCATION + "PlayerView.fxml")); 
		stage.setTitle("Player View"); 
		stage.setScene(new Scene(root, 600, 900)); 
		stage.show();
		
	}

	public static void main(String[] args) {
		
		Application.launch(args);
		
		
		
		
		
		
		
	}
	
	
}
