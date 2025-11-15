package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.*;
import ca.utoronto.utm.assignment2.paint.command.CutCommand;
import javafx.scene.input.MouseEvent;

/**
 * A tool for cutting a currently selected shape by clicking on it which
 * deletes and stories it in the model's clipboard.
 *
 * @author arnold/Ashir/Alex/Ahmed/Abdullah
 */
public class CutTool extends AbstractShapeTool {

    /**
     * Constructs a CutTool with references to the model and panel.
     *
     * @param model the PaintModel storing shapes and selection
     * @param view the PaintPanel where shapes are drawn
     */
    public CutTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    /**
     * Selects the top-most shape near the point of the mouse click, remove
     * it by cutting and storing it in the clipboard.
     * @param e the current mouse press
     */
    @Override
    public void onPress(MouseEvent e) {
        Point click = new Point(e.getX(), e.getY());

        for (int i = model.getDrawables().size() - 1; i >= 0; i--) {
            Drawable d = model.getDrawables().get(i);
            if (d.contains(click)) {

                model.setSelected(d);
                model.executeCommand(new CutCommand(model, d));

                break;
            }
        }
    }

    @Override public void onDrag(MouseEvent e) {}
    @Override public void onRelease(MouseEvent e) {}
    @Override protected void drawPreview(double x, double y) {}
    @Override protected void commit(double x, double y) {}
}
