package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;

/**
 *
 */
public class Eraser extends AbstractShapeDrawable {

    private final ArrayList<Point> points = new ArrayList<>();
    private Color color = Color.WHITE;

    public Eraser() { super(); }

    public void addPoint(Point p) {
        points.add(p);
    }

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
