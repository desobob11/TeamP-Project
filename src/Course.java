
public class Course {
	private String faculty;
	private int buyPrice;
	private int sellPrice;
	private int withdrawPrice;
	private boolean ownedStatus;
	private int courseLevel;
	private int courseID;
	
		public String getFaculty() {
		return faculty;
		}
	
		public int getBuyPrice() {
			return buyPrice;
		}

		public int getSellPrice() {
			return sellPrice;
		}
		
		public int getWithdrawPrice() {
			return withdrawPrice;
		}
		
		public boolean getOwnedStatus() {
			return ownedStatus;
		}
		
		public int getCourseLevel() {
			return courseLevel;
		}
		
		public int getCourseID() {
			return courseID;
		}
		
		public boolean setOwnedStatus(boolean check) {
			this.ownedStatus = check;
			return ownedStatus;
		}
		
		
		
}

