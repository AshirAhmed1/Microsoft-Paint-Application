package ca.utoronto.utm.assignment2.paint.command;

import ca.utoronto.utm.assignment2.paint.Drawable;
import ca.utoronto.utm.assignment2.paint.PaintModel;

/**
 * A command that permanently removes a shape from the canvas.
 * Executing this command deletes the given shape, while undoing it
 * restores the shape back onto the canvas.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class DeleteCommand implements Command {

    private final PaintModel model;
    private final Drawable shape;

    /**
     * Constructs a new DeleteCommand that removes the specified shape
     * from the provided PaintModel when executed.
     *
     * @param model the PaintModel from which the shape will be removed
     * @param shape the Drawable shape to delete
     */
    public DeleteCommand(PaintModel model, Drawable shape) {
        this.model = model;
        this.shape = shape;
    }

    /**
     * Execute the delete operation by removing the shape from the model
     * and notifying observers.
     */
    @Override
    public void execute() {
        model.getDrawables().remove(shape);
        model.updateObservers();
    }

    /**
     * Undo the delete by adding the shape back into the model
     * and notifying observers.
     */
    @Override
    public void undo() {
        model.getDrawables().add(shape);
        model.updateObservers();
    }
}
