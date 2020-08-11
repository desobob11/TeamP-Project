package model;
import java.util.ArrayList;

import application.UI;

/**
 * This is the course class, the most important child of Tile. It handles
 * everything related to course modification, ownership, etc. Student is what
 * handles buying and selling courses, but Course is still very important for
 * CourseList to perform its function.
 * 
 * @author Arnuv Mayank
 *
 */
public class Course extends Tile {
	private String faculty;
	private int buyPrice;
	private int sellPrice;
	private int tutorialPrice;
	private boolean ownedStatus;
	private int courseLevel;
	private int facultyUpgradeCost;
	private int initialFacultyUpgradeCost;
	private Student ownedBy = null;

	/**
	 * Constructs a Course with specified TileID, TileName, faculty, buyPrice,
	 * sellPrice, tutorialPrice. Sets the course level to 1 and ownedStatus to
	 * false.
	 * 
	 * @param tileID        The tile location on the board.
	 * @param tileName      The name of the Tile.
	 * @param faculty       The faculty group of the Course.
	 * @param buyPrice      The price to purchase the Course.
	 * @param sellPrice     The price to sell the Course.
	 * @param tutorialPrice The un-scaled price of tutorial.
	 */
	public Course(int tileID, String tileName, String faculty, int buyPrice, int sellPrice, int tutorialPrice) {
		super(tileID, tileName);
		this.faculty = faculty;
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
		this.tutorialPrice = tutorialPrice;
		this.ownedStatus = false;
		this.courseLevel = 1;

		initializeUpgradeCost();
	}

	/**
	 * Sets the prices of upgrade for each faculty.
	 */
	private void initializeUpgradeCost() {
		if (this.faculty == "Arts") {
			this.facultyUpgradeCost = 150;
			this.initialFacultyUpgradeCost = 150;
		} else if (this.faculty == "Sciences") {
			this.facultyUpgradeCost = 210;
			this.initialFacultyUpgradeCost = 210;
		} else if (this.faculty == "Business") {
			this.facultyUpgradeCost = 270;
			this.initialFacultyUpgradeCost = 270;
		} else if (this.faculty == "Engineering") {
			this.facultyUpgradeCost = 330;
			this.initialFacultyUpgradeCost = 330;
		}
	}

	/**
	 * Fetches the faculty of this Course.
	 * 
	 * @return Faculty name.
	 */
	public String getFaculty() {
		return faculty;
	}

	/**
	 * Fetches the Owner of this Course.
	 * 
	 * @return The Student who possesses this Course.
	 */
	public Student getOwner() {
		return ownedBy;
	}

	/**
	 * Sets the ownership of this course to a specified Student.
	 * 
	 * @param that The Student who is gaining the Course.
	 * @return The student who obtained the Course.
	 */
	public Student setOwner(Student that) {
		ownedBy = that;
		return ownedBy;
	}

	/**
	 * Fetches the price to purchase this Course.
	 * 
	 * @return Price of the Course.
	 */
	public int getBuyPrice() {
		return buyPrice;
	}

	/**
	 * Fetches the sale price of this Course.
	 * 
	 * @return Sale price.
	 */
	public int getSellPrice() {
		return sellPrice;
	}

	/**
	 * Fetches the un-scaled price of tutorial for this Course.
	 * 
	 * @return Un-scaled tutorial price.
	 */
	private int getTutorialPrice() {
		return tutorialPrice;
	}

	/**
	 * Fetches the scaled price of the tutorial for this Course.
	 * 
	 * @return Scaled tutorial price.
	 */
	public int getTutorialPriceOwed() {
		return getTutorialPrice() * courseLevel;
	}

	/**
	 * Fetches the ownership status of this Course.
	 * 
	 * @return Status of ownership.
	 */
	public boolean getOwnedStatus() {
		return ownedStatus;
	}

	/**
	 * Fetches the level of this Course.
	 * 
	 * @return Course level.
	 */
	public int getCourseLevel() {
		return courseLevel;
	}

	/**
	 * Fetches the cost to upgrade the faculty this Course belongs to.
	 * 
	 * @return Upgrade cost.
	 */
	public int getUpgradeCost() {
		return facultyUpgradeCost;
	}

	/**
	 * Sets the status of ownership of this Course.
	 * 
	 * @param check The status to change to.
	 * @return The updated status of ownership.
	 */
	public boolean setOwnedStatus(boolean check) {
		this.ownedStatus = check;
		return ownedStatus;
	}

	/**
	 * Levels up this Course when it is below the max level.
	 * 
	 * @return Numerical confirmation of level up: 1 if a Course level was added, or
	 *         -1 if the Course was maxed out.
	 */
	public int addCourseLevel() {
		//can only upgrade it if it's not fully upgraded
		if (!this.isMaxUpgraded()) {
			//the faculty upgrade cost increases after you upgrade it once
			if (this.courseLevel != 1) {
				this.facultyUpgradeCost *= 1.3;
			}
			this.courseLevel++;
			return 1;
		}
		return -1;
	}

	/**
	 * Resets this Course's level to 1 and reinitializes the faculty upgrade cost.
	 */
	public void resetCourseLevel() {
		this.courseLevel = 1;
		this.facultyUpgradeCost = initialFacultyUpgradeCost;
	}

	/**
	 * Fetches the Tile ID for this Course.
	 * 
	 * @return Tile ID.
	 */
	public int getTileID() {
		return super.getTileID();
	}
	
	/**
	 * Checks whether this Course is upgraded to the maximum level.
	 * 
	 * @return The boolean of the check.
	 */
	public boolean isMaxUpgraded() {
		return (this.courseLevel == 3 ? true : false);
	}

	/**
	 * Fetches (@code purchaseCourse) result from Student specified. Displays result
	 * in the UI.
	 * 
	 * @param student    The student purchasing the course.
	 * @param UI         The UI to be modified.
	 * @param courseList The list of Courses on the Board.
	 * 
	 * @return The numerical confirmation of the purchase. 1 (if action can be
	 *         completed), -1 (if player has not enough money and courses), or -2
	 *         (if player could sell courses to get enough money).
	 */
	private int purchaseCourse(Student student, UI UI, CourseList courseList) {
		int purchaseResult = student.purchaseCourse(this);
		
		//purchaseMenu already confirms that the student has sufficient net worth,
		//so the only possible options are that they can buy it outright or
		//sell some courses to get the required money
		if (purchaseResult == -2) {
			UI.insufficientMoneyError();
		} else if (purchaseResult == 1) {
			courseList.addToCoursesOwned(this);
			UI.displaySuccessfulPurchase(student, this);
		}

		return purchaseResult;
	}
	
	/**
	 * Checks if this Course can be purchased by a specified student and displays
	 * result of purchase attempt in the UI.
	 * 
	 * @param student    The student purchasing the course.
	 * @param UI         The UI to be modified.
	 * @param courseList The list of Courses on the Board.
	 * 
	 * @return Numerical confirmation of the purchase.
	 */
	private int purchaseMenu(Student student, UI UI, CourseList courseList) {
		//only given option to buy if their net worth is more than the buy price
		if (student.getNetWorth() >= this.getBuyPrice()) {
			//once this condition is met, then they get to choose if they'd like to buy it
			boolean wantsToBuy = student.studentBuyCourse(UI, this);
			if (wantsToBuy) {
				return purchaseCourse(student, UI, courseList);
			}
			return 1;
		} else {
			UI.displayInsufficientAssets(student, this);
			return 1;
		}
	}
	
	/**
	 * Completes a transaction of tutorial payment for this Course. Attempts to
	 * withdraw cost of tutorial from a specified student and deposits in account of
	 * this Course's owner.
	 * 
	 * @param student The student who is purchasing a tutorial.
	 * @param UI      The UI to be modified.
	 * 
	 * @return The numerical confirmation of the tutorial payment.
	 */
	private int tutorialPayment(Student student, UI UI) {
		int amountOwed = this.getTutorialPriceOwed();
		UI.displayCourseOwnedMenu(student, this.getOwner(), amountOwed);
		int withdrawalResult = student.withdrawMoney(amountOwed);
		if (withdrawalResult == 1) {
			this.getOwner().depositMoney(amountOwed);
			UI.updatePlayer(student);
			UI.updatePlayer(this.getOwner());
			UI.displayTutorialPaidScreen(student, this.getOwner(), amountOwed);
		}
		
		return withdrawalResult;
	}
	
	/**
	 * Fetches the appropriate methods of this Course when a student lands on it.
	 * 
	 * @param student    The student on this Course.
	 * @param students   The list of students in the game.
	 * @param UI         The UI to be modified.
	 * @param courseList The list of courses on the board.
	 * 
	 * @return Numerical confirmation of tile action.
	 */
	@Override
	public int performTileAction(Student student, ArrayList<Student> students, UI UI, CourseList courseList) {
		// TODO Auto-generated method stub
		//first checks if it's owned or not
		if (this.getOwnedStatus()) {
			//if it is owned, then it's either owned this student or someone else
			if (!this.getOwner().equals(student)) {
				//if someone else, then you owe them the tutorial fee
				return tutorialPayment(student, UI);
			} else {
				//otherwise, nothing happens
				UI.displayAlreadyOwned(student, this);
				return 1;
			}
		} else {
			//if it's not owned, then you potentially have the option of buying the course
			return purchaseMenu(student, UI, courseList);
		}
	}
}
