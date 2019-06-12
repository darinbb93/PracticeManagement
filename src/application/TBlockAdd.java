package application;

import Data.*;
import javafx.stage.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
/**	
 * 
 * 
 * @author Darin Bogdanov  - bogdb001
 *	
 */
public class TBlockAdd {
	private double sizeX;
	private double sizeY;
	private double positionX;
	private double positionY;
	private ChoiceBox<Treatment.Types> typeChoice;
	private ComboBox<Treatment.Types> typeCh;
	private TextArea instructions;
	private Date tDate;
	private TextArea tDateTXT;
	private Scene scene;
	private Stage stage;
	private Patient thePatient;
	private TPlanView prevMenu;
	
	public TBlockAdd(double _sizeX, double _sizeY, double _positionX, double _positionY, Patient _thePatient, TPlanView _prevMenu) {

		thePatient = _thePatient;
		sizeX = _sizeX;
		sizeY = _sizeY;
		positionX = _positionX;
		positionY = _positionY;
		prevMenu = _prevMenu;
		
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
		stage.setTitle("Treatment Block");

		// setup fonts for text
		Font overall = Font.font("Aral", 20);

		// initialising components
		BorderPane content = new BorderPane();
		VBox tBlock = new VBox();
		Text usersName = new Text(thePatient.getName());
		BorderPane title = new BorderPane();
		Text titleTXT = new Text("New Treatment Block");
		BorderPane nameNbuttons = new BorderPane();
		BorderPane backBtnContainer = new BorderPane();
		Button back = new Button();	
		Button add = new Button("Add");
		add.setOnAction(addClick);
		typeChoice = new ChoiceBox<Treatment.Types>();
		typeChoice.getItems().addAll(Treatment.Types.values());
		instructions = new TextArea();
		tDateTXT = new TextArea();
		
		Label typeLabel = new Label("Choose type:");
		Label instLabel = new Label("Instructions:");
		Label dateLabel = new Label("Type in date (e.g. 30/01/2000)");
		instructions.setWrapText(true);
		title.setCenter(titleTXT);
		nameNbuttons.setLeft(usersName);
		content.setTop(nameNbuttons);
		tBlock.getChildren().addAll(backBtnContainer, title, typeLabel, typeChoice,  instLabel, instructions, dateLabel, tDateTXT, add);
		content.setCenter(tBlock);

		// setup buttons
		URL imgURL = getClass().getResource("back.png");
		backBtnContainer.setLeft(back);
		back.setMinSize(50, 15);
		back.setStyle("-fx-background-image: url('" + imgURL.toString() + "'); -fx-background-repeat: no-repeat;"
				+ "  -fx-background-position: center; -fx-background-size: 48px 18px; ");
		back.setOnAction(backClick);

		
		
		//Setup styles
		title.setStyle("-fx-font-size: 20px; -fx-background-color: rgb(200, 238, 242); -fx-text-align: center;");
		nameNbuttons.setStyle("-fx-background-color: rgb(250, 250, 250)");
		tBlock.setStyle("-fx-background-color: rgb(230, 238, 242)");
		usersName.setFont(overall);
		tDateTXT.setMaxWidth(125);
		tDateTXT.setMaxHeight(25);
		// setup and show scene
		scene = new Scene(content, sizeX, sizeY);
		stage.setScene(scene);
		stage.show();

	}

	
		
	// Back button action handler
	EventHandler<ActionEvent> backClick = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			try {
				stage.close();
				prevMenu.showStage(stage.getX(), stage.getY());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	};
	
	// Add button action handler
		EventHandler<ActionEvent> addClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				boolean OK = true;
				
				//check Date
				try {
					tDate = new SimpleDateFormat("dd/MM/yyyy").parse(tDateTXT.getText());

				} catch (Exception dateFail) {
					tDateTXT.setStyle("-fx-text-box-border: red;-fx-focus-color: red ;");
					tDateTXT.setText("");
					tDateTXT.setPromptText("Target date: dd/MM/yyyy");
					OK = false;
				}
				//check instruction
				if(instructions.getText().length() < 20) {
					tDateTXT.setStyle("-fx-text-box-border: red;-fx-focus-color: red ;");
					tDateTXT.setText("");
					tDateTXT.setPromptText("Please provide sufficient instructions");
					OK = false;
				}
				if(OK) {
					Treatment newBlock = new Treatment(prevMenu.getPrevMenuDoc().getPrevMenu().getUser(), instructions.getText(), tDate, typeChoice.getSelectionModel().getSelectedItem());
					thePatient.gettPlan().add(newBlock);
					try {
						stage.close();
						prevMenu.showStage(stage.getX(), stage.getY());
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
			
		};
	
	
}
