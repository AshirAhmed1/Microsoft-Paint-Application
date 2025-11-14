package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Represents a square shape which can be added to the canvas.
 */
public class Square extends Rectangle{

    /**
     * Constructs a new square with the specified parameters
     * @param topLeft
     * @param sideLength
     */
    public Square(Point topLeft, double sideLength)
    {
        super(topLeft, sideLength, sideLength);
    }

    /**
     * Return the side length of the square.
     * @return
     */
    public double getSideLength()
    {
        return super.getWidth();
    }

    /**
     * Set the side lengths of the square.
     * @param width
     */
    @Override
    public void setWidth(double width)
    {
        super.setWidth(width);
        super.setHeight(width);
    }

    /**
     * Set the side lengths of the square.
     * @param height
     */
    @Override
    public void setHeight(double height)
    {
        super.setWidth(height);
        super.setHeight(height);
    }

    @Override
    public void draw(GraphicsContext g)
    {
        g.setStroke(getColor());
        g.setLineWidth(getThickness());

        if (filled) {
            g.setFill(getColor());
            g.fillRect(getTop_left().x, getTop_left().y, getSideLength(), getSideLength());
        }

        else {
            g.strokeRect(getTop_left().x, getTop_left().y, getSideLength(), getSideLength());
        }
        g.setFill(Color.WHITE);
        // Makes sure when the shape is moved after erased, it remains erased.
        erase(g);

    }

    @Override
    public boolean contains(Point p) {
        Point tl = getTop_left();
        double s = getSideLength();
        return p.x >= tl.x && p.x <= tl.x + s &&
                p.y >= tl.y && p.y <= tl.y + s;
    }

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
