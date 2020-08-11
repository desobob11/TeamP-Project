package model;
import java.util.*;

import application.UI;

import java.lang.Math;

/**
 * Student - this is an abstract class that is the actual player of the game, and it handles
 * everything related to student actions and corresponding changes to their attributes.
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

	/**
	 * empty constructor for Student
	 */
	public Student() {
		coursesOwned = new ArrayList<Course>();
		initializeCoursesOwnedOfFaculty();
		initializeOwnsFaculty();
	}

	/**
	 * commonly used constructor of Student
	 * 
	 * @param playerNumber - the player's number
	 * @param playerMoney - the player's starting money
	 */
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

	/**
	 * initializes the ArrayLists and HashMaps of faculty courses owned to be empty
	 */
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

	/**
	 * initializes HashMap of faculty ownership to be false
	 */
	private void initializeOwnsFaculty() {
		ownsFaculty = new HashMap<String, Boolean>();
		//all of the map values will be false since you start with no courses and thus no faculties owned
		ownsFaculty.put("Arts", false);
		ownsFaculty.put("Sciences", false);
		ownsFaculty.put("Business", false);
		ownsFaculty.put("Engineering", false);
	}

	/**
	 * Gets player number
	 * 
	 * @return the player's number
	 */
	public int getPlayerNumber() {
		return playerNumber;
	}

	/**
	 * Gets the player's money
	 * 
	 * @return the player's money
	 */
	public int getPlayerMoney() {
		return playerMoney;
	}

	/**
	 * Gets the player's position
	 * 
	 * @return the player's position
	 */
	public int getPlayerPosition() {
		return playerPosition;
	}

	/**
	 * Gets the player's previous position
	 * 
	 * @return the player's previous position
	 */
	public int getPreviousPlayerPosition() {
		return previousPlayerPosition;
	}

	/**
	 * Gets the player's courses owned
	 * 
	 * @return the player's courses owned
	 */
	public ArrayList<Course> getCoursesOwned() {
		return coursesOwned;
	}

	/**
	 * Checks if the student owns a specific course
	 * 
	 * @param aCourse the course being checked
	 * 
	 * @return true if owned, false if not
	 */
	public boolean doesStudentOwnCourse(Course aCourse) {
		return (coursesOwned.contains(aCourse) ? true : false);
	}

	/**
	 * Adds a course to the courses owned list
	 * 
	 * @param aCourse the course to be added
	 */
	private void addCourse(Course aCourse) {
		this.coursesOwned.add(aCourse);
	}

	/**
	 * Removes a course from the courses owned list
	 * 
	 * @param aCourse the course to be removed
	 * 
	 * @return true if successfully removed, false if not
	 */
	private boolean removeCourse(Course aCourse) {
		return this.coursesOwned.remove(aCourse);
	}

	/**
	 * Withdraws money from student if it can
	 * 
	 * @param money the amount of money to be withdrawn
	 * 
	 * @return 1 if successfully withdrawn, -2 if they must sell courses to get this money, -1 if it's impossible to get the money
	 */
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

	/**
	 * Deposits money to a student
	 * 
	 * @param money the amount of money to be deposited
	 * 
	 * @return 1 if successfully deposited, -1 if the money amount inputed was negative
	 */
	public int depositMoney(int money) {
		if (money >= 0) {
			playerMoney += money;
			return 1;
		}
		return -1;
	}

	/**
	 * Gets the money value of the player's assets
	 * 
	 * @return the money value of the player's assets
	 */
	private int getAssetsValue() {
		int mortgageValue = 0;
		//goes through every course owned and sums their sell prices
		for (Course courseOwned : coursesOwned) {
			mortgageValue += courseOwned.getSellPrice();
		}

		return mortgageValue;
	}

	/**
	 * Gets student's net worth, which is the sum of their money and assets value
	 * 
	 * @return their net worth
	 */
	public int getNetWorth() {
		return getAssetsValue() + playerMoney;
	}

	/**
	 * Gets the amount of a time a student has been in probation
	 * 
	 * @return the number of turns a student has been in probation
	 */
	public int getDurationInProbation() {
		return durationInProbation;
	}

	/**
	 * checks if a student owns any courses
	 * 
	 * @return true if they own at least one course, false otherwise
	 */
	public boolean doesStudentOwnProperty() {
		return (coursesOwned.size() == 0 ? false : true);
	}
	
	/**
	 * Gets a student's upgradable faculties
	 * 
	 * @return the student's upgradable faculties
	 */
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

	/**
	 * checks if student would be bankrupt with a certain amount of money
	 * 
	 * @param money the amount of money that they'd need to pay
	 * 
	 * @return true if it would make them bankrupt, false if not
	 */
	public boolean isBankrupt(int money) {
		return (getNetWorth() < money ? true : false);
	}

	/**
	 * Attempts for the student to buy a course
	 * 
	 * @param aCourse the course they're attempting to buy
	 * 
	 * @return 1 if they successfully buy it, -1 if they're incapable of buying it,
	 * -2 if they need to sell courses to buy it, -3 if it's owned already, and -4 if the course is null
	 */
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

	/**
	 * Attempts for the student to sell a course
	 * 
	 * @param aCourse the course they want to sell
	 * 
	 * @return 1 if they successfully sell it, -3 if they didn't own the course
	 */
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

	/**
	 * Attempts for a student to upgrade a faculty level
	 * 
	 * @param faculty the faculty they want to upgrade
	 * 
	 * @return 1 for a successfully upgrade, -1 if they don't have the assets necessary to upgrade
	 * -2 if they need to sell courses to get the assets they need to upgrade, -3 if the faculty is
	 * max upgraded, and -4 if they don't own the faculty
	 */
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

	/**
	 * Performs all tasks on the Student object to remove them from the game
	 */
	public void studentOut() {
		//set their playerPosition to -1 and remove all of their courses
		coursesOwned.removeAll(coursesOwned);
		previousPlayerPosition = playerPosition;
		playerPosition = -1;
	}

	/**
	 * Moves the student to the nearest parking
	 * 
	 * @param parking1Pos the position of the first parking spot
	 * 
	 * @param parking2Pos the position of the second parking spot
	 * 
	 * @return 0 if they went to the first, 1 if they went to the second
	 */
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

	/**
	 * Moves the student to probation
	 * 
	 * @param probationPos the position of the probation tile
	 */
	public void moveToProbation(int probationPos) {
		previousPlayerPosition = playerPosition;
		playerPosition = probationPos;
		inJail = true;
	}

	/**
	 * Moves the student forward some number of spaces
	 * 
	 * @param spaces the number of spaces they'll move
	 * 
	 * @param boardSize the size of the board
	 */
	public void moveForward(int spaces, int boardSize) {
		previousPlayerPosition = playerPosition;
		playerPosition += spaces;
		//for when they loop around the board
		playerPosition %= boardSize;
	}

	/**
	 * checks if the student is in jail
	 * 
	 * @return true if they are, false if not
	 */
	public boolean isInJail() {
		return inJail;
	}

	/**
	 * Puts the student in jail
	 */
	public void goToJail() {
		inJail = true;
	}

	/**
	 * Increments the number of turns the student has been in probation
	 */
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
	/**
	 * Let's the student choose which of their available initial options they'd like to choose
	 * Proceed, sell course, or upgrade faculty
	 * 
	 * @param UI Logic's instance of UI for this to use
	 * 
	 * @return 1 for proceed, 2 for sell courses, 3 for upgrade faculty
	 */
	public abstract int studentInitialOption(UI UI);
	
	/**
	 * Allows student to continue selling a course until the cycle ends
	 * 
	 * @param UI Logic's instance of UI for this to use
	 * 
	 * @param courseList Logic's instance of CourseList for this to use
	 */
	public abstract void studentSellCourse(UI UI, CourseList courseList);
	
	/**
	 * Menu for human student to choose if they want to buy the course they've landed on
	 * 
	 * @param UI Logic's instance of UI for this to use
	 * 
	 * @param aCourse the course that they've landed on
	 * 
	 * @return true if they want to buy the course, false if not
	 */
	public abstract boolean studentBuyCourse(UI UI, Course aCourse);
	
	/**
	 * Menu for student to choose to upgrade a faculty
	 * 
	 * @param UI Logic's instance of UI for this to use
	 */
	public abstract String studentUpgradeFaculty(UI UI);
}
