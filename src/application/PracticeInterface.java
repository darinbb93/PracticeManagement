package application;
	
import application.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;
import Data.*;



public class PracticeInterface extends Application {
	MainMenu mm;
	LoginMenu login;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
//			login = new LoginMenu(250, 450, 10, 20);
			//debugging code
			Database debug = new Database();
			debug.initialize();
			Practitioner doctor = (Practitioner)debug.getMembers().get(1);
//			System.out.print(doctor.getPatients().get(1).getName());
			mm = new MainMenu(300, 600, 10, 20, doctor);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
}
