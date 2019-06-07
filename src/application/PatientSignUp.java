package application;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import Data.Member;
import Data.Patient;
import Data.Practitioner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**	
 * This class represents and builds the Menu where patients will be added 
 * to the system and takes all their personal details.
 * 
 * @author Darin Bogdanov  - bogdb001
 *	
 */
public class PatientSignUp {
	private double sizeX;
	private double sizeY;
	private double positionX;
	private double positionY;
	private VBox content;
	private TextField username;
	private TextField password;
	private TextField name;
	private TextField address;
	private TextField email;
	private TextField phone;
	private TextField medicare;
	private Date dob;
	private TextField dobTXT;
	private Button signUp;
	private Stage stage;
	private Scene scene;
	private Practitioner user;
	URL imgURL;
	private Text warning = new Text("");

	public PatientSignUp(double _sizeX, double _sizeY, double _positionX, double _positionY, Practitioner _user) {

		sizeX = _sizeX;
		sizeY = _sizeY;
		positionX = _positionX;
		positionY = _positionY;
		user = _user;

		buildUpStage();

	}

	public PatientSignUp(double _sizeX, double _sizeY, double _positionX, double _positionY) {

		sizeX = _sizeX;
		sizeY = _sizeY;
		positionX = _positionX;
		positionY = _positionY;

		buildUpStage();

	}

	// creates the scene and all elements, adds to stage and shows it
	private void buildUpStage() {
		// Stage setup
		stage = new Stage();
		stage.setX(positionX);
		stage.setY(positionY);
		stage.setTitle("Patient Sign Up");
		stage.setHeight(sizeY);
		stage.setMinHeight(sizeY);
		stage.setMaxHeight(sizeY);
		stage.setWidth(sizeX);
		stage.setMaxWidth(sizeX);
		stage.setMinWidth(sizeX);

		// Assigned font for all texts
		Font overall = Font.font("Aral", 20);

		content = new VBox();
		username = new TextField();
		password = new TextField();
		name = new TextField();
		address = new TextField();
		email = new TextField();
		phone = new TextField();
		medicare = new TextField();
		dobTXT = new TextField();
		signUp = new Button("Create Account");
		signUp.setOnAction(signUpAction);
		content.setPadding(new Insets(10, 10, 10, 10));
		content.setSpacing(10);
		username.setPromptText("enter your USERNAME here. (at least 6 characters");
		password.setPromptText("enter your PASSWORD here. (at least 6 characters");
		name.setPromptText("enter your FULL NAME here. (at least one space and 6 chars)");
		address.setPromptText("enter your ADDRESS here.");
		email.setPromptText("enter your EMAIL here.");
		phone.setPromptText("enter tour PHONE NUMBER here.");
		medicare.setPromptText("enter your MEDICARE NUMBER here.");
		dobTXT.setPromptText("enter your DATE OF BIRTH here. (e.g. 16/01/1998)");
		content.getChildren().addAll(username, password, name, address, dobTXT, email, phone, medicare, signUp);

		// setup and show scene
		scene = new Scene(content, sizeX, sizeY);
		stage.setScene(scene);
		stage.show();
	}

	// Login button action
	EventHandler<ActionEvent> signUpAction = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			boolean OK = true;
			warning.setText("");
			// CHECKING UESRNAME MORE THAN 6 CHARS
			if (username.getText().length() < 6) {
				username.setStyle("-fx-text-box-border: red;-fx-focus-color: red ;");
				username.setText("");
				username.setPromptText("USERNAME too short min 6 chars");
				OK = false;
			}
			// CHECKING FOR DUPLICATE USERNAME
			else {
				for (Member member : PracticeInterface.database.getMembers()) {
					if (member.getUsername().equals(username.getText())) {
						username.setStyle("-fx-text-box-border: red;-fx-focus-color: red ;");
						username.setText("");
						username.setPromptText("USERNAME exists");
						OK = false;

					}
				}
			}
			// VERIFYIN PASSWORD MORE THAN 6 CHARS
			if (password.getText().length() < 6) {
				password.setStyle("-fx-text-box-border: red;-fx-focus-color: red ;");
				password.setText("");
				password.setPromptText("PASSWORD too short min 6 chars");
				OK = false;
			}
			// VERIFYING NAME
			if (!name.getText().contains(" ") || name.getText().length() < 6) {
				name.setStyle("-fx-text-box-border: red;-fx-focus-color: red ;");
				name.setText("");
				name.setPromptText("enter FULL NAME please");
				OK = false;
			}
			// VERIFYING ADDRESS(POORLY)
			if (!address.getText().contains(",") || address.getText().length() < 12) {
				address.setStyle("-fx-text-box-border: red;-fx-focus-color: red ;");
				address.setText("");
				address.setPromptText("enter ADDRESS corectly with comas separating");
				OK = false;
			}
			// VERIFYING EMAIL
			if (!email.getText().contains("@") || email.getText().length() < 6) {
				email.setStyle("-fx-text-box-border: red;-fx-focus-color: red ;");
				email.setText("");
				email.setPromptText("enter EMAIL corectly");
				OK = false;

			}
			// VERIFYING PHONE
			for (int i = -1; i < phone.getText().length(); i++) {
				if(i == -1) i++;
				if (phone.getText().length() != 10 || (phone.getText().charAt(i) < '0' && phone.getText().charAt(i) > '9')) {
					phone.setStyle("-fx-text-box-border: red;-fx-focus-color: red ;");
					phone.setText("");
					phone.setPromptText("enter PHONE correctly 10 digits");
					OK = false;
				}
			}
			// verifying medicare
			if (medicare.getText().length() != 11) {
				medicare.setStyle("-fx-text-box-border: red;-fx-focus-color: red ;");
				medicare.setText("");
				medicare.setPromptText("enter MEDICARE NUMBER correctly 11 chars");
				OK = false;
			}
			// VERIFYING DATE
			try {
				dob = new SimpleDateFormat("dd/MM/yyyy").parse(dobTXT.getText());

			} catch (Exception dateFail) {
				dobTXT.setStyle("-fx-text-box-border: red;-fx-focus-color: red ;");
				dobTXT.setText("");
				dobTXT.setPromptText("enter DATE OF BIRTH in the fortmat (dd/MM/yyyy)");
				OK = false;

			}

			if (OK) {
				
				Patient newP = new Patient(username.getText(), password.getText(), name.getText(), address.getText(), email.getText(), phone.getText(), dob, medicare.getText());
				PracticeInterface.database.getMembers().add(newP);
				
				//IF A DOCTOR IS ADDING NEW PATIENT IT WILL ATTACH TO THE DOCTORS ACCOUNT AND WILL RETURN TO 
				//DOCTORS MAIN MENU
				if(user != null) {
					user.getPatients().add(newP);
					stage.close();
					MainMenu mainMenu = new MainMenu(sizeX, sizeY, stage.getX(), stage.getY(), user);	
				}else {
					
					//OTHERWISE WILL JUST OPEN PATIENT MAIN MENU PASSING THE NEW PATIENT TO IT
					stage.close();
					PatientMainMenu mainMenu = new PatientMainMenu(sizeX, sizeY, stage.getX(), stage.getY(), newP);	

				}
			
			}
		}
	};

}