package ca.utoronto.utm.assignment2.scribble;

import javafx.scene.canvas.Canvas;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * A simple drawing panel used in the Scribble application.
 * This panel listens for mouse movement events and draws small
 * red pixels at the cursor location, creating a freehand scribble effect.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class ScribblePanel extends Canvas implements EventHandler<MouseEvent> {

    /**
     * Constructs a new ScribblePanel with a fixed canvas size
     * and registers itself to handle mouse movement events.
     */
    public ScribblePanel() {
        super(200, 200);
        this.addEventHandler(MouseEvent.MOUSE_MOVED, this);
    }

    /**
     * Handles mouse movement events by drawing a small red point
     * at the current cursor location.
     *
     * @param mouseEvent the mouse event containing cursor coordinates
     */
    @Override
    public void handle(MouseEvent mouseEvent) {
        GraphicsContext gc = this.getGraphicsContext2D();
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        gc.setFill(Color.RED);
        gc.fillRect(x, y, 1, 1);
    }
}
