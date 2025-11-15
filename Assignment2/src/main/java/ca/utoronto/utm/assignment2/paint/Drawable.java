package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;

/**
 * An interface representing any drawable object in the paint application.
 * Shapes implementing this interface must support drawing, movement,
 * hit detection, thickness and color changes, and erasing operations.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public interface Drawable {
    void draw(GraphicsContext g);
    Color getColor();
    void setColor(Color color);
    void setThickness(double thickness);
    double getThickness();

    boolean contains(Point p);
    void translate(double dx, double dy);
    void addErasePoint(ErasePoint e);
    void addEraseStroke();
    ArrayList<ErasePoint> getErasePoints();
    void erase(GraphicsContext g);
    void setFilled(boolean filled);
}
