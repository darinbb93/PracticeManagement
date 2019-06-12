package application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;

import Data.Patient;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
 
public class emergencyAppointment {

	private double sizeX;
	private double sizeY;
	private double positionX;
	private double positionY;
	private BorderPane content;
	private VBox loginDetails;
	private TextField username;
	private TextField password;
	private Text warning = new Text("");
	private Button login;
	private Button signup;
	private Stage stage;
	private Scene scene;
    private DatePicker appointmentDatePicker;
    private DatePicker appointmentTimePicker;
    private Patient user;
    
	public emergencyAppointment(double _sizeX, double _sizeY, double _positionX, double _positionY, Patient _user) {

		sizeX = _sizeX;
		sizeY = _sizeY;
		positionX = _positionX;
		positionY = _positionY;
		user = _user;
		buildUpStage();

	}

	// creates the scene and all elements, adds to stage and shows it
	private void buildUpStage() {
		stage = new Stage();
		stage.setX(positionX);
		stage.setY(positionY);
		stage.setTitle("Practice Management - Login Screen");
 
    
    

 
  
        VBox vbox = new VBox(20);
        vbox.setStyle("-fx-padding: 10;");
        Scene emergencyAppointmentScene = new Scene(vbox, 300, 600);
        
        
        //Creating a text field
        TextField subject = new TextField();
        TextField description = new TextField();
        
        appointmentDatePicker = new DatePicker();	
        appointmentDatePicker.setValue(LocalDate.now());
       
        //appointmentTimePicker = new DatePicker();
        //appointmentTimePicker.set(LocalDateTime.of(LocalTime.now()));
        
        
        GridPane root = new GridPane();
        
        root.setHgap(10);
        root.setVgap(10);
        
        //Label for the type of emergency and adding 
        Label subjectLabel = new Label("Write the type of your emergency"); 
        GridPane.setHalignment(subjectLabel, HPos.LEFT);
        root.add(subjectLabel, 0, 1);
        root.add(subject, 0, 2);
        
        //Label to the selecting date and adding the DatePicker
        Label appointmentDate = new Label("Select preferred date.");
        GridPane.setHalignment(appointmentDate, HPos.LEFT);
        root.add(appointmentDate, 0, 3);
        appointmentDate.setPadding(new Insets(20, 0,0,0));
        root.add(appointmentDatePicker, 0, 4);
        
        Label appointmentTime = new Label("Select preferred Time.");
        GridPane.setHalignment(appointmentTime, HPos.LEFT);
        appointmentTime.setPadding(new Insets(20, 0,0,0));
        root.add(appointmentTime, 0, 5);
        
        //Adding the label and setting up the Time Picker.
        ChoiceBox<String> hour = new ChoiceBox<>();
        hour.getItems().addAll("12","1","2","3","4","5","6","7","8","9","10","11");
        hour.setValue("9");
        ChoiceBox<String> minutes = new ChoiceBox<>();
        minutes.getItems().addAll("00","10","20","30","40","50");
        minutes.setValue("00");
        ChoiceBox<String> ampm = new ChoiceBox<>();
        ampm.getItems().addAll("am","pm");
        ampm.setValue("am");
        
        TilePane time = new TilePane(Orientation.HORIZONTAL);
		time.setAlignment(Pos.BOTTOM_LEFT);
		time.setPadding(new Insets(8, 10, 20, 0));
		time.setHgap(10.0);
		time.getChildren().addAll(hour, minutes, ampm);
		root.add(time, 0, 6);
		
        Label descriptionLabel = new Label("Write the description of your emergency"); 
        GridPane.setHalignment(descriptionLabel, HPos.LEFT);
        root.add(descriptionLabel, 0, 7);
        description.setMinHeight(100);
        root.add(description, 0, 8);
        
		//Creating a BACK button
		Button back = new Button("Back");
		back.setStyle("-fx-font: 12 arial; -fx-base: #b6e7c9;");
		
	
		//Write the name of the scene you want to go back to in place of NAME_OF_THE_SCENE
		back.setOnAction(e -> {
			stage.close();
			new PatientMainMenu(sizeX, sizeY, stage.getX(), stage.getY(), user);
		});


		Button save = new Button("Save appointment");
		save.setStyle("-fx-font: 12 arial; -fx-base: #b6e7c9;");

		TilePane btmButtons = new TilePane(Orientation.HORIZONTAL);
		btmButtons.setPadding(new Insets(100, 10, 20, 0));
		btmButtons.getChildren().addAll(save, back);
		root.add(btmButtons, 0, 9);
        
		
		save.setOnAction(e -> {
			Alert saveAppointment = new Alert(AlertType.NONE, "Appointment Created", ButtonType.OK);
			saveAppointment.initStyle(StageStyle.UNDECORATED);
			saveAppointment.show();
		});
        
        vbox.getChildren().add(root);
        
        stage.setTitle("Emergency Appointment");
        stage.setScene(emergencyAppointmentScene);
        stage.show();
    }
}