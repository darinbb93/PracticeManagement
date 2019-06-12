package application;

import javafx.stage.*;
import java.net.URL;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import Data.*;
/**	
 * This class represents and builds the login menu and determines whether a doctor 
 * or patient is trying to login and leads to the appropriate menu. 
 * It also has a SignUp option which takes the user to a signup (as a Patient) page
 * 
 * @author Darin Bogdanov  - bogdb001
 *	
 */
public class LoginMenu {

	private double sizeX;
	private double sizeY;
	private double positionX;
	private double positionY;
	private double xOffset;
	private double yOffset;
	private BorderPane content;
	private VBox loginDetails;
	private TextField username;
	private TextField password;
	private Text warning = new Text("");
	private Button login;
	private Button signup;
	private Stage stage;
	private Scene scene;
	
	public LoginMenu(double _sizeX, double _sizeY, double _positionX, double _positionY) {

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
		stage.setTitle("Practice Management - Login Screen");

		// VBOX login prompt setup
		loginDetails = new VBox();
		Text loginPrompt = new Text("Please enter you login details or\nsign up now so you can use the app.");
		username = new TextField();
		username.setPromptText("Username");
		username.setMaxWidth(150);

		password = new TextField();
		password.setPromptText("Password");
		password.setMaxWidth(150);

		// Button setup
		HBox buttonArea = new HBox();
		login = new Button("Login");
		login.setOnAction(loginAction);
		signup = new Button("Signup");
		signup.setOnAction(signUpAction);
		buttonArea.setAlignment(Pos.CENTER);
		buttonArea.setSpacing(20);
		buttonArea.getChildren().addAll(login, signup);
		warning.setFill(Color.RED);
		loginDetails.getChildren().addAll(loginPrompt, username, password, warning, buttonArea);
		loginDetails.setAlignment(Pos.CENTER);

		// content borderpane setup
		content = new BorderPane();
		content.setStyle("-fx-background-color: rgb(245, 245, 245)");
		content.setCenter(loginDetails);

		// setup custom resize and move fucntions
		stage.setHeight(sizeY);
		stage.setMinHeight(sizeY);
		stage.setMaxHeight(sizeY);
		stage.setWidth(sizeX);
		stage.setMaxWidth(sizeX);
		stage.setMinWidth(sizeX);

		// setup and show scene
		scene = new Scene(content, sizeX, sizeY);
		stage.setScene(scene);
		stage.show();

	}

	// Login button action
	EventHandler<ActionEvent> loginAction = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			if (username.getText().length() < 6) {
				username.setStyle("-fx-text-box-border: red;-fx-focus-color: red ;");
				warning.setText("Invalid username! Try again!");

			} else if (password.getText().length() < 6) {
				password.setStyle("-fx-text-box-border: red;-fx-focus-color: red ;");
				warning.setText("Invalid password! Try again!");

			} else {
				boolean found = false;
				Member theMember = null;
				Practitioner aDoctor = null;
				Patient aPatient = null;
				boolean isDoctor = true;
				for (Member member : PracticeInterface.database.getMembers()) {
					if (username.getText().equals(member.getUsername())) {
						found = true;
						theMember = member;
						try {
							aDoctor = (Practitioner)member;
						} catch (Exception notDoctor) {
							aPatient = (Patient)member;
							isDoctor = false;
						}
						break;
					}

				}
				if (!found) {
					warning.setText("Invalid Username! Try again!");

				} else if (!theMember.getPassword().equals(password.getText())) {
					warning.setText("Invalid Password! Try again!");
				} else {
					if (isDoctor) {
						// DOCTOR LOGIN
						try {
							stage.close();
							MainMenu mainMenu = new MainMenu(sizeX, sizeY, stage.getX(), stage.getY(), aDoctor);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					// PATIENT LOGIN
					else {
						try {
							stage.close();
							PatientMainMenu pMainMenu = new PatientMainMenu(sizeX, sizeY, stage.getX(), stage.getY(),
									aPatient);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}

				}
			}
		}
	};
	
	
	//SignUp action handler
	EventHandler<ActionEvent> signUpAction = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			stage.close();
			PatientSignUp signUp = new PatientSignUp(sizeX, sizeY, stage.getX(), stage.getY());
		}
	};

}