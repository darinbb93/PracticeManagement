package application;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import Data.*;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.util.Duration;
/**
 * 
 * @author Tianyi Fu - fuyty006
 *
 */
public class TimetableView {

	private double sizeX;
	private double sizeY;
	private double positionX;
	private double positionY;
	private Practitioner user;
	private Stage stage;
	SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd"); 

    final String content = "Time Table";
    final Text text = new Text(10, 20, "");
    final Animation animation = new Transition() {
        {
            setCycleDuration(Duration.millis(5000));
            setCycleCount(INDEFINITE);
            setAutoReverse(true);
        }
        protected void interpolate(double frac) {
            final int length = content.length();
            final int n = Math.round(length * (float) frac);
            text.setText(content.substring(0, n));
        }
    
    };
    private final TableView<Appointment> table = new TableView<>();
    private final ObservableList<Appointment> data
            = FXCollections.observableArrayList(
                    new Appointment("Jacob", "Smith","3","4",true,new Date(),new Patient("JS", null, null, null, null, null, null, null,null),new Practitioner("SJdocter", null, null, null, null, null, null, null, null, null, null)),
                    new Appointment("Isabella", "Johnson","3","4",true,new Date(),null,null),
                    new Appointment("Ethan", "Williams","3","4",true,new Date(),null,null),
                    new Appointment("Emma", "Jones","3","4",true,new Date(),null,null),
                    new Appointment("Michael", "Brown","3","4",true,new Date(),null,null)
            );
    
    final VBox vb = new VBox();
    public TimetableView(double _sizeX, double _sizeY, double _positionX, double _positionY, Practitioner _user) {

		user = _user;
		sizeX = _sizeX;
		sizeY = _sizeY;
		positionX = _positionX;
		positionY = _positionY;
		buildUpStage();

    }
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
		stage.setTitle("Practice Management - TimetableView");

        TableColumn reason = new TableColumn("reason");
        reason.setCellValueFactory(new PropertyValueFactory<>("reason"));

        TableColumn findings = new TableColumn("findings");
        findings.setCellValueFactory(new PropertyValueFactory<>("findings"));

        TableColumn procedures = new TableColumn("procedures");
        procedures.setCellValueFactory(new PropertyValueFactory<>("procedures"));

        TableColumn outcome = new TableColumn("outcome");
        outcome.setCellValueFactory(new PropertyValueFactory<>("outcome"));

        TableColumn future = new TableColumn("future");
        future.setCellValueFactory(new PropertyValueFactory<>("future"));

        TableColumn date = new TableColumn("date");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Appointment,String> thePatient = new TableColumn("thePatient");
        thePatient.setCellValueFactory(param -> {
            return new ObservableValue<String>() {
				
				@Override
				public void removeListener(InvalidationListener listener) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void addListener(InvalidationListener listener) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void removeListener(ChangeListener<? super String> listener) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void addListener(ChangeListener<? super String> listener) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public String getValue() {
					Appointment ap = (Appointment)param.getValue();
					Patient p = ap.getThePatient();
					if (p == null)
						return "";
					return p.getUsername();
				}
			};
            });

        TableColumn<Appointment, String> theDoctor = new TableColumn("theDoctor");
        theDoctor.setCellValueFactory(param -> {
            return new ObservableValue<String>() {
				
				@Override
				public void removeListener(InvalidationListener listener) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void addListener(InvalidationListener listener) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void removeListener(ChangeListener<? super String> listener) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void addListener(ChangeListener<? super String> listener) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public String getValue() {
					Appointment ap = (Appointment)param.getValue();
					Practitioner p = ap.getTheDoctor();
					if (p == null)
						return "";
					return p.getUsername();
				}
			};
            });

        
        TableColumn DelCol = new TableColumn("Del");
        DelCol.setCellValueFactory(new PropertyValueFactory<>("Del"));

        Callback<TableColumn<Appointment, String>, TableCell<Appointment, String>> DcellFactory
                = //
                new Callback<TableColumn<Appointment, String>, TableCell<Appointment, String>>() {
            @Override
            public TableCell call(final TableColumn<Appointment, String> param) {
                final TableCell<Appointment, String> cell = new TableCell<Appointment, String>() {

                    final Button btn = new Button("Del");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                            	Appointment person =getTableView().getItems().get(getIndex());
                                data.remove(person);
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };

        DelCol.setCellFactory(DcellFactory);

        table.setItems(data);
        table.setMaxSize(sizeX-4*positionX, sizeY-12*positionY);
        table.getColumns().addAll(reason, date ,thePatient,theDoctor , DelCol);
        
        final TextField addreason = new TextField();
        addreason.setPromptText("reason");
        addreason.setMaxWidth(reason.getPrefWidth());
        
        final TextField addfindings = new TextField();
        addfindings.setMaxWidth(findings.getPrefWidth());
        addfindings.setPromptText("findings");
        

        final TextField addprocedures = new TextField();
        addprocedures.setMaxWidth(procedures.getPrefWidth());
        addprocedures.setPromptText("procedures");

        final TextField addoutcome = new TextField();
        addoutcome.setMaxWidth(outcome.getPrefWidth());
        addoutcome.setPromptText("outcome");

        final TextField addfuture = new TextField();
        addfuture.setMaxWidth(future.getPrefWidth());
        addfuture.setPromptText("future");

        final TextField adddate = new TextField();
        adddate.setMaxWidth(date.getPrefWidth());
        adddate.setPromptText("yyyy-MM-dd");


        final TextField addpatient = new TextField();
        addpatient.setMaxWidth(thePatient.getPrefWidth());
        addpatient.setPromptText("patient");

        final TextField adddoctor = new TextField();
        adddoctor.setMaxWidth(theDoctor.getPrefWidth());
        adddoctor.setPromptText("doctor");

        final Button addButton = new Button("Add");
        addButton.setOnAction((ActionEvent e) -> {
            try {
				data.add(new Appointment(
				        addreason.getText(),
				        addfindings.getText(),
				        addoutcome.getText(),
				        addfuture.getText(),
				        true,
				        ft.parse(adddate.getText()),
				        new Patient(addpatient.getText(), null, null, null, null, null, null, null, null),
				        user
				        ));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            addreason.clear();
            addfindings.clear();
            addprocedures.clear();
            addoutcome.clear();
            addfuture.clear();
            adddate.clear();
            addpatient.clear();
            adddoctor.clear();

        });

    	// Back button click setup
    	EventHandler<ActionEvent> backClick = new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent e) {
    			try {
    				animation.stop();
    				stage.close();
    				MainMenu mainMenu = new MainMenu(sizeX, sizeY, stage.getX(), stage.getY(), user);
    			} catch (Exception e2) {
    				e2.printStackTrace();
    			}
    		}
    		
    	};
		BorderPane backBtnContainer = new BorderPane();
		Button back = new Button();
		// setup buttons
		URL imgURL = getClass().getResource("back.png");
		backBtnContainer.setLeft(back);
		back.setMinSize(50, 15);
		back.setStyle("-fx-background-image: url('" + imgURL.toString() + "'); -fx-background-repeat: no-repeat;"
				+ "  -fx-background-position: center; -fx-background-size: 48px 18px; ");
		back.setOnAction(backClick);
    	

        vb.getChildren().addAll(addreason,adddate,addpatient, addButton);
        vb.setSpacing(5);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(backBtnContainer,text,table, vb);
        animation.play();
        Scene scene = new Scene(new Group());

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }
}
