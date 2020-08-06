package model;
import java.util.*;

import application.UI;

import java.lang.Math;

/**
 * Student - this class is the actual player of the game, and it handles
 * everything related to student actions and corresponding changes to their
 * attributes.
 * 
 * @author Arnuv Mayank
 *
 */

public abstract class Student {
	private int playerNumber;
	private int playerMoney;
	private int playerPosition = 0;
	private int previousPlayerPosition = -1;
	private ArrayList<Course> coursesOwned;
	private HashMap<String, ArrayList<Course>> coursesOwnedOfFaculty;
	private HashMap<String, Boolean> ownsFaculty;
	private boolean inJail = false;
	private int durationInProbation = 0;

	public Student() {
		coursesOwned = new ArrayList<Course>();
		initializeCoursesOwnedOfFaculty();
		initializeOwnsFaculty();
	}

	public Student(int playerNumber, int playerMoney) {
		coursesOwned = new ArrayList<Course>();
		if (playerNumber > 0 && playerNumber < 5) {
			this.playerNumber = playerNumber;
		}
		if (playerMoney > 0) {
			this.playerMoney = playerMoney;
		}
		initializeCoursesOwnedOfFaculty();
		initializeOwnsFaculty();
	}

	private void initializeCoursesOwnedOfFaculty() {
		coursesOwnedOfFaculty = new HashMap<String, ArrayList<Course>>();

		ArrayList<Course> artsCoursesOwned = new ArrayList<Course>();
		ArrayList<Course> sciencesCoursesOwned = new ArrayList<Course>();
		ArrayList<Course> businessCoursesOwned = new ArrayList<Course>();
		ArrayList<Course> engineeringCoursesOwned = new ArrayList<Course>();

		coursesOwnedOfFaculty.put("Arts", artsCoursesOwned);
		coursesOwnedOfFaculty.put("Sciences", sciencesCoursesOwned);
		coursesOwnedOfFaculty.put("Business", businessCoursesOwned);
		coursesOwnedOfFaculty.put("Engineering", engineeringCoursesOwned);
	}

	private void initializeOwnsFaculty() {
		ownsFaculty = new HashMap<String, Boolean>();
		ownsFaculty.put("Arts", false);
		ownsFaculty.put("Sciences", false);
		ownsFaculty.put("Business", false);
		ownsFaculty.put("Engineering", false);
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public int getPlayerMoney() {
		return playerMoney;
	}

	public int getPlayerPosition() {
		return playerPosition;
	}

	public int getPreviousPlayerPosition() {
		return previousPlayerPosition;
	}

	public ArrayList<Course> getCoursesOwned() {
		return coursesOwned;
	}

	public boolean doesStudentOwnCourse(Course aCourse) {
		return (coursesOwned.contains(aCourse) ? true : false);
	}

	private void addCourse(Course aCourse) {
		this.coursesOwned.add(aCourse);
	}

	private boolean removeCourse(Course aCourse) {
		return this.coursesOwned.remove(aCourse);
	}

	public int withdrawMoney(int money) {
		if (money <= playerMoney) {
			playerMoney -= money;
			return 1;
		} else if (!isBankrupt(money)) {
			return -2;
		}
		return -1;
	}

	public int depositMoney(int money) {
		if (money >= 0) {
			playerMoney += money;
			return 1;
		}
		return -1;
	}

	public int getAssetsValue() {
		int mortgageValue = 0;

		for (Course courseOwned : coursesOwned) {
			mortgageValue += courseOwned.getSellPrice();
		}

		return mortgageValue;
	}

	public int getNetWorth() {
		return getAssetsValue() + playerMoney;
	}

	public int getDurationInProbation() {
		return durationInProbation;
	}

	public boolean doesStudentOwnProperty() {
		return (coursesOwned.size() == 0 ? false : true);
	}
	
	public ArrayList<ArrayList<Course>> getUpgradableFaculties() {
		ArrayList<ArrayList<Course>> upgradableFaculties = new ArrayList<ArrayList<Course>>();
		for (String faculty : ownsFaculty.keySet()) {
			ArrayList<Course> coursesOwnedOfAFaculty = this.coursesOwnedOfFaculty.get(faculty);
			if (ownsFaculty.get(faculty) && this.getNetWorth() >= coursesOwnedOfAFaculty.get(0).getUpgradeCost() && !coursesOwnedOfAFaculty.get(0).isMaxUpgraded()) {
				upgradableFaculties.add(coursesOwnedOfAFaculty);
			}
		}
		return upgradableFaculties;
	}

	public boolean isBankrupt(int money) {
		return (getNetWorth() < money ? true : false);
	}

	public int purchaseCourse(Course aCourse) {
		if (!aCourse.equals(null)) {	
			if (!aCourse.getOwnedStatus()) {
				int withdrawResult = withdrawMoney(aCourse.getBuyPrice());
				if (withdrawResult == 1) {
					aCourse.setOwnedStatus(true);
					aCourse.setOwner(this);
					this.addCourse(aCourse);
	
					String courseFaculty = aCourse.getFaculty();
					ArrayList<Course> courseOfFaculty = coursesOwnedOfFaculty.get(courseFaculty);
					courseOfFaculty.add(aCourse);
					coursesOwnedOfFaculty.put(courseFaculty, courseOfFaculty);
					if (courseOfFaculty.size() == 3) {
						ownsFaculty.put(courseFaculty, true);
						for (Course course : courseOfFaculty) {
							course.addCourseLevel();
						}
					}
				}
				return withdrawResult;
			}
			return -3;
		}
		return -4;
	}

	public int sellCourse(Course aCourse) {
		if (this.doesStudentOwnCourse(aCourse)) {
			aCourse.setOwnedStatus(false);
			aCourse.setOwner(null);
			for (Course course: this.coursesOwnedOfFaculty.get(aCourse.getFaculty())) {
				course.resetCourseLevel();
			}
			this.ownsFaculty.put(aCourse.getFaculty(), false);
			depositMoney(aCourse.getSellPrice());
			return (removeCourse(aCourse) ? -1 : 1);
		}
		return -3;
	}

	public int upgradeFacultyLevel(String faculty) {
		if (this.ownsFaculty.get(faculty)) {
			ArrayList<Course> coursesOfFaculty = this.coursesOwnedOfFaculty.get(faculty);
			if (!coursesOfFaculty.get(0).isMaxUpgraded()) {
				int upgradeResult = this.withdrawMoney(coursesOfFaculty.get(0).getUpgradeCost());
				if (upgradeResult == 1) {	
					for (Course course : coursesOfFaculty) {
						course.addCourseLevel();
					}
				}
				return upgradeResult;
			}
			return -3;
		}
		return -4;
	}

	public void studentOut() {
		coursesOwned.removeAll(coursesOwned);
		previousPlayerPosition = playerPosition;
		playerPosition = -1;
	}

	public int moveToClosestParking(int parking1Pos, int parking2Pos) {
		int spacesToParking1 = Math.abs(parking1Pos - playerPosition);
		int spacesToParking2 = Math.abs(parking2Pos - playerPosition);

		previousPlayerPosition = playerPosition;
		if (spacesToParking1 < spacesToParking2) {
			playerPosition = parking1Pos;
			return 0;
		} else {
			playerPosition = parking2Pos;
			return 1;
		}
	}

	public void moveToProbation(int probationPos) {
		previousPlayerPosition = playerPosition;
		playerPosition = probationPos;
		inJail = true;
	}

	public void moveForward(int spaces, int boardSize) {
		previousPlayerPosition = playerPosition;
		playerPosition += spaces;
		playerPosition %= boardSize;
	}

	public boolean isInJail() {
		return inJail;
	}

	public void goToJail() {
		inJail = true;
	}

	public void incrementTurnsInProbation() {
		if (inJail) {
			durationInProbation++;
			if (durationInProbation > 2) {
				durationInProbation = 0;
				inJail = false;
			}
		}
	}
	
	public abstract int studentInitialOption(UI UI);
	public abstract void studentSellCourse(UI UI, CourseList courseList);
	public abstract boolean studentBuyCourse(UI UI, Course aCourse);
	public abstract String studentUpgradeFaculty(UI UI);
}
