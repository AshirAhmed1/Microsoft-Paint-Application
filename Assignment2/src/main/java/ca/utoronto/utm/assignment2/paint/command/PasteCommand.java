package ca.utoronto.utm.assignment2.paint.command;

import ca.utoronto.utm.assignment2.paint.Drawable;
import ca.utoronto.utm.assignment2.paint.PaintModel;

public class PasteCommand implements Command {

    private final PaintModel model;
    private final Drawable pasted;

    public PasteCommand(PaintModel model, Drawable pasted) {
        this.model = model;
        this.pasted = pasted;
    }

    @Override
    public void execute() {
        model.getDrawables().add(pasted);
        model.updateObservers();
    }

    @Override
    public void undo() {
        model.getDrawables().remove(pasted);
        model.updateObservers();
    }
}
