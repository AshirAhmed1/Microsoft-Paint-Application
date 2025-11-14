package ca.utoronto.utm.assignment2.paint.command;

import ca.utoronto.utm.assignment2.paint.Drawable;
import ca.utoronto.utm.assignment2.paint.PaintModel;

public class MoveCommand implements Command {

    private final PaintModel model;
    private final Drawable shape;
    private final double dx;
    private final double dy;

    private boolean firstExecution = true;

    public MoveCommand(PaintModel model, Drawable shape, double dx, double dy) {
        this.model = model;
        this.shape = shape;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void execute() {


        if (firstExecution) {
            firstExecution = false;
        }

        else {
            shape.translate(dx, dy);
        }

        model.updateObservers();
    }

    @Override
    public void undo() {
        shape.translate(-dx, -dy);
        model.updateObservers();
    }
}
