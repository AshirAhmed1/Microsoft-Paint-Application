package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

public class Square extends Rectangle{


    public Square(Point topLeft, double sideLength)
    {
        super(topLeft, sideLength, sideLength);
    }

    public double getSideLength()
    {
        return super.getWidth();
    }

    public void setSideLength(double sideLength)
    {
        super.setHeight(sideLength);
        super.setWidth(sideLength);
    }

    @Override
    public void setWidth(double width)
    {
        super.setWidth(width);
        super.setHeight(width);
    }

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
    }


}
