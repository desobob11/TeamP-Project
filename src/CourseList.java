import java.util.ArrayList;

public class CourseList {
	static private ArrayList<Course> courseList;
	private ArrayList<Course> coursesOwned;
	int boardSize = 20;
	
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
}
