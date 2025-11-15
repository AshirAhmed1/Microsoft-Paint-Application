package ca.utoronto.utm.assignment2.paint;

import java.time.chrono.Era;

/**
 * A point used specifically for erasing operations. Each ErasePoint records
 * its position and the thickness of the eraser stroke at that moment.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class ErasePoint extends Point {

    public double thickness;

    /**
     * Constructs a new ErasePoint with the given coordinates and eraser thickness.
     *
     * @param x the x-coordinate of the erase point
     * @param y the y-coordinate of the erase point
     * @param thickness the eraser stroke thickness at this point
     */
    public ErasePoint(double x, double y, double thickness) {
        super(x, y);
        this.thickness = thickness;
    }
}
