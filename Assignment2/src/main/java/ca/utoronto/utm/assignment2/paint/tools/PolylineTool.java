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
            current = new Polyline(); // creates a start point, no line is created
        }
        current.addPoint(new Point(e.getX(), e.getY()));
        model.setPreview(current);

        // finish shape with right click
        if (e.getButton() == MouseButton.SECONDARY) {
            finishPolyline();
        }
    }

    @Override
    public void onMove(MouseEvent e) {
        // show polyline only if it is started with at least a starting point
        if (current != null && !current.getPoints().isEmpty()) {
            Point lastPoint = current.getPoints().get(current.getPoints().size() - 1);
            Polyline preview = new Polyline();

            // create a temporary copy of the polyline and show the next line feedback
            preview.getPoints().addAll(current.getPoints());
            preview.addPoint(lastPoint);
            preview.addPoint(new Point(e.getX(), e.getY()));
            model.setPreview(preview);
        }
    }

    /**
     Given a polyline is started, end and add the polyline to the canvas.
     */
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
