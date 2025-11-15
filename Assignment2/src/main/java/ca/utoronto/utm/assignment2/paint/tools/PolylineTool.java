package ca.utoronto.utm.assignment2.paint.tools;


import ca.utoronto.utm.assignment2.paint.*;
import ca.utoronto.utm.assignment2.paint.command.AddShapeCommand;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
/**
 * A tool for drawing polylines on a PaintPanel.
 * Users can click multiple points to create connected line segments.
 * The polyline is finalized with a right-click.
 *
 * @author arnold/Ashir/Alex/Ahmed/Abdullah
 */
public class PolylineTool extends AbstractShapeTool{
    private Polyline current;

    /**
     * Constructs a PolylineTool with references to the model and panel.
     *
     * @param model the PaintModel storing shapes
     * @param panel the PaintPanel where shapes are drawn
     */
    public PolylineTool(PaintModel model, PaintPanel panel) {
        super(model, panel);
    }

    /**
     * Handles mouse press events. Starts a new polyline if none exists,
     * adds a point to the current polyline, and shows the preview.
     * Right-click will finish the polyline and add it to the model.
     *
     * @param e the MouseEvent representing the mouse press
     */
    @Override
    public void onPress(MouseEvent e) {
        if (current == null) {
            current = new Polyline(); // creates a start point, no line is created
            current.setColor(model.getCurrentColor());
            current.setThickness(model.getCurrentThickness());
        }
        current.addPoint(new Point(e.getX(), e.getY()));
        model.setPreview(current);

        // finish shape with right click
        if (e.getButton() == MouseButton.SECONDARY) {
            finishPolyline();
        }
    }

    /**
     * Handles mouse move events. Updates the preview of the polyline
     * by showing a temporary line to the current mouse position.
     *
     * @param e the MouseEvent representing the mouse movement
     */
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
            model.executeCommand(new AddShapeCommand(model, current));
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
