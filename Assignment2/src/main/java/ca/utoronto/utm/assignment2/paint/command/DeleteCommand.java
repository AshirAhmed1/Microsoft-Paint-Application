package ca.utoronto.utm.assignment2.paint.command;

import ca.utoronto.utm.assignment2.paint.Drawable;
import ca.utoronto.utm.assignment2.paint.PaintModel;

public class DeleteCommand implements Command {

    private final PaintModel model;
    private final Drawable shape;

    public DeleteCommand(PaintModel model, Drawable shape) {
        this.model = model;
        this.shape = shape;
    }

    @Override
    public void execute() {
        model.getDrawables().remove(shape);
        model.updateObservers();
    }

    @Override
    public void undo() {
        model.getDrawables().add(shape);
        model.updateObservers();
    }
}
