package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.*;
import javafx.scene.input.MouseEvent;
import ca.utoronto.utm.assignment2.paint.command.MoveCommand;

/**
 * A tool for selecting shape by clicking on it, allowing it to be moved
 * on the canvas.
 *
 * @author arnold/Ashir/Alex/Ahmed/Abdullah
 */
public class SelectMoveTool extends AbstractShapeTool {

    private Drawable selectedShape;
    private double lastX, lastY;
    private boolean isDragging = false;

    private double totalDx = 0;
    private double totalDy = 0;

    /**
     * Constructs a SelectMoveTool with references to the model and panel.
     *
     * @param model the PaintModel storing shapes and selection
     * @param view the PaintPanel where shapes are drawn
     */
    public SelectMoveTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    /**
     * Selects the top-most shape near the point of the mouse click.
     * @param e the current mouse press
     */
    @Override
    public void onPress(MouseEvent e) {
        Point click = new Point(e.getX(), e.getY());


        for (int i = model.getDrawables().size() - 1; i >= 0; i--) {
            Drawable d = model.getDrawables().get(i);
            if (d.contains(click)) {

                selectedShape = d;
                model.setSelected(d);

                lastX = e.getX();
                lastY = e.getY();

                totalDx = 0;
                totalDy = 0;

                isDragging = true;
                return;
            }
        }


        selectedShape = null;
        model.setSelected(null);
    }

    /**
     * Takes the selected object, translates and moves it across the canvas
     * according to the path of the mouse.
     * @param e the current mouse drag
     */
    @Override
    public void onDrag(MouseEvent e) {
        if (!isDragging || selectedShape == null) return;

        double dx = e.getX() - lastX;
        double dy = e.getY() - lastY;

        if (dx != 0 || dy != 0) {
            selectedShape.translate(dx, dy);
            model.updateObservers();

            totalDx += dx;
            totalDy += dy;
        }

        lastX = e.getX();
        lastY = e.getY();
    }

    /**
     * Sets the selected shape at a new position on the canvas.
     * @param e the current mouse press
     */
    @Override
    public void onRelease(MouseEvent e) {
        if (!isDragging || selectedShape == null) return;


        if (totalDx != 0 || totalDy != 0) {
            model.executeCommand(
                    new MoveCommand(model, selectedShape, totalDx, totalDy)
            );
        }

        isDragging = false;
        selectedShape = null;
    }

    @Override protected void drawPreview(double x, double y) {}
    @Override protected void commit(double x, double y) {}
}
