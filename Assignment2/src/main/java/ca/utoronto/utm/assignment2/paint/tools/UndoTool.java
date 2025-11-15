package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.PaintPanel;
import javafx.scene.input.MouseEvent;


/**
 * A tool for redoing the last action made on the canvas
 *
 * @author arnold/Ashir/Alex/Ahmed/Abdullah
 */
public class UndoTool extends AbstractShapeTool {

    /**
     * Constructs a UndoTool with references to the model and panel.
     *
     * @param model the PaintModel storing shapes and selection
     * @param view the PaintPanel where shapes are drawn
     */
    public UndoTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    /**
     * Undo the last action done in the canvas.
     * @param e
     */
    @Override
    public void onPress(MouseEvent e) {
        // Just undo immediately when the button is active and user clicks canvas
        model.undo();
    }

    @Override
    public void onDrag(MouseEvent e) { }

    @Override
    public void onMove(MouseEvent e) { }

    @Override
    public void onRelease(MouseEvent e) { }

    @Override
    protected void drawPreview(double x, double y) { }

    @Override
    protected void commit(double x, double y) { }
}