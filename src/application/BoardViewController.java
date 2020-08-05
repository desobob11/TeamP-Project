package application;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.Student;
public class BoardViewController {
	
	private HashMap<Integer, GridPane> tileMap = new HashMap<Integer, GridPane>();
	Image dino1 = new Image("/images/Yellow Dino.png");
	Image dino2 = new Image("/images/Blue Dino.png");
	Image dino3 = new Image("/images/Purple Dino.png");
	Image dino4 = new Image("/images/Orange Dino.png");
 	ImageView pic1 = new ImageView();
	ImageView pic2 = new ImageView();
 	ImageView pic3 = new ImageView();
	ImageView pic4 = new ImageView();
	private HashMap<Integer, ImageView> imageMap = new HashMap<Integer, ImageView>();
	
	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private ImageView board;

	    @FXML
	    private GridPane grid0;

	    @FXML
	    private GridPane grid1;

	    @FXML
	    private GridPane grid10;

	    @FXML
	    private GridPane grid11;

	    @FXML
	    private GridPane grid12;

	    @FXML
	    private GridPane grid13;

	    @FXML
	    private GridPane grid14;

	    @FXML
	    private GridPane grid15;

	    @FXML
	    private GridPane grid16;

	    @FXML
	    private GridPane grid17;

	    @FXML
	    private GridPane grid18;

	    @FXML
	    private GridPane grid19;

	    @FXML
	    private GridPane grid2;

	    @FXML
	    private GridPane grid3;

	    @FXML
	    private GridPane grid4;

	    @FXML
	    private GridPane grid5;

	    @FXML
	    private GridPane grid6;

	    @FXML
	    private GridPane grid7;

	    @FXML
	    private GridPane grid8;

	    @FXML
	    private GridPane grid9;

	    private void  setImage(int playerNumber, int playerPosition) {
	    	GridPane grid = tileMap.get(playerPosition);
	    	grid.add(imageMap.get(playerNumber), (playerNumber == 1 | playerNumber == 4 ? 0: 1), (playerNumber == 1 | playerNumber == 2 ? 0: 1));	
	    }
	    
	    private void clearImage(int playerNumber, int previousPosition) {
	    	 GridPane grid = tileMap.get(previousPosition);
	    	 if (previousPosition != -1) {
	    		 grid.getChildren().remove(imageMap.get(playerNumber)); 
	    	 } 
	     }
	   
	    public void updateBoard(Student student) {
	    	clearImage(student.getPlayerNumber(), student.getPreviousPlayerPosition());
	    	setImage(student.getPlayerNumber(), student.getPlayerPosition());    	
	    }
	     
	    @FXML
	    void initialize() {
	        assert board != null : "fx:id=\"board\" was not injected: check your FXML file 'BoardView.fxml'.";
	        assert grid0 != null : "fx:id=\"grid0\" was not injected: check your FXML file 'BoardView.fxml'.";
	        assert grid1 != null : "fx:id=\"grid1\" was not injected: check your FXML file 'BoardView.fxml'.";
	        assert grid10 != null : "fx:id=\"grid10\" was not injected: check your FXML file 'BoardView.fxml'.";
	        assert grid11 != null : "fx:id=\"grid11\" was not injected: check your FXML file 'BoardView.fxml'.";
	        assert grid12 != null : "fx:id=\"grid12\" was not injected: check your FXML file 'BoardView.fxml'.";
	        assert grid13 != null : "fx:id=\"grid13\" was not injected: check your FXML file 'BoardView.fxml'.";
	        assert grid14 != null : "fx:id=\"grid14\" was not injected: check your FXML file 'BoardView.fxml'.";
	        assert grid15 != null : "fx:id=\"grid15\" was not injected: check your FXML file 'BoardView.fxml'.";
	        assert grid16 != null : "fx:id=\"grid16\" was not injected: check your FXML file 'BoardView.fxml'.";
	        assert grid17 != null : "fx:id=\"grid17\" was not injected: check your FXML file 'BoardView.fxml'.";
	        assert grid18 != null : "fx:id=\"grid18\" was not injected: check your FXML file 'BoardView.fxml'.";
	        assert grid19 != null : "fx:id=\"grid19\" was not injected: check your FXML file 'BoardView.fxml'.";
	        assert grid2 != null : "fx:id=\"grid2\" was not injected: check your FXML file 'BoardView.fxml'.";
	        assert grid3 != null : "fx:id=\"grid3\" was not injected: check your FXML file 'BoardView.fxml'.";
	        assert grid4 != null : "fx:id=\"grid4\" was not injected: check your FXML file 'BoardView.fxml'.";
	        assert grid5 != null : "fx:id=\"grid5\" was not injected: check your FXML file 'BoardView.fxml'.";
	        assert grid6 != null : "fx:id=\"grid6\" was not injected: check your FXML file 'BoardView.fxml'.";
	        assert grid7 != null : "fx:id=\"grid7\" was not injected: check your FXML file 'BoardView.fxml'.";
	        assert grid8 != null : "fx:id=\"grid8\" was not injected: check your FXML file 'BoardView.fxml'.";
	        assert grid9 != null : "fx:id=\"grid9\" was not injected: check your FXML file 'BoardView.fxml'.";	    
	        tileMap.put(0, grid0);
	        tileMap.put(1, grid1);
	        tileMap.put(2, grid2);
	        tileMap.put(3, grid3);
	        tileMap.put(4, grid4);
	        tileMap.put(5, grid5);
	        tileMap.put(6, grid6);
	        tileMap.put(7, grid7);
	        tileMap.put(8, grid8);
	        tileMap.put(9, grid9);
	        tileMap.put(10, grid10);
	        tileMap.put(11, grid11);
	        tileMap.put(12, grid12);
	        tileMap.put(13, grid13);
	        tileMap.put(14, grid14);
	        tileMap.put(15, grid15);
	        tileMap.put(16, grid16);
	        tileMap.put(17, grid17);
	        tileMap.put(18, grid18);
	        tileMap.put(19, grid19);
	        pic1.setFitHeight(40);
	        pic1.setFitWidth(40);
	        pic2.setFitHeight(40);
	        pic2.setFitWidth(40);
	        pic3.setFitHeight(40);
	        pic3.setFitWidth(40);
	        pic4.setFitHeight(40);
	        pic4.setFitWidth(40);
	        pic1.setImage(dino1);
	        pic2.setImage(dino2);
	        pic3.setImage(dino3);
	        pic4.setImage(dino4);
	        imageMap.put(1, pic1);
	        imageMap.put(2, pic2);
	        imageMap.put(3, pic3);
	        imageMap.put(4, pic4);
	        setImage(1, 5);
	        clearImage(1,5);
	        setImage(1, 17);  
	    }

}
