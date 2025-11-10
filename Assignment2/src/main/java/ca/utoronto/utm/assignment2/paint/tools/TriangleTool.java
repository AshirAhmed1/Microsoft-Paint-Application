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

        double minX = Math.min(x0, x);
        double maxY = Math.max(y0, y);

        return new Triangle(new Point(minX, maxY), base, side, side);
    }

    @Override
    protected void drawPreview(double x, double y) {

        Triangle preview = makeTriangle(x, y);
        preview.setColor(model.getCurrentColor());
        preview.setThickness(model.getCurrentThickness());
        preview.setFilled(model.isFillMode());
        model.setPreview(preview);
    }

    @Override
    protected void commit(double x, double y) {
        Triangle triangle = makeTriangle(x, y);
        triangle.setColor(model.getCurrentColor());
        triangle.setThickness(model.getCurrentThickness());
        triangle.setFilled(model.isFillMode());
        model.addDrawable(triangle);
        model.clearPreview();
    }
}