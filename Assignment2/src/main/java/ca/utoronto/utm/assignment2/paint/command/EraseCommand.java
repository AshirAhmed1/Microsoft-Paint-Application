package ca.utoronto.utm.assignment2.paint.command;

import ca.utoronto.utm.assignment2.paint.Drawable;
import ca.utoronto.utm.assignment2.paint.ErasePoint;
import ca.utoronto.utm.assignment2.paint.PaintModel;

import java.util.ArrayList;

/**
 * A command to erase a sequence of points from a Drawable shape.
 * Undo restores the points.
 */
public class EraseCommand implements Command {

    private final Drawable shape;
    private final ArrayList<ErasePoint> erasedPoints;

    public EraseCommand(Drawable shape, ArrayList<ErasePoint> erasedPoints) {
        this.shape = shape;
        this.erasedPoints = new ArrayList<>(erasedPoints);
    }

    @Override
    public void execute() {
        for (ErasePoint p : erasedPoints) {
            shape.addErasePoint(p);
        }
    }

    @Override
    public void undo() {
        // Remove these points from the shape
        shape.getErasePoints().removeAll(erasedPoints);
    }
}