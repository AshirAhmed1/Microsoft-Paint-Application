package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.*;
import javafx.scene.input.MouseEvent;

public class SelectMoveTool extends AbstractShapeTool {
    private Drawable selectedShape;   // the shape being moved
    private double lastX, lastY;      // last mouse position
    private boolean isDragging = false;

    public SelectMoveTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    @Override
    public void onPress(MouseEvent e) {
        Point click = new Point(e.getX(), e.getY());


        for (int i = model.getDrawables().size() - 1; i >= 0; i--) {
            Drawable d = model.getDrawables().get(i);
            if (d.contains(click)) {
                selectedShape = d;
                lastX = e.getX();
                lastY = e.getY();
                isDragging = true;
                System.out.println("Selected: " + d.getClass().getSimpleName());
                break;
            }
        }
    }

    @Override
    public void onDrag(MouseEvent e) {
        if (selectedShape != null && isDragging) {
            double dx = e.getX() - lastX;
            double dy = e.getY() - lastY;

            selectedShape.translate(dx, dy);
            lastX = e.getX();
            lastY = e.getY();

            model.updateObservers();
        }
    }

    @Override
    public void onRelease(MouseEvent e) {
        if (selectedShape != null) {
            selectedShape = null;
            isDragging = false;
            model.clearPreview();
        }
    }

    @Override protected void drawPreview(double x, double y) {}
    @Override protected void commit(double x, double y) {}
}
