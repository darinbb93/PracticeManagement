package application;

import Data.*;
import javafx.stage.*;
import javafx.util.Callback;
import java.net.URL;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class MyPatients {
	private double sizeX;
	private double sizeY;
	private double positionX;
	private double positionY;	
	private Scene scene;
	private Stage stage;
	private Practitioner user;
	private ListView<Patient> patientsList;
	private MyPatients self = this;
	
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
		BorderPane content = new BorderPane();
		VBox patients = new VBox();
		Text usersName = new Text(user.getName());
		BorderPane title = new BorderPane();
		Text titleTXT = new Text("My Patients");
		BorderPane nameNbuttons = new BorderPane();
		BorderPane backBtnContainer = new BorderPane();
		Button back = new Button();
		patientsList = new ListView<Patient>();
		BorderPane addViewButtons = new BorderPane();
		Button view = new Button();
		Button addExist = new Button();
		title.setCenter(titleTXT);
		addViewButtons.setLeft(view);
		addViewButtons.setRight(addExist);		
		nameNbuttons.setLeft(usersName);
		content.setTop(nameNbuttons);
		patients.getChildren().addAll(backBtnContainer, title, patientsList, addViewButtons);
		content.setCenter(patients);

		// setup buttons
		URL imgURL = getClass().getResource("back.png");
		backBtnContainer.setLeft(back);
		back.setMinSize(70, 30);
		back.setStyle("-fx-background-image: url('" + imgURL.toString() + "'); -fx-background-repeat: no-repeat;"
				+ "  -fx-background-position: center; -fx-background-size: 68px 24px; ");
		back.setOnAction(backClick);

		view.setText("View");
		view.setOnAction(viewClick);
		addExist.setText("Add Existing");
		
		
		//Setup styles
		title.setStyle("-fx-font-size: 20px; -fx-background-color: rgb(200, 238, 242); -fx-text-align: center;");
		nameNbuttons.setStyle("-fx-background-color: rgb(250, 250, 250)");
		patients.setStyle("-fx-background-color: rgb(230, 238, 242)");
		usersName.setFont(overall);
		patientsList.setStyle("-fx-font-size: 15px");
		//Populating and setting display name for ListView of patients
		patientsList.setCellFactory(new Callback<ListView<Patient>, ListCell<Patient>>() {
		    @Override
		    public ListCell<Patient> call(ListView<Patient> param) {
		         ListCell<Patient> cell = new ListCell<Patient>() {
		             @Override
		            protected void updateItem(Patient item, boolean empty) {
		                super.updateItem(item, empty);
		                if(item != null) {
		                    setText(item.getName());
		                } else {
		                    setText(null);
		                }
		            }
		         };
		        return cell;
		    }
		});
		
		for (Patient patient : user.getPatients()) {
			patientsList.getItems().add(patient);

		}


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
	
	// Back button click setup
	EventHandler<ActionEvent> backClick = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			try {
				stage.close();
				MainMenu mainMenu = new MainMenu(sizeX, sizeY, stage.getX(), stage.getY(), user);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	};
	
	EventHandler<ActionEvent> viewClick = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			try {
				PatientOptionsMenu mainMenu = new PatientOptionsMenu(sizeX, sizeY, 
						stage.getX(), stage.getY(), patientsList.getSelectionModel().getSelectedItem(), self);
				stage.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	};
}