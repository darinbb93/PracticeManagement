package application;
import Data.*;
import javafx.stage.*;
import javafx.util.Callback;
import java.net.URL;
import java.text.SimpleDateFormat;

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
public class TBlockView {
	private double sizeX;
	private double sizeY;
	private double positionX;
	private double positionY;	
	private Scene scene;
	private Stage stage;
	private Member user;
	private TPlanView prevMenu;
	private Treatment theBlock;
	
	public TBlockView(double _sizeX, double _sizeY, double _positionX, double _positionY, Member _user, TPlanView _prevMenu, Treatment _theBlock) {

		user = _user;
		sizeX = _sizeX;
		sizeY = _sizeY;
		positionX = _positionX;
		positionY = _positionY;
		prevMenu = _prevMenu;
		theBlock = _theBlock;
		
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
		Font overall = Font.font("Aral", 18);

		// initialising components
		BorderPane content = new BorderPane();
		VBox tBlock = new VBox();
		Text usersName = new Text(user.getName());
		BorderPane title = new BorderPane();
		Text titleTXT = new Text("Treatment Block");
		BorderPane nameNbuttons = new BorderPane();
		BorderPane backBtnContainer = new BorderPane();
		Button back = new Button();
		SimpleDateFormat dateF = new SimpleDateFormat("MMM dd, yyyy");
		String dateStr = dateF.format(theBlock.getDate().getTime());
		Text tBlockTXT = new Text(theBlock.getType().toString() + 
				"\nInstructions: \n" + theBlock.getInstructions() + 
				"\nTarget date: \n" + dateStr + 
				"\nPerscribed by: \n" + theBlock.getDoctor().getName());
		tBlockTXT.setFont(overall);
		tBlockTXT.setWrappingWidth(sizeX-10);
		title.setCenter(titleTXT);
		nameNbuttons.setLeft(usersName);
		content.setTop(nameNbuttons);
		tBlock.getChildren().addAll(backBtnContainer, title, tBlockTXT);
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
		

		// setup and show scene
		scene = new Scene(content, sizeX, sizeY);
		stage.setScene(scene);
		stage.show();

	}

	
		
	// Back button click setup
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
	
}
