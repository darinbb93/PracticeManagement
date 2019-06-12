package application;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Progress {
	private double timer = 0;
	private Stage stage;
	public Progress() {
		Group root = new Group();
	    Scene scene = new Scene(buildUpStage(), 180, 100);
	    stage = new Stage();
	    stage.setTitle("Generating...");
	    stage.setScene(scene);
	    stage.show();
		
	}
	
	public Parent buildUpStage() {
		

	    VBox root = new VBox();
	    root.setPadding(new Insets(30));
	    root.setSpacing(10);
	    
	    Label label = new Label("Generating a report...");

	    ProgressBar bar = new ProgressBar();
	    Timeline timeline = new Timeline();
	    KeyValue keyValue = new KeyValue(bar.progressProperty(), 1.0);
	    KeyFrame keyFrame = new KeyFrame(new Duration(1000), e->{
	    	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setContentText("Complete!");
	    	alert.show();
	    	}, keyValue) ;
	    timeline.getKeyFrames().add(keyFrame);
	    
	    

	    
	    timeline.play();
	    
	    root.getChildren().add(label);
	    
	    
	    root.getChildren().add(bar);
	    

	    
	    
	    return root;
	}

}

