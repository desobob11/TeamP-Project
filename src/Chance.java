
public class Chance extends Tile {
	String[] chanceOptions = {
            "SU Donation: Pay $100",
            "Grants have been doubled everyone collect 300$",
            "Your offered a ride from your friend, move to free parking",
            "Your midterm started 10 minutes ago advance to the nearest bus station",
            "Help out a friend: Pay the player next to you $50",
            "Your student loans have caught up with you, pay 250 $",
            "You withdrew from a course, past the deadline!, pay 150",
            "You have been caught for cheating, move to academic probation",
            "You forgot your uni card at home, advance to the nearest bus station, if owned pay double",
    };
	
	public Chance(int position) {
		super(position, "Chance");
	}
}
