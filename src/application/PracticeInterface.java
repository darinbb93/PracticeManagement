package application;
	
import application.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;
import Data.*;



public class PracticeInterface extends Application {
	LoginMenu login;
	public static Database database = new Database();

	
	@Override
	public void start(Stage primaryStage) {
		try {
			database.initialize();
			
			login = new LoginMenu(250, 450, 10, 20);
			//debugging code
			
//			Practitioner doctor = (Practitioner)database.getMembers().get(1);
//			System.out.print(doctor.getPatients().get(1).getName());
//			MainMenu mm = new MainMenu(300, 600, 10, 20, doctor);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
}
