package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.*;

public class TriangleTool extends AbstractShapeTool {
    public TriangleTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    private Triangle makeTriangle(double x, double y) {
        double base = Math.abs(x - x0);
        double height = Math.abs(y - y0);
        double side = Math.sqrt(height * height + (base / 2.0) * (base / 2.0));
        return new Triangle(new Point(x0, y0), base, side, side);
    }

    @Override
    protected void drawPreview(double x, double y) {
        model.setPreview(makeTriangle(x, y));
    }

    @Override
    protected void commit(double x, double y) {
        model.addTriangle(makeTriangle(x, y));
        model.clearPreview();
    }
}