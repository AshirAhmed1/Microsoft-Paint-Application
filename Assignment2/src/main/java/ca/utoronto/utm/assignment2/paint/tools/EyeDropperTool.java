package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.*;
import ca.utoronto.utm.assignment2.paint.command.RecolorCommand;
import javafx.scene.input.MouseEvent;

/**
 * Represent a paint bucket tool that can change the color of a selected shape
 * to the current color selected on the canvas.
 */
public class EyeDropperTool extends AbstractShapeTool {

    public EyeDropperTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    @Override
    public void onPress(MouseEvent e) {
        Point click = new Point(e.getX(), e.getY());

        for (int i = model.getDrawables().size() - 1; i >= 0; i--) {
            Drawable d = model.getDrawables().get(i);
            if (d.contains(click)) {
                model.setCurrentColor(d.getColor());
                break;
            }
        }
    }

    @Override
    protected void drawPreview(double x, double y) {}

    @Override
    protected void commit(double x, double y) {}
}
