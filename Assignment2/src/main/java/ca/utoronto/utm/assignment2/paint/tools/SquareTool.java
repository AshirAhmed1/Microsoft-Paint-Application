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

        double topLeftX = x0;
        double topLeftY = y0;

        if (dx < 0)
        {
            topLeftX = x0 - side;
        }

        if (dy < 0)
        {
            topLeftY = y0 - side;
        }

        Point topLeft = new Point(topLeftX, topLeftY);

        return new Square(topLeft, side);
    }

    @Override
    protected void drawPreview(double x, double y) {
        model.setPreview(makeSquare(x, y));
    }

    @Override
    protected void commit(double x, double y) {
        Square square = makeSquare(x, y);
        square.setColor(model.getCurrentColor());
        model.addDrawable(square);
        model.clearPreview();
    }
}