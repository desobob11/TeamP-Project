package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controls the JavaFX window for displaying the Chance Card.
 * 
 * @author Jamie MacDonald
 *
 */
public class ChanceCardController {

    @FXML
    private Label chanceLabel;
    
    @FXML
    public void setLabelDialogue() {
    	chanceLabel.setText(/*GETCHANCEMESSAGE*/);

}

