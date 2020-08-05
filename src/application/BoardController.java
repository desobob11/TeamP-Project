package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * Interactive main board window.
 * 
 * @author Jamie MacDonald
 *
 */
public class BoardController {

    @FXML
    private ImageView dice1;

    @FXML
    private ImageView dice2;

    @FXML
    private ImageView dice5;

    @FXML
    private ImageView dice6;

    @FXML
    private ImageView dice3;

    @FXML
    private ImageView dice4;
    
    @FXML
    private Button rollDiceButton;

    /*
     * Allows user to roll the dice on their turn.
     */
    void rollDiceCLick(ActionEvent event) {
    	dice1.setVisible(false);
    	dice2.setVisible(false);
    	dice3.setVisible(false);
    	dice4.setVisible(false);
    	dice5.setVisible(false);
    	dice6.setVisible(false);
    	
    	int num = rollDice(); //Need more info about linking this to main gameplay
    	if (num == 1) {
    		dice1.setVisible(true);
    	}
    	else if (num == 2) {
    		dice2.setVisible(true);
    	}
    	else if (num == 3) {
    		dice3.setVisible(true);
    	}
    	else if (num == 4) {
    		dice4.setVisible(true);
    	}
    	else if (num == 5) {
    		dice5.setVisible(true);
    	}
    	else if (num == 6) {
    		dice6.setVisible(true);
    	}
    }
}
