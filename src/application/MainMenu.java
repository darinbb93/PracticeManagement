package application;

import javafx.stage.*;
import java.net.URL;
import Data.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
/**	
 * This class represents and builds the Main Menu that doctors would see when
 * they login and where they can navigate to see their pateints,see all
 * patients signed up to the hospital, see their timetable, change some setting 
 * and change some settings
 * 
 * @author Darin Bogdanov  - bogdb001
 *	
 */
public class MainMenu {
	private double sizeX;
	private double sizeY;
	private double positionX;
	private double positionY;
	private BorderPane content;
	private VBox buttonArea;
	private HBox patientFind;
	private HBox settings;
	private HBox timetable;
	private HBox myPatients;
	private HBox newP;
	private Stage stage;
	private Text usersName;
	private Scene scene;
	private Practitioner user;
	private MainMenu self = this;
	URL imgURL;

	public MainMenu(double _sizeX, double _sizeY, double _positionX, double _positionY, Practitioner _user) {

		sizeX = _sizeX;
		sizeY = _sizeY;
		positionX = _positionX;
		positionY = _positionY;
		user = _user;

		buildUpStage();

	}

	// creates the scene and all elements, adds to stage and shows it
	private void buildUpStage() {
		// Stage setup
		stage = new Stage();
		stage.setX(positionX);
		stage.setY(positionY);
		stage.setTitle("Practice Management - Main Menu");
		stage.setHeight(sizeY);
		stage.setMinHeight(sizeY);
		stage.setMaxHeight(sizeY);
		stage.setWidth(sizeX);
		stage.setMaxWidth(sizeX);
		stage.setMinWidth(sizeX);

		// Assigned font for all texts
		Font overall = Font.font("Aral", 20);

		
		content = new BorderPane();
		buttonArea = new VBox();
		usersName = new Text(user.getName());
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

//Buttons setup as HBox with an image component and Text 

		// My Patient menu link
		myPatients = new HBox();
		imgURL = getClass().getResource("patient50.png");
		Image myPIMG = new Image(imgURL.toString());
		ImageView myPIMG_VIEW = new ImageView(myPIMG);
		Text myPTXT = new Text("My Patients");
		myPTXT.setFont(overall);
		myPatients.getChildren().addAll(myPIMG_VIEW, myPTXT);
		myPatients.setOnMouseClicked(myPatientsClick);

		// Patient Find menu link
		patientFind = new HBox();
		imgURL = getClass().getResource("pFind50.png");
		Image pFindIMG = new Image(imgURL.toString());
		ImageView pFindIMG_VIEW = new ImageView(pFindIMG);
		Text pFindTXT = new Text("Find Patient");
		pFindTXT.setFont(overall);
		patientFind.getChildren().addAll(pFindIMG_VIEW, pFindTXT);

		// time Table view link
		timetable = new HBox();
		imgURL = getClass().getResource("tTable50.png");
		Image tTblIMG = new Image(imgURL.toString());
		ImageView tTblIMG_VIEW = new ImageView(tTblIMG);
		Text tTblTXT = new Text("Timetable");
		tTblTXT.setFont(overall);
		timetable.getChildren().addAll(tTblIMG_VIEW, tTblTXT);
		timetable.setOnMouseClicked(myTimeTableClick);

		// settings menu link
		settings = new HBox();
		imgURL = getClass().getResource("stngs50.png");
		Image stngsIMG = new Image(imgURL.toString());
		ImageView stngsIMG_VIEW = new ImageView(stngsIMG);
		Text stngsTXT = new Text("Settings");
		stngsTXT.setFont(overall);
		settings.getChildren().addAll(stngsIMG_VIEW, stngsTXT);

		// New Patient sign up menu link
		newP = new HBox();
		imgURL = getClass().getResource("new50.png");
		Image newPIMG = new Image(imgURL.toString());
		ImageView newPIMG_VIEW = new ImageView(newPIMG);
		Text newPTXT = new Text("New Patient");
		newPTXT.setFont(overall);
		newP.getChildren().addAll(newPIMG_VIEW, newPTXT);
		newP.setOnMouseClicked(newPClicked);

		buttonArea.getChildren().addAll(myPatients, patientFind, timetable, settings, newP);


		// setup and show scene
		scene = new Scene(content, sizeX, sizeY);
		stage.setScene(scene);
		stage.show();
		
	}

	//show stage
	public void showStage(double _positionX, double _positionY) {
		positionX = _positionX;
		positionY = _positionY;
		buildUpStage();
	}
	
	// EVENT HANDLERS

	//Mouse click setup for My patients  
	EventHandler<MouseEvent> myPatientsClick = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent e) {
			if (e.getButton() == MouseButton.PRIMARY) {
				try {
					stage.close();
					MyPatients patientsMenu = new MyPatients(sizeX, sizeY, stage.getX(), stage.getY(), user);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		}
	};
	
	//Mouse click setup for New patient
	EventHandler<MouseEvent> newPClicked = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent e) {
			if (e.getButton() == MouseButton.PRIMARY) {
				try {
					stage.close();
					PatientSignUp signUpMenu = new PatientSignUp(sizeX, sizeY, stage.getX(), stage.getY(), user, self);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		}
	};
	
	//Mouse click setup for time table
		EventHandler<MouseEvent> myTimeTableClick = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (e.getButton() == MouseButton.PRIMARY) {
					try {
						stage.close();
						TimetableView timetableMenu = new TimetableView(sizeX, sizeY, stage.getX(), stage.getY(), user);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}

			}
		};
}
