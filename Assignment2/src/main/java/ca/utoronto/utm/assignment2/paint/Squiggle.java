package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

/**
 * Represents a freehand drawing stroke made by the user. A Squiggle is an
 * ordered list of points connected by straight segments, supporting drawing,
 * hit detection, translation, and erasing operations.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class Squiggle extends AbstractShapeDrawable{

    private final ArrayList<Point> points = new ArrayList<>();

    /**
     * Constructs a new empty squiggle.
     */
    public Squiggle(){
        super();
    }

    /**
     * Adds a point to the squiggle’s list of vertices.
     *
     * @param p the point to add
     */
    public void addPoint(Point p) {
        points.add(p);
    }

    /**
     * Returns all points that form this squiggle.
     *
     * @return the ordered list of points
     */
    public ArrayList<Point> getPoints() {
        return points;
    }

    /**
     * Draws the squiggle by connecting each pair of consecutive points.
     *
     * @param g the graphics context used to draw on the canvas
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

        // Ensure erased segments remain erased after movement
        erase(g);
    }

    /**
     * Determines whether the given point is close enough to any segment of
     * the squiggle to be considered "contained" within it.
     *
     * @param p the point to test
     * @return true if the point lies within tolerance of the squiggle, false otherwise
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
     * Computes the shortest distance from point p to the segment ab.
     *
     * @param p the test point
     * @param a segment start
     * @param b segment end
     * @return the perpendicular or endpoint distance
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
     * Moves the squiggle by the given horizontal and vertical offsets.
     * All stored erase points are translated along with the shape.
     *
     * @param dx horizontal translation
     * @param dy vertical translation
     */
    @Override
    public void translate(double dx, double dy) {
        for (Point pt : points) {
            pt.x += dx;
            pt.y += dy;
        }
        for (ArrayList<ErasePoint> stroke: erasedStrokes) {
            for (ErasePoint p: stroke) {
                p.x += dx;
                p.y += dy;
            }
        }
    }
}
