package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
    // Store all independent squiggle strokes
    private final ArrayList<Squiggle> squiggles = new ArrayList<>();
    private ArrayList<Circle> circles = new ArrayList<Circle>();
    private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
    private ArrayList<Square> squares = new ArrayList<Square>();
    private ArrayList<Oval> ovals = new ArrayList<Oval>();
    private ArrayList<Triangle> triangles = new ArrayList<>();

    /**
     * Add a completed squiggle to the model.
     *
     * @param s the squiggle to add
     */
    public void addSquiggle(Squiggle s) {
        this.squiggles.add(s);
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Return all squiggles currently stored in the model.
     */
    public ArrayList<Squiggle> getSquiggles() {
        return this.squiggles;
    }

    public void addCircle(Circle c) {
        this.circles.add(c);
        this.setChanged();
        this.notifyObservers();
    }

    public ArrayList<Circle> getCircles() {
        return circles;
    }

    public void addRectangle(Rectangle r) {
        this.rectangles.add(r);
        this.setChanged();
        this.notifyObservers();
    }

    public ArrayList<Rectangle> getRectangles() {
        return this.rectangles;
    }

    public void addSquare(Square s) {
        this.squares.add(s);
        this.setChanged();
        this.notifyObservers();
    }

    public ArrayList<Square> getSquares() {
        return this.squares;
    }

    public void addOval(ca.utoronto.utm.assignment2.paint.Oval o) {
        this.ovals.add(o);
        this.setChanged();
        this.notifyObservers();
    }

    public ArrayList<ca.utoronto.utm.assignment2.paint.Oval> getOvals() {
        return this.ovals;
    }

    public void addTriangle(ca.utoronto.utm.assignment2.paint.Triangle t) {
        this.triangles.add(t);
        this.setChanged();
        this.notifyObservers();
    }
    public ArrayList<ca.utoronto.utm.assignment2.paint.Triangle> getTriangles() {
        return this.triangles;
    }
}



