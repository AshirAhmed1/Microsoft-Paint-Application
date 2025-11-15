package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Represents a circular shape that can be drawn on the canvas.
 * A Circle is defined by a centre point and a radius, and supports
 * features such as filling, outlining, translation, and erasing.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class Circle extends AbstractShapeDrawable {
    private Point centre;
    private double radius;

    /**
     * Constructs a new Circle with the given centre and radius.
     *
     * @param centre the centre point of the circle
     * @param radius the radius of the circle
     */
    public Circle(Point centre, int radius){
        super();
        this.centre = centre;
        this.radius = radius;
    }

    /**
     * Returns the centre point of this circle.
     *
     * @return the centre Point
     */
    public Point getCentre() {
        return centre;
    }

    /**
     * Returns the radius of this circle.
     *
     * @return the radius value
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Draws the circle onto the provided GraphicsContext. The circle
     * is drawn filled or outlined depending on the current fill setting.
     * Any erase strokes affecting this circle are also rendered.
     *
     * @param g the GraphicsContext used to draw the circle
     */
    @Override
    public void draw(GraphicsContext g)
    {
        double x = centre.x - radius;
        double y = centre.y - radius;

        g.setStroke(color);
        g.setLineWidth(thickness);

        if (filled) {
            g.setFill(color);
            g.fillOval(x, y, radius * 2, radius * 2);
        } else {
            g.strokeOval(x, y, radius * 2, radius * 2);
        }

        // Ensure erased portions remain erased after movement.
        g.setFill(Color.WHITE);
        erase(g);
    }

    /**
     * Determines whether a given point lies within this circle.
     *
     * @param p the point to test
     * @return true if the point lies inside or on the boundary of the circle
     */
    @Override
    public boolean contains(Point p) {
        double dx = p.x - centre.x;
        double dy = p.y - centre.y;
        return dx * dx + dy * dy <= radius * radius;
    }

    /**
     * Translates the circle by the specified horizontal and vertical amounts.
     * Any associated erase strokes are moved by the same offset.
     *
     * @param dx the horizontal displacement
     * @param dy the vertical displacement
     */
    @Override
    public void translate(double dx, double dy) {
        centre = new Point(centre.x + dx, centre.y + dy);

        for (ArrayList<ErasePoint> stroke: erasedStrokes) {
            for (ErasePoint p: stroke) {
                p.x += dx;
                p.y += dy;
            }
        }
    }
}
