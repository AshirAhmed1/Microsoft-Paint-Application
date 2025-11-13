package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.PaintPanel;
import ca.utoronto.utm.assignment2.paint.Point;
import ca.utoronto.utm.assignment2.paint.Drawable;
import javafx.scene.input.MouseEvent;

public class CopyTool extends AbstractShapeTool {

    public CopyTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

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
