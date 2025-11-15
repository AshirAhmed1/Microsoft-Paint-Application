package ca.utoronto.utm.assignment2.paint;

/**
 * A basic point representing an (x, y) coordinate used throughout the paint
 * application for positioning shapes and tracking mouse locations.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class Point {
    public double x, y;

    /**
     * Creates a new point with the given coordinates.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
}
