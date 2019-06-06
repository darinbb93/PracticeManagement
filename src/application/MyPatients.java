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
	private Practitioner user;
	ListView<Patient> patientsList;

	public MyPatients(double _sizeX, double _sizeY, double _positionX, double _positionY, Practitioner _user) {

		user = _user;
		sizeX = _sizeX;
		sizeY = _sizeY;
		positionX = _positionX;
		positionY = _positionY;

		buildUpStage();

	}

	// creates the scene and all elements, adds to stage and shows it
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

		// setup fonts for text
		Font overall = Font.font("Aral", 20);

		// initialising components
		content = new BorderPane();
		VBox patients = new VBox();
		Text usersName = new Text(user.getName());
//		VBox topAreaV = new VBox();
		BorderPane nameNbuttons = new BorderPane();
		BorderPane backBtnContainer = new BorderPane();
		Button back = new Button();
		patientsList = new ListView<>();

		// layout consists of a main border pane "content" ON TOP
		// it has a VBOX inside is an HBOX with name and buttons
		// then the border pane with back button
		nameNbuttons.setLeft(usersName);
//		topAreaV.getChildren().addAll(nameNbuttons, backBtnContainer);
		content.setTop(nameNbuttons);
		patients.getChildren().addAll(backBtnContainer, patientsList);
		content.setCenter(patients);

		// back button setup
		URL imgURL = getClass().getResource("back.png");
		backBtnContainer.setLeft(back);
		back.setMinSize(70, 30);
		back.setStyle("-fx-background-image: url('" + imgURL.toString() + "'); -fx-background-repeat: no-repeat;"
				+ "  -fx-background-position: center; -fx-background-size: 68px 24px; ");
		back.setOnAction(backClick);

		// centering.setAlignment(Pos.CENTER);
		nameNbuttons.setStyle("-fx-background-color: rgb(250, 250, 250)");
		patients.setStyle("-fx-background-color: rgb(230, 238, 242)");
		usersName.setFont(overall);

		for (Patient patient : user.getPatients()) {
			patientsList.getItems().add(patient);

		}

//		patientsList.getItems().

		// setup and show scene
		scene = new Scene(content, sizeX, sizeY);
		stage.setScene(scene);
		stage.show();

	}

	// Back button click setup
	EventHandler<ActionEvent> backClick = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			try {
				MainMenu mainMenu = new MainMenu(sizeX, sizeY, stage.getX(), stage.getY(), user);
				stage.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	};
}