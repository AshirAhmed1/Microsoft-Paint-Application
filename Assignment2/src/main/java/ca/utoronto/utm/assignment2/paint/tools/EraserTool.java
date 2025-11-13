package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class EraserTool extends AbstractShapeTool{

    private Eraser current;

    public EraserTool(PaintModel model, PaintPanel view){ super(model,view); }

    @Override
    public void onPress(MouseEvent e){
        current = new Eraser();
        current.addPoint(new Point(e.getX(), e.getY()));
        model.addDrawable(current);
    }

    @Override
    public void onDrag(MouseEvent e){
        if (current != null) {
            current.addPoint(new Point(e.getX(), e.getY()));
            model.updateObservers();
        }
    }

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
