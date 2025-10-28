package ca.utoronto.utm.assignment2.paint;

public class Oval {
    private Point topLeft;
    private double width;
    private double height;

    public Oval(Point topLeft, double width, double height){
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(Point corner) {
        this.topLeft = corner;
    }

    public double getWidth() { return width; }

    public double getHeight() { return height; }

    public void setWidth(double newWidth) { this.width = newWidth; }

    public void setHeight(double newHeight) { this.height = newHeight; }
}

