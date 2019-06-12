package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import Data.Member;
import Data.Patient;
import Data.Practitioner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SearchView {
	private double sizeX;
	private double sizeY;
	private double positionX;
	private double positionY;
	
	private TextField textfield;
	private ListView<String> listview;
	
	private List<String> names = new ArrayList<String>();
	
	private Stage stage = new Stage();
	
	private Button back = new Button();
	
	final Practitioner user;
	
	
	public SearchView(double sizeX, double sizeY, double positionX, double positionY, Practitioner user) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.positionX = positionX;
		this.positionY = positionY;
		this.user = user;
		
		stage.setX(positionX);
		stage.setY(positionY);
		stage.setHeight(sizeY);
		stage.setWidth(sizeX);
		stage.setResizable(false);
		stage.setTitle("Find Patients");
		stage.setScene(new Scene(buildUpStage()));
		stage.show();
		
		for(Member m: PracticeInterface.database.getMembers()) {
			if(m.getClass().equals(Patient.class)) {
				names.add(m.getUsername() + ": " + m.getName());
			}
			
		}
	}

	private Parent buildUpStage() {
		textfield = textfield();
        listview = listView();
        
        listview.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
            	for(Member m: PracticeInterface.database.getMembers()) {
        			if(m.getClass().equals(Patient.class)) {
        				if ((m.getUsername() + ": " + m.getName()).equals(listview.getSelectionModel().getSelectedItem())) {
        					stage.close();
        					PatientDetailsView patientView = new PatientDetailsView(sizeX, sizeY, positionX, positionY, (Patient)m, user);
        					break;
        				}
        			}
        			
        		}
            }
        });

        VBox root = new VBox(textfield, listview);
        root.setPadding(new Insets(20));
        root.setSpacing(10);
        
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
    	
    	back.setOnAction(backClick);
    	root.getChildren().add(back);
        return root;
	}
	
	private TextField textfield() {
        TextField textField = new TextField();
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            String trimed = newValue.trim();
            if (trimed.length() > 0) {
                doSearch(trimed);
            }
        });
        return textField;
    }
	
	private void doSearch(String keyword) {
		InstantSearch<String> instantSearch = new InstantSearch<>(names, String::contains);
        List<String> searchResult = instantSearch.search(keyword);
        listview.getItems().clear();
        listview.getItems().addAll(searchResult);
    }
	
	private ListView<String> listView() {
        return new ListView<>();
    }
	
}

class InstantSearch<T> {

    private Collection<T> tCollection;

    private BiFunction<T, String, Boolean> matcher;

    public InstantSearch(Collection<T> tCollection, BiFunction<T, String, Boolean> matcher) {
        this.tCollection = tCollection;
        this.matcher = matcher;
    }

    public List<T> search(String keyword) {
        return (tCollection == null || matcher == null) ?
                Collections.emptyList() :
                tCollection.stream()
                        .filter(t -> matcher.apply(t, keyword))
                        .collect(Collectors.toList());
    }
}
