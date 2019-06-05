package application;

import javafx.stage.*;
import java.net.URL;
import Data.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

public class MainMenu {
	private double sizeX;
	private double sizeY;
	private double positionX;
	private double positionY;	
	private BorderPane content;
	private VBox buttonArea;
	private HBox patientFind;
	private HBox settings;
	private HBox timetable;
	private HBox myPatients;
	private HBox newP;
	private Stage stage;
	private Text usersName;
	private Scene scene;
	private Member user;

	public MainMenu(double _sizeX, double _sizeY, double _positionX, double _positionY, Member _user) {
		
		sizeX = _sizeX;
		sizeY = _sizeY;
		positionX = _positionX;
		positionY = _positionY;		
		user = _user;
		
		buildUpStage();
		
		}
		
		//creates the scene and all elements, adds to stage and shows it
		private void buildUpStage() {
		//Stage setup
		stage = new Stage();			
		stage.setX(positionX);
		stage.setY(positionY);
		stage.setTitle("Practice Management - Main Menu");
		stage.setHeight(sizeY);
		stage.setMinHeight(sizeY);
		stage.setMaxHeight(sizeY);
		stage.setWidth(sizeX);
		stage.setMaxWidth(sizeX);
		stage.setMinWidth(sizeX);
		
		//Assigned font for all texts
		Font overall = Font.font("Aral", 20);
		
		//setup layout having a border pane with top containing
		//an HBox which displays username, more and help button 
		//then center Using an HBox to center content horizontally
		//containing a VBox of all the buttons 
		content = new BorderPane();
		buttonArea = new VBox();
		usersName = new Text(user.getName());
		HBox topArea = new HBox();
		HBox centering = new HBox();
		topArea.getChildren().addAll(usersName);
		content.setTop(topArea);
		content.setCenter(centering);
		centering.getChildren().add(buttonArea);
		
		centering.setAlignment(Pos.CENTER);
		topArea.setStyle("-fx-background-color: rgb(250, 250, 250)");
		centering.setStyle("-fx-background-color: rgb(230, 238, 242)");
		usersName.setFont(overall);
		
		
//Buttons setup as HBox with an image component and Text 
		
		URL imgURL;
		
		
		//My Patient
		myPatients = new HBox();
		imgURL = getClass().getResource("patient50.png");
		Image myPIMG = new Image(imgURL.toString());
		ImageView myPIMG_VIEW = new ImageView(myPIMG);
		Text myPTXT = new Text("My Patients");
		myPTXT.setFont(overall);
		myPatients.getChildren().addAll(myPIMG_VIEW, myPTXT);
		myPatients.setOnMouseClicked(myPatientsClick);

		//Patient Find
		patientFind =  new HBox();		
		imgURL = getClass().getResource("pFind50.png");
		Image pFindIMG = new Image(imgURL.toString());
		ImageView pFindIMG_VIEW = new ImageView(pFindIMG);
		Text pFindTXT = new Text("Find Patient");
		pFindTXT.setFont(overall);
		patientFind.getChildren().addAll(pFindIMG_VIEW, pFindTXT);
		

		//time Table
		timetable =  new HBox();		
		imgURL = getClass().getResource("tTable50.png");
		Image tTblIMG = new Image(imgURL.toString());
		ImageView tTblIMG_VIEW = new ImageView(tTblIMG);
		Text tTblTXT = new Text("Timetable");
		tTblTXT.setFont(overall);
		timetable.getChildren().addAll(tTblIMG_VIEW, tTblTXT);

		//settings
		settings =  new HBox();		
		imgURL = getClass().getResource("stngs50.png");
		Image stngsIMG = new Image(imgURL.toString());
		ImageView stngsIMG_VIEW = new ImageView(stngsIMG);
		Text stngsTXT = new Text("Settings");
		stngsTXT.setFont(overall);
		settings.getChildren().addAll(stngsIMG_VIEW, stngsTXT);
		
		
		//New Patient
		newP =  new HBox();		
		imgURL = getClass().getResource("new50.png");
		Image newPIMG = new Image(imgURL.toString());
		ImageView newPIMG_VIEW = new ImageView(newPIMG);
		Text newPTXT = new Text("New Patient");
		newPTXT.setFont(overall);
		newP.getChildren().addAll(newPIMG_VIEW, newPTXT);
		
		buttonArea.getChildren().addAll(myPatients, patientFind, timetable, settings, newP);

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
//	//CUSTOM CLOSE BUTTON
//	EventHandler<ActionEvent> closeButton = new EventHandler<ActionEvent>(){
//		@Override 
//		public void handle(ActionEvent e){
//			stage.close();
//		}
//	};
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
	//My patients setup mouse click
	EventHandler<MouseEvent> myPatientsClick = new EventHandler<MouseEvent>() {
		@Override 
		public void handle(MouseEvent e) {
			if(e.getButton() == MouseButton.PRIMARY) {
				try {
					MyPatients patientsMenu = new MyPatients(sizeX, sizeY, stage.getX(), stage.getY(), user);
							}
				catch(Exception e2) {
					e2.printStackTrace();
				}
			}

		}
	};

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
