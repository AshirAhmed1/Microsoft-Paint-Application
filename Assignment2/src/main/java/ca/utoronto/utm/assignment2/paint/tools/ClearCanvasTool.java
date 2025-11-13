package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.PaintPanel;

public class ClearCanvasTool extends AbstractShapeTool {

    public ClearCanvasTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    @Override
    protected void drawPreview(double x, double y) {
        // No preview for clearing the canvas.
    }

    @Override
    protected void commit(double x, double y) {
        // When the user clicks/releases, clear the canvas.
        model.clearCanvas();
    }
}