package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

/**
 * Represent a triangle shape which can be added to the canvas.
 */
public class Triangle extends AbstractShapeDrawable{
    private Point bottom_left;
    private double base;
    private double side1; //longer side for height
    private double side2;
    private double height;

    /**
     * Construct a new triangle shape with the specified parameters.
     * @param bottom_left
     * @param base
     * @param side1
     * @param side2
     */
    public Triangle(Point bottom_left, double base, double side1, double side2)
    {
        super();
        this.bottom_left = bottom_left;
        this.base = base;
        this.side1 = side1;
        this.side2 = side2;
        this.height = Math.sqrt(side1 * side1 - (base / 2) * (base / 2));
    }

    /**
     * Return the bottom left point
     * @return
     */
    public Point getbottom_left()
    {
        return this.bottom_left;
    }

    /**
     * Return the length of the base of the triangle.
     * @return base length
     */
    public double getbase()
    {
        return this.base;
    }

    /**
     * Return the length of side1 of the triangle.
     * @return side1 length
     */
    public double getside1()
    {
        return this.side1;
    }

    /**
     * Return the length of side2 of the triangle
     * @return side2 length
     */
    public double getside2()
    {
        return this.side2;
    }

    @Override
    public void draw(GraphicsContext g)
    {
        double [] xs = {bottom_left.x, bottom_left.x + base, bottom_left.x + base / 2.0 };
        double[] ys = { bottom_left.y, bottom_left.y, bottom_left.y - height };

        g.setStroke(color);
        g.setLineWidth(thickness);

        if (filled) {
            g.setFill(color);
            g.fillPolygon(xs, ys, 3);
        }

        else {
            g.strokePolygon(xs, ys, 3);
        }

    }

    @Override
    public boolean contains(Point p) {
        double minX = bottom_left.x;
        double maxX = bottom_left.x + base;
        double minY = bottom_left.y - height;
        double maxY = bottom_left.y;
        return p.x >= minX && p.x <= maxX && p.y >= minY && p.y <= maxY;
    }

    @Override
    public void translate(double dx, double dy) {
        bottom_left = new Point(bottom_left.x + dx, bottom_left.y + dy);
    }


}

