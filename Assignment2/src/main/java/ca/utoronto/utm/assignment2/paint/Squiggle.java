package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;

/**
 * Represents one freehand stroke drawn by the user.
 * Each Squiggle is an ordered list of points.
 */

public class Squiggle {

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
}
