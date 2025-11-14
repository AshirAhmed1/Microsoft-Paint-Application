package ca.utoronto.utm.assignment2.paint.command;

import ca.utoronto.utm.assignment2.paint.Drawable;
import ca.utoronto.utm.assignment2.paint.PaintModel;

public class StyleCommand implements Command {

    private final PaintModel model;
    private final Drawable shape;
    private final boolean oldFilled;
    private final boolean newFilled;

    public StyleCommand(PaintModel model, Drawable shape, boolean oldFilled, boolean newFilled) {
        this.model = model;
        this.shape = shape;
        this.oldFilled = oldFilled;
        this.newFilled = newFilled;
    }

    @Override
    public void execute() {
        shape.setFilled(newFilled);
        model.updateObservers();
    }

    @Override
    public void undo() {
        shape.setFilled(oldFilled);
        model.updateObservers();
    }
}
