package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.*;
import ca.utoronto.utm.assignment2.paint.command.RecolorCommand;
import javafx.scene.input.MouseEvent;

/**
 * Represent an eyedropper tool that can change the selected color
 * to the color of the selected shape on the canvas.
 */
public class EyeDropperTool extends AbstractShapeTool {

    /**
     * Construct a new EyeDropperTool
     * @param model
     * @param view
     */
    public EyeDropperTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    /**
     * Select the color of the shape clicked on.
     * @param e
     */
    @Override
    public void onPress(MouseEvent e) {
        Point click = new Point(e.getX(), e.getY());

        for (int i = model.getDrawables().size() - 1; i >= 0; i--) {
            Drawable d = model.getDrawables().get(i);
            if (d.contains(click)) {
                model.setCurrentColor(d.getColor());
                model.updateObservers();
                break;
            }
        }
    }

    @Override
    protected void drawPreview(double x, double y) {}

    @Override
    protected void commit(double x, double y) {}
}
