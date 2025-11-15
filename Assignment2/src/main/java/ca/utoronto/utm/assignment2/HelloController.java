package ca.utoronto.utm.assignment2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller class for the HelloApplication FXML view.
 * Handles user interactions by updating the UI components
 * defined in the associated FXML file.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class HelloController {

    @FXML
    private Label welcomeText;

    /**
     * Event handler for the "Hello" button click.
     * Updates the label text to display a welcome message.
     */
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
