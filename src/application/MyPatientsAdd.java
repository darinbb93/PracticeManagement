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
/**	
 * This class represents and builds the My patients view which shows the doctor all 
 * patients currently tied to their account/schedule. They can select and vie patients details.
 * Alternatively they can add a patient that is on record to their list, and can also remove patients.
 * (basically a favourites/bookmarks list for quick access)
 * 
 * @author Darin Bogdanov  - bogdb001
 *	
 */
public class MyPatientsAdd {
	private double sizeX;
	private double sizeY;
	private double positionX;
	private double positionY;	
	private Scene scene;
	private Stage stage;
	private Practitioner user;
	private VBox patients;
	private ListView<Patient> patientsList;
	private MyPatientsAdd self = this;
	
	public MyPatientsAdd(double _sizeX, double _sizeY, double _positionX, double _positionY, Practitioner _user) {

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
		patients = new VBox();
		BorderPane title = new BorderPane();
		BorderPane nameNbuttons = new BorderPane();
		BorderPane backBtnContainer = new BorderPane();
		Button back = new Button();
		patientsList = new ListView<Patient>();
		BorderPane addButton = new BorderPane();
		Button add = new Button("Add");
		addButton.setCenter(add);
		content.setTop(nameNbuttons);
		patients.getChildren().addAll(backBtnContainer, title, patientsList, addButton);
		content.setCenter(patients);

		// setup buttons
		URL imgURL = getClass().getResource("back.png");
		backBtnContainer.setLeft(back);
		back.setMinSize(50, 15);
		back.setStyle("-fx-background-image: url('" + imgURL.toString() + "'); -fx-background-repeat: no-repeat;"
				+ "  -fx-background-position: center; -fx-background-size: 48px 18px; ");
		back.setOnAction(backClick);
		add.setOnAction(addClick);
		
		
		//Setup styles
		title.setStyle("-fx-font-size: 20px; -fx-background-color: rgb(200, 238, 242); -fx-text-align: center;");
		nameNbuttons.setStyle("-fx-background-color: rgb(250, 250, 250)");
		patients.setStyle("-fx-background-color: rgb(230, 238, 242)");
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
		
		for (Member member : PracticeInterface.database.getMembers()) {
			try {			
				Patient patient = (Patient)member;
				patientsList.getItems().add(patient);
			}catch(Exception e) {
			}

		}


		// setup and show scene
		scene = new Scene(content, sizeX, sizeY);
		stage.setScene(scene);
		stage.show();

	}
	
	//getter for the user doctor/practitioner
	public Practitioner getUser() {
		return user;
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
				MyPatients patientsMenu = new MyPatients(sizeX, sizeY, stage.getX(), stage.getY(), user);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	};
	
	
	
	
	//Add button action handler
		EventHandler<ActionEvent> addClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					user.getPatients().add(patientsList.getSelectionModel().getSelectedItem());
					stage.close();
					MyPatients patientsMenu = new MyPatients(sizeX, sizeY, stage.getX(), stage.getY(), user);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
		};
}