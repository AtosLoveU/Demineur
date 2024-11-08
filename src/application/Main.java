package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
	        Parent root = loader.load();
	        primaryStage.setTitle("Jeu");
	        primaryStage.setScene(new Scene(root));
	        primaryStage.show();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

    public static void main(String[] args) {
        launch(args);
    }
    
}

