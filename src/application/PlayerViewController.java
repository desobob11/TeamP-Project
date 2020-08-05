package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class PlayerViewController {

    @FXML
    private Label playerNameText;

    @FXML
    private Label playerLocationText;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private TextArea appText;

    @FXML
    private Label playerMoneyText;

    @FXML
    private Pane playerViewPane;

    @FXML
    private GridPane coursesOwnedGrid;

    @FXML
    private Label playerNetWorthText;

    @FXML
    private Font x1;

    @FXML
    private Font x2;

    @FXML
    private Font x3;

    @FXML
    private Button button1;

    @FXML
    void button1Action(ActionEvent event) {

    }

    @FXML
    void button2Action(ActionEvent event) {

    }

    @FXML
    void button3Action(ActionEvent event) {

    }
    
    public void changePlayerNameText(String name) {
    	this.playerNameText.setText(name);
    }
    
    public void setPlayerMoney(int money) {
    	this.playerMoneyText.setText("" + money);
    }
    
    public void clearAppText() {
    	this.appText.clear();
    }
    
    @FXML
    void initialize() {
    	//this.playerNameText.setText("Player 1");
    }

}
