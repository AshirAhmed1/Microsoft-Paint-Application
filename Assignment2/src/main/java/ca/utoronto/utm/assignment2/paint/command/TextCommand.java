package ca.utoronto.utm.assignment2.paint.command;

import ca.utoronto.utm.assignment2.paint.Drawable;
import ca.utoronto.utm.assignment2.paint.PaintModel;

/**
 * A command that adds a text drawable to the canvas. Executing this
 * command places the text onto the PaintModel, while undoing it removes
 * the text shape.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class TextCommand implements Command {

    private final PaintModel model;
    private final Drawable textShape;

    /**
     * Constructs a new TextCommand that will insert the provided text shape
     * into the PaintModel when executed.
     *
     * @param model the PaintModel to modify
     * @param textShape the text drawable to add to the canvas
     */
    public TextCommand(PaintModel model, Drawable textShape) {
        this.model = model;
        this.textShape = textShape;
    }

    /**
     * Execute the add-text operation by adding the text shape to the model
     * and notifying observers.
     */
    @Override
    public void execute() {
        model.getDrawables().add(textShape);
        model.updateObservers();
    }

    /**
     * Undo the add-text action by removing the text shape from the model
     * and notifying observers.
     */
    @Override
    public void undo() {
        model.getDrawables().remove(textShape);
        model.updateObservers();
    }
}
