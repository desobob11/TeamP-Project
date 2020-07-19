import java.util.ArrayList;

public class Student {
	private int playerNumber;
	private int playerMoney;
	private int playerPosition = 0;
	private ArrayList<Course> coursesOwned;
	private boolean ownsFacultyOne = false;
	private boolean ownsFacultyTwo = false;
	private boolean ownsFacultyThree = false;
	private boolean ownsFacultyFour = false;
	
	public Student() {
		
	}
	
	public Student(int playerNumber, int playerMoney)
	{
		if (playerNumber > 0 && playerNumber < 5) {	
			this.playerNumber = playerNumber;
		}
		if (playerMoney > 0) {
			this.playerMoney = playerMoney;
		}
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
	
	private int withdrawMoney(int money) {
		if (money <= playerMoney) {
			playerMoney -= money;
			return 1;
		}
		return -1;
	}
	
	private int depositMoney(int money) {
		if (money >= 0) {
			playerMoney += money;
			return 1;
		}
		return -1;
	}
	
	public int purchaseCourse(Course aCourse) {
		if (!aCourse.getOwnedStatus()) {
			if (withdrawMoney(aCourse.getBuyPrice()) == 1) {
				aCourse.setOwnedStatus(true);
				aCourse.setOwner(this);
				this.addCourse(aCourse);
				return 1;
			}
			return -2;
		}
		return -1;
	}
	
	public int sellCourse(Course aCourse) {
		if (this.doesStudentOwnCourse(aCourse)) {
			aCourse.setOwnedStatus(false);
			aCourse.setOwner(null);
			depositMoney(aCourse.getSellPrice());
			return (removeCourse(aCourse) ? -2 : 0);
		}
		return -1;
	}
	
	public void moveForward(int spaces, int boardSize) {
		playerPosition += spaces;
		playerPosition %= boardSize;
	}
}
