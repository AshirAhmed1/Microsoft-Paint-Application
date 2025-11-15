package ca.utoronto.utm.assignment2.paint.command;

import ca.utoronto.utm.assignment2.paint.Drawable;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.scene.paint.Color;

/**
 * A command that changes the color of a shape. Executing this command
 * applies a new color to the shape, while undoing it restores the
 * original color.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class RecolorCommand implements Command {

    private final PaintModel model;
    private final Drawable shape;
    private final Color oldColor;
    private final Color newColor;

    /**
     * Constructs a new RecolorCommand that will recolor the given shape.
     * The previous color is stored so that the action can be undone.
     *
     * @param model the PaintModel to update after recoloring
     * @param shape the drawable shape whose color will be changed
     * @param newColor the new color to apply to the shape
     */
    public RecolorCommand(PaintModel model, Drawable shape, Color newColor) {
        this.model = model;
        this.shape = shape;
        this.oldColor = shape.getColor();
        this.newColor = newColor;
    }

    /**
     * Execute the recolor operation by setting the shape's color to
     * the new value and updating observers.
     */
    @Override
    public void execute() {
        shape.setColor(newColor);
        model.updateObservers();
    }

    /**
     * Undo the recolor operation by restoring the original color and
     * notifying observers.
     */
    @Override
    public void undo() {
        shape.setColor(oldColor);
        model.updateObservers();
    }
}
