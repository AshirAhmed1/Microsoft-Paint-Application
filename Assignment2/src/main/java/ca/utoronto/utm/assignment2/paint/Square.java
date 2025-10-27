package ca.utoronto.utm.assignment2.paint;

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

}
