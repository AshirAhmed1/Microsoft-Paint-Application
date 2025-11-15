package ca.utoronto.utm.assignment2.paint.command;

import ca.utoronto.utm.assignment2.paint.Drawable;
import ca.utoronto.utm.assignment2.paint.PaintModel;

/**
 * A command that removes a selected shape from the canvas and places
 * a copy of it into the clipboard. Undoing this command restores the
 * shape to the canvas and resets the clipboard to its previous value.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class CutCommand implements Command {

    private final PaintModel model;
    private final Drawable shape;
    private final Drawable oldClipboard;

    /**
     * Constructs a new CutCommand that will cut the specified shape
     * from the model and store the previous clipboard state for undo.
     *
     * @param model the PaintModel from which the shape is cut
     * @param shape the Drawable shape being removed
     */
    public CutCommand(PaintModel model, Drawable shape) {
        this.model = model;
        this.shape = shape;
        this.oldClipboard = model.getClipboard();
    }

    /**
     * Execute the cut operation: copy the shape to clipboard,
     * remove it from the canvas, and clear the selection.
     */
    @Override
    public void execute() {
        model.setClipboard(model.copyForUndo(shape));
        model.removeDrawable(shape);
        model.setSelected(null);
    }

    /**
     * Undo the cut by restoring the shape to the canvas and
     * resetting the clipboard to its previous state.
     */
    @Override
    public void undo() {
        model.addDrawable(shape);
        model.setClipboard(oldClipboard);
    }
}
