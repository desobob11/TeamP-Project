package application;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
public class BoardViewController {
	
	private HashMap<Integer, GridPane> tileMap;
	
	
	
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

	    }

}
