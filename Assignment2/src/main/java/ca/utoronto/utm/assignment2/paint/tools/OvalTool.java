package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.*;

public class OvalTool extends AbstractShapeTool {
    public OvalTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    private Oval makeOval(double x, double y) {
        // top left is always the leftmost position
        double cornerX = Math.min(x, x0);
        double cornerY = Math.min(y, y0);
        double w = Math.abs(x - x0);
        double h = Math.abs(y - y0);
        return new Oval(new Point(cornerX, cornerY), w, h);
    }

    @Override
    protected void drawPreview(double x, double y) {
        model.setPreview(makeOval(x, y));
    }

    @Override
    protected void commit(double x, double y) {
        Oval oval = makeOval(x, y);
        oval.setColor(model.getCurrentColor());
        model.addDrawable(oval);
        model.clearPreview();
    }
}