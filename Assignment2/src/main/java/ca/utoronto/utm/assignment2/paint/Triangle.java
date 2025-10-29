package ca.utoronto.utm.assignment2.paint;

public class Triangle {
    private Point bottom_left;
    private double width;
    private double height;

    public Triangle(Point bottom_left, double width, double height)
    {
        this.bottom_left = bottom_left;
        this.width = width;
        this.height = height;
    }
    public Point getbottom_left()
    {
        return this.bottom_left;
    }
    public void setbotton_left(Point botton_left)
    {
        this.bottom_left = botton_left;
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

}

