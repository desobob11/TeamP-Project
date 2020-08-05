package Application;

import Views.*;
import images.*;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import model.Logic;

import java.util.*;



public class Main extends Logic {
	private static final Stage theStage = new Stage();
	
	
	
	public static final String FXML_FILES_LOCATION = "src/view/";
	
	
	public static void start(Stage stage) {	
		Scene scene = new Scene(root,500,400);
		
		stage.setScene(scene);
		stage.show();
	}
	
	
	public static void main(String[] args) {

		start(theStage);
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
