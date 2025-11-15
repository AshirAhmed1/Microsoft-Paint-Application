package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * An abstract class representing a drawable shape in the paint application.
 * This class provides shared attributes such as color, thickness, fill style,
 * and erase-stroke management for all drawable shapes.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public abstract class AbstractShapeDrawable implements Drawable {

    protected Color color;
    protected double thickness;
    protected boolean filled = true;
    protected ArrayList<ArrayList<ErasePoint>> erasedStrokes = new ArrayList<>();
    protected ArrayList<ErasePoint> currentStroke = new ArrayList<>();

    /**
     * Constructs a new AbstractShapeDrawable with default style attributes.
     */
    public AbstractShapeDrawable() {}

    /**
     * Sets the color of this shape.
     *
     * @param color the new color to apply
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Returns the current color of this shape.
     *
     * @return the shape's color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the outline thickness of this shape.
     *
     * @param thickness the new stroke thickness
     */
    public void setThickness(double thickness) {
        this.thickness = thickness;
    }

    /**
     * Returns the outline thickness of the shape.
     *
     * @return the current thickness value
     */
    public double getThickness() {
        return thickness;
    }

    /**
     * Returns whether this shape is filled or outlined.
     *
     * @return true if filled; false otherwise
     */
    public boolean isFilled() {
        return filled;
    }

    /**
     * Sets whether the shape should be filled or outlined.
     *
     * @param filled true to fill the shape; false for outline only
     */
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    /**
     * Draws the shape using the provided GraphicsContext.
     *
     * @param gc the canvas rendering context
     */
    public abstract void draw(GraphicsContext gc);

    /**
     * Determines whether the given point lies within this shape.
     * Subclasses should override this with appropriate hit-detection logic.
     *
     * @param p the point to test
     * @return true if the point is inside the shape; false otherwise
     */
    public boolean contains(Point p) {
        return true;
    }

    /**
     * Translates (moves) the shape by the given x and y offsets.
     * Concrete shape classes should override this as needed.
     *
     * @param dx horizontal shift
     * @param dy vertical shift
     */
    public void translate(double dx, double dy) {}

    /**
     * Adds an erase point to the current stroke if the point lies inside the shape.
     *
     * @param p the erase point to include
     */
    public void addErasePoint(ErasePoint p) {
        if (contains(p)) {
            currentStroke.add(p);
        }
    }

    /**
     * Returns the list of erase points recorded in the current stroke.
     *
     * @return a list representing the active erase stroke
     */
    public ArrayList<ErasePoint> getErasePoints() {
        return currentStroke;
    }

    /**
     * Draws all erased stroke paths over the shape, visually removing
     * portions of it as done by the eraser tool.
     *
     * @param g the canvas rendering context
     */
    public void erase(GraphicsContext g) {
        g.setStroke(Color.WHITE);

        // Draw completed erase strokes
        for (ArrayList<ErasePoint> stroke : erasedStrokes) {
            for (int i = 0; i < stroke.size() - 1; i++) {
                ErasePoint p1 = stroke.get(i);
                ErasePoint p2 = stroke.get(i + 1);
                g.setLineWidth(p1.thickness);
                g.strokeLine(p1.x, p1.y, p2.x, p2.y);
            }
        }

        // Draw the in-progress erase stroke
        for (int i = 0; i < currentStroke.size() - 1; i++) {
            ErasePoint p1 = currentStroke.get(i);
            ErasePoint p2 = currentStroke.get(i + 1);
            g.setLineWidth(p1.thickness);
            g.strokeLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    /**
     * Finalizes the current erase stroke by committing it
     * to the list of completed erased paths.
     */
    public void addEraseStroke() {
        if (!currentStroke.isEmpty()) {
            erasedStrokes.add(new ArrayList<>(currentStroke));
            currentStroke.clear();
        }
    }
}
