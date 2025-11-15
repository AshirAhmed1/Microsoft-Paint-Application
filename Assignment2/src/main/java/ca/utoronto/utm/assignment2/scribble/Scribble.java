package ca.utoronto.utm.assignment2.scribble;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * A simple standalone scribble application used for demonstrating JavaFX layouts,
 * event handling, and basic canvas interaction. Creates a window containing a
 * ScribblePanel where users can draw freehand.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class Scribble extends Application {

    /**
     * Initializes the GUI, creates the ScribblePanel, places it inside an HBox
     * layout, and attaches the scene to the primary stage.
     *
     * @param stage the main application window provided by JavaFX
     * @throws Exception if any unexpected error occurs during initialization
     */
    @Override
    public void start(Stage stage) throws Exception {

        /**
         * A stage is the top level GUI window.
         * A stage has a scene.
         * A scene is a tree/graph of stuff, that is, nodes in the scene are:
         *
         * LAYOUTS: Arrange how child components appear.
         * CONTROLS: UI components users interact with.
         * EVENTS: Controls communicate with callbacks (Observer pattern).
         */

        ScribblePanel scribblePanel = new ScribblePanel();
        HBox root = new HBox(); // LAYOUT
        root.setPadding(new Insets(5));
        root.getChildren().add(scribblePanel);

        Scene scene = new Scene(root); // SCENE

        stage.setTitle("Scribble");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Launches the Scribble application by starting the JavaFX runtime,
     * creating an instance of Scribble, and invoking start().
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        launch(args);

        /**
         * static method of Application
         * Creates an instance of Application,
         * starts the gui thread and calls
         * Application.start(stage)
         */
    }
}
