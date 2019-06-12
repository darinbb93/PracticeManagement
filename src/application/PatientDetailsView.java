package application;

import java.net.URL;

import Data.Member;
import Data.Patient;
import Data.Practitioner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.image.Image ;
import javafx.scene.image.ImageView;

public class PatientDetailsView {
	private double sizeX;
	private double sizeY;
	private double positionX;
	private double positionY;
	final Patient patient;
	private Button back = new Button();
	final Practitioner user;
	
	private Stage stage = new Stage();
	Group root = new Group();
	Scene scene = new Scene(root, sizeX, sizeY);
	
	public PatientDetailsView(double sizeX, double sizeY, double positionX, double positionY, Patient patient, Practitioner user) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.positionX = positionX;
		this.positionY = positionY;
		this.patient = patient;
		this.user = user;
		
		stage.setX(positionX);
		stage.setY(positionY);
		stage.setHeight(sizeY);
		stage.setWidth(sizeX);
		stage.setResizable(false);
		stage.setTitle("Patient Detail");
		
		scene.setRoot(buildUpStage());
		stage.setScene(scene);
		stage.show();
		
	}

	private Parent buildUpStage() {
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(0, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);
        
        Image image = new Image("/application/patient.png");
        ImageView patientImage = new ImageView(image);
        patientImage.setFitHeight(150);
        patientImage.setFitWidth(150);
        GridPane.setConstraints(patientImage, 0, 1, 2, 1);
        grid.getChildren().add(patientImage);
        
        final Label name = new Label("Name: \n" + patient.getName());
        GridPane.setConstraints(name, 2, 2, 2, 1);
        grid.getChildren().add(name);
        
        final Label address = new Label("Address: \n" + patient.getAddress());
        address.setWrapText(true);
        GridPane.setConstraints(address, 2, 3, 2, 1);
        grid.getChildren().add(address);
        
        final Label email = new Label("Email: \n" + patient.getEmail());
        email.setWrapText(true);
        GridPane.setConstraints(email, 2, 4, 2, 1);
        grid.getChildren().add(email);
        
        final Label dob = new Label("Date of Birth: \n" + patient.getDob().toString());
        GridPane.setConstraints(dob, 2, 5, 2, 1);
        dob.setWrapText(true);
        grid.getChildren().add(dob);
        
        final Label phone = new Label("Phone: \n" + patient.getPhone());
        GridPane.setConstraints(phone, 2, 6, 2, 1);
        grid.getChildren().add(phone);
        
        final Label history = new Label("History: \n" + patient.getHistory().toString());
        GridPane.setConstraints(history, 2, 7, 2, 1);
        history.setWrapText(true);
        grid.getChildren().add(history);
        
        final Label eqts = new Label("Borrowed Equipments: \n" + patient.getBorrowed().toString());
        GridPane.setConstraints(eqts, 2, 8, 2, 1);
        history.setWrapText(true);
        grid.getChildren().add(eqts);
        
        final Label tPlan = new Label("Treatment Plan: \n" + patient.gettPlan());
        GridPane.setConstraints(tPlan, 2, 9, 2, 1);
        tPlan.setWrapText(true);
        grid.getChildren().add(tPlan);
        
        URL imgURL = getClass().getResource("back.png");
		back.setMinSize(70, 30);
		back.setStyle("-fx-background-image: url('" + imgURL.toString() + "'); -fx-background-repeat: no-repeat;"
				+ "  -fx-background-position: center; -fx-background-size: 68px 24px; ");
        EventHandler<ActionEvent> backClick = new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent e) {
    			try {
    				stage.close();
    				MainMenu main = new MainMenu(sizeX, sizeY, positionX, positionY, user);
    			} catch (Exception e2) {
    				e2.printStackTrace();
    			}
    		}
    		
    	};
    	
    	EventHandler<ActionEvent> gClick = new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent e) {
    			try {
    				stage.close();
    				Progress p = new Progress();
    			} catch (Exception e2) {
    				e2.printStackTrace();
    			}
    		}
    		
    	};
    	back.setOnAction(backClick);
    	GridPane.setConstraints(back, 0, 10);
    	grid.getChildren().add(back);
    	
    	Button generateReport = new Button("Generate Report");
    	generateReport.setMinSize(70, 30);
    	generateReport.setStyle("-fx-background-color: #ff0000;");
    	generateReport.setOnAction(gClick);
    	GridPane.setConstraints(generateReport, 3, 10);
    	grid.getChildren().add(generateReport);
    	
    	ScrollPane root = new ScrollPane();
    	root.setHbarPolicy(ScrollBarPolicy.NEVER);
    	root.setFitToWidth(true);
		root.setContent(grid);
        
        return root;
		
	}
}
