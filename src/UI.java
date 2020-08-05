import java.io.FileInputStream;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public interface UI {
	void showRoll(int roll);
	void insufficientMoneyError();
	void displayPurchasedScreen(Course theCourse);
	void rollDiceMenu(Student student);
	void displayAlreadyOwned(Student student, Course theCourse);
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
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			AnchorPane root = loader.load(new FileInputStream("/view/BoardController.fxml"));
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch (Exception e) {
			
		}
	}
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
