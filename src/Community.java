
public class Community extends Tile {
	String[] communityOptions = {
            "Your good grades have been rewarded, you collect 400$ from scholarships",
            "It's your lucky day you find 50$ on the ground",
            "Grants have been doubled everyone collect 200$",
            "Someone hits your car on campus, luckily there were cameras collect 225$",
            "Tuition increased everyone pay 250$",
            "Your student loans have caught up with you, pay $400",
            "Whoops! You slipped on black ice and a 100 fell out of your pocket"};
	
	public Community(int position) {
		super(position, "Community");
	}
}
