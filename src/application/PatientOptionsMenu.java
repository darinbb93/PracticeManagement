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

public class PatientOptionsMenu {
	private double sizeX;
	private double sizeY;
	private double positionX;
	private double positionY;
	private Stage stage;
	private Scene scene;
	private Patient user;
	private MyPatients prevMenu;
	URL imgURL;
	PatientOptionsMenu self = this;

	public PatientOptionsMenu(double _sizeX, double _sizeY, double _positionX, double _positionY, Patient _user,
			MyPatients _prevMenu) {

		sizeX = _sizeX;
		sizeY = _sizeY;
		positionX = _positionX;
		positionY = _positionY;
		user = _user;
		prevMenu = _prevMenu;

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

		BorderPane content = new BorderPane();
		BorderPane subContent = new BorderPane();
		VBox patientOptions = new VBox();
		Text usersName = new Text(user.getName());
		HBox centering = new HBox();
		BorderPane nameNbuttons = new BorderPane();
		BorderPane backBtnContainer = new BorderPane();
		Button back = new Button();
		BorderPane addViewButtons = new BorderPane();
		Button view = new Button();
		Button addExist = new Button();
		subContent.setTop(backBtnContainer);
		subContent.setCenter(centering);
		centering.getChildren().add(patientOptions);
		addViewButtons.setLeft(view);
		addViewButtons.setRight(addExist);
		nameNbuttons.setLeft(usersName);
		content.setTop(nameNbuttons);
		content.setCenter(subContent);

		// setup buttons
		imgURL = getClass().getResource("back.png");
		backBtnContainer.setLeft(back);
		back.setMinSize(70, 30);
		back.setStyle("-fx-background-image: url('" + imgURL.toString() + "'); -fx-background-repeat: no-repeat;"
				+ "  -fx-background-position: center; -fx-background-size: 68px 24px; ");
		back.setOnAction(backClick);

		centering.setAlignment(Pos.CENTER);
		nameNbuttons.setStyle("-fx-background-color: rgb(250, 250, 250)");
		subContent.setStyle("-fx-background-color: rgb(230, 238, 242)");
		usersName.setFont(overall);

//Buttons setup as HBox with an image component and Text 

		// equipment
		HBox equipment = new HBox();
		imgURL = getClass().getResource("eq50.png");
		Image eqIMG = new Image(imgURL.toString());
		ImageView eqIMG_VIEW = new ImageView(eqIMG);
		Text eqTXT = new Text("Equipment");
		eqTXT.setFont(overall);
		equipment.getChildren().addAll(eqIMG_VIEW, eqTXT);
		equipment.setOnMouseClicked(equipmentClick);

		// Patient Find
		HBox tPlan = new HBox();
		imgURL = getClass().getResource("tPlan50.png");
		Image tPlanIMG = new Image(imgURL.toString());
		ImageView tPlanIMG_VIEW = new ImageView(tPlanIMG);
		Text tPlanTXT = new Text("Treatment Plan");
		tPlanTXT.setFont(overall);
		tPlan.getChildren().addAll(tPlanIMG_VIEW, tPlanTXT);
		tPlan.setOnMouseClicked(tPlanClick);

		// time Table
		HBox info = new HBox();
		imgURL = getClass().getResource("info50.png");
		Image infoIMG = new Image(imgURL.toString());
		ImageView infoIMG_VIEW = new ImageView(infoIMG);
		Text infoTXT = new Text("Patient Details");
		infoTXT.setFont(overall);
		info.getChildren().addAll(infoIMG_VIEW, infoTXT);
		info.setOnMouseClicked(infoClick);

		// adding content to center of content border pane
		patientOptions.getChildren().addAll(equipment, tPlan, info);

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
		// following code must remain below "stage.show()" in order to properly
		// modify background color for the "textArea" casting it to a "Region"
//		Region region = (Region) textArea.lookup(".content");
//		region.setStyle( "-fx-background-color: rgb(253, 253, 201)" );
	}

	// show stage
	public void showStage(double _positionX, double _positionY) {
		positionX = _positionX;
		positionY = _positionY;
		buildUpStage();
	}

	// Back button click setup
	EventHandler<ActionEvent> backClick = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			try {
				prevMenu.showStage(stage.getX(), stage.getY());
				stage.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	};

	// Treatment plan setup mouse click
	EventHandler<MouseEvent> tPlanClick = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent e) {
			if (e.getButton() == MouseButton.PRIMARY) {
				try {
//					MyPatients patientsMenu = new MyPatients(sizeX, sizeY, stage.getX(), stage.getY(), user);
					stage.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		}
	};

	// Equipment setup mouse click
	EventHandler<MouseEvent> equipmentClick = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent e) {
			if (e.getButton() == MouseButton.PRIMARY) {
				try {
					stage.close();
					EquipmentView equipmentMenu = new EquipmentView(sizeX, sizeY, stage.getX(), stage.getY(), user,
							self);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		}
	};

	// Patient Details mouse click
	EventHandler<MouseEvent> infoClick = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent e) {
			if (e.getButton() == MouseButton.PRIMARY) {
				try {
//							MyPatients patientsMenu = new MyPatients(sizeX, sizeY, stage.getX(), stage.getY(), user);
					stage.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		}
	};
}
