package application;

import java.net.URL;

import Data.Practitioner;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SettingsView {
	private double sizeX;
	private double sizeY;
	private double positionX;
	private double positionY;
	
	private Stage stage = new Stage();
	Group root = new Group();
	Scene scene = new Scene(root, sizeX, sizeY);
	
	private MainMenu main;
	
	private Label title = new Label("Room Settings ");
	
	
	final Slider tempSlider = new Slider(0, 100, 0);
	private ToggleSwitch toggle1 = new ToggleSwitch(tempSlider);
    final Label tempCaption = new Label("Tempurature: ");

    final Slider brightSlider = new Slider(0, 100, 0);    
    private ToggleSwitch toggle2 = new ToggleSwitch(brightSlider);
    final Label brightCaption = new Label("Brightness: ");
    
    final Slider humiditySlider = new Slider(0, 100, 0);
    private ToggleSwitch toggle3 = new ToggleSwitch(humiditySlider);
    final Label humidityCaption = new Label("Humidity: ");
    
    final Slider fanSlider = new Slider(0, 100, 0);
    private ToggleSwitch toggle4 = new ToggleSwitch(fanSlider);
    final Label fanCaption = new Label("Fan Speed: ");
    
    final Slider shutterSlider = new Slider(0, 100, 0);
    private ToggleSwitch toggle5 = new ToggleSwitch(shutterSlider);
    final Label shutterCaption = new Label("Shutter: ");
    
    final Practitioner user;
    private Button back = new Button();
    
    
	
	
	
	public SettingsView(double sizeX, double sizeY, double positionX, double positionY, Practitioner user, MainMenu main){
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.positionX = positionX;
		this.positionY = positionY;
		this.user = user;
		this.main = main;
		
		stage.setX(positionX);
		stage.setY(positionY);
		stage.setHeight(sizeY);
		stage.setWidth(sizeX + 120);
		stage.setResizable(false);
		stage.setTitle("Room Settings");
		
		scene.setRoot(buildUpStage());
		stage.setScene(scene);
		
		stage.hide();
	}
	
	public void showStage() {
		this.stage.show();
	}
	
	private Parent buildUpStage() {
		
		Label text1 = new Label();
		text1.textProperty().bind(Bindings.when(toggle1.siwchedOnProperty()).then("ON").otherwise("OFF"));
		Label text2 = new Label();
		text2.textProperty().bind(Bindings.when(toggle2.siwchedOnProperty()).then("ON").otherwise("OFF"));
		Label text3 = new Label();
		text3.textProperty().bind(Bindings.when(toggle3.siwchedOnProperty()).then("ON").otherwise("OFF"));
		Label text4 = new Label();
		text4.textProperty().bind(Bindings.when(toggle4.siwchedOnProperty()).then("ON").otherwise("OFF"));
		Label text5 = new Label();
		text5.textProperty().bind(Bindings.when(toggle5.siwchedOnProperty()).then("ON").otherwise("OFF"));
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(0, 10, 10, 10));
        grid.setVgap(35);
        grid.setHgap(10);
        
		
		title.setFont(new Font("Arial", 25));
		GridPane.setConstraints(title, 0, 1, 2, 1);
		grid.getChildren().add(title);
		
		GridPane.setConstraints(tempCaption, 0, 2);
		tempCaption.setFont(new Font("Arial", 15));
        grid.getChildren().add(tempCaption);
        GridPane.setConstraints(text1, 2, 2);
        
        GridPane.setConstraints(tempSlider, 1, 2);
        tempSlider.setDisable(true);
        grid.getChildren().add(tempSlider);
        grid.getChildren().add(text1);
        GridPane.setConstraints(toggle1, 3, 2);
        grid.getChildren().add(toggle1);
        
        GridPane.setConstraints(brightCaption, 0, 3);
        brightCaption.setFont(new Font("Arial", 15));
        grid.getChildren().add(brightCaption);
        
        GridPane.setConstraints(brightSlider, 1, 3);
        brightSlider.setDisable(true);
        grid.getChildren().add(brightSlider);
        GridPane.setConstraints(text2, 2, 3);
        grid.getChildren().add(text2);
        GridPane.setConstraints(toggle2, 3, 3);
        grid.getChildren().add(toggle2);
        
        humidityCaption.setFont(new Font("Arial", 15));
        GridPane.setConstraints(humidityCaption, 0, 4);
        grid.getChildren().add(humidityCaption);
        
        GridPane.setConstraints(humiditySlider, 1, 4);
        humiditySlider.setDisable(true);
        grid.getChildren().add(humiditySlider);
        GridPane.setConstraints(text3, 2, 4);
        grid.getChildren().add(text3);
        GridPane.setConstraints(toggle3, 3, 4);
        grid.getChildren().add(toggle3);
        
        fanCaption.setFont(new Font("Arial", 15));
        GridPane.setConstraints(fanCaption, 0, 5);
        grid.getChildren().add(fanCaption);
        
        GridPane.setConstraints(fanSlider, 1, 5);
        fanSlider.setDisable(true);
        grid.getChildren().add(fanSlider);
        GridPane.setConstraints(text4, 2, 5);
        grid.getChildren().add(text4);
        GridPane.setConstraints(toggle4, 3, 5);
        grid.getChildren().add(toggle4);
        
        shutterCaption.setFont(new Font("Arial", 15));
        GridPane.setConstraints(shutterCaption, 0, 6);
        grid.getChildren().add(shutterCaption);
        
        GridPane.setConstraints(shutterSlider, 1, 6);
        shutterSlider.setDisable(true);
        grid.getChildren().add(shutterSlider);
        GridPane.setConstraints(text5, 2, 6);
        grid.getChildren().add(text5);
        GridPane.setConstraints(toggle5, 3, 6);
        grid.getChildren().add(toggle5);
        
        
        URL imgURL = getClass().getResource("back.png");
		back.setMinSize(70, 30);
		back.setStyle("-fx-background-image: url('" + imgURL.toString() + "'); -fx-background-repeat: no-repeat;"
				+ "  -fx-background-position: center; -fx-background-size: 68px 24px; ");
        EventHandler<ActionEvent> backClick = new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent e) {
    			try {
    				stage.hide();
    				main.showStage();
    			} catch (Exception e2) {
    				e2.printStackTrace();
    			}
    		}
    		
    	};
    	
    	back.setOnAction(backClick);
    	GridPane.setConstraints(back, 0, 7);
    	grid.getChildren().add(back);
       
        
        return grid;
		
	}
	
	public static class ToggleSwitch extends Parent{
		private BooleanProperty switchedOn = new SimpleBooleanProperty(false);
		
		private TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(0.25));
		private FillTransition fillAnimation = new FillTransition(Duration.seconds(0.25));
		private ParallelTransition animation = new ParallelTransition(translateAnimation, fillAnimation);
		private Slider slider;
		public BooleanProperty siwchedOnProperty() {
			return switchedOn;
		}
		public ToggleSwitch(Slider slider) {
			Rectangle background = new Rectangle(40,20);
			background.setArcWidth(20);
			background.setArcHeight(20);
			background.setFill(Color.WHITE);
			background.setStroke(Color.LIGHTGREY);
			this.slider = slider;
			
			Circle trigger = new Circle(10);
			trigger.setCenterX(10);
			trigger.setCenterY(10);
			trigger.setFill(Color.WHITE);
			trigger.setStroke(Color.LIGHTGREY);
			
			translateAnimation.setNode(trigger);
			fillAnimation.setShape(background);
			
			getChildren().addAll(background, trigger);
			
			switchedOn.addListener((obs, oldState, newState) ->{
				boolean isOn = newState.booleanValue();
				slider.setValue(isOn ? 40 : 0);
				slider.setDisable(isOn ? false : true);
				translateAnimation.setToX(isOn ? 40 - 20 : 0);
				fillAnimation.setFromValue(isOn ? Color.WHITE : Color.LIGHTGREEN);
				fillAnimation.setFromValue(isOn ? Color.LIGHTGREEN: Color.WHITE);
				animation.play();
			});
			
			setOnMouseClicked(e -> {
				switchedOn.set(!switchedOn.get());
			});
			
		}
	}
}
