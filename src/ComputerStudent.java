import java.util.ArrayList;

public class ComputerStudent extends Student {

	public ComputerStudent() {
		// TODO Auto-generated constructor stub
	}

	public ComputerStudent(int playerNumber, int playerMoney) {
		super(playerNumber, playerMoney);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int studentInitialOption(UI UI) {
		ArrayList<ArrayList<Course>> upgradableFaculties = this.getUpgradableFaculties();
		if (upgradableFaculties.size() != 0) {
			for (ArrayList<Course> facultyList : upgradableFaculties) {
				if (facultyList.get(0).getUpgradeCost() <= this.getPlayerMoney()) {
					return 3;
				}
			}
		}
		return 1;
	}

	@Override
	public void studentSellCourse(UI UI, CourseList courseList) {
		// TODO Auto-generated method stub
		for (Course course : this.getCoursesOwned()) {
			course.resetCourseLevel();
			courseList.removeFromCoursesOwned(course);
		}
	}

	@Override
	public boolean studentBuyCourse(UI UI, Course aCourse) {
		// TODO Auto-generated method stub
		return (this.getPlayerMoney() >= aCourse.getBuyPrice() ? true : false);
	}

	@Override
	public String studentUpgradeFaculty(UI UI) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<Course>> upgradableFaculties = this.getUpgradableFaculties();
		for (ArrayList<Course> facultyList : upgradableFaculties) {
			if (facultyList.get(0).getUpgradeCost() <= this.getPlayerMoney()) {
				return facultyList.get(0).getFaculty();
			}
		}
		return "-1";
	}

}
