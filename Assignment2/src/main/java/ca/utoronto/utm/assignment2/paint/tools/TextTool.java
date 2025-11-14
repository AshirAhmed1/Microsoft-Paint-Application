package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.PaintPanel;
import ca.utoronto.utm.assignment2.paint.Point;
import ca.utoronto.utm.assignment2.paint.Text;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

/**
 * Tool that lets the user place text on the canvas.
 */
public class TextTool extends AbstractShapeTool {

    public TextTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    @Override
    public void onPress(MouseEvent e) {
        // Ask the user for the text to place
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Text");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter text:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String s = result.get().trim();
            if (!s.isEmpty()) {
                Point pos = new Point(e.getX(), e.getY());
                // Choose a reasonable default font size
                double fontSize = 18;

                Text ts = new Text(pos, s, fontSize);
                ts.setColor(model.getCurrentColor());
                ts.setThickness(model.getCurrentThickness());
                ts.setFilled(model.isFillMode());

                model.addDrawable(ts);
            }
        }
    }

    @Override
    public void onDrag(MouseEvent e) {
        // No dragging behaviour for this simple text tool
    }

    @Override
    public void onMove(MouseEvent e) {
        // Not needed
    }

    @Override
    public void onRelease(MouseEvent e) {
        // Nothing special here
    }

    @Override
    protected void drawPreview(double x, double y) {
        // No preview for text in this basic version
    }

    @Override
    protected void commit(double x, double y) {
        // Not used
    }
}
