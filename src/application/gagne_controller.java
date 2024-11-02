package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class gagne_controller {

    
    @FXML
    private Button fermer_gagne;
    
    @FXML
    public void initialize() {
        // Initialisation, si nécessaire
    }
    

    // Action pour fermer la fenêtre
    public void fermer_gagne() {
        Stage currentStage = (Stage) fermer_gagne.getScene().getWindow(); 
        currentStage.close();
    }
}

