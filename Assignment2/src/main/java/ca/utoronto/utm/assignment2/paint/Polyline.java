package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

/**
 * Represents a polyline created by a sequence of user clicks. Each point forms
 * a connected path, allowing free-form line drawing on the canvas.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class Polyline extends AbstractShapeDrawable {

    private final ArrayList<Point> points = new ArrayList<>();

    /**
     * Creates a new empty polyline.
     */
    public Polyline() {
        super();
    }

    /**
     * Adds a new point to the polyline.
     *
     * @param p the point to add
     */
    public void addPoint(Point p) { points.add(p); }

    /**
     * Returns the list of points forming the polyline.
     *
     * @return list of points
     */
    public ArrayList<Point> getPoints() {
        return points;
    }

    /**
     * Draws the polyline by connecting consecutive points.
     *
     * @param g the GraphicsContext used to draw
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

        erase(g);
    }

    /**
     * Determines whether a point lies close enough to any segment of the polyline
     * to be considered "inside."
     *
     * @param p the point to test
     * @return true if the point is within tolerance of any segment
     */
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

    /**
     * Computes the shortest distance from a point to a line segment.
     *
     * @param p the point to evaluate
     * @param a start of the segment
     * @param b end of the segment
     * @return the shortest distance from p to segment ab
     */
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

    /**
     * Moves every point in the polyline by the given offset.
     *
     * @param dx movement in x direction
     * @param dy movement in y direction
     */
    @Override
    public void translate(double dx, double dy) {
        for (Point pt : points) {
            pt.x += dx;
            pt.y += dy;
        }

        for (ArrayList<ErasePoint> stroke : erasedStrokes) {
            for (ErasePoint p : stroke) {
                p.x += dx;
                p.y += dy;
            }
        }
    }
}
