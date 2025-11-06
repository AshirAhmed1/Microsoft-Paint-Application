package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.*;

public class RectangleTool extends AbstractShapeTool {
    public RectangleTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    private Rectangle makeRect(double x, double y) {
        double w = x - x0;
        double h = y - y0;
        return new Rectangle(new Point(x0, y0), w, h);
    }

    @Override
    protected void drawPreview(double x, double y) {
        model.setPreview(makeRect(x, y));
    }

    @Override
    protected void commit(double x, double y) {
        model.addDrawable(makeRect(x, y));
        model.clearPreview();
    }
}