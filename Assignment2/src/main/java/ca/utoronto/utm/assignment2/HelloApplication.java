package ca.utoronto.utm.assignment2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A simple JavaFX application that loads and displays an FXML-defined
 * user interface. This class initializes the JavaFX runtime, loads the
 * "hello-view.fxml" layout, and attaches it to the primary stage.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class HelloApplication extends Application {

    /**
     * Loads the FXML view, creates the scene, and displays it in the primary stage.
     *
     * @param stage the main application window provided by the JavaFX runtime
     * @throws IOException if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Launches the JavaFX application.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        launch();
    }
}
