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
		//will only add them as a player if their player number is between 0 and 4
		if (playerNumber > 0 && playerNumber < 5) {
			this.playerNumber = playerNumber;
		}
		if (playerMoney > 0) {
			this.playerMoney = playerMoney;
		}
		//initialize the hashmaps for faculty ownership
		initializeCoursesOwnedOfFaculty();
		initializeOwnsFaculty();
	}

	private void initializeCoursesOwnedOfFaculty() {
		coursesOwnedOfFaculty = new HashMap<String, ArrayList<Course>>();

		//these lists will all start as empty since you start with no courses
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
		//all of the map values will be false since you start with no courses and thus no faculties owned
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
		//3 cases
		//1) they can withdraw the money outright (1)
		if (money <= playerMoney) {
			playerMoney -= money;
			return 1;
		//2) they don't have the money but if they sell some courses then they will (-2)
		} else if (!isBankrupt(money)) {
			return -2;
		}
		//3) they don't have the money required (-1)
		return -1;
	}

	public int depositMoney(int money) {
		if (money >= 0) {
			playerMoney += money;
			return 1;
		}
		return -1;
	}

	private int getAssetsValue() {
		int mortgageValue = 0;
		//goes through every course owned and sums their sell prices
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
		//iterates through each faculty
		for (String faculty : ownsFaculty.keySet()) {
			//grabs the corresponding array list of courses owned in each faculty
			ArrayList<Course> coursesOwnedOfAFaculty = this.coursesOwnedOfFaculty.get(faculty);
			//a faculty is upgradable with 3 conditions
			//1) they own all of the courses in the faculty
			//2) they have sufficient net worth to pay the upgrade price
			//3) the faculty upgrade level is not at its maximum
			if (ownsFaculty.get(faculty) && this.getNetWorth() >= coursesOwnedOfAFaculty.get(0).getUpgradeCost() && !coursesOwnedOfAFaculty.get(0).isMaxUpgraded()) {
				//if these are met, then this faculty is added to the list of upgradable ones
				upgradableFaculties.add(coursesOwnedOfAFaculty);
			}
		}
		return upgradableFaculties;
	}

	public boolean isBankrupt(int money) {
		return (getNetWorth() < money ? true : false);
	}

	public int purchaseCourse(Course aCourse) {
		//first check that the Course provided isn't null (safety check) (-4)
		if (!aCourse.equals(null)) {	
			//then ensure the Course isn't owned already (another safety check) (-3)
			if (!aCourse.getOwnedStatus()) {
				int withdrawResult = withdrawMoney(aCourse.getBuyPrice());
				//if they can pay outright, then purchase the course
				//otherwise, they'd need to sell courses or they don't have enough assets (the latter is another safety check)
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
				//return the result of the purchase attempt
				return withdrawResult;
			}
			return -3;
		}
		return -4;
	}

	public int sellCourse(Course aCourse) {
		//ensures that they own the course that they're attempting to sell (safety check) (-3)
		if (this.doesStudentOwnCourse(aCourse)) {
			//reset all the stats of the course
			aCourse.setOwnedStatus(false);
			aCourse.setOwner(null);
			//ensure all the courses' upgrade levels are reset in the faculty
			for (Course course: this.coursesOwnedOfFaculty.get(aCourse.getFaculty())) {
				course.resetCourseLevel();
			}
			//remove from the list of courses owned, faculty ownership, and get the money back
			this.ownsFaculty.put(aCourse.getFaculty(), false);
			depositMoney(aCourse.getSellPrice());
			return (removeCourse(aCourse) ? -1 : 1);
		}
		return -3;
	}

	public int upgradeFacultyLevel(String faculty) {
		//ensures that they own the faculty they want to upgrade (safety check) (-4)
		if (this.ownsFaculty.get(faculty)) {
			ArrayList<Course> coursesOfFaculty = this.coursesOwnedOfFaculty.get(faculty);
			//ensures the course is not max upgraded (safety check) (-3)
			if (!coursesOfFaculty.get(0).isMaxUpgraded()) {
				int upgradeResult = this.withdrawMoney(coursesOfFaculty.get(0).getUpgradeCost());
				//if they have the required money then go ahead and upgrade
				if (upgradeResult == 1) {	
					//upgrading the level for all courses in the faculty
					for (Course course : coursesOfFaculty) {
						course.addCourseLevel();
					}
				}
				//return the upgrade attempt result
				return upgradeResult;
			}
			return -3;
		}
		return -4;
	}

	public void studentOut() {
		//set their playerPosition to -1 and remove all of their courses
		coursesOwned.removeAll(coursesOwned);
		previousPlayerPosition = playerPosition;
		playerPosition = -1;
	}

	public int moveToClosestParking(int parking1Pos, int parking2Pos) {
		int spacesToParking1 = Math.abs(parking1Pos - playerPosition);
		int spacesToParking2 = Math.abs(parking2Pos - playerPosition);

		previousPlayerPosition = playerPosition;
		//calculating which parking space is nearer, then moving them there
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
		//for when they loop around the board
		playerPosition %= boardSize;
	}

	public boolean isInJail() {
		return inJail;
	}

	public void goToJail() {
		inJail = true;
	}

	public void incrementTurnsInProbation() {
		//ensures that they're in jail (safety check)
		if (inJail) {
			durationInProbation++;
			//once they've gotten to 3 turns, then remove them from jail
			if (durationInProbation > 2) {
				durationInProbation = 0;
				inJail = false;
			}
		}
	}
	
	//these methods are abstract because they differ for a human and computer student, and will be defined separately there
	public abstract int studentInitialOption(UI UI);
	public abstract void studentSellCourse(UI UI, CourseList courseList);
	public abstract boolean studentBuyCourse(UI UI, Course aCourse);
	public abstract String studentUpgradeFaculty(UI UI);
}
