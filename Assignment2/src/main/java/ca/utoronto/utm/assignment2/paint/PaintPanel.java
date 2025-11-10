package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.util.Observer;
import java.util.Observable;
import ca.utoronto.utm.assignment2.paint.tools.*; // import tools package

public class PaintPanel extends Canvas implements Observer {

    private PaintModel model;
    private Tool currentTool; // New Strategy delegate

    public PaintPanel(PaintModel model) {
        super(300, 300);
        this.model = model;
        this.model.addObserver(this);

        // 🧩 Delegate all mouse events to the current Tool
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
     * Called by View to update which Tool (Controller) is active.
     */
    public void setCurrentTool(Tool tool) {
        this.currentTool = tool;
        if (tool != null)
            System.out.println("Tool set to: " + tool.getClass().getSimpleName());
    }

    @Override
    public void update(Observable o, Object arg) {
        GraphicsContext g = getGraphicsContext2D();
        g.clearRect(0, 0, getWidth(), getHeight());

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
}
