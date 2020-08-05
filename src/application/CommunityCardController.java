package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controls the JavaFX window for displaying the Community Cards.
 * 
 * @author Jamie MacDonald
 *
 */

public class CommunityCardController {

    @FXML
    private Label communityLabel;
    
    @FXML
    public void setLabelDialogue() {
    	communityLabel.setText(/*GETCOMMUNITYMESSAGE*/);
    }
}
