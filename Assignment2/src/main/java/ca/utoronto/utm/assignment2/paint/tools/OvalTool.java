package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.*;
import ca.utoronto.utm.assignment2.paint.command.AddShapeCommand;

/**
 * A tool for drawing Ovals on a PaintPanel.
 * Users can click and drag to define the length and width of the oval.
 *
 * @author arnold/Ashir/Alex/Ahmed/Abdullah
 */
public class OvalTool extends AbstractShapeTool {

    /**
     * Construct a new oval tool with reference to the given model and view.
     * @param model where shapes are stored
     * @param view where shapes are drawn
     */
    public OvalTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    /**
     * Creates an Oval object based on the initial mouse press (x0, y0)
     * and the current mouse coordinates (x, y).
     *
     * @param x the mouse's x-coordinate
     * @param y the mouse's y-coordinate
     * @return an oval object
     */
    private Oval makeOval(double x, double y) {
        // top left is always the leftmost position
        double cornerX = Math.min(x, x0);
        double cornerY = Math.min(y, y0);
        double w = Math.abs(x - x0);
        double h = Math.abs(y - y0);
        return new Oval(new Point(cornerX, cornerY), w, h);
    }

    /**
     * Draws a preview of the Oval while dragging the mouse.
     *
     * @param x the mouse's x-coordinate
     * @param y the mouse's y-coordinate
     */
    @Override
    protected void drawPreview(double x, double y) {
        Oval preview = makeOval(x, y);
        preview.setColor(model.getCurrentColor());
        preview.setThickness(model.getCurrentThickness());
        preview.setFilled(model.isFillMode());
        model.setPreview(preview);
    }

    /**
     * Finalizes the oval when the mouse is released.
     * The oval is added to the model as a command, and the preview is cleared.
     *
     * @param x the mouse's x-coordinate on release
     * @param y the mouse's y-coordinate on release
     */
    @Override
    protected void commit(double x, double y) {
        Oval oval = makeOval(x, y);
        oval.setColor(model.getCurrentColor());
        oval.setThickness(model.getCurrentThickness());
        oval.setFilled(model.isFillMode());
        model.executeCommand(new AddShapeCommand(model, oval));
        model.clearPreview();
    }
}