package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

/**
 * Represents one freehand stroke drawn by the user.
 * Each Squiggle is an ordered list of points.
 */

public class Squiggle implements Drawable {

    private final ArrayList<Point> points = new ArrayList<>();

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

        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            g.strokeLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

}
