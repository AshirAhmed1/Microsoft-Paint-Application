package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.PaintPanel;
import javafx.scene.input.MouseEvent;

public abstract class AbstractShapeTool implements Tool {
    protected final PaintModel model;
    protected final PaintPanel view;
    protected double x0, y0;

    public AbstractShapeTool(PaintModel model, PaintPanel view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void onPress(MouseEvent e) {
        x0 = e.getX();
        y0 = e.getY();
    }

    @Override
    public void onDrag(MouseEvent e) {
        drawPreview(e.getX(), e.getY());
    }
    @Override
    public void onMove(MouseEvent e) {}

    @Override
    public void onRelease(MouseEvent e) {
        commit(e.getX(), e.getY());
    }

    protected abstract void drawPreview(double x, double y);
    protected abstract void commit(double x, double y);
}
