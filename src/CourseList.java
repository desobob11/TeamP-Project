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
		Course course1 = new Course(1, "SOCI 201", "Arts", 500, 300, 50);
		coursesOwned.add(course1);
		
		
		Course course10 = new Course(16, "ENGG 201", "ENGG", 1, 14656, 1);
		coursesOwned.add(course10);
		Course course11 = new Course(17, "ENGG 311", "ENGG", 1, 1, 1);
		coursesOwned.add(course11);
		Course course12 =  new Course(19, "ENGG 599", "ENGG", 1, 1,1 );
		coursesOwned.add(course12);
	}
	
	public void initializeUnbuyableTiles() {
		Community community1 = new Community(2);
		unbuyableTiles.add(community1);
		Chance chance1 = new Chance(7);
		unbuyableTiles.add(chance1);
		Probation probation = new Probation(5);
		unbuyableTiles.add(probation);
		Parking parking1 = new Parking(10);
		unbuyableTiles.add(parking1);
		Parking parking2 = new Parking(15);
		unbuyableTiles.add(parking2);
		
	}
	
	public int getBoardSize() {
		return boardSize;
	}
	
	public Course getCourseAt(int position) {
		return courseList.get(position);
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
				aCourse.setOwner(null);
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
