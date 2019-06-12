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
	SettingsView settingsView;
	URL imgURL;

	public MainMenu(double _sizeX, double _sizeY, double _positionX, double _positionY, Practitioner _user) {

		sizeX = _sizeX;
		sizeY = _sizeY;
		positionX = _positionX;
		positionY = _positionY;
		user = _user;
		
		settingsView = new SettingsView(_sizeX, _sizeY, _positionX, _positionY, _user, this);
		

		buildUpStage();

	}
	
	public void showStage() {
		this.stage.show();
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
		patientFind.setOnMouseClicked(findClick);

		// time Table view link
		timetable = new HBox();
		imgURL = getClass().getResource("tTable50.png");
		Image tTblIMG = new Image(imgURL.toString());
		ImageView tTblIMG_VIEW = new ImageView(tTblIMG);
		Text tTblTXT = new Text("Timetable");
		tTblTXT.setFont(overall);
		timetable.getChildren().addAll(tTblIMG_VIEW, tTblTXT);

		// settings menu link
		settings = new HBox();
		imgURL = getClass().getResource("stngs50.png");
		Image stngsIMG = new Image(imgURL.toString());
		ImageView stngsIMG_VIEW = new ImageView(stngsIMG);
		Text stngsTXT = new Text("Settings");
		stngsTXT.setFont(overall);
		settings.getChildren().addAll(stngsIMG_VIEW, stngsTXT);
		settings.setOnMouseClicked(settingsClick);

		// New Patient sign up menu link
		newP = new HBox();
		imgURL = getClass().getResource("new50.png");
		Image newPIMG = new Image(imgURL.toString());
		ImageView newPIMG_VIEW = new ImageView(newPIMG);
		Text newPTXT = new Text("New Patient");
		newPTXT.setFont(overall);
		newP.getChildren().addAll(newPIMG_VIEW, newPTXT);

		buttonArea.getChildren().addAll(myPatients, patientFind, timetable, settings, newP);

//		buttonArea.setLeft(patientFind);
//		buttonArea.setRight(myPatients);
//		Font buttonFont = Font.font("Arial", FontWeight.BOLD,20);
//		newPostItNote.setFont(buttonFont);
//		newPostItNote.setTextFill(Color.GREY);
//		newPostItNote.setStyle("-fx-background-color: transparent");
//		deletePostItNote.setFont(buttonFont);
//		deletePostItNote.setTextFill(Color.GREY);
//		deletePostItNote.setStyle("-fx-background-color: transparent");
//		newPostItNote.setOnAction(newButton);
//		deletePostItNote.setOnAction(closeButton);

		// replacing the right click menu with custom
//		textArea.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
//		textArea.setOnMouseClicked(rightClick);
//
//		rightClickMenu = new ContextMenu();
//		MenuItem cut = new MenuItem("Cut");
//		rightClickMenu.getItems().add(cut);
//		MenuItem copy = new MenuItem("Copy");
//		rightClickMenu.getItems().add(copy);
//		MenuItem paste = new MenuItem("Paste");
//		rightClickMenu.getItems().add(paste);
//		MenuItem about = new MenuItem("About");
//		rightClickMenu.getItems().add(about);
//		MenuItem exit = new MenuItem("Exit");
//		rightClickMenu.getItems().add(exit);
//
//		exit.setOnAction(closeButton);
//		paste.setOnAction(pasteButton);
//		copy.setOnAction(copyButton);
//		cut.setOnAction(cutButton);
//		about.setOnAction(aboutButton);

		// setup custom resize and move fucntions

//		buttonArea.setOnMousePressed(clickContent);		 
//		buttonArea.setOnMouseDragged(dragContent);
//		buttonArea.setOnMouseReleased(hoverExit);

		// setup and show scene
		scene = new Scene(content, sizeX, sizeY);
		stage.setScene(scene);
		stage.show();
		
	}

	// EVENT HANDLERS

	// My patients setup mouse click
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
	
	EventHandler<MouseEvent> settingsClick = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent e) {
			if (e.getButton() == MouseButton.PRIMARY) {
				try {
					stage.hide();
					settingsView.showStage();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		}
	};
	
	EventHandler<MouseEvent> findClick = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent e) {
			if (e.getButton() == MouseButton.PRIMARY) {
				try {
					stage.hide();
					SearchView search = new SearchView(sizeX, sizeY, stage.getX(), stage.getY(), user);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		}
	};
	
}
