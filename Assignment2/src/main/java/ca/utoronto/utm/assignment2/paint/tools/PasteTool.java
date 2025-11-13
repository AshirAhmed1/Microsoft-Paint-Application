package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.PaintPanel;
import javafx.scene.input.MouseEvent;

public class PasteTool extends AbstractShapeTool {

    public PasteTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    @Override
    public void onPress(MouseEvent e) {
        model.pasteAt(e.getX(), e.getY());
    }

    @Override
    public void onDrag(MouseEvent e) {}

    @Override
    public void onRelease(MouseEvent e) {}
    @Override
    protected void drawPreview(double x, double y) {}

    @Override
    protected void commit(double x, double y) {}
}