package ca.utoronto.utm.assignment2.paint.command;

import ca.utoronto.utm.assignment2.paint.Drawable;
import ca.utoronto.utm.assignment2.paint.PaintModel;

/**
 * A command that pastes a previously copied or cut shape onto the canvas.
 * Executing this command adds the pasted shape, while undoing it removes
 * the shape from the model.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class PasteCommand implements Command {

    private final PaintModel model;
    private final Drawable pasted;

    /**
     * Constructs a new PasteCommand that will insert the given shape
     * into the specified PaintModel when executed.
     *
     * @param model the PaintModel to modify
     * @param pasted the shape to be added to the canvas
     */
    public PasteCommand(PaintModel model, Drawable pasted) {
        this.model = model;
        this.pasted = pasted;
    }

    /**
     * Execute the paste operation by adding the shape to the model
     * and notifying observers.
     */
    @Override
    public void execute() {
        model.getDrawables().add(pasted);
        model.updateObservers();
    }

    /**
     * Undo the paste by removing the shape from the model
     * and notifying observers.
     */
    @Override
    public void undo() {
        model.getDrawables().remove(pasted);
        model.updateObservers();
    }
}
