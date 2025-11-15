package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.PaintPanel;
import ca.utoronto.utm.assignment2.paint.Point;
import ca.utoronto.utm.assignment2.paint.Text;
import ca.utoronto.utm.assignment2.paint.command.TextCommand;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

/**
 * A tool for adding text onto the canvas by clicking on a specific point.
 * The user will be prompt to enter the text which will be added to the canvas.
 *
 * @author arnold/Ashir/Alex/Ahmed/Abdullah
 */
public class TextTool extends AbstractShapeTool {

    /**
     * Constructs a TextTool with references to the model and panel.
     *
     * @param model the PaintModel storing shapes and selection
     * @param view the PaintPanel where shapes are drawn
     */
    public TextTool(PaintModel model, PaintPanel view) {
        super(model, view);
    }

    /**
     * Selects a point on the canvas in which the user is prompted with a textbox
     * in which the following inputed text will be added to the canvas.
     * @param e the current mouse press
     */
    @Override
    public void onPress(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();

        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Text");
        dialog.setHeaderText("Enter text:");
        dialog.setContentText("Text:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent() && !result.get().isEmpty()) {
            Text t = new Text(new Point(x, y), result.get());
            t.setColor(model.getCurrentColor());
            t.setThickness(model.getCurrentThickness());
            t.setFilled(false);

            model.executeCommand(new TextCommand(model, t));
        }
    }

    @Override
    public void onDrag(MouseEvent e) {
        // no dragging behaviour for text
    }

    @Override
    public void onMove(MouseEvent e) {
        // no preview for now
    }

    @Override
    public void onRelease(MouseEvent e) {
        // nothing to do
    }

    @Override
    protected void drawPreview(double x, double y) {
        // no preview drawing
    }

    @Override
    protected void commit(double x, double y) {
        // not used in this tool
    }
}