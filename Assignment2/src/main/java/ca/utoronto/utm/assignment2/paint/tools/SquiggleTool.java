package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.*;
import ca.utoronto.utm.assignment2.paint.command.AddShapeCommand;
import javafx.scene.input.MouseEvent;

/**
 * A tool for drawing freeform lines on a PaintPanel.
 * Users can click drag to draw a line.
 *
 * @author arnold/Ashir/Alex/Ahmed/Abdullah
 */
public class SquiggleTool extends AbstractShapeTool {
    private Squiggle current;

    /**
     * Constructs a SquiggleTool with references to the model and panel.
     *
     * @param model the PaintModel storing shapes
     * @param view the PaintPanel where shapes are drawn
     */
    public SquiggleTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    /**
     * Starts a new squiggle if none exists.
     *
     * @param e the MouseEvent representing the mouse press
     */
    @Override
    public void onPress(MouseEvent e) {
        current = new Squiggle();
        current.setColor(model.getCurrentColor());
        current.setThickness(model.getCurrentThickness());
        current.addPoint(new Point(e.getX(), e.getY()));
        model.setPreview(current);
    }

    /**
     * Adds points to the current squiggle while dragging the mouse.
     * Updates the preview in real-time.
     *
     * @param e the MouseEvent representing the mouse drag
     */
    @Override
    public void onDrag(MouseEvent e) {
        if (current != null) {
            current.addPoint(new Point(e.getX(), e.getY()));
            model.setPreview(current);
        }
    }

    /**
     * Finalizes the squiggle when the mouse is released.
     * Executes an AddShapeCommand to add the squiggle to the model
     * and clears the preview.
     *
     * @param e the MouseEvent representing the mouse release
     */
    @Override
    public void onRelease(MouseEvent e) {
        if (current != null) {
            model.executeCommand(new AddShapeCommand(model, current));
            model.clearPreview();
            current = null;
        }
    }

    @Override protected void drawPreview(double x, double y) {}
    @Override protected void commit(double x, double y) {}
}