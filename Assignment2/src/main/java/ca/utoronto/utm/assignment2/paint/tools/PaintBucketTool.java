package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.*;
import ca.utoronto.utm.assignment2.paint.command.RecolorCommand;
import javafx.scene.input.MouseEvent;

/**
 * Represent a paint bucket tool that can change the color of a selected shape
 * to the current color selected on the canvas.
 *
 * @author arnold/Ashir/Alex/Ahmed/Abdullah
 */
public class PaintBucketTool extends AbstractShapeTool {

    public PaintBucketTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    /**
     * Change the color of the shape clicked on to the current selected color of the canvas.
     * @param e
     */
    @Override
    public void onPress(MouseEvent e) {
        Point click = new Point(e.getX(), e.getY());

        for (int i = model.getDrawables().size() - 1; i >= 0; i--) {
            Drawable d = model.getDrawables().get(i);
            if (d.contains(click)) {

                model.setSelected(d);
                model.executeCommand(
                        new RecolorCommand(model, d, model.getCurrentColor()));
                model.setSelected(null);
                break;
            }
        }
    }

    @Override
    protected void drawPreview(double x, double y) {}

    @Override
    protected void commit(double x, double y) {}
}
