package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.*;
import ca.utoronto.utm.assignment2.paint.command.AddShapeCommand;

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

        Circle preview = makeCircle(x, y);
        preview.setColor(model.getCurrentColor());
        preview.setThickness(model.getCurrentThickness());
        preview.setFilled(model.isFillMode());
        model.setPreview(preview);

    }

    @Override
    protected void commit(double x, double y) {
        Circle circle = makeCircle(x, y);
        circle.setColor(model.getCurrentColor());
        circle.setThickness(model.getCurrentThickness());
        circle.setFilled(model.isFillMode());
        model.executeCommand(new AddShapeCommand(model, circle));
        model.clearPreview();
    }
}