package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.*;
import ca.utoronto.utm.assignment2.paint.command.AddShapeCommand;

/**
 * A tool for drawing Triangles on a PaintPanel.
 * Users can click and drag to define the side lengths of the triangle.
 *
 * @author arnold/Ashir/Alex/Ahmed/Abdullah
 */
public class TriangleTool extends AbstractShapeTool {
    /**
     * Construct a new rectangle tool with reference to the given model and view.
     * @param model where shapes are stored
     * @param view where shapes are drawn
     */
    public TriangleTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    /**
     * Creates a triangle object based on the initial mouse press (x0, y0)
     * and the current mouse coordinates (x, y).
     *
     * @param x the mouse's x-coordinate
     * @param y the mouse's y-coordinate
     * @return a triangle object
     */
    private Triangle makeTriangle(double x, double y) {
        double base = Math.abs(x - x0);
        double height = Math.abs(y - y0);
        double side = Math.sqrt(height * height + (base / 2.0) * (base / 2.0));

        double minX = Math.min(x0, x);
        double maxY = Math.max(y0, y);

        return new Triangle(new Point(minX, maxY), base, side, side);
    }

    /**
     * Draws a preview of the triangle while dragging the mouse.
     *
     * @param x the mouse's x-coordinate
     * @param y the mouse's y-coordinate
     */
    @Override
    protected void drawPreview(double x, double y) {

        Triangle preview = makeTriangle(x, y);
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
        Triangle triangle = makeTriangle(x, y);
        triangle.setColor(model.getCurrentColor());
        triangle.setThickness(model.getCurrentThickness());
        triangle.setFilled(model.isFillMode());
        model.executeCommand(new AddShapeCommand(model, triangle));
        model.clearPreview();
    }
}