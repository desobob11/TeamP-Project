package application;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.*;


public class PlayerViewController {

	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea appText;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private GridPane coursesOwnedGrid;

    @FXML
    private Label playerLocationText;

    @FXML
    private Label playerMoneyText;

    @FXML
    private Label playerNameText;

    @FXML
    private Label playerNetWorthText;


    @FXML
    void button1Action(ActionEvent event) {
    
    	
    	
    
    }

    @FXML
    void button2Action(ActionEvent event) {
    
    
    
    }

    @FXML
    void button3Action(ActionEvent event) {
      
    	
    }
    
    void setPlayerMoneyText(int money) {
    	this.playerMoneyText.setText(money + "");
    }

    @FXML
    void initialize() {
    	
        System.out.println("Hello");
    }

    	
    	
  }
    
    
    
    
    
    

