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
public class TPlanView {
	private double sizeX;
	private double sizeY;
	private double positionX;
	private double positionY;	
	private Scene scene;
	private Stage stage;
	private Patient thePatient;
	private boolean doctorView;
	private ListView<Treatment> tPLan;
	private TPlanView self = this;
	private PatientOptionsMenu prevMenuDoc;
	
	
	//Previous menu getter for doctors
	public PatientOptionsMenu getPrevMenuDoc() {
		return prevMenuDoc;
	}
	
	public TPlanView(double _sizeX, double _sizeY, double _positionX, double _positionY, Patient _thePatient,PatientOptionsMenu _prevMenuDoc, boolean _doctorView) {

		thePatient = _thePatient;
		sizeX = _sizeX;
		sizeY = _sizeY;
		positionX = _positionX;
		positionY = _positionY;
		doctorView = _doctorView;
		prevMenuDoc = _prevMenuDoc;
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
		stage.setTitle("Treatment Plan");

		// setup fonts for text
		Font overall = Font.font("Aral", 20);

		// initialising components
		BorderPane content = new BorderPane();
		VBox patients = new VBox();
		Text usersName = new Text(thePatient.getName());	
		BorderPane title = new BorderPane();
		Text titleTXT = new Text("Treatment Plan");
		BorderPane nameNbuttons = new BorderPane();
		BorderPane backBtnContainer = new BorderPane();
		Button back = new Button();
		tPLan = new ListView<>();
		BorderPane addViewButtons = new BorderPane();
		Button view = new Button();
		Button addNew = new Button();
		Button remove = new Button();
		title.setCenter(titleTXT);
		addViewButtons.setLeft(view);
		nameNbuttons.setLeft(usersName);
		content.setTop(nameNbuttons);
		content.setCenter(patients);

	
		// setup buttons
		URL imgURL = getClass().getResource("back.png");
		backBtnContainer.setLeft(back);
		back.setMinSize(50, 15);
		back.setStyle("-fx-background-image: url('" + imgURL.toString() + "'); -fx-background-repeat: no-repeat;"
				+ "  -fx-background-position: center; -fx-background-size: 48px 18px; ");
		back.setOnAction(backClick);

		view.setText("View");
		view.setOnAction(viewClick);
		addNew.setText("Add new");
		addNew.setOnAction(addClick);
		remove.setText("Remove");
		remove.setOnAction(removeClick);
		
		//Setup styles
		title.setStyle("-fx-font-size: 20px; -fx-background-color: rgb(200, 238, 242); -fx-text-align: center;");
		nameNbuttons.setStyle("-fx-background-color: rgb(250, 250, 250)");
		patients.setStyle("-fx-background-color: rgb(230, 238, 242)");
		usersName.setFont(overall);
		tPLan.setStyle("-fx-font-size: 15px");
//		tPLan.getSelectionModel().i
		
		//populate tPlan list
		for (Treatment treatment : thePatient.gettPlan()) {
			tPLan.getItems().add(treatment);

		}
		
		//setup add/remove button if doctor viewing page		
		if(doctorView) {
			addViewButtons.setRight(addNew);		
			addViewButtons.setCenter(remove);
		}
		
		//add components to main part of the scene
		patients.getChildren().addAll(backBtnContainer, title, tPLan, addViewButtons);

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
	
	// Back button action handler
	EventHandler<ActionEvent> backClick = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			try {
				if(doctorView) {
					stage.close();
					prevMenuDoc.showStage(stage.getX(), stage.getY());
				}else {
				stage.close();
				PatientMainMenu mainMenuP = new PatientMainMenu(sizeX, sizeY, stage.getX(), stage.getY(), thePatient);
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	};
	
	//view button action handler
	EventHandler<ActionEvent> viewClick = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			try {				
				stage.close();
				TBlockView viewBlock = new TBlockView(sizeX, sizeY, stage.getX(), stage.getY(), thePatient, self, tPLan.getSelectionModel().getSelectedItem());
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
					stage.close();
					TBlockAdd viewBlock = new TBlockAdd(sizeX, sizeY, stage.getX(), stage.getY(), thePatient, self);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
		};
		
		//Remove button action handler
		EventHandler<ActionEvent> removeClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {	
					thePatient.gettPlan().remove(tPLan.getSelectionModel().getSelectedItem());
					tPLan.getItems().clear();
					for (Treatment treatment : thePatient.gettPlan()) {
						tPLan.getItems().add(treatment);

					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
		};
}