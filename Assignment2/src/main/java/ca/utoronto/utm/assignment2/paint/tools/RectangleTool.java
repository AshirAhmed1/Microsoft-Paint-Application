package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.*;
import ca.utoronto.utm.assignment2.paint.command.AddShapeCommand;

/**
 * A tool for drawing Rectangles on a PaintPanel.
 * Users can click and drag to define the length and width of the rectangle.
 *
 * @author arnold/Ashir/Alex/Ahmed/Abdullah
 */
public class RectangleTool extends AbstractShapeTool {
    /**
     * Construct a new rectangle tool with reference to the given model and view.
     * @param model where shapes are stored
     * @param view where shapes are drawn
     */
    public RectangleTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    /**
     * Creates a rectangle object based on the initial mouse press (x0, y0)
     * and the current mouse coordinates (x, y).
     *
     * @param x the mouse's x-coordinate
     * @param y the mouse's y-coordinate
     * @return a rectangle object
     */
    private Rectangle makeRect(double x, double y) {
        double w = x - x0;
        double h = y - y0;

        double topLeftX = x0;
        double topLeftY = y0;

        if (w < 0)
        {
            topLeftX = x0 + w;
            w = Math.abs(w);
        }

        if (h < 0)
        {
            topLeftY = y0 + h;
            h = Math.abs(h);
        }

        Point topLeft = new Point(topLeftX, topLeftY);

        return new Rectangle(topLeft, w, h);
    }

    /**
     * Draws a preview of the rectangle while dragging the mouse.
     *
     * @param x the mouse's x-coordinate
     * @param y the mouse's y-coordinate
     */
    @Override
    protected void drawPreview(double x, double y) {

        Rectangle preview = makeRect(x, y);
        preview.setColor(model.getCurrentColor());
        preview.setThickness(model.getCurrentThickness());
        preview.setFilled(model.isFillMode());
        model.setPreview(preview);
    }

    /**
     * Finalizes the rectangle when the mouse is released.
     * The oval is added to the model as a command, and the preview is cleared.
     *
     * @param x the mouse's x-coordinate on release
     * @param y the mouse's y-coordinate on release
     */
    @Override
    protected void commit(double x, double y) {
        Rectangle rectangle = makeRect(x, y);
        rectangle.setColor(model.getCurrentColor());
        rectangle.setThickness(model.getCurrentThickness());
        rectangle.setFilled(model.isFillMode());
        model.executeCommand(new AddShapeCommand(model, rectangle));
        model.clearPreview();
    }
}