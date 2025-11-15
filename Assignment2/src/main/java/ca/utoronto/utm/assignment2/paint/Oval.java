package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Represents an oval that can be drawn on the canvas. The oval is defined
 * by a top-left point along with a width and height, and supports filling,
 * outlining, hit detection, translation, and erasing.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class Oval extends AbstractShapeDrawable {
    private Point topLeft;
    private double width;
    private double height;

    /**
     * Creates a new Oval with the given top-left point, width, and height.
     *
     * @param topLeft the top-left coordinate of the oval
     * @param width the width of the oval
     * @param height the height of the oval
     */
    public Oval(Point topLeft, double width, double height){
        super();
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the top-left point of the oval.
     *
     * @return the top-left point
     */
    public Point getTopLeft() {
        return topLeft;
    }

    /**
     * Returns the width of the oval.
     *
     * @return the width
     */
    public double getWidth() { return width; }

    /**
     * Returns the height of the oval.
     *
     * @return the height
     */
    public double getHeight() { return height; }

    /**
     * Draws the oval onto the canvas using the current style settings.
     *
     * @param g the GraphicsContext used to render the oval
     */
    @Override
    public void draw(GraphicsContext g)
    {
        g.setStroke(color);
        g.setLineWidth(thickness);

        if (filled) {
            g.setFill(color);
            g.fillOval(topLeft.x, topLeft.y, width, height);
        } else {
            g.strokeOval(topLeft.x, topLeft.y, width, height);
        }
    }

    /**
     * Determines if a point lies inside the oval.
     *
     * @param p the point to check
     * @return true if the point lies within the oval
     */
    @Override
    public boolean contains(Point p) {
        double rx = width / 2.0;
        double ry = height / 2.0;
        double cx = topLeft.x + rx;
        double cy = topLeft.y + ry;
        double normalized = Math.pow((p.x - cx) / rx, 2) + Math.pow((p.y - cy) / ry, 2);
        return normalized <= 1.0;
    }

    /**
     * Moves the oval by the given horizontal and vertical offsets.
     *
     * @param dx change in x direction
     * @param dy change in y direction
     */
    @Override
    public void translate(double dx, double dy) {
        topLeft = new Point(topLeft.x + dx, topLeft.y + dy);
    }
}
