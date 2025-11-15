package ca.utoronto.utm.assignment2.paint.command;

import ca.utoronto.utm.assignment2.paint.Drawable;
import ca.utoronto.utm.assignment2.paint.PaintModel;

/**
 * A command that moves a selected shape by a specified horizontal and vertical
 * offset. The first execution is ignored to avoid double-translating the shape,
 * while undo reverses the translation.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class MoveCommand implements Command {

    private final PaintModel model;
    private final Drawable shape;
    private final double dx;
    private final double dy;

    private boolean firstExecution = true;

    /**
     * Constructs a new MoveCommand that will translate the given shape
     * by (dx, dy) when executed.
     *
     * @param model the PaintModel to update after movement
     * @param shape the shape being moved
     * @param dx the horizontal translation amount
     * @param dy the vertical translation amount
     */
    public MoveCommand(PaintModel model, Drawable shape, double dx, double dy) {
        this.model = model;
        this.shape = shape;
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Execute the movement operation. The first call performs no translation
     * because the shape has already been dragged interactively; subsequent
     * calls apply the movement offset.
     */
    @Override
    public void execute() {

        if (firstExecution) {
            firstExecution = false;
        } else {
            shape.translate(dx, dy);
        }

        model.updateObservers();
    }

    /**
     * Undo the movement by translating the shape back by (-dx, -dy).
     */
    @Override
    public void undo() {
        shape.translate(-dx, -dy);
        model.updateObservers();
    }
}
