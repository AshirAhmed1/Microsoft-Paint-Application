package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.*;
import ca.utoronto.utm.assignment2.paint.command.AddShapeCommand;
import javafx.scene.input.MouseEvent;

public class SquiggleTool extends AbstractShapeTool {
    private Squiggle current;

    public SquiggleTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    @Override
    public void onPress(MouseEvent e) {
        current = new Squiggle();
        current.setColor(model.getCurrentColor());
        current.setThickness(model.getCurrentThickness());
        current.addPoint(new Point(e.getX(), e.getY()));
        model.setPreview(current);
    }

    @Override
    public void onDrag(MouseEvent e) {
        if (current != null) {
            current.addPoint(new Point(e.getX(), e.getY()));
            model.setPreview(current);
        }
    }

    @Override
    public void onRelease(MouseEvent e) {
        if (current != null) {
            model.executeCommand(new AddShapeCommand(model, current));
            model.clearPreview();
            current = null;
        }
    }

    @Override protected void drawPreview(double x, double y) {}
    @Override protected void commit(double x, double y) {}
}