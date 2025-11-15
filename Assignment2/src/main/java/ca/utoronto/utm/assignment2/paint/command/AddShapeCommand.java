package ca.utoronto.utm.assignment2.paint.command;

import ca.utoronto.utm.assignment2.paint.Drawable;
import ca.utoronto.utm.assignment2.paint.PaintModel;

/**
 * A command that adds a new shape to the canvas. Executing this command
 * inserts the given shape into the PaintModel, while undoing it removes
 * the shape from the model.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class AddShapeCommand implements Command {

    private final PaintModel model;
    private final Drawable shape;

    /**
     * Constructs a new AddShapeCommand that will add the specified shape
     * to the given PaintModel when executed.
     *
     * @param model the PaintModel to modify
     * @param shape the Drawable shape to add
     */
    public AddShapeCommand(PaintModel model, Drawable shape) {
        this.model = model;
        this.shape = shape;
    }

    /**
     * Execute the command by adding the shape to the model
     * and notifying observers.
     */
    @Override
    public void execute() {
        model.getDrawables().add(shape);
        model.updateObservers();
    }

    /**
     * Undo the command by removing the previously added shape
     * from the model and notifying observers.
     */
    @Override
    public void undo() {
        model.getDrawables().remove(shape);
        model.updateObservers();
    }
}
