package ca.utoronto.utm.assignment2.paint;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class AbstractShapeDrawable implements Drawable {
    protected Color color;
    protected double thickness;
    protected boolean filled = true;

    public AbstractShapeDrawable() {}

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {return color;}

    public void setThickness(double thickness) {
        this.thickness = thickness;
    }

    public double getThickness() {return thickness;}

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public abstract void draw(GraphicsContext gc);
}
