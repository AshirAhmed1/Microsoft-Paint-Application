package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
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

    /**
     * Observer update — responsible for re-drawing model contents and preview.
     */
    @Override
    public void update(Observable o, Object arg) {
        GraphicsContext g2d = this.getGraphicsContext2D();
        g2d.clearRect(0, 0, this.getWidth(), this.getHeight());

        // Draw all finalized shapes
        g2d.setStroke(Color.RED);
        for (Squiggle s : this.model.getSquiggles()) {
            ArrayList<Point> pts = s.getPoints();
            for (int i = 0; i < pts.size() - 1; i++) {
                Point p1 = pts.get(i);
                Point p2 = pts.get(i + 1);
                g2d.strokeLine(p1.x, p1.y, p2.x, p2.y);
            }
        }

        g2d.setFill(Color.GREEN);
        for (Circle c : this.model.getCircles()) {
            double r = c.getRadius();
            double x = c.getCentre().x - r;
            double y = c.getCentre().y - r;
            g2d.fillOval(x, y, r * 2, r * 2);
        }

        for (Rectangle r : this.model.getRectangles()) {
            g2d.fillRect(r.getTop_left().x, r.getTop_left().y, r.getWidth(), r.getHeight());
        }

        for (Square s : this.model.getSquares()) {
            g2d.fillRect(s.getTop_left().x, s.getTop_left().y, s.getSideLength(), s.getSideLength());
        }

        for (Oval ov : this.model.getOvals()) {
            g2d.fillOval(ov.getTopLeft().x, ov.getTopLeft().y, ov.getWidth(), ov.getHeight());
        }

        for (Triangle t : this.model.getTriangles()) {
            Point bl = t.getbottom_left();
            double w = t.getbase();
            double h = t.getHeight();
            double[] xs = {bl.x, bl.x + w, bl.x + w / 2.0};
            double[] ys = {bl.y, bl.y, bl.y - h};
            g2d.fillPolygon(xs, ys, 3);
        }

        // Draw live preview if available
        Object preview = model.getPreview();
        if (preview != null) {
            g2d.setFill(Color.LIGHTGREEN);
            if (preview instanceof Circle) {
                Circle c = (Circle) preview;
                double r = c.getRadius();
                double x = c.getCentre().x - r;
                double y = c.getCentre().y - r;
                g2d.fillOval(x, y, r * 2, r * 2);
            } else if (preview instanceof Rectangle) {
                Rectangle r = (Rectangle) preview;
                g2d.fillRect(r.getTop_left().x, r.getTop_left().y, r.getWidth(), r.getHeight());
            } else if (preview instanceof Square) {
                Square s = (Square) preview;
                g2d.fillRect(s.getTop_left().x, s.getTop_left().y, s.getSideLength(), s.getSideLength());
            } else if (preview instanceof Oval) {
                Oval ov = (Oval) preview;
                g2d.fillOval(ov.getTopLeft().x, ov.getTopLeft().y, ov.getWidth(), ov.getHeight());
            } else if (preview instanceof Triangle) {
                Triangle t = (Triangle) preview;
                Point bl = t.getbottom_left();
                double w = t.getbase();
                double h = t.getHeight();
                double[] xs = {bl.x, bl.x + w, bl.x + w / 2.0};
                double[] ys = {bl.y, bl.y, bl.y - h};
                g2d.fillPolygon(xs, ys, 3);
            } else if (preview instanceof Squiggle) {
                Squiggle s = (Squiggle) preview;
                ArrayList<Point> pts = s.getPoints();
                g2d.setStroke(Color.LIGHTGREEN);
                for (int i = 0; i < pts.size() - 1; i++) {
                    Point p1 = pts.get(i);
                    Point p2 = pts.get(i + 1);
                    g2d.strokeLine(p1.x, p1.y, p2.x, p2.y);
                }
            }
        }
    }
}
