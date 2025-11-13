package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.*;
import javafx.scene.input.MouseEvent;

/**
 * Controls the eraser on the canvas depending on the current MouseEvent.
 */
public class EraserTool extends AbstractShapeTool{

    private Eraser current;

    /**
     * Constructs a new EraserTool object
     * @param model
     * @param view
     */
    public EraserTool(PaintModel model, PaintPanel view){ super(model,view); }

    /**
     * Begins the current erase.
     * @param e
     */
    @Override
    public void onPress(MouseEvent e){
        current = new Eraser();
        current.setThickness(model.getCurrentThickness());
        current.addPoint(new Point(e.getX(), e.getY()));
        model.addDrawable(current);
    }

    /**
     * Erases anything the mouse is dragged over.
     * @param e
     */
    @Override
    public void onDrag(MouseEvent e){
        if (current != null) {
            current.addPoint(new Point(e.getX(), e.getY()));
            model.updateObservers();
        }
    }

    /**
     * Ends the erase.
     * @param e
     */
    @Override
    public void onRelease(MouseEvent e){
        current.addPoint(new Point(e.getX(), e.getY()));
        model.updateObservers();
        current = null;
    }

    @Override
    protected void drawPreview(double x, double y) {}

    @Override
    protected void commit(double x, double y) {}

    @Override
    public void onMove(MouseEvent e){}
}
