package model;
import java.util.ArrayList;

/**
 * CourseList - this is effectively the "board" class and acts as a container
 * for all the tiles, with separate containers for each type of tile. It handles
 * all changes to course ownership and enables the Application to know where
 * each student is.
 * 
 * @author Arnuv Mayank and Victor Manuel Campos Goitia Campos
 *
 */

public class CourseList {
	private ArrayList<Tile> board;
	private ArrayList<Course> courseList;
	private ArrayList<Course> coursesOwned;
	private ArrayList<Chance> chanceTiles;
	private ArrayList<Community> communityTiles;
	private ArrayList<Parking> parkingTiles;
	private Probation probationTile;
	private Go goTile;
	private int boardSize = 20;

	public CourseList() {
		board = new ArrayList<Tile>();
		courseList = new ArrayList<Course>();
		coursesOwned = new ArrayList<Course>();
		chanceTiles = new ArrayList<Chance>();
		communityTiles = new ArrayList<Community>();
		parkingTiles = new ArrayList<Parking>();

		initializeCourses();
		initializeUnbuyableTiles();
		ArrayList<Tile> allTiles = new ArrayList<Tile>();
		allTiles.addAll(courseList);
		allTiles.addAll(chanceTiles);
		allTiles.addAll(communityTiles);
		allTiles.addAll(parkingTiles);
		allTiles.add(probationTile);
		allTiles.add(goTile);
		sortByTilePosition(allTiles);
	}

	public CourseList(ArrayList<Course> courseList) {
		this.courseList = courseList;
		board = new ArrayList<Tile>();
		coursesOwned = new ArrayList<Course>();
		chanceTiles = new ArrayList<Chance>();
		communityTiles = new ArrayList<Community>();
		parkingTiles = new ArrayList<Parking>();

		initializeUnbuyableTiles();
		ArrayList<Tile> allTiles = new ArrayList<Tile>();
		allTiles.addAll(courseList);
		allTiles.addAll(chanceTiles);
		allTiles.addAll(communityTiles);
		allTiles.addAll(parkingTiles);
		allTiles.add(probationTile);
		allTiles.add(goTile);
		sortByTilePosition(allTiles);
	}

	private void initializeCourses() {
		//Course constructor format: (position, name, faculty, buy price, sell price, tutorial price)
		Course course1 = new Course(1, "SOCI 201", "Arts", 150, 50, 50);
		courseList.add(course1);

		Course course2 = new Course(3, "POLI 201", "Arts", 175, 60, 55);
		courseList.add(course2);

		Course course3 = new Course(4, "PSYC 201", "Arts", 200, 70, 60);
		courseList.add(course3);

		Course course4 = new Course(6, "CPSC 231", "Sciences", 250, 80, 65);
		courseList.add(course4);

		Course course5 = new Course(8, "CPSC 219", "Sciences", 275, 90, 70);
		courseList.add(course5);

		Course course6 = new Course(9, "CPSC 233", "Sciences", 300, 100, 75);
		courseList.add(course6);

		Course course7 = new Course(11, "STAT 213", "Business", 350, 110, 80);
		courseList.add(course7);

		Course course8 = new Course(13, "ENMG 301", "Business", 375, 120, 85);
		courseList.add(course8);

		Course course9 = new Course(14, "MGMT 311", "Business", 400, 130, 90);
		courseList.add(course9);

		Course course10 = new Course(16, "ENGG 599", "Engineering", 450, 140, 100);
		courseList.add(course10);

		Course course11 = new Course(18, "ENMG 501", "Engineering", 475, 150, 110);
		courseList.add(course11);

		Course course12 = new Course(19, "ENGG 683", "Engineering", 500, 160, 120);
		courseList.add(course12);
	}

	public void initializeUnbuyableTiles() {
		final int parking1Pos = 10;
		final int parking2Pos = 15;
		final int probationPos = 5;

		probationTile = new Probation(probationPos);

		goTile = new Go();

		Parking parking1 = new Parking(parking1Pos);
		parkingTiles.add(parking1);

		Parking parking2 = new Parking(parking2Pos);
		parkingTiles.add(parking2);

		Chance chance1 = new Chance(7);
		chanceTiles.add(chance1);

		Community community1 = new Community(2);
		communityTiles.add(community1);

		Chance chance2 = new Chance(17);
		chanceTiles.add(chance2);

		Community community2 = new Community(12);
		communityTiles.add(community2);
	}

	public int getBoardSize() {
		return boardSize;
	}

	public Tile getTileAt(int position) {
		return board.get(position);
	}

	public Probation getProbation() {
		return probationTile;
	}

	public Parking getParkingAt(int position) {
		//iterates through parking tiles
		for (Parking parking : parkingTiles) {
			//matches the tile IDs
			if (parking.getTileID() == position) {
				return parking;
			}
		}
		return null;
	}

	public int getParkingPosition(int parkingNumber) {
		return parkingTiles.get(parkingNumber - 1).getTileID();
	}

	public int getProbationPosition() {
		return probationTile.getTileID();
	}

	public ArrayList<Parking> getParkingTiles() {
		return parkingTiles;
	}

	public boolean inCourseList(Course aCourse) {
		return (courseList.contains(aCourse) ? true : false);
	}

	public boolean inCoursesOwned(Course aCourse) {
		return (coursesOwned.contains(aCourse) ? true : false);
	}

	public int addToCoursesOwned(Course aCourse) {
		//should only add the course if it's in the course list (safety check) (-1)
		if (inCourseList(aCourse)) {
			//should only add the course if it's not already owned (safety check) (-2)
			if (!inCoursesOwned(aCourse)) {
				//add the course
				coursesOwned.add(aCourse);
				return 1;
			}
			return -2;
		}
		return -1;
	}

	public int removeFromCoursesOwned(Course aCourse) {
		//should only remove the course if it's in the course list (safety check) (-1)
		if (inCourseList(aCourse)) {
			//should only remove the course if it's already owned (safety check) (-2)
			if (inCoursesOwned(aCourse)) {
				//remove the course
				coursesOwned.remove(aCourse);
				aCourse.setOwner(null);
			}
			return -2;
		}
		return -1;
	}

	private void sortByTilePosition(ArrayList<Tile> allTiles) {
		board = new ArrayList<Tile>();
		//iterate through all of the Tile IDs
		for (int i = 0; i < boardSize; i++) {
			//iterate through the tile list
			for (Tile tile : allTiles) {
				//match the IDs
				if (tile.getTileID() == i) {
					//add to official board list
					board.add(tile);
					break;
				}
			}
		}
	}
}
