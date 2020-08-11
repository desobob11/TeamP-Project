package application;

import java.util.*;

import model.Logic;
import javafx.application.Application;

/**
 * Main method -- starts the entire program
 * 
 * @author Arnuv Mayank
 *
 */
public class Main {
	
	/**
	 * the main method that starts the main thread, and consequently the whole program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//asks if they want the text-based or GUI version, then makes the appropriate game
		System.out.println("Would you like to play the Text-Based version, or the GUI-based version? '1' / '2'");
		Scanner input = new Scanner(System.in);
		int versionInt = Integer.parseInt(input.nextLine());
		if (versionInt == 1) {
			Logic logic = new Logic(new TextVisualizer());
			logic.run();
		}
		else {	
			//for the GUI, a new Thread needs to be created to run JavaFX, and then it is started
			new Thread() {
	            @Override
	            public void run() {
	                javafx.application.Application.launch(GUI.class, args);
	            }
	        }.start();
	        GUI startUp = GUI.waitForStartUpTest();
			Logic logic = new Logic(startUp);
			logic.run();
		}
	}

}
