package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Represents a triangle shape that can be drawn on the canvas. The triangle is
 * defined by a bottom-left point, a base length, and two side lengths. It
 * supports filling, outlining, hit detection, translation, and erasing.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class Triangle extends AbstractShapeDrawable {
    private Point bottom_left;
    private double base;
    private double side1; // longer side used to calculate height
    private double side2;
    private double height;

    /**
     * Constructs a new Triangle using the provided geometry values.
     *
     * @param bottom_left the bottom-left vertex of the triangle
     * @param base        the length of the triangle's base
     * @param side1       length of first side
     * @param side2       length of second side
     */
    public Triangle(Point bottom_left, double base, double side1, double side2)
    {
        super();
        this.bottom_left = bottom_left;
        this.base = base;
        this.side1 = side1;
        this.side2 = side2;
        this.height = Math.sqrt(side1 * side1 - (base / 2) * (base / 2));
    }

    /**
     * Returns the bottom-left point of the triangle.
     *
     * @return the bottom-left coordinate
     */
    public Point getbottom_left()
    {
        return this.bottom_left;
    }

    /**
     * Returns the base length of the triangle.
     *
     * @return the base length
     */
    public double getbase()
    {
        return this.base;
    }

    /**
     * Returns the length of side1.
     *
     * @return length of side1
     */
    public double getside1()
    {
        return this.side1;
    }

    /**
     * Returns the length of side2.
     *
     * @return length of side2
     */
    public double getside2()
    {
        return this.side2;
    }

    /**
     * Draws the triangle on the canvas using the current fill, color, and thickness.
     *
     * @param g the graphics context used for drawing
     */
    @Override
    public void draw(GraphicsContext g)
    {
        double[] xs = {bottom_left.x, bottom_left.x + base, bottom_left.x + base / 2.0};
        double[] ys = {bottom_left.y, bottom_left.y, bottom_left.y - height};

        g.setStroke(color);
        g.setLineWidth(thickness);

        if (filled) {
            g.setFill(color);
            g.fillPolygon(xs, ys, 3);
        } else {
            g.strokePolygon(xs, ys, 3);
        }
    }

    /**
     * Checks if a point lies within the triangle’s bounding box. This is an
     * approximation for hit detection.
     *
     * @param p the point to test
     * @return true if the point lies within the bounding box
     */
    @Override
    public boolean contains(Point p) {
        double minX = bottom_left.x;
        double maxX = bottom_left.x + base;
        double minY = bottom_left.y - height;
        double maxY = bottom_left.y;
        return p.x >= minX && p.x <= maxX && p.y >= minY && p.y <= maxY;
    }

    /**
     * Moves the triangle by the specified horizontal and vertical offsets.
     *
     * @param dx movement in the x direction
     * @param dy movement in the y direction
     */
    @Override
    public void translate(double dx, double dy) {
        bottom_left = new Point(bottom_left.x + dx, bottom_left.y + dy);
    }
}
