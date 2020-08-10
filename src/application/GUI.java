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
	public static final String FXML_FILES_LOCATION = "src/views/";
	
	public static final CountDownLatch latch = new CountDownLatch(1);
    public static GUI gui = null;
    
    private BoardViewController boardView = new BoardViewController();
    private PlayerViewController[] playerViews = {new PlayerViewController(), new PlayerViewController(), new PlayerViewController(), new PlayerViewController()};
    private ArrayList<Stage> playerStages = new ArrayList<Stage>();

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

	
	
	@Override
	public void showRoll(int roll) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boardView.setDiceImage(roll);
				boardView.setLabelText("Student rolled a " + roll);
				boardView.setButtonText("OK");
				boardView.waitForButtonPress(latch);
			}
			
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void insufficientMoneyError() {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boardView.setLabelText("Student does not have sufficient money to purchase this course. ");
				boardView.setButtonText("OK");
				boardView.waitForButtonPress(latch);
			}
			
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void displayPurchasedScreen(Course theCourse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rollDiceMenu(Student student) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boardView.setLabelText("Click ROLL for Student " + student.getPlayerNumber() + " to roll the die: ");
				boardView.setButtonText("ROLL");
				boardView.waitForButtonPress(latch);
			}
			
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void createPlayer(Student student) {
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					FXMLLoader newLoader = new FXMLLoader(getClass().getResource("../views/PlayerView.fxml"));
					Parent newRoot = (Parent) newLoader.load();
					playerViews[student.getPlayerNumber() - 1] = newLoader.getController();
					PlayerViewController playerView = playerViews[student.getPlayerNumber() - 1];
					playerView.updatePlayerView(student);
					Stage newStage = new Stage();
					newStage.setScene(new Scene(newRoot));
					playerStages.add(newStage);
					newStage.show();
					latch.countDown();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
	}
	
	@Override
	public void updatePlayer(Student student) {
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				PlayerViewController playerView = playerViews[student.getPlayerNumber() - 1];
				playerView.updatePlayerView(student);
				latch.countDown();
			}
			
		});
	}
	
	@Override
	public void displayAlreadyOwned(Student student, Course theCourse) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boardView.setLabelText("Student " + student.getPlayerNumber() + " already owns this course.");
				boardView.setButtonText("OK");
				boardView.waitForButtonPress(latch);
			}
			
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void displayCourseOwnedMenu(Student ower, Student owner, int amountOwed) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boardView.setLabelText("Student " + ower.getPlayerNumber() + " has landed on Student " + owner.getPlayerNumber() + "'s course and owes them $" + amountOwed + " for a tutorial.");
				boardView.setButtonText("OK");
				boardView.waitForButtonPress(latch);
			}
			
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void displayTutorialPaidScreen(Student ower, Student owner, int amountOwed) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boardView.setLabelText("Student " + ower.getPlayerNumber() + " has paid Student " + owner.getPlayerNumber() + " $"
						+ amountOwed + " for the tutorial.");
				boardView.setButtonText("OK");
				boardView.waitForButtonPress(latch);
			}
			
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void displaySuccessfulSell(Student student, Course aCourse) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boardView.setLabelText("Student " + student.getPlayerNumber() + " has successfully sold " + aCourse.getTileName()
				+ " for $" + aCourse.getSellPrice());
				boardView.setButtonText("OK");
				boardView.waitForButtonPress(latch);
			}
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void displaySuccessfulPurchase(Student student, Course aCourse) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boardView.setLabelText("Student " + student.getPlayerNumber() + " has successfully bought " + aCourse.getTileName()
				+ " for $" + aCourse.getBuyPrice());
				boardView.setButtonText("OK");
				boardView.waitForButtonPress(latch);
			}
			
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void displaySuccessfulUpgrade(String faculty) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boardView.setLabelText("The " + faculty + " faculty was successfully upgraded.");
				boardView.setButtonText("OK");
				boardView.waitForButtonPress(latch);
			}
			
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void displayBankruptcyScreen(Student student) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boardView.setLabelText(student.getPlayerNumber() + " is bankrupt! No money, no education, you're expelled!");
				boardView.setButtonText("OK");
				boardView.waitForButtonPress(latch);
			}
			
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void removePlayerFromUI(Student student) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				playerStages.get(student.getPlayerNumber() - 1).close();
				
				boardView.setLabelText("Student " + student.getPlayerNumber() + " has been removed from the game");
				boardView.setButtonText("OK");
				boardView.waitForButtonPress(latch);
			}
			
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void displayMustMortgageScreen(Student student) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boardView.setLabelText("Student " + student.getPlayerNumber()
				+ " does not have enough available assets, please mortgage some courses");
				boardView.setButtonText("OK");
				boardView.waitForButtonPress(latch);
			}
			
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
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
		final CountDownLatch latch = new CountDownLatch(1);
		ArrayList<String> choices = new ArrayList<String>();
		for (int i = 0; i < upgradableFaculties.size(); i++) {
			choices.add(upgradableFaculties.get(i).get(0).getFaculty());
		}
		
		final StringProperty stpResult = new SimpleStringProperty();
		
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ChoiceDialog<String> dialog = new ChoiceDialog<> ("", choices);
				dialog.setTitle("Upgrade Faculty");
				dialog.setHeaderText(null);
				dialog.setContentText("Which faculty do you want to upgrade? ");
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
		return stpResult.get();
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
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boardView.setLabelText("Student has no property to sell. ");
				boardView.setButtonText("OK");
				boardView.waitForButtonPress(latch);
			}
			
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void displayInsufficientAssets(Student student, Course theCourse) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boardView.setLabelText("Student " + student.getPlayerNumber()
				+ " has insufficient assets to purchase the following course: " + theCourse.getTileName());
				boardView.setButtonText("OK");
				boardView.waitForButtonPress(latch);
			}
			
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void turnMainMenu(Student student) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boardView.setLabelText("It is now Student " + student.getPlayerNumber() + "'s turn");
				boardView.setButtonText("OK");
				boardView.waitForButtonPress(latch);
			}
			
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Override
	public int initialOptions(boolean ownsProperty, ArrayList<ArrayList<Course>> upgradableFaculties) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		final StringProperty stpResult = new SimpleStringProperty();
		
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Initial Options");
				alert.setHeaderText(null);
				alert.setContentText("Pick an available option: ");

				ButtonType buttonTypeProceed = new ButtonType("Proceed");
				ButtonType buttonTypeSell = new ButtonType("Sell Courses");
				ButtonType buttonTypeFaculty = new ButtonType("Upgrade Faculty");

				if (ownsProperty) {
					if (upgradableFaculties.size() != 0) {
						alert.getButtonTypes().setAll(buttonTypeProceed, buttonTypeSell, buttonTypeFaculty);
					} else {
						alert.getButtonTypes().setAll(buttonTypeProceed, buttonTypeSell);
					}
				} else {
					alert.getButtonTypes().setAll(buttonTypeProceed);
				}
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == buttonTypeProceed) {
					stpResult.set("1");
				} else if (result.get() == buttonTypeSell){
					stpResult.set("2");
				} else {
					stpResult.set("3");
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
		return Integer.parseInt(stpResult.get());
	}

	@Override
	public void displayCommunityOption(Community communityOn, int communityOption) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boardView.setLabelText("You have landed on a community tile!" + "\n" + communityOn.getCommunityOptions()[communityOption]);
				boardView.setButtonText("OK");
				boardView.waitForButtonPress(latch);
			}
			
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void displayBoard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBoard(Student student) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(() -> {
			boardView.updateBoard(student);
			latch.countDown();
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void displayChanceOption(Chance chanceOn, int chanceOption) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boardView.setLabelText("Student has landed on a chance tile!" + "\n" + chanceOn.getChanceOptions()[chanceOption]);
				boardView.setButtonText("OK");
				boardView.waitForButtonPress(latch);
			}
			
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void displayLandedInProbation(Student student) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boardView.setLabelText("Student has landed in probation.");
				boardView.setButtonText("OK");
				boardView.waitForButtonPress(latch);
			}
			
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void displayInProbation(Student student, Probation probation) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boardView.setLabelText("You are stuck for " + (3 - student.getDurationInProbation()) + " turn(s) and owe $"
						+ probation.getProbationCost() + " every turn.");
				boardView.setButtonText("OK");
				boardView.waitForButtonPress(latch);
			}
			
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void displayInParking(Student student, Parking parking) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boardView.setLabelText("Student has landed in parking. You owe $" + parking.getParkingCost());
				boardView.setButtonText("OK");
				boardView.waitForButtonPress(latch);
			}
			
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void displayStillInJail(Student student) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boardView.setLabelText("Student " + student.getPlayerNumber() + " is still in jail and cannot move.");
				boardView.setButtonText("OK");
				boardView.waitForButtonPress(latch);
			}
			
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
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
	public void displayStudentStats(Student student) {}

	@Override
	public void displayOnGo(int goAmount) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boardView.setLabelText("Student has landed on Go! Collect $200");
				boardView.setButtonText("OK");
				boardView.waitForButtonPress(latch);
			}
			
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
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
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boardView.setLabelText("Turn complete: ");
				boardView.setButtonText("OK");
				boardView.waitForButtonPress(latch);
			}
			
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void continuePlaying() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayWinner(Student student) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boardView.setLabelText("Student " + student.getPlayerNumber() + " is the winner!");
				boardView.setButtonText("OK");
				boardView.waitForButtonPress(latch);
			}
			
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void closeScanner() {
		// TODO Auto-generated method stub
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/BoardView.fxml"));
		Parent root = (Parent) loader.load();
		boardView = loader.getController();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
