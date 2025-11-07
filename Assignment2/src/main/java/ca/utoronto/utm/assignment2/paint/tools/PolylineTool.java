package ca.utoronto.utm.assignment2.paint.tools;


import ca.utoronto.utm.assignment2.paint.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class PolylineTool extends AbstractShapeTool{
    private Polyline current;

    public PolylineTool(PaintModel model, PaintPanel panel) {
        super(model, panel);
    }

    @Override
    public void onPress(MouseEvent e) {
        if (current == null) {
            current = new Polyline();
        }
        current.addPoint(new Point(e.getX(), e.getY()));
        model.setPreview(current);

        if (e.getButton() == MouseButton.SECONDARY) {
            finishPolyline();
        }
    }

    private void finishPolyline(){
        if (current != null) {
            model.addDrawable(current);
            model.clearPreview();
            current = null;
        }
    }

    @Override
    protected void drawPreview(double x, double y) {

    }

    @Override
    protected void commit(double x, double y) {

    }
}
