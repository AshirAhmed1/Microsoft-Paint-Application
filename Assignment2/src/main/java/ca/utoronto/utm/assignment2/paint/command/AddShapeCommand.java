package ca.utoronto.utm.assignment2.paint.command;

import ca.utoronto.utm.assignment2.paint.Drawable;
import ca.utoronto.utm.assignment2.paint.PaintModel;

public class AddShapeCommand implements Command {

    private final PaintModel model;
    private final Drawable shape;

    public AddShapeCommand(PaintModel model, Drawable shape) {
        this.model = model;
        this.shape = shape;
    }

    @Override
    public void execute() {
        model.getDrawables().add(shape);
        model.updateObservers();
    }

    @Override
    public void undo() {
        model.getDrawables().remove(shape);
        model.updateObservers();
    }
}
