package application;


import javafx.stage.*;
import java.net.URL;
import Data.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

public class MyPatients {
	private double sizeX;
	private double sizeY;
	private double positionX;
	private double positionY;
	private double xOffset;
	private double yOffset;
	private BorderPane content;
	private VBox loginDetails;
	private TextField username;
	private TextField password;
	private Text warning = new Text("");
	private Button login;
	private Button signup;	
	private Stage stage;
	private Scene scene;
	private Member user;

	public MyPatients(double _sizeX, double _sizeY, double _positionX, double _positionY, Member _user) {
		
		user = _user;
		sizeX = _sizeX;
		sizeY = _sizeY;
		positionX = _positionX;
		positionY = _positionY;		
		
		buildUpStage();
	
	}
	
	//creates the scene and all elements, adds to stage and shows it
	private void buildUpStage() {
		stage = new Stage();			
		stage.setX(positionX);
		stage.setY(positionY);
		stage.setHeight(sizeY);
		stage.setMinHeight(sizeY);
		stage.setMaxHeight(sizeY);
		stage.setWidth(sizeX);
		stage.setMaxWidth(sizeX);
		stage.setMinWidth(sizeX);
		stage.setTitle("Practice Management - My patients");

		//Assigned font for all texts
		Font overall = Font.font("Aral", 20);
		
		content = new BorderPane();
		VBox buttonArea = new VBox();
		Text usersName = new Text(user.getName());
		HBox topArea = new HBox();
		HBox centering = new HBox();
		topArea.getChildren().addAll(usersName);
		content.setTop(topArea);
		content.setCenter(centering);
		centering.getChildren().add(buttonArea);
		
		centering.setAlignment(Pos.CENTER);
		topArea.setStyle("-fx-background-color: rgb(250, 250, 250)");
		centering.setStyle("-fx-background-color: rgb(230, 238, 242)");
		usersName.setFont(overall);
		
		
		
		
	
		//setup and show scene
		scene = new Scene(content,sizeX,sizeY);
		stage.setScene(scene);
		stage.show();
		
	}
	

}