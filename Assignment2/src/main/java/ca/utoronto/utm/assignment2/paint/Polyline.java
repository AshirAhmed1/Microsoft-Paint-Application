package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Polyline extends AbstractShapeDrawable{

    private final ArrayList<Point> points = new ArrayList<>();

    public Polyline(){
        super();
    }
    /**
     * Add a new point to polyline segment.
     * @param p the point to add
     */
    public void addPoint(Point p) {points.add(p);}

    public ArrayList<Point> getPoints(){
        return points;
    }
    @Override
    public void draw(GraphicsContext g) {
        if (points.size() < 2) return;

        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            g.strokeLine(p1.x, p1.y, p2.x, p2.y);
        }
    }
}
