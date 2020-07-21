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
		Course course1 = new Course(1, "SOCI 201", "Arts", 150, 50, 50);
        coursesOwned.add(course1);
        
        Course course2 = new Course(2, "POLI 201", "Arts", 175, 60, 55);
        coursesOwned.add(course2);
        
        Course course3 = new Course(3, "PSYC 201", "Arts", 200, 70, 60);
        coursesOwned.add(course3);
        
        Course course4 = new Course(4, "CPSC 231", "Sciences", 250, 80, 65);
        coursesOwned.add(course4);   
        
        Course course5 = new Course(5, "CPSC 219", "Sciences", 275, 90, 70);
        coursesOwned.add(course5);
        
        Course course6 = new Course(6, "CPSC 233", "Sciences", 300, 100, 75);
        coursesOwned.add(course6);
        
        Course course7 = new Course(7, "STAT 213","Business", 350, 110, 80);
        coursesOwned.add(course7);
        
        Course course8 = new Course(8, "ENMG 301", "Business", 375, 120, 85);
        coursesOwned.add(course8);
        
        Course course9 = new Course(9, "MGMT 311", "Business", 400, 130, 90);
        coursesOwned.add(course9);
        
        Course course10 = new Course(10, "ENGG 599", "Engineering", 450, 140, 100);
        coursesOwned.add(course10);
        
        Course course11 = new Course(11, "ENMG 301", "Engineering", 475, 150, 110);
        coursesOwned.add(course11);
        
        Course course12 = new Course(12, "MGMT 311", "Engineering", 500, 160, 120);
        coursesOwned.add(course12);
	}
	
	public void initializeUnbuyableTiles() {
		Chance chance1 = new Chance(2);
        unbuyableTiles.add(chance1);
        
        Community community1 = new Community(7);
        unbuyableTiles.add(community1);
        
        Chance chance2 = new Chance(13);
        unbuyableTiles.add(chance2);
        
        Community community2 = new Community(17);
        unbuyableTiles.add(community2);
        
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
	
	public Tile getTileAt(int position) {
		return board.get(position);
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
