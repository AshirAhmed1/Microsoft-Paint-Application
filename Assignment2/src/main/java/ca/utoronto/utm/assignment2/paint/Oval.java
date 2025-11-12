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
        g.setStroke(color);
        g.setLineWidth(thickness);

        if (filled) {
            g.setFill(color);
            g.fillOval(topLeft.x, topLeft.y, width, height);
        }

        else {
            g.strokeOval(topLeft.x, topLeft.y, width, height);
        }
    }

    @Override
    public boolean contains(Point p) {
        double rx = width / 2.0;
        double ry = height / 2.0;
        double cx = topLeft.x + rx;
        double cy = topLeft.y + ry;
        double normalized = Math.pow((p.x - cx) / rx, 2) + Math.pow((p.y - cy) / ry, 2);
        return normalized <= 1.0;
    }

    @Override
    public void translate(double dx, double dy) {
        topLeft = new Point(topLeft.x + dx, topLeft.y + dy);
    }

}

