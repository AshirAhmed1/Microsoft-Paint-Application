package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

/**
 * Represents one freehand stroke drawn by the user.
 * Each Squiggle is an ordered list of points.
 */

public class Squiggle extends AbstractShapeDrawable{

    private final ArrayList<Point> points = new ArrayList<>();

    public Squiggle(){
        super();
    }

    /**
     * Add a new point to this squiggle.
     * @param p the point to add
     */
    public void addPoint(Point p) {
        points.add(p);
    }

    /**
     * Return all points in this squiggle.
     * @return list of points forming the squiggle
     */
    public ArrayList<Point> getPoints() {
        return points;
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
        for (int i = 0; i < points.size() - 1; i++) {
            Point a = points.get(i);
            Point b = points.get(i + 1);
            double distance = pointToSegmentDistance(p, a, b);
            if (distance <= thickness / 2) return true;
        }
        return false;
    }

    private double pointToSegmentDistance(Point p, Point a, Point b) {
        double dx = b.x - a.x;
        double dy = b.y - a.y;
        double lenSq = dx * dx + dy * dy;
        if (lenSq == 0) return Math.hypot(p.x - a.x, p.y - a.y);
        double t = ((p.x - a.x) * dx + (p.y - a.y) * dy) / lenSq;
        t = Math.max(0, Math.min(1, t));
        double projX = a.x + t * dx;
        double projY = a.y + t * dy;
        return Math.hypot(p.x - projX, p.y - projY);
    }

    @Override
    public void translate(double dx, double dy) {
        for (Point pt : points) {
            pt.x += dx;
            pt.y += dy;
        }
    }
}
