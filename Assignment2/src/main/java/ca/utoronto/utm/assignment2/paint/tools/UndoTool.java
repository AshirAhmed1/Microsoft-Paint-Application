package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.PaintPanel;
import javafx.scene.input.MouseEvent;

public class UndoTool extends AbstractShapeTool {

    public UndoTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    @Override
    public void onPress(MouseEvent e) {
        // Just undo immediately when the button is active and user clicks canvas
        model.undo();
    }

    @Override
    public void onDrag(MouseEvent e) { }

    @Override
    public void onMove(MouseEvent e) { }

    @Override
    public void onRelease(MouseEvent e) { }

    @Override
    protected void drawPreview(double x, double y) { }

    @Override
    protected void commit(double x, double y) { }
}