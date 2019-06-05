package application;
	
import application.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;



public class PracticeInterface extends Application {
	MainMenu mm;
	LoginMenu login;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
//			login = new LoginMenu(250, 450, 10, 20);
			mm = new MainMenu(300, 600, 10, 20, "Darko");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
}
