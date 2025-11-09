package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

public class Triangle extends AbstractShapeDrawable{
    private Point bottom_left;
    private double base;
    private double side1; //longer side for height
    private double side2;
    private double height;

    public Triangle(Point bottom_left, double base, double side1, double side2)
    {
        super();
        this.bottom_left = bottom_left;
        this.base = base;
        this.side1 = side1;
        this.side2 = side2;
        this.height = Math.sqrt(side1 * side1 - (base / 2) * (base / 2));
    }
    public Point getbottom_left()
    {
        return this.bottom_left;
    }
    public void setbottom_left(Point botton_left)
    {
        this.bottom_left = botton_left;
    }
    public void setbase(double base)
    {
        this.base = base;
    }
    public void setside1(double side1)
    {
        this.side1 = side1;
    }
    public void setside2(double side2)
    {
        this.side2 = side2;
    }
    public void setHeight(double height)
    {
        this.height = height;
    }
    public double getbase()
    {
        return this.base;
    }
    public double getside1()
    {
        return this.side1;
    }
    public double getside2()
    {
        return this.side2;
    }
    public double getHeight()
    {
        return this.height;
    }

    @Override
    public void draw(GraphicsContext g)
    {
        double [] xs = {bottom_left.x, bottom_left.x + base, bottom_left.x + base / 2.0 };
        double[] ys = { bottom_left.y, bottom_left.y, bottom_left.y - height };
        g.fillPolygon(xs, ys, 3);

    }

}

