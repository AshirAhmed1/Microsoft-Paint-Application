package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.*;

public class SquareTool extends AbstractShapeTool {
    public SquareTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    private Square makeSquare(double x, double y) {
        double dx = x - x0;
        double dy = y - y0;
        double side = Math.max(Math.abs(dx), Math.abs(dy));
        return new Square(new Point(x0, y0), side);
    }

    @Override
    protected void drawPreview(double x, double y) {
        model.setPreview(makeSquare(x, y));
    }

    @Override
    protected void commit(double x, double y) {
        model.addDrawable(makeSquare(x, y));
        model.clearPreview();
    }
}