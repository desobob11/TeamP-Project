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

	public void initializeUpgradeCost() {
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

	public String getFaculty() {
		return faculty;
	}

	public Student getOwner() {
		return ownedBy;
	}

	public Student setOwner(Student that) {
		ownedBy = that;
		return ownedBy;
	}

	public int getBuyPrice() {
		return buyPrice;
	}

	public int getSellPrice() {
		return sellPrice;
	}

	private int getTutorialPrice() {
		return tutorialPrice;
	}

	public int getTutorialPriceOwed() {
		return getTutorialPrice() * courseLevel;
	}

	public boolean getOwnedStatus() {
		return ownedStatus;
	}

	public int getCourseLevel() {
		return courseLevel;
	}

	public int getUpgradeCost() {
		return facultyUpgradeCost;
	}

	public boolean setOwnedStatus(boolean check) {
		this.ownedStatus = check;
		return ownedStatus;
	}

	public int addCourseLevel() {
		if (this.courseLevel < 4) {
			if (this.courseLevel != 1) {
				this.facultyUpgradeCost *= 1.3;
			}
			this.courseLevel++;
			return 1;
		}
		return -1;
	}

	public void resetCourseLevel() {
		this.courseLevel = 1;
		this.facultyUpgradeCost = initialFacultyUpgradeCost;
	}

	public int getTileID() {
		return super.getTileID();
	}

}
