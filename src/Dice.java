import java.util.Random;

public class Dice {
	
	public Dice() {
	}
	
	public int rollDice() {
		int random = new Random().nextInt(6) + 1;
		return random;
	}
}