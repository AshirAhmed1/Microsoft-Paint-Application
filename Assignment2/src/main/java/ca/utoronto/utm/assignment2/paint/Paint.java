package ca.utoronto.utm.assignment2.paint;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main entry point for the paint application. This class initializes the
 * PaintModel and constructs the View, which sets up the UI and controllers.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class Paint extends Application {

    PaintModel model; // Model
    View view; // View + Controller

    /**
     * Launches the paint application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the JavaFX application, initializes the model, and constructs the view.
     *
     * @param stage the primary stage provided by JavaFX
     * @throws Exception if initialization fails
     */
    @Override
    public void start(Stage stage) throws Exception {

        this.model = new PaintModel();

        // View + Controller
        this.view = new View(model, stage);
    }
}
