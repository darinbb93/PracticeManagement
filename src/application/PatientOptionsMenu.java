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
 * This class represents and builds the Menu giving the doctor
 * options to either give the patient equipment,
 * view/change their treatment plan or just view their details.
 * 
 * @author Darin Bogdanov  - bogdb001
 *	
 */
public class PatientOptionsMenu {
	private double sizeX;
	private double sizeY;
	private double positionX;
	private double positionY;
	private Stage stage;
	private Scene scene;
	private Patient thePatient;
	private MyPatients prevMenu;
	URL imgURL;
	PatientOptionsMenu self = this;

	public PatientOptionsMenu(double _sizeX, double _sizeY, double _positionX, double _positionY, Patient _thePatient,
			MyPatients _prevMenu) {

		sizeX = _sizeX;
		sizeY = _sizeY;
		positionX = _positionX;
		positionY = _positionY;
		thePatient = _thePatient;
		prevMenu = _prevMenu;

		buildUpStage();

	}

	// creates the scene and all elements, adds to stage and shows it
	private void buildUpStage() {
		// Stage setup
		stage = new Stage();
		stage.setX(positionX);
		stage.setY(positionY);
		stage.setTitle("Patient Options for Doctors");
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
		Text usersName = new Text(thePatient.getName());
		HBox centering = new HBox();
		BorderPane nameNbuttons = new BorderPane();
		BorderPane backBtnContainer = new BorderPane();
		Button back = new Button();
		subContent.setTop(backBtnContainer);
		subContent.setCenter(centering);
		centering.getChildren().add(patientOptions);
		nameNbuttons.setLeft(usersName);
		content.setTop(nameNbuttons);
		content.setCenter(subContent);

		// setup buttons
		imgURL = getClass().getResource("back.png");
		backBtnContainer.setLeft(back);
		back.setMinSize(50, 15);
		back.setStyle("-fx-background-image: url('" + imgURL.toString() + "'); -fx-background-repeat: no-repeat;"
				+ "  -fx-background-position: center; -fx-background-size: 48px 18px; ");
		back.setOnAction(backClick);

		centering.setAlignment(Pos.CENTER);
		nameNbuttons.setStyle("-fx-background-color: rgb(250, 250, 250)");
		subContent.setStyle("-fx-background-color: rgb(230, 238, 242)");
		usersName.setFont(overall);

//Buttons setup as HBox with an image component and Text 

		// equipment view link
		HBox equipment = new HBox();
		imgURL = getClass().getResource("eq50.png");
		Image eqIMG = new Image(imgURL.toString());
		ImageView eqIMG_VIEW = new ImageView(eqIMG);
		Text eqTXT = new Text("Equipment");
		eqTXT.setFont(overall);
		equipment.getChildren().addAll(eqIMG_VIEW, eqTXT);
		equipment.setOnMouseClicked(equipmentClick);

		// Treatment plan view link
		HBox tPlan = new HBox();
		imgURL = getClass().getResource("tPlan50.png");
		Image tPlanIMG = new Image(imgURL.toString());
		ImageView tPlanIMG_VIEW = new ImageView(tPlanIMG);
		Text tPlanTXT = new Text("Treatment Plan");
		tPlanTXT.setFont(overall);
		tPlan.getChildren().addAll(tPlanIMG_VIEW, tPlanTXT);
		tPlan.setOnMouseClicked(tPlanClick);

		// Patients details view link
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


		// setup and show scene
		scene = new Scene(content, sizeX, sizeY);
		stage.setScene(scene);
		stage.show();

	}

	// show stage
	public void showStage(double _positionX, double _positionY) {
		positionX = _positionX;
		positionY = _positionY;
		buildUpStage();
	}

	//Getter for previous menu
	public MyPatients getPrevMenu() {
		return prevMenu;
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



	// Equipment setup mouse click
	EventHandler<MouseEvent> equipmentClick = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent e) {
			if (e.getButton() == MouseButton.PRIMARY) {
				try {
					stage.close();
					EquipmentView equipmentMenu = new EquipmentView(sizeX, sizeY, stage.getX(), stage.getY(), thePatient,
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
					stage.close();
					PatientDetailsView pDetails = new PatientDetailsView(sizeX, sizeY, stage.getX(), stage.getY(), thePatient, prevMenu.getUser());
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		}
	};
	//Mouse click setup for tPlan
		EventHandler<MouseEvent> tPlanClick = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (e.getButton() == MouseButton.PRIMARY) {
					try {
						stage.close();
						TPlanView tPlanMenu = new TPlanView(sizeX, sizeY, stage.getX(), stage.getY(), thePatient, self, true);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}

			}
		};
}
