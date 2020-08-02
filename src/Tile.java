/**
 * Tile - this represents a piece on the board. There will never be an instance
 * of Tile itself, but rather one of its many children.
 * 
 * @author Desmond O'Brien and Arnuv Mayank
 *
 */
public abstract class Tile {
	private int tileID;
	private String tileName;

	public Tile() {

	}

	public Tile(int tileID, String tileName) {
		this.tileID = tileID;
		this.tileName = tileName;
	}

	public int getTileID() {
		return tileID;
	}

	public String getTileName() {
		return tileName;
	}

}
