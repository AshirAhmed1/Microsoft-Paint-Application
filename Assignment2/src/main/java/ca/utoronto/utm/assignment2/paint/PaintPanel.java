package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.util.Observer;
import java.util.Observable;
import ca.utoronto.utm.assignment2.paint.tools.*; // import tools package

/**
 * The canvas on which all shapes are drawn. This panel listens to the model
 * for updates and displays all drawables as well as any preview shape.
 * Mouse actions are delegated to the currently active Tool.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class PaintPanel extends Canvas implements Observer {

    private PaintModel model;
    private Tool currentTool;

    /**
     * Creates a new PaintPanel and attaches mouse listeners that delegate to
     * the active Tool. The panel observes the PaintModel for updates.
     *
     * @param model the PaintModel providing drawable content
     */
    public PaintPanel(PaintModel model) {
        super(300, 300);
        this.model = model;
        this.model.addObserver(this);

        this.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            if (currentTool != null) currentTool.onPress(e);
        });
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            if (currentTool != null) currentTool.onDrag(e);
        });
        this.addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
            if (currentTool != null) currentTool.onMove(e);
        });
        this.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            if (currentTool != null) currentTool.onRelease(e);
        });
    }

    /**
     * Sets the currently active Tool, which receives all mouse input.
     *
     * @param tool the tool that should handle mouse actions
     */
    public void setCurrentTool(Tool tool) {
        this.currentTool = tool;
        if (tool != null)
            System.out.println("Tool set to: " + tool.getClass().getSimpleName());
    }

    /**
     * Redraws the canvas when the model changes.
     *
     * @param o   the observable PaintModel
     * @param arg unused
     */
    @Override
    public void update(Observable o, Object arg) {
        GraphicsContext g = getGraphicsContext2D();
        g.setFill(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (Drawable d : model.getDrawables()) {
            g.setFill(d.getColor());
            g.setStroke(d.getColor());
            d.draw(g);
        }

        Drawable preview = model.getPreview();
        if (preview != null) {
            g.setFill(model.getCurrentColor());
            g.setStroke(model.getCurrentColor());
            preview.draw(g);
        }
    }

    /**
     * Forces the panel to repaint the canvas immediately.
     */
    public void refresh() {
        update(model, null);
    }
}
