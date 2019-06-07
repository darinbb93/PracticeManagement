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
		Text titleTXT = new Text("My Patients");
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

		//setup username display and add button based on what is the user of the page 
	
		// setup buttons
		URL imgURL = getClass().getResource("back.png");
		backBtnContainer.setLeft(back);
		back.setMinSize(70, 30);
		back.setStyle("-fx-background-image: url('" + imgURL.toString() + "'); -fx-background-repeat: no-repeat;"
				+ "  -fx-background-position: center; -fx-background-size: 68px 24px; ");
		back.setOnAction(backClick);

		view.setText("View");
		view.setOnAction(viewClick);
		addNew.setText("Add new");
		remove.setText("Remove");
		
		//Setup styles
		title.setStyle("-fx-font-size: 20px; -fx-background-color: rgb(200, 238, 242); -fx-text-align: center;");
		nameNbuttons.setStyle("-fx-background-color: rgb(250, 250, 250)");
		patients.setStyle("-fx-background-color: rgb(230, 238, 242)");
		usersName.setFont(overall);
		tPLan.setStyle("-fx-font-size: 15px");
		//Populating and setting display name for ListView of patients
//		tPLan.setCellFactory(new Callback<ListView<Treatment>, ListCell<Treatment>>() {
//		    @Override
//		    public ListCell<Treatment> call(ListView<Treatment> param) {
//		         ListCell<Treatment> cell = new ListCell<Treatment>() {
//		             @Override
//		            protected void updateItem(Treatment item, boolean empty) {
//		                super.updateItem(item, empty);
//		                if(item != null) {
//		                    setText(item.);
//		                } else {
//		                    setText(null);
//		                }
//		            }
//		         };
//		        return cell;
//		    }
//		});
		
		for (Treatment treatment : thePatient.gettPlan()) {
			tPLan.getItems().add(treatment);

		}
		
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
	
	// Back button click setup
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
	
	EventHandler<ActionEvent> viewClick = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			try {
//				PatientOptionsMenu mainMenu = new PatientOptionsMenu(sizeX, sizeY, 
//						stage.getX(), stage.getY(), patientsList.getSelectionModel().getSelectedItem(), self);
				stage.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	};
}