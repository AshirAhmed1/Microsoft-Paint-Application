package ca.utoronto.utm.assignment2.paint.command;

import ca.utoronto.utm.assignment2.paint.Drawable;
import ca.utoronto.utm.assignment2.paint.PaintModel;

public class CutCommand implements Command {

    private final PaintModel model;
    private final Drawable shape;
    private final Drawable oldClipboard;

    public CutCommand(PaintModel model, Drawable shape) {
        this.model = model;
        this.shape = shape;
        this.oldClipboard = model.getClipboard();
    }

    @Override
    public void execute() {
        model.setClipboard(model.copyForUndo(shape));
        model.removeDrawable(shape);
        model.setSelected(null);
    }

    @Override
    public void undo() {
        model.addDrawable(shape);
        model.setClipboard(oldClipboard);
    }
}
