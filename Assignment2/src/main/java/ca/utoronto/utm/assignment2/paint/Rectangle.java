package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

public class Rectangle extends AbstractShapeDrawable{
    private Point top_left;
    private double width;
    private double height;

    public Rectangle(Point top_left, double width, double height)
    {
        super();
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
