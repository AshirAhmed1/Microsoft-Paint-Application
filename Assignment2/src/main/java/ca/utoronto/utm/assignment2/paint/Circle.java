package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Represent a circle shape that can be drawn on the convas.
 */
public class Circle extends AbstractShapeDrawable {
        private Point centre;
        private double radius;

    /**
     * Constructs a new circle with the specified centre and radius.
     * @param centre
     * @param radius
     */
    public Circle(Point centre, int radius){
            super();
            this.centre = centre;
            this.radius = radius;
        }

    /**
     * Returns the centre point of the circle
     *
     * @return centre Point
     */
    public Point getCentre() {
                return centre;
        }

    /**
     * Return the radius of the circle.
     *
     * @return radius
     */
    public double getRadius() {
                return radius;
        }

    @Override
    public void draw(GraphicsContext g)
    {
        double x = centre.x - radius;
        double y = centre.y - radius;

        g.setStroke(color);
        g.setLineWidth(thickness);
        if (filled)
        {
            g.setFill(color);
            g.fillOval(x, y, radius * 2, radius * 2);
        }
        else
        {
            g.strokeOval(x, y, radius * 2, radius * 2);
        }
        g.setFill(Color.WHITE);
        // Makes sure when the shape is moved after erased, it remains erased.
        erase(g);

    }

    @Override
    public boolean contains(Point p) {
        double dx = p.x - centre.x;
        double dy = p.y - centre.y;
        return dx * dx + dy * dy <= radius * radius;
    }

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
