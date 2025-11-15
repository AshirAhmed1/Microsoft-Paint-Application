package ca.utoronto.utm.assignment2.paint.command;

import ca.utoronto.utm.assignment2.paint.Drawable;
import ca.utoronto.utm.assignment2.paint.PaintModel;

import java.util.ArrayList;

/**
 * A command that clears all shapes from the canvas. When executed,
 * the current list of drawables is removed, while undo restores
 * the previous canvas state from a saved backup.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class ClearCanvasCommand implements Command {

    private final PaintModel model;
    private final ArrayList<Drawable> backup;

    /**
     * Constructs a ClearCanvasCommand and stores a deep copy of all
     * shapes currently on the canvas. This backup allows the state
     * to be restored if the command is undone.
     *
     * @param model the PaintModel whose canvas will be cleared
     */
    public ClearCanvasCommand(PaintModel model) {
        this.model = model;

        // Create deep backup of current shapes for undo
        this.backup = new ArrayList<>();
        for (Drawable d : model.getDrawables()) {
            backup.add(model.copyForUndo(d));
        }
    }

    /**
     * Execute the command by clearing all shapes and the preview
     * from the model, then updating observers.
     */
    @Override
    public void execute() {
        model.getDrawables().clear();
        model.clearPreview();
        model.updateObservers();
    }

    /**
     * Undo the command by restoring all previously removed shapes
     * from the stored backup and updating observers.
     */
    @Override
    public void undo() {
        model.getDrawables().clear();
        model.getDrawables().addAll(backup);
        model.updateObservers();
    }
}
