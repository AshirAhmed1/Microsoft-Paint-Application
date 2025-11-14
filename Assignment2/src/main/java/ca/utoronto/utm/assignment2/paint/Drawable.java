package ca.utoronto.utm.assignment2.paint;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

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
