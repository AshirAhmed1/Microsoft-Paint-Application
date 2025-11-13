package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.PaintPanel;
import javafx.scene.input.MouseEvent;

public class PasteTool extends AbstractShapeTool {

    public PasteTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    // When user clicks on the canvas, paste the clipboard shape at that point
    @Override
    public void onPress(MouseEvent e) {
        model.pasteAt(e.getX(), e.getY());
    }

    // Paste is a click-only operation, so we don't need drag or release
    @Override
    public void onDrag(MouseEvent e) {
        // no-op
    }

    @Override
    public void onRelease(MouseEvent e) {
        // no-op
    }

    // Not used for paste; required by AbstractShapeTool
    @Override
    protected void drawPreview(double x, double y) {
        // no-op
    }

    @Override
    protected void commit(double x, double y) {
        // no-op
    }
}