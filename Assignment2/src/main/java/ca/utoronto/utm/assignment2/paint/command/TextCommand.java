package ca.utoronto.utm.assignment2.paint.command;

import ca.utoronto.utm.assignment2.paint.Drawable;
import ca.utoronto.utm.assignment2.paint.PaintModel;

public class TextCommand implements Command {

    private final PaintModel model;
    private final Drawable textShape;

    public TextCommand(PaintModel model, Drawable textShape) {
        this.model = model;
        this.textShape = textShape;
    }

    @Override
    public void execute() {
        model.getDrawables().add(textShape);
        model.updateObservers();
    }

    @Override
    public void undo() {
        model.getDrawables().remove(textShape);
        model.updateObservers();
    }
}
