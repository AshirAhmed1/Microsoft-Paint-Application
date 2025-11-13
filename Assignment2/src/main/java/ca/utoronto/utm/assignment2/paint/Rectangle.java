package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

/**
 * Represent a rectangle shape which can be drawn on the canvas.
 */
public class Rectangle extends AbstractShapeDrawable{
    private Point top_left;
    private double width;
    private double height;

    /**
     * Construct a new rectangle with the given parameters.
     * @param top_left
     * @param width
     * @param height
     */
    public Rectangle(Point top_left, double width, double height)
    {
        super();
        this.top_left = top_left;
        this.width = width;
        this.height = height;
    }

    /**
     * return the top left coordinate of the rectangle
     * @return top left Point
     */
    public Point getTop_left()
    {
        return this.top_left;
    }

    /**
     * Set the top left point of the rectangle to specified point.
     * @param top_left
     */
    public void setTop_left(Point top_left)
    {
        this.top_left = top_left;
    }

    /**
     * Set the width of the current rectangle to specified width.
     * @param width
     */
    public void setWidth(double width)
    {
        this.width = width;
    }

    /**
     *  Set the height of the current rectangle to specified height.
     * @param height
     */
    public void setHeight(double height)
    {
        this.height = height;
    }

    /**
     * Return the width of the current rectangle.
     * @return width
     */
    public double getWidth()
    {
        return this.width;
    }

    /**
     * Return the height of the current rectangle.
     * @return height
     */
    public double getHeight()
    {
        return this.height;
    }

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
    }

    @Override
    public boolean contains(Point p) {
        return p.x >= top_left.x && p.x <= top_left.x + width &&
                p.y >= top_left.y && p.y <= top_left.y + height;
    }

    @Override
    public void translate(double dx, double dy) {
        top_left = new Point(top_left.x + dx, top_left.y + dy);
    }


}
