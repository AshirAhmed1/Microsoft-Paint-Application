package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.PaintPanel;
import ca.utoronto.utm.assignment2.paint.Point;
import ca.utoronto.utm.assignment2.paint.Drawable;
import javafx.scene.input.MouseEvent;

/**
 * A tool for copying a currently selected shape by clicking on it and storing it
 * in the model's clipboard.
 *
 * @author arnold/Ashir/Alex/Ahmed/Abdullah
 */
public class CopyTool extends AbstractShapeTool {

    /**
     * Constructs a CopyTool with references to the model and panel.
     *
     * @param model the PaintModel storing shapes and selection
     * @param view the PaintPanel where shapes are drawn
     */
    public CopyTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    /**
     * Selects the top-most shape near the point of the mouse click and
     * copies it.
     * @param e the current mouse press
     */
    @Override
    public void onPress(MouseEvent e) {
        Point click = new Point(e.getX(), e.getY());

        for (int i = model.getDrawables().size() - 1; i >= 0; i--) {
            Drawable d = model.getDrawables().get(i);
            if (d.contains(click)) {
                model.setSelected(d);
                model.copySelected();
                System.out.println("Copied: " + d.getClass().getSimpleName());
                break;
            }
        }
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
