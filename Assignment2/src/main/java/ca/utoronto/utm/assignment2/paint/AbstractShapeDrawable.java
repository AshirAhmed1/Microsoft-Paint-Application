package ca.utoronto.utm.assignment2.paint;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * An abstract class that represents a drawable shape which can be added to the canvas in
 * the paint application.
 */

public abstract class AbstractShapeDrawable implements Drawable {

    protected Color color;
    protected double thickness;
    protected boolean filled = true;

    /**
     * Construct a new AbstractShapeDrawable with initial default values
     */
    public AbstractShapeDrawable() {}

    /**
     * Set the color of the shape
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Returns the current color of the shape.
     *
     * @return color of the shape
     */
    public Color getColor() {return color;}

    /**
     * Sets the current thickness of the shapes outline
     *
     * @param thickness
     */
    public void setThickness(double thickness) {
        this.thickness = thickness;
    }

    /**
     * Returns the thickness of the shape
     *
     * @return the current thickness
     */
    public double getThickness() {return thickness;}

    /**
     * Returns whether the shape is filled.
     *
     * @return if the shape is filled
     */
    public boolean isFilled() {
        return filled;
    }

    /**
     *
     *
     * @param filled
     */
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    /**
     * Draws the specific shape onto the canvas.
     * @param gc which is the current canvas.
     */
    public abstract void draw(GraphicsContext gc);

    /**
     * returns if the current point clicked lies within the specified shape.
     * @param p
     * @return
     */
    public boolean contains(Point p){ return true;}

    /**
     * Moves the selected shape by the specified directions.
     * @param dx
     * @param dy
     */
    public void translate(double dx, double dy){}
}
