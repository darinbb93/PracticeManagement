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
	
	//creates the scene and all elements, adds to stage and shows it
	private void buildUpStage() {
		stage = new Stage();			
		stage.setX(positionX);
		stage.setY(positionY);
		stage.setTitle("Practice Management - Main Menu");

		
		//Hbox login details setup
		loginDetails = new VBox();
		Text loginPrompt = new Text("Please enter you login ndetails or\nsign up now so you can use the app.");
		username = new TextField();
		username.setPromptText("Username");
		username.setMaxWidth(150);

		password = new TextField();
		password.setPromptText("Password");
		password.setMaxWidth(150);
//		password.setAlignment(Pos.TOP_CENTER);
		HBox buttonArea = new HBox();
		login = new Button("Login");
		login.setOnAction(loginAction);
		signup= new Button("Signup");
		buttonArea.setAlignment(Pos.CENTER);
		buttonArea.setSpacing(20);
		buttonArea.getChildren().addAll(login, signup);
		warning.setFill(Color.RED);
		loginDetails.getChildren().addAll(loginPrompt, username, password, warning, buttonArea);
		loginDetails.setAlignment(Pos.CENTER);
		//content borderpane setup
		content = new BorderPane();
		content.setStyle("-fx-background-color: rgb(245, 245, 245)");
		
//		content.setTop();
//		content.setRight(resizePaneX);
//		content.setBottom(resizePaneY);
		content.setCenter(loginDetails);
		
		//Buttons setup
		
		

//		patientFind.setStyle("-fx-background-image: url("+ imgURL + "); -fx-background-repeat: no-repeat; -fx-background-position: center;");
//		patientFind.setMinSize(78, 73);
//		buttonArea.setLeft(patientFind);
//		buttonArea.setRight(myPatients);
//		Font buttonFont = Font.font("Arial", FontWeight.BOLD,20);
//		newPostItNote.setFont(buttonFont);
//		newPostItNote.setTextFill(Color.GREY);
//		newPostItNote.setStyle("-fx-background-color: transparent");
//		deletePostItNote.setFont(buttonFont);
//		deletePostItNote.setTextFill(Color.GREY);
//		deletePostItNote.setStyle("-fx-background-color: transparent");
//		newPostItNote.setOnAction(newButton);
//		deletePostItNote.setOnAction(closeButton);


		//replacing the right click menu with custom
//		textArea.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
//		textArea.setOnMouseClicked(rightClick);
//
//		rightClickMenu = new ContextMenu();
//		MenuItem cut = new MenuItem("Cut");
//		rightClickMenu.getItems().add(cut);
//		MenuItem copy = new MenuItem("Copy");
//		rightClickMenu.getItems().add(copy);
//		MenuItem paste = new MenuItem("Paste");
//		rightClickMenu.getItems().add(paste);
//		MenuItem about = new MenuItem("About");
//		rightClickMenu.getItems().add(about);
//		MenuItem exit = new MenuItem("Exit");
//		rightClickMenu.getItems().add(exit);
//
//		exit.setOnAction(closeButton);
//		paste.setOnAction(pasteButton);
//		copy.setOnAction(copyButton);
//		cut.setOnAction(cutButton);
//		about.setOnAction(aboutButton);

		//setup custom resize and move fucntions
		stage.setHeight(sizeY);
		stage.setMinHeight(sizeY);
		stage.setMaxHeight(sizeY);
		stage.setWidth(sizeX);
		stage.setMaxWidth(sizeX);
		stage.setMinWidth(sizeX);

//		buttonArea.setOnMousePressed(clickContent);		 
//		buttonArea.setOnMouseDragged(dragContent);
//		buttonArea.setOnMouseReleased(hoverExit);

	
		//setup and show scene
		scene = new Scene(content,sizeX,sizeY);
		stage.setScene(scene);
		stage.show();
		//following code must remain below "stage.show()" in order to properly 
		//modify background color for the "textArea" casting it to a "Region"	
//		Region region = (Region) textArea.lookup(".content");
//		region.setStyle( "-fx-background-color: rgb(253, 253, 201)" );
	}

	//EVENT HANDLERS


//	//NEW NOTE BUTTON FUNCTION
//	EventHandler<ActionEvent> newButton = new EventHandler<ActionEvent>(){
//		@Override 
//		public void handle(ActionEvent e){
//			double newX = stage.getX() + stage.getWidth();
//			double newY = stage.getY();
//			Rectangle2D screen = Screen.getPrimary().getVisualBounds();
//			if(stage.getX() + stage.getWidth()*2 > screen.getWidth()){
//				newX = 0;
//				newY = stage.getY() + stage.getHeight();
//			}
//			new MainMenu(sizeX,sizeY,newX,newY);
//		}
//	};
//
//
	//Login button action
	EventHandler<ActionEvent> loginAction = new EventHandler<ActionEvent>(){
		@Override 
		public void handle(ActionEvent e){
			if(username.getText().length() < 6) {
				username.setStyle("-fx-text-box-border: red;-fx-focus-color: red ;");
				warning.setText("Invalid username! Try again!");
				
			}
			else if(password.getText().length() < 6) {
				password.setStyle("-fx-text-box-border: red;-fx-focus-color: red ;");
				warning.setText("Invalid password! Try again!");
				
			}
			else {
				try{
					MainMenu mainMenu = new MainMenu(sizeX, sizeY, stage.getX(), stage.getY(), username.getText());
				}catch(Exception e1) {
					e1.getMessage();
				}
				stage.close();
				

			}
		}
	};
//
//
//	//PASTE FUNCTION
//	EventHandler<ActionEvent> pasteButton = new EventHandler<ActionEvent>(){
//		@Override 
//		public void handle(ActionEvent e){
//			if(Clipboard.getSystemClipboard().hasString()){
//				textArea.insertText(textArea.getCaretPosition(),(String)Clipboard.getSystemClipboard().getContent(DataFormat.PLAIN_TEXT));
//			}
//		}
//	};
//
//
//	//COPYING FUNCTION
//	EventHandler<ActionEvent> copyButton = new EventHandler<ActionEvent>(){
//		@Override 
//		public void handle(ActionEvent e){
//			if(!textArea.getSelectedText().isEmpty()){
//				final Clipboard clipboard = Clipboard.getSystemClipboard();
//				final ClipboardContent content = new ClipboardContent();
//				content.putString(textArea.getSelectedText());
//				clipboard.setContent(content);
//			}
//			else {
//				final Clipboard clipboard = Clipboard.getSystemClipboard();
//				final ClipboardContent content = new ClipboardContent();
//				content.putString(textArea.getText());
//				clipboard.setContent(content);
//			}
//		}
//	};
//
//	//CUTTING FUNCITON
//	EventHandler<ActionEvent> cutButton = new EventHandler<ActionEvent>(){
//		@Override 
//		public void handle(ActionEvent e){
//			if(!textArea.getSelectedText().isEmpty()){
//				final Clipboard clipboard = Clipboard.getSystemClipboard();
//				final ClipboardContent content = new ClipboardContent();
//				content.putString(textArea.getSelectedText());
//				clipboard.setContent(content);
//				textArea.replaceSelection("");
//			}
//			else {
//				final Clipboard clipboard = Clipboard.getSystemClipboard();
//				final ClipboardContent content = new ClipboardContent();
//				content.putString(textArea.getText());
//				clipboard.setContent(content);
//				textArea.clear();
//			}
//		}
//	};
//
//	//ABOUT INFO MENU
//	EventHandler<ActionEvent> aboutButton = new EventHandler<ActionEvent>(){
//		@Override 
//		public void handle(ActionEvent e) {
//			Alert about = new Alert(AlertType.INFORMATION);
//			about.setHeaderText("Post-It Note");
//			GridPane contents = new GridPane();
//			URL imgURL = getClass().getResource("darin.jpg");
//			Image image = new Image(imgURL.toString()); 
//			//			Image image = new Image("darin.jpg")// Why did that not work for me? also tried "../darin.jpg" "/darin.jpg" etc
//			ImageView imgView = new ImageView(image);
//			Label infoText = new Label("Digital Post-It Note using JavaFX\n"
//					+ "Version 1.0\n"
//					+ "Author: Darin Bogdanov\n"
//					+ "Copyright(c) 2019");
//			contents.add(imgView, 0, 0);
//			contents.add(infoText, 1, 0);
//			about.getDialogPane().setContent(contents);
//			about.show();
//
//		}
//	};
//
//	//CUSTOM RIGHT CLICK MENU
//	EventHandler<MouseEvent> rightClick = new EventHandler<MouseEvent>() {
//		@Override 
//		public void handle(MouseEvent e) {
//			if(e.getButton() == MouseButton.SECONDARY) {
//				rightClickMenu.show(content, e.getScreenX(), e.getScreenY());;
//			}
//
//		}
//	};

//	//MOVE WINDOW HANDLERS
//	EventHandler<MouseEvent> clickContent = new EventHandler<MouseEvent>() {	
//		@Override
//		public void handle(MouseEvent event) {
//			scene.setCursor(Cursor.MOVE);
//			xOffset = event.getSceneX();
//			yOffset = event.getSceneY();
//		}
//	};
//
//	EventHandler<MouseEvent> dragContent = new EventHandler<MouseEvent>() {   
//		@Override
//		public void handle(MouseEvent event) {
//			stage.setX(event.getScreenX() - xOffset);
//			stage.setY(event.getScreenY() - yOffset);
//		}
//	};
//
//
//	//RESIZING HANDLERS
//	EventHandler<MouseEvent> clickResizeXY = new EventHandler<MouseEvent>() {	
//		@Override
//		public void handle(MouseEvent event) {
//			xOffset = event.getSceneX();
//			yOffset = event.getSceneY();
//		}
//	};
//
//	EventHandler<MouseEvent> dragResizeXY = new EventHandler<MouseEvent>() {   
//		@Override
//		public void handle(MouseEvent event) {
//			double newX = event.getSceneX();
//			double newY = event.getSceneY();
//
//			if(newX - xOffset != 0 && event.getSceneX() > sizeX)
//				stage.setWidth(newX - xOffset + event.getSceneX());
//			if(newY - yOffset != 0 && event.getSceneY() > sizeY)
//				stage.setHeight(newY - yOffset + event.getSceneY());
//
//			yOffset = newY;
//			xOffset = newX;
//		}
//	};
//
//	EventHandler<MouseEvent> clickResizeX = new EventHandler<MouseEvent>() {	
//		@Override
//		public void handle(MouseEvent event) {
//			xOffset = event.getSceneX();
//		}
//	};
//
//	EventHandler<MouseEvent> dragResizeX = new EventHandler<MouseEvent>() {   
//		@Override
//		public void handle(MouseEvent event) {
//			double newX = event.getSceneX();
//			if(newX - xOffset != 0 && event.getSceneX() > sizeX)
//				stage.setWidth(newX - xOffset + event.getSceneX());			
//			xOffset = newX;
//		}
//	};
//
//	EventHandler<MouseEvent> clickResizeY = new EventHandler<MouseEvent>() {	
//		@Override
//		public void handle(MouseEvent event) {
//			yOffset = event.getSceneY();
//		}
//	};
//
//	EventHandler<MouseEvent> dragResizeY = new EventHandler<MouseEvent>() {   
//		@Override
//		public void handle(MouseEvent event) {
//			double newY = event.getSceneY();
//			if(newY - yOffset != 0 && event.getSceneY() > sizeY)
//				stage.setHeight(newY - yOffset + event.getSceneY());			
//			yOffset = newY;			
//		}
//	};
//
//
//	EventHandler<MouseEvent> hoverY = new EventHandler<MouseEvent>() {   
//		@Override
//		public void handle(MouseEvent event) {
//			scene.setCursor(Cursor.S_RESIZE);
//		}
//	};
//
//
//	EventHandler<MouseEvent> hoverX = new EventHandler<MouseEvent>() {   
//		@Override
//		public void handle(MouseEvent event) {
//			scene.setCursor(Cursor.E_RESIZE);
//		}
//	};
//
//	EventHandler<MouseEvent> hoverXY = new EventHandler<MouseEvent>() {   
//		@Override
//		public void handle(MouseEvent event) {
//			scene.setCursor(Cursor.SE_RESIZE);
//		}
//	};
//
//	EventHandler<MouseEvent> hoverExit = new EventHandler<MouseEvent>() {   
//		@Override
//		public void handle(MouseEvent event) {
//			scene.setCursor(Cursor.DEFAULT);
//		}
//	};
}
