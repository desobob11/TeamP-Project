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
		for (Course course : student.getCoursesOwned()) {
			course.resetCourseLevel();
			course.setOwnedStatus(false);
			course.setOwner(null);
			courseList.removeFromCoursesOwned(course);
		}
		students.remove(student);
		UI.removePlayerFromUI(student);
		student.studentOut();
		UI.updateBoard(student);
	}
	
	private void upgradeLevelMenu(Student student) {
		String faculty = student.studentUpgradeFaculty(UI);
		int upgradeResult = student.upgradeFacultyLevel(faculty);
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
		if (initialChoice == 1) {
			if (!student.isInJail()) {
				UI.rollDiceMenu(student);
				landingTile = rollDice(student);
				UI.updatePlayer(student);
				UI.updateBoard(student);
				UI.displayBoard();
			}
			int tileActionResult = landingTile.performTileAction(student, students, UI, courseList);
			UI.updatePlayer(student);
			landingTile.setPerformedTileAction(true);
			while (tileActionResult == -2) {
				UI.displayMustMortgageScreen(student);
				student.studentSellCourse(UI, courseList);
				tileActionResult = landingTile.performTileAction(student, students, UI, courseList);
				UI.updatePlayer(student);
			}
			if (tileActionResult == -1) {
				removeStudentFromGame(student);
				UI.updateBoard(student);
			}
		} else if (initialChoice == 2) {
			student.studentSellCourse(UI, courseList);
			completeTurn(student, 2);
		} else {
			upgradeLevelMenu(student);
			completeTurn(student, 2);
		}

		if (counter == 1) {
			displayStudentStats();
			UI.displayTurnComplete();
			UI.continuePlaying();
		}
		landingTile.setPerformedTileAction(false);
	}

	public void run() {
		numStudents = UI.askForNumPlayers();
		students = new ArrayList<Student>();
		Student student;
		int turn = 0;

		for (int i = 1; i <= numStudents; i++) {
			if (UI.isStudentHuman(i)) {	
				students.add(new HumanStudent(i, startingMoney));
			} else {
				students.add(new ComputerStudent(i, startingMoney));
			}
			UI.createPlayer(students.get(i - 1));
			UI.updateBoard(students.get(i - 1));
		}

		while (students.size() != 1) {
			student = students.get(turn);
			completeTurn(student, 1);
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
