package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class perdu_controller {


	@FXML
	private Button fermer_perdu;
	
	@FXML
	public void initialize() {
		
	}
	
	public void fermer_perdu() {
		Stage currentStage = (Stage) fermer_perdu.getScene().getWindow();
		currentStage.close();
	}
}
