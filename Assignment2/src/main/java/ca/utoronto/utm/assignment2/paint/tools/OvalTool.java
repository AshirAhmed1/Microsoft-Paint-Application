package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.*;
import ca.utoronto.utm.assignment2.paint.command.AddShapeCommand;

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
        Oval preview = makeOval(x, y);
        preview.setColor(model.getCurrentColor());
        preview.setThickness(model.getCurrentThickness());
        preview.setFilled(model.isFillMode());
        model.setPreview(preview);
    }

    @Override
    protected void commit(double x, double y) {
        Oval oval = makeOval(x, y);
        oval.setColor(model.getCurrentColor());
        oval.setThickness(model.getCurrentThickness());
        oval.setFilled(model.isFillMode());
        model.executeCommand(new AddShapeCommand(model, oval));
        model.clearPreview();
    }
}