package application;

// Java program to implement Slider Class 
// using ChangeListener 
import javafx.animation.PauseTransition;
import javafx.application.Application; 
import javafx.beans.value.ChangeListener; 
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene; 
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox; 
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration; 

public class textToSpeech extends Application { 
	
	static double prog = 0;

	public void start(Stage stage) 
	{ 

		// create label 
		Label loudnessLabel = new Label("Adjust the Loudness"); 
		Label loudnessValue = new Label(" "); 
		Label speedLabel = new Label("Adjust the Speed");
		Label speedValue = new Label(" ");
		Label languageLabel = new Label("Select your preferred Language");
		Label accentLabel = new Label("Select your preferred accent");
		Label test = new Label("");


		// create sliders
		Slider loudness = new Slider(); 
		Slider speed = new Slider();

		// set the value of property min, max and value 
		loudness.setMin(0); 
		loudness.setMax(100); 
		loudness.setValue(80);
		speed.setMin(0.5);
		speed.setMax(2);
		speed.setValue(1);

		// enable TickLabels and Tick Marks 
		loudness.setShowTickLabels(true); 
		loudness.setShowTickMarks(true); 
		loudness.setBlockIncrement(10); 

		speed.setShowTickLabels(true);
		speed.setShowTickMarks(true);
		speed.setBlockIncrement(10);

		// Adding Listener to value property. 
		loudness.valueProperty().addListener(new ChangeListener<Number>() { 

			public void changed(ObservableValue <? extends Number > observable, Number oldValue, Number newValue) 
			{ 
				loudnessValue.setText("value: " + newValue); 
			} 
		}); 

		speed.valueProperty().addListener(new ChangeListener<Number>() { 

			public void changed(ObservableValue <? extends Number > observable, Number oldValue, Number newValue) 
			{ 
				speedValue.setText("value: " + newValue); 
			} 
		}); 
		
		loudness.setStyle("-fx-font: 10 arial; -fx-base: #b6e7c9;");
		speed.setStyle("-fx-font: 10 arial; -fx-base: #b6e7c9;");
		
		//Drop-down menu for languages and accents
		ChoiceBox<String> languages = new ChoiceBox<>();
		ChoiceBox<String> accent = new ChoiceBox<>();
		
		//Adding items to the dropDown Languages and setting default value
		languages.getItems().addAll("English", "Spanish", "German", "Russian", "Italian", "Arabic", "Hindi", "French");
		languages.setValue("English");

		//Adding items to the dropDown Accent and setting default value
		accent.getItems().addAll("American", "Bristish", "Australian");
		accent.setValue("American");

		//Creating a BACK button
		Button back = new Button("Back");
		back.setStyle("-fx-font: 12 arial; -fx-base: #b6e7c9;");
		
		/**
		 * PLEASE DONT FORGET TO EDIT THIS BIT
		 */
		//Write the name of the scene you want to go back to in place of NAME_OF_THE_SCENE
		//back.setOnAction(e -> stage.setScene(NAME_OF_THE_SCENE));


		Button save = new Button("Save");
		save.setStyle("-fx-font: 12 arial; -fx-base: #b6e7c9;");

		TilePane btmButtons = new TilePane(Orientation.HORIZONTAL);
		btmButtons.setAlignment(Pos.BOTTOM_LEFT);
		btmButtons.setPadding(new Insets(200, 10, 20, 0));
		btmButtons.setHgap(10.0);
		btmButtons.getChildren().addAll(save, back);
		
		
		save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	System.out.println("works");
            	Stage loading = new Stage();
            	loading.initStyle(StageStyle.UNDECORATED);
            	GridPane load = new GridPane();
            	load.setAlignment(Pos.CENTER);
            	Scene loadScene = new Scene(load, 200, 100);
            	loading.setScene(loadScene);
            	
            	ProgressIndicator round = new ProgressIndicator();
            	
            	
            	Label saving = new Label("Saving");
            	saving.setTextFill(Color.WHITE);
            	saving .setFont(Font.font("Arial", FontWeight.NORMAL, 15));
            	load.add(round,1,0);
            	load.add(saving, 1, 2);
            	
            	load.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
            	
				PauseTransition delay = new PauseTransition(Duration.seconds(3));
                delay.setOnFinished(event1 -> loading.close());
                delay.play();
            	loading.show();
            }
        });
        
		
		
		DropShadow shadow = new DropShadow();
		//Adding the shadow when the mouse cursor is on
		save.addEventHandler(MouseEvent.MOUSE_ENTERED, 
		    new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent e) {
		            save.setEffect(shadow);
		        }
		});
		
		//Removing the shadow when the mouse cursor is off
		save.addEventHandler(MouseEvent.MOUSE_EXITED, 
		    new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent e) {
		            save.setEffect(null);
		        }
		});

		//Adding the shadow when the mouse cursor is on
		back.addEventHandler(MouseEvent.MOUSE_ENTERED, 
		    new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent e) {
		            back.setEffect(shadow);
		        }
		});
		
		//Removing the shadow when the mouse cursor is off
		back.addEventHandler(MouseEvent.MOUSE_EXITED, 
		    new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent e) {
		            back.setEffect(null);
		        }
		});
		
		// create a VBox 
		VBox root = new VBox(); 

		root.setPadding(new Insets(20)); 
		root.setSpacing(10); 
		root.getChildren().addAll(loudnessLabel, loudness, loudnessValue);
		root.getChildren().addAll(speedLabel, speed, speedValue);
		root.getChildren().addAll(languageLabel, languages);
		root.getChildren().addAll(accentLabel, accent);
		root.getChildren().add(btmButtons);
		//root.getChildren().add(save);
		
		
		stage.setTitle("Text-To-Speech"); 

		// create Scene and add to the frame 
		Scene txtToSpeechScene = new Scene(root, 300, 600); 
		stage.setScene(txtToSpeechScene); 
		stage.show(); 


	} 

	// Main Method 
	public static void main(String[] args) 
	{ 

		// Launch Application 
		Application.launch(args); 
	} 
} 
