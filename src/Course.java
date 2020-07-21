
public class Course extends Tile {
	private String faculty;
	private int buyPrice;
	private int sellPrice;
	private int tutorialPrice;
	private boolean ownedStatus;
	private int courseLevel;
	private Student ownedBy = null;
	
	public Course(int tileID, String tileName, String faculty, int buyPrice, int sellPrice, int tutorialPrice) {
		super(tileID, tileName);
		this.faculty = faculty;
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
		this.tutorialPrice = tutorialPrice;
		this.ownedStatus = false;
		this.courseLevel = 1;
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
	
	public boolean setOwnedStatus(boolean check) {
		this.ownedStatus = check;
		return ownedStatus;
	}
	
	public int addCourseLevel() {
		if (this.courseLevel < 3) {
			this.courseLevel++;
			return 1;
		}
		return -1;
	}
	
	public void resetCourseLevel() {
		this.courseLevel = 1;
	}
	
	public int getTileId() {
		return this.getTileId();
	}
		
		
}

