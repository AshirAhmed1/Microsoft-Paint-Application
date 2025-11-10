package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.*;

public class CircleTool extends AbstractShapeTool {
    public CircleTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    private Circle makeCircle(double x, double y) {
        double dx = x - x0;
        double dy = y - y0;
        double r = Math.sqrt(dx * dx + dy * dy);
        return new Circle(new Point(x0, y0), (int) r);
    }

    @Override
    protected void drawPreview(double x, double y) {
        model.setPreview(makeCircle(x, y));
    }

    @Override
    protected void commit(double x, double y) {
        Circle circle = makeCircle(x, y);
        circle.setColor(model.getCurrentColor());
        model.addDrawable(circle);
        model.clearPreview();
    }
}