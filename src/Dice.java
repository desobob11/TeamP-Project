import java.util.Random;

/**
 * Dice - Rolls a dice (separate class because it may be helpful later for the GUI)
 * @author Desmond O'Brien
 *
 */
public class Dice {
	
	public Dice() {
	}
	
	public int rollDice() {
		int random = new Random().nextInt(6) + 1;
		return random;
	}
}