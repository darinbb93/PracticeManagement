package application;

import Data.*;
import javafx.stage.*;
import javafx.util.Callback;
import java.net.URL;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.*;
/**	
 * This class represents and builds equipment management screen. 
 * It uses listview to show all items available at the practice, a Textfield
 * acting as a searchbar to search available equipment and generates another listview 
 * based on items currently borrowed by the patient passed in.
 * 
 * @author Darin Bogdanov  - bogdb001
 *	
 */
public class EquipmentView {
	private double sizeX;
	private double sizeY;
	private double positionX;
	private double positionY;
	private Scene scene;
	private Stage stage;
	private Patient user;
	private TextField search;
	private ListView<Equipment> eqList;
	private PatientOptionsMenu prevMenu;
	private ListView<Equipment> toReturn;

	public EquipmentView(double _sizeX, double _sizeY, double _positionX, double _positionY, Patient _user,
			PatientOptionsMenu _prevMenu) {

		user = _user;
		sizeX = _sizeX;
		sizeY = _sizeY;
		positionX = _positionX;
		positionY = _positionY;
		prevMenu = _prevMenu;

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
		stage.setTitle("Practice Management - My patients");

		// setup fonts for text
		Font overall = Font.font("Aral", 20);

		// initialising components
		BorderPane content = new BorderPane();
		VBox equipment = new VBox();
		Text usersName = new Text(user.getName());
		BorderPane title = new BorderPane();
		Text titleTXT = new Text("Equipment Lending");
		BorderPane nameNbuttons = new BorderPane();
		BorderPane backBtnContainer = new BorderPane();
		Button back = new Button();
		search = new TextField();

		eqList = new ListView<>();
		toReturn = new ListView<>();
		BorderPane returnBtn = new BorderPane();
		BorderPane lendBtn = new BorderPane();
		Button lend = new Button("Lend");
		Button returned = new Button("Return");
		lend.setOnAction(lendClicked);
		returned.setOnAction(returnedClicked);
		title.setCenter(titleTXT);
		lendBtn.setCenter(lend);
		returnBtn.setCenter(returned);
		nameNbuttons.setLeft(usersName);
		content.setTop(nameNbuttons);
		equipment.getChildren().addAll(backBtnContainer, title);
		content.setCenter(equipment);

		// setup buttons and search
		URL imgURL = getClass().getResource("back.png");
		backBtnContainer.setLeft(back);
		back.setMinSize(70, 30);
		back.setStyle("-fx-background-image: url('" + imgURL.toString() + "'); -fx-background-repeat: no-repeat;"
				+ "  -fx-background-position: center; -fx-background-size: 68px 24px; ");
		back.setOnAction(backClick);
		search.setOnKeyReleased(searching);

		// Setup styles
		title.setStyle("-fx-font-size: 20px; -fx-background-color: rgb(200, 238, 242); -fx-text-align: center;");
		nameNbuttons.setStyle("-fx-background-color: rgb(250, 250, 250)");
		equipment.setStyle("-fx-background-color: rgb(230, 238, 242)");
		usersName.setFont(overall);
		content.setStyle("-fx-font-size: 15px;");
		eqList.setPrefHeight(350);
		search.setPromptText("Search");
		toReturn.setMaxHeight(50);

		// check if any items are borrowed create returns list
		if (user.getBorrowed().size() > 0) {
			// Populating and setting display name for ListView of patients borrowed equipment
			toReturn.setCellFactory(new Callback<ListView<Equipment>, ListCell<Equipment>>() {
				@Override
				public ListCell<Equipment> call(ListView<Equipment> param) {
					ListCell<Equipment> cell = new ListCell<Equipment>() {
						@Override
						protected void updateItem(Equipment item, boolean empty) {
							super.updateItem(item, empty);
							if (item != null) {
								setText(item.getDescription());
							} else {
								setText(null);
							}
						}
					};
					return cell;
				}

			});
			for (Equipment eq : user.getBorrowed()) {
				toReturn.getItems().add(eq);

			}
			equipment.getChildren().addAll(toReturn, returnBtn);

		} // END IF BORROWED NOT EMPTY

		// Populating and setting display name for ListView of available equipment
		eqList.setCellFactory(new Callback<ListView<Equipment>, ListCell<Equipment>>() {
			@Override
			public ListCell<Equipment> call(ListView<Equipment> param) {
				ListCell<Equipment> cell = new ListCell<Equipment>() {
					@Override
					protected void updateItem(Equipment item, boolean empty) {
						super.updateItem(item, empty);
						if (item != null) {
							setText(item.getDescription());
						} else {
							setText(null);
						}
					}
				};
				return cell;
			}
		});
		for (Equipment eq : PracticeInterface.database.getEquipmentOwned()) {
			eqList.getItems().add(eq);

		}

		equipment.getChildren().addAll(search, eqList, lendBtn);

		// setup and show scene
		scene = new Scene(content, sizeX, sizeY);
		stage.setScene(scene);
		stage.show();

	}

	// show stage
	public void showStage(double _positionX, double _positionY) {
		positionX = _positionX;
		positionY = _positionY;
		buildUpStage();
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

	EventHandler<ActionEvent> lendClicked = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			try {
				user.getBorrowed().add(eqList.getSelectionModel().getSelectedItem());
				stage.close();
				showStage(stage.getX(), stage.getY());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	};

	EventHandler<ActionEvent> returnedClicked = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			try {
				user.getBorrowed().remove(toReturn.getSelectionModel().getSelectedItem());
				stage.close();
				showStage(stage.getX(), stage.getY());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	};

	EventHandler<KeyEvent> searching = new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent e) {
			try {
				eqList.getItems().clear();
				if (search.getText().isEmpty()) {
					for (Equipment eq : PracticeInterface.database.getEquipmentOwned()) {
						eqList.getItems().add(eq);

					}
				} else {
					for (Equipment eq : PracticeInterface.database.getEquipmentOwned()) {
						if (eq.getDescription().toLowerCase().contains(search.getText().toLowerCase())) {
							eqList.getItems().add(eq);
						}

					}
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	};
}