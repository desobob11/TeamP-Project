package model;
import java.util.*;

import application.TextVisualizer;
import application.UI;
import model.*;

/**
 * This is the class that has all of the game's ruleset/logic. It takes from all
 * the classes previously built, as well as the UI, and makes the entire game
 * happen
 * 
 * @author Arnuv Mayank
 *
 */
public class Logic {
	private int numStudents;
	private final int startingMoney = 1000;
	private ArrayList<Student> students;
	private CourseList courseList;
	private UI UI;

	public Logic(UI UI) {
		students = new ArrayList<Student>();
		courseList = new CourseList();
		this.UI = UI;
	}

	private Tile rollDice(Student student) {
		int roll = new Random().nextInt(6) + 1;
		student.moveForward(roll, courseList.getBoardSize());
		UI.showRoll(roll);
		return courseList.getTileAt(student.getPlayerPosition());
	}

	private void removeStudentFromGame(Student student) {
		//first must make all adjustments to the course and courseList
		for (Course course : student.getCoursesOwned()) {
			course.resetCourseLevel();
			course.setOwnedStatus(false);
			course.setOwner(null);
			courseList.removeFromCoursesOwned(course);
		}
		//then makes adjustments to the student
		students.remove(student);
		UI.removePlayerFromUI(student);
		student.studentOut();
		//removes them from the board
		UI.updateBoard(student);
	}
	
	private void upgradeLevelMenu(Student student) {
		String faculty = student.studentUpgradeFaculty(UI);
		int upgradeResult = student.upgradeFacultyLevel(faculty);
		//this method can only be called if the student has a faculty to potentially upgrade
		//but they still might not have enough money, so it handles this case as well and lets
		//them sell courses to get that money
		if (upgradeResult == 1) {
			UI.displaySuccessfulUpgrade(faculty);
		} else if (upgradeResult == -2) {
			UI.displayMustMortgageScreen(student);
			student.studentSellCourse(UI, courseList);
			this.upgradeLevelMenu(student);
		}
	}
	
	private void displayStudentStats() {
		for (Student aStudent : students) {
			UI.displayStudentStats(aStudent);
		}
	}

	private void completeTurn(Student student, int counter) {
		UI.displayBoard();
		UI.displayStudentStats(student);
		Tile landingTile = courseList.getTileAt(student.getPlayerPosition());
		UI.turnMainMenu(student);
		int initialChoice = student.studentInitialOption(UI); 
		//if they choose to proceed with their turn
		if (initialChoice == 1) {
			//should only roll the dice and move forward if they're not in probation
			if (!student.isInJail()) {
				UI.rollDiceMenu(student);
				landingTile = rollDice(student);
				UI.updatePlayer(student);
				UI.updateBoard(student);
				UI.displayBoard();
			}
			//polymorphic call performTileAction - this is abstract in Tile and defined in all of its children
			//so each type of tile will do something different when this method is called, and Logic does not
			//need to worry about what the actual tile is
			int tileActionResult = landingTile.performTileAction(student, students, UI, courseList);
			UI.updatePlayer(student);
			//the tile has already performed its action once this turn, so it shouldn't do any of the preliminary
			//actions if it is called again this turn
			landingTile.setPerformedTileAction(true);
			//as long as they do not have enough cash to perform the tile action but still have the net worth
			while (tileActionResult == -2) {
				UI.displayMustMortgageScreen(student);
				student.studentSellCourse(UI, courseList);
				//then it should prompt them to perform the tile action again after selling some courses
				tileActionResult = landingTile.performTileAction(student, students, UI, courseList);
				UI.updatePlayer(student);
			}
			//if they do not have the net worth to perform the tile action then they lose
			if (tileActionResult == -1) {
				removeStudentFromGame(student);
				UI.updateBoard(student);
			}
		//if they choose to sell courses at the beginning of their turn
		} else if (initialChoice == 2) {
			student.studentSellCourse(UI, courseList);
			//recursively call completeTurn with 2 to indicate there has been one run-through
			completeTurn(student, 2);
		//if they choose to upgrade a faculty at the beginning of their turn
		} else {
			upgradeLevelMenu(student);
			//recursively call completeTurn with 2 to indicate there has been one run-through
			completeTurn(student, 2);
		}
		
		//this method is called recurisvely, but these UI methods should only be called once per turn
		if (counter == 1) {
			displayStudentStats();
			UI.displayTurnComplete();
			UI.continuePlaying();
		}
		//now that the turn has finished, the tile will go back to not having performed its action for the next turn
		landingTile.setPerformedTileAction(false);
	}

	public void run() {
		numStudents = UI.askForNumPlayers();
		students = new ArrayList<Student>();
		Student student;
		int turn = 0;
		
		//checks if each student is human or computer, then adds them
		for (int i = 1; i <= numStudents; i++) {
			if (UI.isStudentHuman(i)) {	
				students.add(new HumanStudent(i, startingMoney));
			} else {
				students.add(new ComputerStudent(i, startingMoney));
			}
			//generates their player profile and adds them to the board
			UI.createPlayer(students.get(i - 1));
			UI.updateBoard(students.get(i - 1));
		}

		//the game continues until there is one player left
		while (students.size() != 1) {
			//get the student whose turn it is and complete the turn
			student = students.get(turn);
			completeTurn(student, 1);
			//if they have lose, then remove them and adjust the turn counter appropriately
			if (student.getPlayerPosition() == -1) {
				students.remove(student);
				numStudents--;
				turn--;
			}

			turn++;
			turn %= numStudents;
		}
		UI.closeScanner();
		UI.displayWinner(students.get(0));
	}
}
