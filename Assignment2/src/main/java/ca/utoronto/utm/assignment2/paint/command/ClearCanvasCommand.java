package ca.utoronto.utm.assignment2.paint.command;

import ca.utoronto.utm.assignment2.paint.Drawable;
import ca.utoronto.utm.assignment2.paint.PaintModel;

import java.util.ArrayList;

public class ClearCanvasCommand implements Command {

    private final PaintModel model;
    private final ArrayList<Drawable> backup;

    public ClearCanvasCommand(PaintModel model) {
        this.model = model;

        // make deep copy of all shapes
        this.backup = new ArrayList<>();
        for (Drawable d : model.getDrawables()) {
            backup.add(model.copyForUndo(d)); // we add this helper below
        }
    }

    @Override
    public void execute() {
        model.getDrawables().clear();
        model.clearPreview();
        model.updateObservers();
    }

    @Override
    public void undo() {
        model.getDrawables().clear();
        model.getDrawables().addAll(backup);
        model.updateObservers();
    }
}
