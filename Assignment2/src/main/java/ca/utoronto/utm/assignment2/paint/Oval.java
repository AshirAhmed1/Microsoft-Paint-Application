package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Represents an oval shape that can be drawn on the canvas.
 */

public class Oval extends AbstractShapeDrawable {
    private Point topLeft;
    private double width;
    private double height;

    /**
     * Constructs an oval with the given parameters.
     * @param topLeft
     * @param width
     * @param height
     */
    public Oval(Point topLeft, double width, double height){
        super();
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Return the top left point of the oval.
     *
     * @return top left Point
     */
    public Point getTopLeft() {
        return topLeft;
    }

    /**
     * Return the width of the oval.
     *
     * @return height
     */
    public double getWidth() { return width; }

    /**
     * return the height of the oval.
     *
     * @return height
     */
    public double getHeight() { return height; }

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

