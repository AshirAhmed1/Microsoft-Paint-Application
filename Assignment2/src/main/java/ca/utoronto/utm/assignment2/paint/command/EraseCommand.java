package ca.utoronto.utm.assignment2.paint.command;

import ca.utoronto.utm.assignment2.paint.Drawable;
import ca.utoronto.utm.assignment2.paint.ErasePoint;
import ca.utoronto.utm.assignment2.paint.PaintModel;

import java.util.ArrayList;

/**
 * A command that applies an erasing action to a shape by adding a sequence
 * of erase points. Executing this command erases the specified points,
 * while undoing it restores the erased portions of the shape.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class EraseCommand implements Command {

    private final Drawable shape;
    private final ArrayList<ErasePoint> erasedPoints;

    /**
     * Constructs a new EraseCommand which records the list of erase points
     * applied to the given shape. A defensive copy of the erase points is stored
     * to ensure proper undo behavior.
     *
     * @param shape the Drawable shape being erased
     * @param erasedPoints the list of erase points applied during the erase action
     */
    public EraseCommand(Drawable shape, ArrayList<ErasePoint> erasedPoints) {
        this.shape = shape;
        this.erasedPoints = new ArrayList<>(erasedPoints);
    }

    /**
     * Execute the erase operation by adding all recorded erase points
     * to the shape.
     */
    @Override
    public void execute() {
        for (ErasePoint p : erasedPoints) {
            shape.addErasePoint(p);
        }
    }

    /**
     * Undo the erase operation by removing all previously applied
     * erase points from the shape.
     */
    @Override
    public void undo() {
        shape.getErasePoints().removeAll(erasedPoints);
    }
}
