package ca.utoronto.utm.assignment2.paint.command;

import ca.utoronto.utm.assignment2.paint.Drawable;
import ca.utoronto.utm.assignment2.paint.PaintModel;

/**
 * A command that changes the fill style of a shape. Executing this command
 * sets the shape to either filled or unfilled, while undoing restores the
 * original fill state.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class StyleCommand implements Command {

    private final PaintModel model;
    private final Drawable shape;
    private final boolean oldFilled;
    private final boolean newFilled;

    /**
     * Constructs a StyleCommand which modifies a shape's filled/unfilled
     * style. The previous style is stored for undo operations.
     *
     * @param model the PaintModel to update after styling
     * @param shape the shape whose fill style is being changed
     * @param oldFilled the previous fill state of the shape
     * @param newFilled the new fill state to apply
     */
    public StyleCommand(PaintModel model, Drawable shape, boolean oldFilled, boolean newFilled) {
        this.model = model;
        this.shape = shape;
        this.oldFilled = oldFilled;
        this.newFilled = newFilled;
    }

    /**
     * Execute the style change by applying the new fill state and
     * updating observers.
     */
    @Override
    public void execute() {
        shape.setFilled(newFilled);
        model.updateObservers();
    }

    /**
     * Undo the style change by restoring the original fill state and
     * updating observers.
     */
    @Override
    public void undo() {
        shape.setFilled(oldFilled);
        model.updateObservers();
    }
}
