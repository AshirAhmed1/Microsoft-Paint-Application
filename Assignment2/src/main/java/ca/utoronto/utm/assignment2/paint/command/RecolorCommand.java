package ca.utoronto.utm.assignment2.paint.command;

import ca.utoronto.utm.assignment2.paint.Drawable;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.scene.paint.Color;

public class RecolorCommand implements Command {

    private final PaintModel model;
    private final Drawable shape;
    private final Color oldColor;
    private final Color newColor;

    public RecolorCommand(PaintModel model, Drawable shape, Color newColor) {
        this.model = model;
        this.shape = shape;
        this.oldColor = shape.getColor();
        this.newColor = newColor;
    }

    @Override
    public void execute() {
        shape.setColor(newColor);
        model.updateObservers();
    }

    @Override
    public void undo() {
        shape.setColor(oldColor);
        model.updateObservers();
    }
}
