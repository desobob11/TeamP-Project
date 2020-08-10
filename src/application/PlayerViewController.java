package application;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.*;


public class PlayerViewController {

	Image SOCI201Image = new Image("/images/SOCI201.png");
	Image POLI201Image = new Image("/images/POLI201.png");
	Image PSYC201Image = new Image("/images/PSYC201.png");
	Image CPSC231Image = new Image("/images/CPSC231.png");
	Image CPSC219Image = new Image("/images/CPSC219.png");
	Image CPSC233Image = new Image("/images/CPSC233.png");
	Image STAT213Image = new Image("/images/STAT213.png");
	Image ENMG301Image = new Image("/images/ENMG301.png");
	Image MGMT311Image = new Image("/images/MGMT311.png");
	Image ENGG599Image = new Image("/images/ENGG599.png");
	Image ENGG501Image = new Image("/images/ENMG301 ENGG.png");
	Image ENGG683Image = new Image("/images/ENGG683.png");
 	ImageView SOCI201ImageView = new ImageView();
	ImageView POLI201ImageView = new ImageView();
 	ImageView PSYC201ImageView = new ImageView();
	ImageView CPSC231ImageView = new ImageView();
	ImageView CPSC219ImageView = new ImageView();
	ImageView CPSC233ImageView = new ImageView();
	ImageView STAT213ImageView = new ImageView();
	ImageView ENMG301ImageView = new ImageView();
	ImageView MGMT311ImageView = new ImageView();
	ImageView ENGG599ImageView = new ImageView();
	ImageView ENGG501ImageView = new ImageView();
	ImageView ENGG683ImageView = new ImageView();
	private HashMap<String, ImageView> imageMap = new HashMap<String, ImageView>();
	
	
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

    
    public void updatePlayerView(Student student) {
    	updateFields(student);
    }
    
    private void updateCourses(Student student) {
    	int rowCounter = 0;
    	this.coursesOwnedGrid.getChildren().clear();
    	ArrayList<Course> studentCourses = student.getCoursesOwned();
    	for (int i = 0; i < studentCourses.size(); i++) {
    		Course course = studentCourses.get(i);
    		ImageView coursePic = imageMap.get(course.getTileName());
    		rowCounter = (i % 4 == 0 ? rowCounter + 1 : rowCounter);
    		GridPane.setRowIndex(coursePic, rowCounter);
    		GridPane.setColumnIndex(coursePic, i % 4);
        	coursesOwnedGrid.getChildren().addAll(coursePic);
    	}
    }
    
    private void updateFields(Student student) {
    	this.playerNameText.setText("Student " + student.getPlayerNumber());
    	this.playerMoneyText.setText("" + student.getPlayerMoney());
    	this.playerNetWorthText.setText("" + student.getNetWorth());
    	this.playerLocationText.setText("" + student.getPlayerPosition());
    	updateCourses(student);
    }
    
    void setPlayerMoneyText(int money) {
    	this.playerMoneyText.setText(money + "");
    }

    @FXML
    void initialize() {
     	final double height = 500/3;
     	final double width = 400/3;
    	
    	SOCI201ImageView.setFitHeight(height);
     	SOCI201ImageView.setFitWidth(width);
     	SOCI201ImageView.setImage(SOCI201Image);
     	POLI201ImageView.setFitHeight(height);
     	POLI201ImageView.setFitWidth(width);
     	POLI201ImageView.setImage(POLI201Image);
     	PSYC201ImageView.setFitHeight(height);
     	PSYC201ImageView.setFitWidth(width);
     	PSYC201ImageView.setImage(PSYC201Image);
     	CPSC231ImageView.setFitHeight(height);
     	CPSC231ImageView.setFitWidth(width);
     	CPSC231ImageView.setImage(CPSC231Image);
     	CPSC219ImageView.setFitHeight(height);
     	CPSC219ImageView.setFitWidth(width);
     	CPSC219ImageView.setImage(CPSC219Image);
     	CPSC233ImageView.setFitHeight(height);
     	CPSC233ImageView.setFitWidth(width);
     	CPSC233ImageView.setImage(CPSC233Image);
     	STAT213ImageView.setFitHeight(height);
     	STAT213ImageView.setFitWidth(width);
     	STAT213ImageView.setImage(STAT213Image);
     	ENMG301ImageView.setFitHeight(height);
     	ENMG301ImageView.setFitWidth(width);
     	ENMG301ImageView.setImage(ENMG301Image);
     	MGMT311ImageView.setFitHeight(height);
     	MGMT311ImageView.setFitWidth(width);
     	MGMT311ImageView.setImage(MGMT311Image);
     	ENGG599ImageView.setFitHeight(height);
     	ENGG599ImageView.setFitWidth(width);
     	ENGG599ImageView.setImage(ENGG599Image);
     	ENGG599ImageView.setFitHeight(height);
     	ENGG599ImageView.setFitWidth(width);
     	ENGG599ImageView.setImage(ENGG501Image);
     	ENGG683ImageView.setFitHeight(height);
     	ENGG683ImageView.setFitWidth(width);
     	ENGG683ImageView.setImage(ENGG683Image);
    	
    	imageMap.put("SOCI 201", SOCI201ImageView);
    	imageMap.put("POLI 201", POLI201ImageView);
    	imageMap.put("PSYC 201", PSYC201ImageView);
    	imageMap.put("CPSC 231", CPSC231ImageView);
    	imageMap.put("CPSC 219", CPSC219ImageView);
    	imageMap.put("CPSC 233", CPSC233ImageView);
    	imageMap.put("STAT 213", STAT213ImageView);
    	imageMap.put("ENMG 301", ENMG301ImageView);
    	imageMap.put("MGMT 311", MGMT311ImageView);
    	imageMap.put("ENGG 599", ENGG599ImageView);
    	imageMap.put("ENGG 501", ENGG501ImageView);
    	imageMap.put("ENGG 683", ENGG683ImageView);
    }

    	
    	
  }
    
    
    
    
    
    

