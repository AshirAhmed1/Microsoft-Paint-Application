package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.*;
import ca.utoronto.utm.assignment2.paint.command.AddShapeCommand;

/**
 * A tool for drawing Squares on a PaintPanel.
 * Users can click and drag to define the side lengths of the Square.
 *
 * @author arnold/Ashir/Alex/Ahmed/Abdullah
 */
public class SquareTool extends AbstractShapeTool {
    public SquareTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    /**
     * Creates a square object based on the initial mouse press (x0, y0)
     * and the current mouse coordinates (x, y).
     *
     * @param x the mouse's x-coordinate
     * @param y the mouse's y-coordinate
     * @return a square object
     */
    private Square makeSquare(double x, double y) {
        double dx = x - x0;
        double dy = y - y0;
        double side = Math.max(Math.abs(dx), Math.abs(dy));

        double topLeftX = x0;
        double topLeftY = y0;

        if (dx < 0)
        {
            topLeftX = x0 - side;
        }

        if (dy < 0)
        {
            topLeftY = y0 - side;
        }

        Point topLeft = new Point(topLeftX, topLeftY);

        return new Square(topLeft, side);
    }

    /**
     * Draws a preview of the square while dragging the mouse.
     *
     * @param x the mouse's x-coordinate
     * @param y the mouse's y-coordinate
     */
    @Override
    protected void drawPreview(double x, double y) {

        Square preview = makeSquare(x, y);
        preview.setColor(model.getCurrentColor());
        preview.setThickness(model.getCurrentThickness());
        preview.setFilled(model.isFillMode());
        model.setPreview(preview);
    }

    /**
     * Finalizes the square when the mouse is released.
     * The oval is added to the model as a command, and the preview is cleared.
     *
     * @param x the mouse's x-coordinate on release
     * @param y the mouse's y-coordinate on release
     */
    @Override
    protected void commit(double x, double y) {
        Square square = makeSquare(x, y);
        square.setColor(model.getCurrentColor());
        square.setThickness(model.getCurrentThickness());
        square.setFilled(model.isFillMode());
        model.executeCommand(new AddShapeCommand(model, square));
        model.clearPreview();
    }
}