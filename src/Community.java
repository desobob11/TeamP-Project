import java.util.ArrayList;
import java.util.Random;

/**
 * This is a Community Tile that randomly chooses something for the student to do.
 * @author Arnuv Mayank 
 * @author Victor Manuel Campos Goitia Campos
 *
 */

public class Community extends Tile {
	private String[] communityOptions = {
            "Your good grades have been rewarded, you collect 400$ from scholarships",
            "It's your lucky day you find 50$ on the ground",
            "Grants have been doubled everyone collect 200$",
            "Someone hits your car on campus, luckily there were cameras collect 225$",
            "Your student loans have caught up with you, pay $400"};
	
	private int randCommunity;
	
	/*
	 * Creates a Tile object called Community.
	 * 
	 * @param position Tile number on board.
	 */
	public Community(int position) {
		super(position, "Community");
	}
	
	/*
	 * Fetches all strings of text telling user what card they've gotten 
	 * 
	 * @return The string array with community options.
	 */
	public String[] getCommunityOptions() {
		return communityOptions;
	}
	
	/*
	 * Calls the appropriate methods to act on each community
	 * 
	 * @param selection The number referencing which card case should be used.
	 * @param studentOn The student currently invoking Community.
	 * @param allStudents The list of all students.
	 */
	public int performCommunityOption(int selection, Student studentOn, ArrayList<Student> allStudents) {
		switch (selection) {
		case 0:
			studentOn.depositMoney(400);
			return 1;
		case 1:
			studentOn.depositMoney(50);
			return 1;
		case 2:
			for (Student student: allStudents) {
				student.depositMoney(200);
			}
			return 1;
		case 3:
			studentOn.depositMoney(225);
			return 1;
		case 4:
			return studentOn.withdrawMoney(400);
		}
		return 1;
	}
	
	@Override
	public int performTileAction(Student student, ArrayList<Student> students, UI UI, CourseList courseList) {
		//randomly shuffles the Community string options
		if (!this.getPerformedTileAction()) {
			randCommunity = new Random().nextInt(this.getCommunityOptions().length);
		}
		//returns the community option chosen
		UI.displayCommunityOption(this, randCommunity);
		return this.performCommunityOption(randCommunity, student, students);
	}
}
