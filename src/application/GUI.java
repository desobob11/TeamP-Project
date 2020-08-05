package application;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.*;

import model.*;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.beans.property.*;

public class GUI extends Application implements UI {
	Scanner input = new Scanner(System.in);
	public static final String FXML_FILES_LOCATION = "src/views/";
	
	public static final CountDownLatch latch = new CountDownLatch(1);
    public static GUI gui = null;
    
    private PlayerViewController player1View = new PlayerViewController();

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
		final CountDownLatch latch = new CountDownLatch(1);
		ArrayList<String> choices = new ArrayList<String>();
		for (Course courseOwned: student.getCoursesOwned()) {
			choices.add(courseOwned.getTileName());
		}
		
		final StringProperty stpResult = new SimpleStringProperty();
		
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ChoiceDialog<String> dialog = new ChoiceDialog<> ("", choices);
				dialog.setTitle("Number of Players");
				dialog.setHeaderText(null);
				dialog.setContentText("Which course do you want to sell? ");
				Optional<String> result = dialog.showAndWait();
				stpResult.set(dialog.getSelectedItem());
				latch.countDown();
			}
			
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Course courseOwned: student.getCoursesOwned()) {
			if (courseOwned.getTileName().equals(stpResult.get())) {
				return courseOwned;
			}
		}
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
		final CountDownLatch latch = new CountDownLatch(1);
		final StringProperty stpResult = new SimpleStringProperty();
		
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Purchase Course Menu");
				alert.setHeaderText(null);
				alert.setContentText("Would you like to purchase " + theCourse.getTileName());

				ButtonType buttonTypeYes = new ButtonType("Yes");
				ButtonType buttonTypeNo = new ButtonType("No");


				alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == buttonTypeYes) {
					stpResult.set("y");
				} else {
					stpResult.set("n");
				}
				latch.countDown();
			}
			
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (stpResult.get().equals("y") ? true : false);
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
		return 1;
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
		Platform.runLater(() -> {
			player1View.setPlayerMoneyText(student.getPlayerMoney());
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
		final CountDownLatch latch = new CountDownLatch(1);
		ArrayList<String> choices = new ArrayList<String>();
		choices.add("2");
		choices.add("3");
		choices.add("4");
		
		final StringProperty stpResult = new SimpleStringProperty();
		
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ChoiceDialog<String> dialog = new ChoiceDialog<> ("", choices);
				dialog.setTitle("Number of Players");
				dialog.setHeaderText(null);
				dialog.setContentText("Choose the number of players: ");
				Optional<String> result = dialog.showAndWait();
				stpResult.set(dialog.getSelectedItem());
				latch.countDown();
			}
			
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Integer.parseInt(stpResult.get());
	}

	@Override
	public boolean isStudentHuman(int i) {
		final CountDownLatch latch = new CountDownLatch(1);
		final StringProperty stpResult = new SimpleStringProperty();
		
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Human or Computer");
				alert.setHeaderText(null);
				alert.setContentText("Is Student " + i + " a human?");

				ButtonType buttonTypeYes = new ButtonType("Yes");
				ButtonType buttonTypeNo = new ButtonType("No");


				alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == buttonTypeYes) {
					stpResult.set("y");
				} else {
					stpResult.set("n");
				}
				latch.countDown();
			}
			
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (stpResult.get().equals("y") ? true : false);
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/PlayerView.fxml"));
		Parent root = (Parent) loader.load();
		player1View = loader.getController();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
		
	}

	public static void main(String[] args) {
		
		Application.launch(args);
		
		
		
		
		
		
		
	}
	
	
}
