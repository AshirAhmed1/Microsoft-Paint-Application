package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Represents a square that can be drawn on the canvas. A square is a special
 * type of rectangle where the width and height remain equal at all times.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class Square extends Rectangle{

    /**
     * Constructs a new square with the given top-left coordinate and side length.
     *
     * @param topLeft     the top-left corner of the square
     * @param sideLength  the length of all sides
     */
    public Square(Point topLeft, double sideLength)
    {
        super(topLeft, sideLength, sideLength);
    }

    /**
     * Returns the side length of the square.
     *
     * @return the side length
     */
    public double getSideLength()
    {
        return super.getWidth();
    }

    /**
     * Sets the width of the square. Since a square must have equal
     * width and height, both dimensions are updated.
     *
     * @param width the new side length
     */
    @Override
    public void setWidth(double width)
    {
        super.setWidth(width);
        super.setHeight(width);
    }

    /**
     * Sets the height of the square. Since a square must have equal
     * width and height, both dimensions are updated.
     *
     * @param height the new side length
     */
    @Override
    public void setHeight(double height)
    {
        super.setWidth(height);
        super.setHeight(height);
    }

    /**
     * Draws the square on the canvas using the current color, fill mode,
     * thickness, and erase strokes.
     *
     * @param g the graphics context used for drawing
     */
    @Override
    public void draw(GraphicsContext g)
    {
        g.setStroke(getColor());
        g.setLineWidth(getThickness());

        if (filled) {
            g.setFill(getColor());
            g.fillRect(getTop_left().x, getTop_left().y, getSideLength(), getSideLength());
        } else {
            g.strokeRect(getTop_left().x, getTop_left().y, getSideLength(), getSideLength());
        }

        g.setFill(Color.WHITE);
        erase(g);
    }

    /**
     * Determines whether the given point lies inside the square.
     *
     * @param p the point to test
     * @return true if the point lies inside the square, false otherwise
     */
    @Override
    public boolean contains(Point p) {
        Point tl = getTop_left();
        double s = getSideLength();
        return p.x >= tl.x && p.x <= tl.x + s &&
                p.y >= tl.y && p.y <= tl.y + s;
    }

    /**
     * Moves the square by the given horizontal and vertical offsets.
     * All stored erase paths are also translated so erased regions remain aligned.
     *
     * @param dx the horizontal movement
     * @param dy the vertical movement
     */
    @Override
    public void translate(double dx, double dy) {
        Point tl = getTop_left();
        setTop_left(new Point(tl.x + dx, tl.y + dy));

        for (ArrayList<ErasePoint> stroke: erasedStrokes) {
            for (ErasePoint p: stroke) {
                p.x += dx;
                p.y += dy;
            }
        }
    }
}
