
public class Chance extends Tile {
	String[] chanceOptions = {"SU Donation: Pay $100",
	                          "Reimbursement: Earn $200",
	                          "Help out a friend: Pay the player next to you $50"};
	
	public Chance(int position) {
		super(position, "Chance");
	}
}
