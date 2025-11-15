package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.PaintPanel;
import javafx.scene.input.MouseEvent;

/**
 * An abstract base class for tools that manipulate shapes on a PaintPanel.
 * Provides common functionality for mouse interactions such as drag, click,
 * and release which is done by storing the intial click coordinates.
 *
 * @author arnold/Ashir/Alex/Ahmed/Abdullah
 */

public abstract class AbstractShapeTool implements Tool {
    protected final PaintModel model;
    protected final PaintPanel view;
    protected double x0, y0;

    /**
     * Constructs a new AbstractShapeTool attached to the current paint panel.
     * @param model
     * @param view
     */
    public AbstractShapeTool(PaintModel model, PaintPanel view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Store the initial click's both x and y coordinates
     * @param e
     */
    @Override
    public void onPress(MouseEvent e) {
        x0 = e.getX();
        y0 = e.getY();
    }

    /**
     * Show visual feedback of the current shape being drawn before release.
     * @param e
     */
    @Override
    public void onDrag(MouseEvent e) {
        drawPreview(e.getX(), e.getY());
    }

    @Override
    public void onMove(MouseEvent e) {}

    /**
     * Draw the specified shape on the canvas by releasing the mouse button.
     * @param e
     */
    @Override
    public void onRelease(MouseEvent e) {
        commit(e.getX(), e.getY());
    }

    /**
     * Draw the shape at the current position in order to show the user feedback
     * of the current shape being drawn.
     * @param x
     * @param y
     */
    protected abstract void drawPreview(double x, double y);

    /**
     * Add the current shape to the canvas.
     * @param x
     * @param y
     */
    protected abstract void commit(double x, double y);
}
