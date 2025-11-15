package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.PaintPanel;

/**
 * A tool that clears the entire canvas when activated.
 * Clicking or releasing the mouse executes the clear operation.
 * No preview is shown for this tool.
 *
 * @author arnold/Ashir/Alex/Ahmed/Abdullah
 */

public class ClearCanvasTool extends AbstractShapeTool {

    /**
     * Constructs a ClearCanvasTool with references to the model and panel.
     *
     * @param model the PaintModel storing shapes
     * @param view the PaintPanel where shapes are drawn
     */
    public ClearCanvasTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    @Override
    protected void drawPreview(double x, double y) {
        // No preview for clearing the canvas.
    }

    /**
     * Clear the canvas.
     *
     * @param x current x-coordinate of the mouse
     * @param y current y-coordinate of the mouse
     */
    @Override
    protected void commit(double x, double y) {
        // When the user clicks/releases, clear the canvas.
        model.clearCanvas();
    }
}