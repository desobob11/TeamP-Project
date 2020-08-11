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

/**
 * GUI class - implements all methods from UI interface and defines them
 * extends Application from JavaFX
 * 
 * @author Arnuv Mayank
 *
 */
public class GUI extends Application implements UI {
    
    public static final String FXML_FILES_LOCATION = "src/views/";
	
    //these variables must be public for Main to start	
    public static final CountDownLatch latch = new CountDownLatch(1);
    public static GUI gui = null;
    
    //creating instances of a board view controller and 4 player view controllers, which are saved in an array
    private BoardViewController boardView = new BoardViewController();
    private PlayerViewController[] playerViews = {new PlayerViewController(), new PlayerViewController(), new PlayerViewController(), new PlayerViewController()};
    //an array list containing each of the player view stages
    private ArrayList<Stage> playerStages = new ArrayList<Stage>();

    /**
     * Allows JavaFX thread to start up from main
     * 
     * @return an object of the GUI
     */
    public static GUI waitForStartUpTest() {
	//this try except allows the GUI to start the JavaFX thread once the latch has counted down
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return gui;
    }

    /**
     * Method that needs to be called by the constructor to start the JavaFX thread
     * 
     * @param gui0 the GUI from the constructor
     */
    public static void setStartUpTest(GUI gui0) {
	//initialize GUI, then count down the latch which invokes waitForStartUpTest
        gui = gui0;
        latch.countDown();
    }

    /**
     * GUI constructor - uses setStartUpTest to start JavaFX thread
     */
    public GUI() {
        setStartUpTest(this);
    }

	/*
	 * all GUI methods will have a CountDownLatch that starts at 1
	 * then, using Platform.runLater, they switch from the main thread to the JavaFX thread
	 * and perform what the GUI needs to do
	 * Once the GUI action is complete, the latch counts down and completes,
	 * allowing the program to return to the main thread
	 */
	
	/**
	 * Shows what the student rolled
	 * 
	 * @param roll what the student rolled
	 */
    @Override
	public void showRoll(int roll) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				//changes the dice to show the roll, and displays the roll in the text
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

	/**
	 * Displays a message that the user does not have sufficient money to perform the task
	 */
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

	/**
	 * Prompts the student to roll the dice
	 * 
	 * @param student the student whose turn it is
	 */
	@Override
	public void rollDiceMenu(Student student) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boardView.setLabelText("Click ROLL for Student " + student.getPlayerNumber() + " to roll the die: ");
				//change the button text from OK to ROLL
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

	/**
	 * Creates a player in the UI
	 * 
	 * @param student the new player
	 */
	@Override
	public void createPlayer(Student student) {
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					//every time a new player is created, we must load another instance of the PlayerView fxml file
					FXMLLoader newLoader = new FXMLLoader(getClass().getResource("../views/PlayerView.fxml"));
					Parent newRoot = (Parent) newLoader.load();
					playerViews[student.getPlayerNumber() - 1] = newLoader.getController();
					//the controller will be attached to the corresponding controller in playerViews, based on player number
					PlayerViewController playerView = playerViews[student.getPlayerNumber() - 1];
					playerView.updatePlayerView(student);
					//create the new scene and stage for this player view
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
	
	/**
	 * Updates a player's info in the UI
	 * 
	 * @param student the student whose info is being updated
	 */
	@Override
	public void updatePlayer(Student student) {
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				//grabs the student's player view, then updates their player information
				PlayerViewController playerView = playerViews[student.getPlayerNumber() - 1];
				playerView.updatePlayerView(student);
				latch.countDown();
			}
			
		});
	}

	/**
	 * Displays a message that the student already owns this course
	 * 
	 * @param student the student who landed on the course
	 * 
	 * @param theCourse the course on which they landed
	 */
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

	/**
	 * Displays a message that the course the student has landed on is owned by someone else, so they owe them a tutorial fee
	 * 
	 * @param ower the student who owes money
	 * 
	 * @param owner the student who will receive the money
	 * 
	 * @param amountOwed the amount owed
	 */
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

	/**
	 * Displays a message that the ower successfully paid the owner the tutorial fee
	 * 
	 * @param ower the student who owed money
	 * 
	 * @param owner the student who received the money
	 * 
	 * @param amountOwed the amount transferred
	 */
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

	/**
	 * Displays a message that the student has successfully sold a course
	 * 
	 * @param student the student who sold the course
	 * 
	 * @param aCourse the course they sold
	 */
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

	/**
	 * Displays a message that the student has successfully bought a course
	 * 
	 * @param student the student who bought the course
	 * 
	 * @param aCourse the course they bought
	 */
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

	/**
	 * Displays a message that a faculty has successfully been upgraded
	 * 
	 * @param faculty the faculty that was upgraded
	 */
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

	/**
	 * Displays a message that a student is bankrupt and will be removed
	 * 
	 * @param student the student who is bankrupt
	 */
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

	/**
	 * Displays a message that the student must mortgage courses to get the money required to perform the action
	 * 
	 * @param student the student who must mortgage
	 */
	@Override
	public void removePlayerFromUI(Student student) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				//close that player's window when they're out
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

	/**
	 * Displays a message that the student must mortgage courses to get the money required to perform the action
	 * 
	 * @param student the student who must mortgage
	 */
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

	/**
	 * Provides the user with a list of all of their courses, and they pick one to sell
	 * 
	 * @param student the student selling a cousre
	 * 
	 * @return the course they want to sell
	 */
	@Override
	public Course sellCourseMenu(Student student) {
		final CountDownLatch latch = new CountDownLatch(1);
		ArrayList<String> choices = new ArrayList<String>();
		for (Course courseOwned: student.getCoursesOwned()) {
			choices.add(courseOwned.getTileName());
		}
		
		//StringProperty allows us to store strings between threads
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

	/**
	 * Prompts the user if they would like to buy a course
	 * 
	 * @param theCourse the course that they've landed on
	 * 
	 * @return true if they want to buy, false if not
	 */
	@Override
	public String upgradeFacultyMenu(ArrayList<ArrayList<Course>> upgradableFaculties) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		ArrayList<String> choices = new ArrayList<String>();
		for (int i = 0; i < upgradableFaculties.size(); i++) {
			choices.add(upgradableFaculties.get(i).get(0).getFaculty());
		}
		
		//StringProperty allows us to store strings between threads
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

	/**
	 * Prompts the user if they would like to buy a course
	 * 
	 * @param theCourse the course that they've landed on
	 * 
	 * @return true if they want to buy, false if not
	 */
	@Override
	public boolean displayPurchaseScreen(Course theCourse) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		//StringProperty allows us to store strings between threads
		final StringProperty stpResult = new SimpleStringProperty();
		
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Purchase Course Menu");
				alert.setHeaderText(null);
				alert.setContentText("Would you like to purchase " + theCourse.getTileName());
				//creates buttons for Yes and No
				ButtonType buttonTypeYes = new ButtonType("Yes");
				ButtonType buttonTypeNo = new ButtonType("No");


				alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
				Optional<ButtonType> result = alert.showAndWait();
				//determining which button they picked
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

	/**
	 * Displays that the student owns no property
	 */
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

	/**
	 * Displays that the student has insufficient assets to buy the course on which they've landed
	 * 
	 * @param student the student who landed on the course
	 * 
	 * @param theCourse the course on which they've landed
	 */
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

	/**
	 * Displays a message that it's this student's turn
	 * 
	 * @param student the student whose turn it is
	 */
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

	/**
	 * Offers the student their initial turn options (proceed, sell courses, upgrade faculty) based on what's available to them
	 * 
	 * @param ownsProperty whether or not the student owns property
	 * 
	 * @param upgradableFaculties the list of faculties (list of courses) that are upgradable
	 * 
	 * @return 1 to proceed, 2 for selling courses, 3 for upgrading a faculty
	 */
	@Override
	public int initialOptions(boolean ownsProperty, ArrayList<ArrayList<Course>> upgradableFaculties) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		//StringProperty allows us to store strings between threads
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
				//the buttons it shows depends on their available options
				//if they have courses to sell, then that gets added to the list of buttons
				if (ownsProperty) {
					//if they have faculties to upgrade, then that gets added to the list of buttons
					if (upgradableFaculties.size() != 0) {
						alert.getButtonTypes().setAll(buttonTypeProceed, buttonTypeSell, buttonTypeFaculty);
					} else {
						alert.getButtonTypes().setAll(buttonTypeProceed, buttonTypeSell);
					}
				} else {
					//proceed will always be an option
					alert.getButtonTypes().setAll(buttonTypeProceed);
				}
				Optional<ButtonType> result = alert.showAndWait();
				//determining which button they chose
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

	/**
	 * Displays the randomly selected community option
	 * 
	 * @param communityOn the community tile that triggered this call
	 * 
	 * @param communityOption the option that was chosen
	 */
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

	/**
	 * Displays the board
	 */
	@Override
	public void displayBoard() {
		// TODO Auto-generated method stub
		//this does nothing since the board is always displaying
	}

	/**
	 * Updates the student's location on the board and removes them if they're out
	 * 
	 * @param student the student whose location is being updated
	 */
	@Override
	public void updateBoard(Student student) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(() -> {
			//simply gives the board view controllerr the student whose location needs to be updated
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

	/**
	 * Displays the randomly selected chance option
	 * 
	 * @param chanceOn the chance tile that triggered this call
	 * 
	 * @param chanceOption the option that was chosen
	 */
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

	/**
	 * Displays a message that the student has landed in probation
	 * 
	 * @param student the student who landed in probation
	 */
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

	/**
	 * Displays information about how much longer the student is in probation for and how much they owe
	 * 
	 * @param student the student in probation
	 * 
	 * @param probation the Probation tile that triggered this call
	 */
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

	/**
	 * Displays that the student is in parking and how much they owe
	 * 
	 * @param student the student in parking
	 * 
	 * @param parking the Parking tile that triggered this call
	 */
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

	/**
	 * Displays that the student is still in probation
	 * 
	 * @param student the student in probation
	 */
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

	/**
	 * Displays all of the student's courses owned in order
	 * 
	 * @param student the student whose courses are being displayed
	 */
	@Override
	public void displayStudentCoursesOwned(Student student) {
		// TODO Auto-generated method stub
		//this does not do anything since the student's stats are always displaying
	}

	/**
	 * Displays the student's current money
	 * 
	 * @param student the student whose money is being displayed
	 */
	@Override
	public void displayStudentMoney(Student student) {
		// TODO Auto-generated method stub
		//this does not do anything since the student's stats are always displaying
	}
	
	/**
	 * Displays the student's money and courses owned
	 * 
	 * @param student the student whose stats are being displayed
	 */
	@Override
	public void displayStudentStats(Student student) {
		//this does not do anything since the student's stats are always displaying
	}

	/**
	 * Displays a message that the student has landed on Go and will receive money
	 * 
	 * @param goAmount the amount of money the student receives when landing on Go
	 */
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

	/**
	 * Prompts the user for the number of players in the game
	 * 
	 * @return the number of players (2 to 4)
	 */
	@Override
	public int askForNumPlayers() {
		final CountDownLatch latch = new CountDownLatch(1);
		ArrayList<String> choices = new ArrayList<String>();
		choices.add("2");
		choices.add("3");
		choices.add("4");
		
		//StringProperty allows us to store strings between threads
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

	/**
	 * Asks the user if student i is a human or computer
	 * 
	 * @param i the number of the student
	 * 
	 * @return true if human, false if computer
	 */
	@Override
	public boolean isStudentHuman(int i) {
		final CountDownLatch latch = new CountDownLatch(1);
		//StringProperty allows us to store strings between threads
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

	/**
	 * Displays a message that the turn is complete
	 */
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

	/**
	 * Lets the user perform any action to continue playing the game
	 */
	@Override
	public void continuePlaying() {
		// TODO Auto-generated method stub
		//this does nothing because the views are always there so there's no point freezing the game
	}

	/**
	 * Displays the winner of the game
	 * 
	 * @param student the student who won
	 */
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

	/**
	 * Closes the scanner that was taking input
	 */
	@Override
	public void closeScanner() {
		// TODO Auto-generated method stub
		//this does nothing because there is no scanner
	}
	
	/**
	 * start method overridden from Application
	 * 
	 * @param primaryStage the primary stage, which will hold the board view
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		//the board view should always be the first to load, then the player views after each player is selected
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/BoardView.fxml"));
		Parent root = (Parent) loader.load();
		boardView = loader.getController();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * main launches the application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}
}
