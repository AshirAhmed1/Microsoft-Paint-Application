package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.*;
import ca.utoronto.utm.assignment2.paint.command.AddShapeCommand;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

/**
 * Controls the eraser on the canvas depending on the current MouseEvent.
 */
public class EraserTool extends AbstractShapeTool{

    private ArrayList<ErasePoint> currentStroke = new  ArrayList<>();
    private Drawable erasedShape;

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
    public void onPress(MouseEvent e) {
        erasedShape = null;
        currentStroke.clear();
        eraseAt(e);
    }
    public void onDrag(MouseEvent e) {
        eraseAt(e);
        model.updateObservers();
    }

    @Override
    public void onRelease(MouseEvent e) {
        if (erasedShape != null) {
            erasedShape.addEraseStroke();
        }
        erasedShape = null;
        model.updateObservers();
        }

    public void eraseAt(MouseEvent e) {
        Point click = new Point(e.getX(), e.getY());

        for (int i = model.getDrawables().size() - 1; i >= 0; i--) {
            Drawable d = model.getDrawables().get(i);
            if (d.contains(click)) {
                if (erasedShape == null) {
                    erasedShape = d;
                }
                d.addErasePoint(new ErasePoint(click.x, click.y, model.getCurrentThickness()));
                model.updateObservers();
                break;
            }
        }
    }
    @Override
    protected void drawPreview(double x, double y) {}

    @Override
    protected void commit(double x, double y) {}

    @Override
    public void onMove(MouseEvent e){}
}
