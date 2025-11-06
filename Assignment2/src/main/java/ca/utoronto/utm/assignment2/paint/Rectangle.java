package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

public class Rectangle implements Drawable {
    private Point top_left;
    private double width;
    private double height;

    public Rectangle(Point top_left, double width, double height)
    {
        this.top_left = top_left;
        this.width = width;
        this.height = height;
    }
    public Point getTop_left()
    {
        return this.top_left;
    }
    public void setTop_left(Point top_left)
    {
        this.top_left = top_left;
    }
    public void setWidth(double width)
    {
        this.width = width;
    }
    public void setHeight(double height)
    {
        this.height = height;
    }
    public double getWidth()
    {
        return this.width;
    }
    public double getHeight()
    {
        return this.height;
    }

    @Override
    public void draw(GraphicsContext g)
    {
        g.fillRect(top_left.x, top_left.y, width, height);
    }

}
