import java.util.ArrayList;

public class CourseList {
	private ArrayList<Tile> board;
	private ArrayList<Course> courseList;
	private ArrayList<Course> coursesOwned;
	private ArrayList<Tile> unbuyableTiles;
	int boardSize = 20;
	
	public CourseList() {
		initializeCourses();
		initializeUnbuyableTiles();
		ArrayList<Tile> allTiles = new ArrayList<Tile>();
		allTiles.addAll(courseList);
		allTiles.addAll(unbuyableTiles);
		sortByTilePosition(allTiles);
	}
	
	public CourseList(ArrayList<Course> courseList) {
		this.courseList = courseList;
		initializeUnbuyableTiles();
		ArrayList<Tile> allTiles = new ArrayList<Tile>();
		allTiles.addAll(courseList);
		allTiles.addAll(unbuyableTiles);
		sortByTilePosition(allTiles);
	}
	
	public void initializeCourses() {
		
	}
	
	public void initializeUnbuyableTiles() {
		
	}
	
	public boolean inCourseList(Course aCourse) {
		return (courseList.contains(aCourse) ? true: false);
	}
	
	public boolean inCoursesOwned(Course aCourse) {
		return (coursesOwned.contains(aCourse) ? true: false);
	}
	
	public int addToCoursesOwned(Course aCourse) {
		if (inCourseList(aCourse)) {
			if (!inCoursesOwned(aCourse)) {
				coursesOwned.add(aCourse);
				return 1;
			}
			return -2;
		}
		return -1;
	}
	
	public int removeFromCoursesOwned(Course aCourse) {
		if (inCourseList(aCourse)) {
			if (inCoursesOwned(aCourse)) {
				coursesOwned.remove(aCourse);
			}
			return -2;
		}
		return -1;
	}
	
	private void sortByTilePosition(ArrayList<Tile> allTiles) {
		board = new ArrayList<Tile>();
		for (int i = 0; i < boardSize; i++) {
			for (Tile tile: allTiles) {
				if (tile.getTileID() == i) {
					board.add(tile);
				}
				break;
			}
		}
	}
}
