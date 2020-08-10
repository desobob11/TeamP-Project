package model;
import java.util.ArrayList;
import java.util.Random;

import application.UI;

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
	
	/**
	 * Creates a Tile object called Community.
	 * 
	 * @param position Tile number on board.
	 */
	public Community(int position) {
		super(position, "Community");
	}
	
	/**
	 * Fetches all strings of text telling user what card they've gotten 
	 * 
	 * @return The string array with community options.
	 */
	public String[] getCommunityOptions() {
		return communityOptions;
	}
	
	/**
	 * Calls the appropriate methods to act on each community
	 * 
	 * @param selection The number referencing which card case should be used.
	 * @param studentOn The student currently invoking Community.
	 * @param allStudents The list of all students.
	 * @param UI The UI to be modified.
	 */
	private int performCommunityOption(int selection, Student studentOn, ArrayList<Student> allStudents, UI UI) {
		//upon receiving the randomly chosen selection, it performs the task corresponding to the community option, as listed above
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
				//must update player money here, since this is the only access point to all of the students
				UI.updatePlayer(student);
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
	
	/**
	 * Randomly chooses a community card and performs the appropriate action.
	 * 
	 * @param student The student who landed on Community.
	 * @param students The list of students in the game.
	 * @param UI The UI to be modified.
	 * @param courseList The list of all course names.
	 * 
	 * @return The result of the Community option chosen.
	 */
	@Override
	public int performTileAction(Student student, ArrayList<Student> students, UI UI, CourseList courseList) {
		//only chooses a random option if this is the first time the tile action is being invoked this turn
		if (!this.getPerformedTileAction()) {
			randCommunity = new Random().nextInt(this.getCommunityOptions().length);
		}
		UI.displayCommunityOption(this, randCommunity);
		return this.performCommunityOption(randCommunity, student, students, UI);
	}
}
