package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.oracle.tools.packager.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import model.Student;


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

    @FXML
   
   
    	Platform.runLater(New Runnable()){
    	@Override
    	public void initialize() {
    		assert appText != null : "fx:id=\"appText\" was not injected: check your FXML file 'PlayerView.fxml'.";
    		
             assert button1 != null : "fx:id=\"button1\" was not injected: check your FXML file 'PlayerView.fxml'.";
             assert button2 != null : "fx:id=\"button2\" was not injected: check your FXML file 'PlayerView.fxml'.";
             assert button3 != null : "fx:id=\"button3\" was not injected: check your FXML file 'PlayerView.fxml'.";
        assert coursesOwnedGrid != null : "fx:id=\"coursesOwnedGrid\" was not injected: check your FXML file 'PlayerView.fxml'.";
        assert playerLocationText != null : "fx:id=\"playerLocationText\" was not injected: check your FXML file 'PlayerView.fxml'.";
        assert playerMoneyText != null : "fx:id=\"playerMoneyText\" was not injected: check your FXML file 'PlayerView.fxml'.";
        assert playerNameText != null : "fx:id=\"playerNameText\" was not injected: check your FXML file 'PlayerView.fxml'.";
        assert playerNetWorthText != null : "fx:id=\"playerNetWorthText\" was not injected: check your FXML file 'PlayerView.fxml'.";

        System.out.println("Hello");
    }

    }	
   
    
    
    
    
    
    

