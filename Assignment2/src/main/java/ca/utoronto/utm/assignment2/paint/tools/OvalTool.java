package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.*;

public class OvalTool extends AbstractShapeTool {
    public OvalTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    private Oval makeOval(double x, double y) {
        double w = x - x0;
        double h = y - y0;
        return new Oval(new Point(x0, y0), w, h);
    }

    @Override
    protected void drawPreview(double x, double y) {
        model.setPreview(makeOval(x, y));
    }

    @Override
    protected void commit(double x, double y) {
        model.addOval(makeOval(x, y));
        model.clearPreview();
    }
}