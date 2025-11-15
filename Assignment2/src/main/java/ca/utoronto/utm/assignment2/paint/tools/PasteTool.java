package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.PaintPanel;
import javafx.scene.input.MouseEvent;

/**
 * A tool for paste the last selected shape in the model's clipboard.
 *
 * @author arnold/Ashir/Alex/Ahmed/Abdullah
 */
public class PasteTool extends AbstractShapeTool {

    /**
     * Constructs a PasteTool with references to the model and panel.
     *
     * @param model the PaintModel storing shapes and selection
     * @param view the PaintPanel where shapes are drawn
     */
    public PasteTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    /**
     * Pastes the last copied/cut item onto the canvas.
     * @param e the current mouse press
     */
    @Override
    public void onPress(MouseEvent e) {
        model.pasteAt(e.getX(), e.getY());
    }

    @Override
    public void onDrag(MouseEvent e) {}

    @Override
    public void onRelease(MouseEvent e) {}
    @Override
    protected void drawPreview(double x, double y) {}

    @Override
    protected void commit(double x, double y) {}
}