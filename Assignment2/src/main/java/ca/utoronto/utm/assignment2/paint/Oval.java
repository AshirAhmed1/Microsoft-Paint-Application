package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

public class Oval extends AbstractShapeDrawable {
    private Point topLeft;
    private double width;
    private double height;

    public Oval(Point topLeft, double width, double height){
        super();
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

    @Override
    public void draw(GraphicsContext g)
    {
        g.setFill(color);
        g.fillOval(topLeft.x, topLeft.y, width, height);

        g.setStroke(color);
        g.setLineWidth(thickness);
        g.strokeOval(topLeft.x, topLeft.y, width, height);
    }
}

