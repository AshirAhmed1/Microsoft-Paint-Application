package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.*;
import ca.utoronto.utm.assignment2.paint.command.AddShapeCommand;

/**
 * A tool for drawing circles on a PaintPanel.
 * Users can click and drag to define the radius of the circle.
 *
 * @author arnold/Ashir/Alex/Ahmed/Abdullah
 */
public class CircleTool extends AbstractShapeTool {

    /**
     * Construct a new circle tool with reference to the given model and view.
     * @param model where shapes are stored
     * @param view where shapes are drawn
     */
    public CircleTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    /**
     * Creates a Circle object based on the initial mouse press (x0, y0)
     * and the current mouse coordinates (x, y).
     *
     * @param x the mouse's x-coordinate
     * @param y the mouse's y-coordinate
     * @return a circle object
     */
    private Circle makeCircle(double x, double y) {
        double dx = x - x0;
        double dy = y - y0;
        double r = Math.sqrt(dx * dx + dy * dy);
        return new Circle(new Point(x0, y0), (int) r);
    }

    /**
     * Draws a preview of the circle while dragging the mouse.
     *
     * @param x the mouse's x-coordinate
     * @param y the mouse's y-coordinate
     */
    @Override
    protected void drawPreview(double x, double y) {

        Circle preview = makeCircle(x, y);
        preview.setColor(model.getCurrentColor());
        preview.setThickness(model.getCurrentThickness());
        preview.setFilled(model.isFillMode());
        model.setPreview(preview);

    }

    /**
     * Finalizes the circle when the mouse is released.
     * The circle is added to the model as a command, and the preview is cleared.
     *
     * @param x the mouse's x-coordinate on release
     * @param y the mouse's y-coordinate on release
     */
    @Override
    protected void commit(double x, double y) {
        Circle circle = makeCircle(x, y);
        circle.setColor(model.getCurrentColor());
        circle.setThickness(model.getCurrentThickness());
        circle.setFilled(model.isFillMode());
        model.executeCommand(new AddShapeCommand(model, circle));
        model.clearPreview();
    }
}