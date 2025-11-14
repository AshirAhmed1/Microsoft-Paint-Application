package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;

/**
 * Represents an eraser which can "erase" parts of the drawing by clicking and dragging the ouse
 * over the canvas.
 */
public class Eraser extends AbstractShapeDrawable {

    private final ArrayList<Point> points = new ArrayList<>();
    private Color color = Color.WHITE;

    /**
     * Constructs a new eraser.
     */
    public Eraser() { super(); }

    /**
     * Adds a point to the eraser's path which connect to eachother.
     * @param p
     */
    public void addPoint(Point p) {
        points.add(p);
    }

    /**
     * Erases anything on the canvas on the path of the points given.
     *
     * @param g which is the current canvas.
     */
    @Override
    public void draw(GraphicsContext g) {
        if (points.size() < 2) return;

        g.setStroke(color);
        g.setLineWidth(thickness);

        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            g.strokeLine(p1.x, p1.y, p2.x, p2.y);
        }
    }


    @Override
    public boolean contains(Point p) {
        return false;
    }

    @Override
    public void translate(double dx, double dy) {    }
}
