
public class Parking extends Tile {
	private int parkingCost = 250;
	
	public Parking(int position) {
		super(position, "Parking");
	}
	
	public Parking (int position, int parkingCost) {
		super(position, "Parking");
		this.parkingCost = parkingCost;
	}
}