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
 * This class represents and builds the Main Menu for patients.
 * It presents them with options to go to setting, view their 
 * appointments time table view their treatment plan and book emergency appointment.
 * @author Darin Bogdanov  - bogdb001
 *	
 */
public class PatientMainMenu {
	private double sizeX;
	private double sizeY;
	private double positionX;
	private double positionY;
	private BorderPane content;
	private VBox buttonArea;
	private HBox settings;
	private HBox timetable;
	private HBox tPlan;
	private HBox info;
	private Stage stage;
	private Text usersName;
	private Scene scene;
	private Patient user;
	URL imgURL;

	public PatientMainMenu(double _sizeX, double _sizeY, double _positionX, double _positionY, Patient _user) {

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
		stage.setTitle("Patients Main Menu");
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


		// time Table
		timetable = new HBox();
		imgURL = getClass().getResource("tTable50.png");
		Image tTblIMG = new Image(imgURL.toString());
		ImageView tTblIMG_VIEW = new ImageView(tTblIMG);
		Text tTblTXT = new Text("Timetable");
		tTblTXT.setFont(overall);
		timetable.getChildren().addAll(tTblIMG_VIEW, tTblTXT);
		
		// Treatment Plan view link
		tPlan = new HBox();
		imgURL = getClass().getResource("tPlan50.png");
		Image tPlanIMG = new Image(imgURL.toString());
		ImageView tPlanIMG_VIEW = new ImageView(tPlanIMG);
		Text tPlanTXT = new Text("Treatment Plan");
		tPlanTXT.setFont(overall);
		tPlan.getChildren().addAll(tPlanIMG_VIEW, tPlanTXT);
		tPlan.setOnMouseClicked(tPlanClick);
		
		// settings
		settings = new HBox();
		imgURL = getClass().getResource("stngs50.png");
		Image stngsIMG = new Image(imgURL.toString());
		ImageView stngsIMG_VIEW = new ImageView(stngsIMG);
		Text stngsTXT = new Text("Settings");
		stngsTXT.setFont(overall);
		settings.getChildren().addAll(stngsIMG_VIEW, stngsTXT);		
	

		// Patient info view link
		info = new HBox();
		imgURL = getClass().getResource("info50.png");
		Image infoIMG = new Image(imgURL.toString());
		ImageView infoIMG_VIEW = new ImageView(infoIMG);
		Text infoTXT = new Text("Patient Details");
		infoTXT.setFont(overall);
		info.getChildren().addAll(infoIMG_VIEW, infoTXT);
		info.setOnMouseClicked(infoClick);
		

		buttonArea.getChildren().addAll(timetable, tPlan, info, settings);


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
	
	// Treatment plan setup mouse click
	EventHandler<MouseEvent> tPlanClick = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent e) {
			if (e.getButton() == MouseButton.PRIMARY) {
				try {
					stage.close();
					TPlanView tPlanMenu = new TPlanView(sizeX, sizeY, stage.getX(), stage.getY(), user, null, false);

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
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}

			}
		};
	
}
