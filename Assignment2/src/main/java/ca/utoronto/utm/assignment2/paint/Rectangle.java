package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Represents a rectangle shape that can be drawn on the canvas. The rectangle
 * is defined by its top-left point, width, and height, and supports filling,
 * outlining, hit detection, translation, and erasing.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class Rectangle extends AbstractShapeDrawable {
    private Point top_left;
    private double width;
    private double height;

    /**
     * Creates a new rectangle with the specified top-left point, width, and height.
     *
     * @param top_left the top-left coordinate
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(Point top_left, double width, double height)
    {
        super();
        this.top_left = top_left;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the top-left point of the rectangle.
     *
     * @return the top-left coordinate
     */
    public Point getTop_left()
    {
        return this.top_left;
    }

    /**
     * Sets the top-left corner of the rectangle.
     *
     * @param top_left the new top-left point
     */
    public void setTop_left(Point top_left)
    {
        this.top_left = top_left;
    }

    /**
     * Sets the width of the rectangle.
     *
     * @param width the new width
     */
    public void setWidth(double width)
    {
        this.width = width;
    }

    /**
     * Sets the height of the rectangle.
     *
     * @param height the new height
     */
    public void setHeight(double height)
    {
        this.height = height;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return width
     */
    public double getWidth()
    {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return height
     */
    public double getHeight()
    {
        return this.height;
    }

    /**
     * Draws the rectangle on the canvas using the shape's style settings.
     *
     * @param g the GraphicsContext used to draw
     */
    @Override
    public void draw(GraphicsContext g)
    {
        g.setStroke(color);
        g.setLineWidth(thickness);
        if (filled) {
            g.setFill(color);
            g.fillRect(top_left.x, top_left.y, width, height);
        }
        else {
            g.strokeRect(top_left.x, top_left.y, width, height);
        }

        g.setFill(Color.WHITE);
        erase(g);
    }

    /**
     * Checks whether a point lies inside the rectangle.
     *
     * @param p the point to test
     * @return true if the point is inside the rectangle
     */
    @Override
    public boolean contains(Point p) {
        return p.x >= top_left.x && p.x <= top_left.x + width &&
                p.y >= top_left.y && p.y <= top_left.y + height;
    }

    /**
     * Moves the rectangle by the given offsets.
     *
     * @param dx movement in x direction
     * @param dy movement in y direction
     */
    @Override
    public void translate(double dx, double dy) {
        top_left = new Point(top_left.x + dx, top_left.y + dy);

        for (ArrayList<ErasePoint> stroke : erasedStrokes) {
            for (ErasePoint p : stroke) {
                p.x += dx;
                p.y += dy;
            }
        }
    }
}
